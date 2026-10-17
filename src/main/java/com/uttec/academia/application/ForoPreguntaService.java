package com.uttec.academia.application;

import com.uttec.academia.domain.Etiqueta;
import com.uttec.academia.domain.ForoPregunta;
import com.uttec.academia.domain.Usuario;
import com.uttec.academia.infrastructure.persistence.EtiquetaRepository;
import com.uttec.academia.infrastructure.persistence.ForoPreguntaRepository;
import com.uttec.academia.infrastructure.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForoPreguntaService {

    private final ForoPreguntaRepository preguntaRepository;
    private final EtiquetaRepository etiquetaRepository;
    private final UsuarioRepository usuarioRepository;

    public ForoPreguntaService(ForoPreguntaRepository preguntaRepository,
                               EtiquetaRepository etiquetaRepository,
                               UsuarioRepository usuarioRepository) {
        this.preguntaRepository = preguntaRepository;
        this.etiquetaRepository = etiquetaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ForoPregunta> listar() {
        return preguntaRepository.findAll();
    }

    public ForoPregunta obtener(Long id) {
        return preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
    }

    public ForoPregunta crear(ForoPregunta pregunta) {
        Etiqueta etiqueta = etiquetaRepository.findById(pregunta.getEtiqueta().getId())
                .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada"));

        Usuario usuario = usuarioRepository.findById(pregunta.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pregunta.setEtiqueta(etiqueta);
        pregunta.setUsuario(usuario);

        if (pregunta.getFecha() == null) {
            pregunta.setFecha(LocalDateTime.now());
        }

        return preguntaRepository.save(pregunta);
    }

    public ForoPregunta actualizar(Long id, ForoPregunta datos) {
        ForoPregunta existente = obtener(id);

        existente.setTitulo(datos.getTitulo());
        existente.setContenido(datos.getContenido());

        if (datos.getEtiqueta() != null && datos.getEtiqueta().getId() != null) {
            Etiqueta etiqueta = etiquetaRepository.findById(datos.getEtiqueta().getId())
                    .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada"));
            existente.setEtiqueta(etiqueta);
        }

        if (datos.getUsuario() != null && datos.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(datos.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            existente.setUsuario(usuario);
        }

        return preguntaRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!preguntaRepository.existsById(id)) {
            throw new RuntimeException("Pregunta no encontrada");
        }
        preguntaRepository.deleteById(id);
    }
}