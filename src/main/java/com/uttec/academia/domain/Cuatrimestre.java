package com.uttec.academia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cuatrimestres")
public class Cuatrimestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Short numero;

    public Cuatrimestre() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Short getNumero() { return numero; }
    public void setNumero(Short numero) { this.numero = numero; }
}