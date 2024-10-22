package com.example.meus_processos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.meus_processos.model.Processo;
import com.example.meus_processos.repository.ProcessoRepository;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    public ResponseEntity<String> salvarProcesso(Processo processo) {
        if (processoRepository.existsByNumero(processo.getNumero())) {
            return ResponseEntity.badRequest().body("Processo já cadastrado!");
        }
        processoRepository.save(processo);
        return ResponseEntity.ok("Processo salvo com sucesso!");
    }

    public List<Processo> listarProcessos() {
        return processoRepository.findAll();
    }

    public ResponseEntity<String> deletarProcesso(Long id) {
        processoRepository.deleteById(id);
        return ResponseEntity.ok("Processo deletado com sucesso!");
    }

    public ResponseEntity<String> adicionarReu(Long processoId, String reu) {
        // Implementação para adicionar Réu
        return ResponseEntity.ok("Réu adicionado com sucesso!");
    }
}
