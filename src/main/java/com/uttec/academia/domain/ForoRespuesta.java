package com.uttec.academia.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "foro_respuestas")
public class ForoRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private ForoPregunta pregunta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public ForoRespuesta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public ForoPregunta getPregunta() { return pregunta; }
    public void setPregunta(ForoPregunta pregunta) { this.pregunta = pregunta; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}