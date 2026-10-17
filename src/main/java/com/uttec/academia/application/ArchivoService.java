package com.uttec.academia.application;

import com.uttec.academia.domain.*;
import com.uttec.academia.infrastructure.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final TipoArchivoRepository tipoArchivoRepository;
    private final OfertaAcademicaRepository ofertaRepository;
    private final UsuarioRepository usuarioRepository;

    public ArchivoService(ArchivoRepository archivoRepository,
                          TipoArchivoRepository tipoArchivoRepository,
                          OfertaAcademicaRepository ofertaRepository,
                          UsuarioRepository usuarioRepository) {
        this.archivoRepository = archivoRepository;
        this.tipoArchivoRepository = tipoArchivoRepository;
        this.ofertaRepository = ofertaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Archivo> listar() {
        return archivoRepository.findAll();
    }

    public Archivo obtener(Long id) {
        return archivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    public Archivo crear(Archivo archivo) {
        TipoArchivo tipo = tipoArchivoRepository.findById(archivo.getTipoArchivo().getId())
                .orElseThrow(() -> new RuntimeException("Tipo de archivo no encontrado"));

        OfertaAcademica oferta = ofertaRepository.findById(archivo.getOfertaAcademica().getId())
                .orElseThrow(() -> new RuntimeException("Oferta académica no encontrada"));

        Usuario usuario = usuarioRepository.findById(archivo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        archivo.setTipoArchivo(tipo);
        archivo.setOfertaAcademica(oferta);
        archivo.setUsuario(usuario);

        if (archivo.getFechaSubida() == null) {
            archivo.setFechaSubida(LocalDateTime.now());
        }
        if (archivo.getDescargas() == null) {
            archivo.setDescargas(0);
        }

        return archivoRepository.save(archivo);
    }

    public Archivo actualizar(Long id, Archivo datos) {
        Archivo existente = obtener(id);

        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setNombreArchivo(datos.getNombreArchivo());
        existente.setStoragePath(datos.getStoragePath());

        if (datos.getDescargas() != null) {
            existente.setDescargas(datos.getDescargas());
        }

        if (datos.getTipoArchivo() != null && datos.getTipoArchivo().getId() != null) {
            TipoArchivo tipo = tipoArchivoRepository.findById(datos.getTipoArchivo().getId())
                    .orElseThrow(() -> new RuntimeException("Tipo de archivo no encontrado"));
            existente.setTipoArchivo(tipo);
        }

        if (datos.getOfertaAcademica() != null && datos.getOfertaAcademica().getId() != null) {
            OfertaAcademica oferta = ofertaRepository.findById(datos.getOfertaAcademica().getId())
                    .orElseThrow(() -> new RuntimeException("Oferta académica no encontrada"));
            existente.setOfertaAcademica(oferta);
        }

        if (datos.getUsuario() != null && datos.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(datos.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            existente.setUsuario(usuario);
        }

        return archivoRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!archivoRepository.existsById(id)) {
            throw new RuntimeException("Archivo no encontrado");
        }
        archivoRepository.deleteById(id);
    }
}