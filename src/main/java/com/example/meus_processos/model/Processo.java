package com.example.meus_processos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Processo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    @OneToMany
    private List<Reu> reus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Reu> getReus() {
        return reus;
    }

    public void setReus(List<Reu> reus) {
        this.reus = reus;
    }

    public Processo(Long id, String numero, List<Reu> reus) {
        this.id = id;
        this.numero = numero;
        this.reus = reus;
    }

    public Processo() {
    }
}
