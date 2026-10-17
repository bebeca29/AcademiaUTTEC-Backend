package com.uttec.academia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "materia_profesor_cuatrimestre")
public class OfertaAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public OfertaAcademica() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}