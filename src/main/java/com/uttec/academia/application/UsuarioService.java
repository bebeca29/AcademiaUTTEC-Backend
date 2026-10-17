package com.uttec.academia.application;

import com.uttec.academia.domain.Cuatrimestre;
import com.uttec.academia.domain.Usuario;
import com.uttec.academia.infrastructure.persistence.CuatrimestreRepository;
import com.uttec.academia.infrastructure.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CuatrimestreRepository cuatrimestreRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          CuatrimestreRepository cuatrimestreRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuatrimestreRepository = cuatrimestreRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtener(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuario.getCuatrimestre() != null && usuario.getCuatrimestre().getId() != null) {
            Cuatrimestre c = cuatrimestreRepository.findById(usuario.getCuatrimestre().getId())
                    .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
            usuario.setCuatrimestre(c);
        }

        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDateTime.now());
        }
        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        Usuario existente = obtener(id);

        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setGoogleSub(datos.getGoogleSub());
        existente.setActivo(datos.getActivo() != null ? datos.getActivo() : existente.getActivo());

        if (datos.getCuatrimestre() != null && datos.getCuatrimestre().getId() != null) {
            Cuatrimestre c = cuatrimestreRepository.findById(datos.getCuatrimestre().getId())
                    .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
            existente.setCuatrimestre(c);
        }

        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}