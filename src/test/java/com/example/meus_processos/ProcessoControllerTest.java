package com.example.meus_processos;

import com.example.meus_processos.model.Processo;
import com.example.meus_processos.repository.ProcessoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        processoRepository.deleteAll(); // Limpa o banco de dados antes de cada teste
    }

    @Test
    public void deveSalvarProcessoComSucesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumero("12345-2024");

        mockMvc.perform(post("/api/processos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isOk());
    }

    @Test
    public void naoDeveSalvarProcessoDuplicado() throws Exception {
        Processo processo = new Processo();
        processo.setNumero("12345-2024");

        // Salvar o primeiro processo
        mockMvc.perform(post("/api/processos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isOk());

        // Tentar salvar o mesmo processo novamente
        mockMvc.perform(post("/api/processos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveListarProcessosComSucesso() throws Exception {
        Processo processo1 = new Processo();
        processo1.setNumero("12345-2024");

        Processo processo2 = new Processo();
        processo2.setNumero("67890-2024");

        // Salvar processos
        processoRepository.save(processo1);
        processoRepository.save(processo2);

        // Verificar a listagem
        mockMvc.perform(get("/api/processos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].numero").value("12345-2024"))
                .andExpect(jsonPath("$[1].numero").value("67890-2024"));
    }

    @Test
    public void deveDeletarProcessoComSucesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumero("12345-2024");

        processoRepository.save(processo);

        // Deletar o processo
        mockMvc.perform(delete("/api/processos/" + processo.getId()))
                .andExpect(status().isOk());

        // Verificar se foi excluído
        mockMvc.perform(get("/api/processos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void deveAdicionarReuAoProcesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumero("12345-2024");

        processoRepository.save(processo);

        // Adicionar um réu ao processo
        mockMvc.perform(post("/api/processos/" + processo.getId() + "/reus")
                .param("reu", "João da Silva"))
                .andExpect(status().isOk());
    }

}