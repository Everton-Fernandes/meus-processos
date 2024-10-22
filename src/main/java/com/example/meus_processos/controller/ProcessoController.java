package com.example.meus_processos.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.meus_processos.model.Processo;
import com.example.meus_processos.service.ProcessoService;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {
    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<String> salvarProcesso(@RequestBody Processo processo) {
        return processoService.salvarProcesso(processo);
    }

    @GetMapping
    public List<Processo> listarProcessos() {
        return processoService.listarProcessos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProcesso(@PathVariable Long id) {
        return processoService.deletarProcesso(id);
    }

    @PostMapping("/{id}/reus")
    public ResponseEntity<String> adicionarReu(@PathVariable Long id, @RequestParam String reu) {
        return processoService.adicionarReu(id, reu);
    }
}
