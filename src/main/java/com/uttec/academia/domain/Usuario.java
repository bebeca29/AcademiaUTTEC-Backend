package com.uttec.academia.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se vuelve opcional (nullable = true) porque un usuario con registro manual no tendrá googleSub de inicio
    @Column(name = "google_sub", unique = true)
    private String googleSub;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Se vuelve opcional (nullable = true) porque un usuario registrado con Google no tendrá contraseña
    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "cuatrimestre_id")
    private Cuatrimestre cuatrimestre;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean activo = true;

    public Usuario() {}

    // Getters y Setters agregados para password
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGoogleSub() { return googleSub; }
    public void setGoogleSub(String googleSub) { this.googleSub = googleSub; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Cuatrimestre getCuatrimestre() { return cuatrimestre; }
    public void setCuatrimestre(Cuatrimestre cuatrimestre) { this.cuatrimestre = cuatrimestre; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}