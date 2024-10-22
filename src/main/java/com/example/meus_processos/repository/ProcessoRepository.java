package com.example.meus_processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meus_processos.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    boolean existsByNumero(String numero);
}