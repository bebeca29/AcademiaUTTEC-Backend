package com.uttec.academia.application;

import com.uttec.academia.domain.Materia;
import com.uttec.academia.infrastructure.persistence.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository repository;

    public MateriaService(MateriaRepository repository) {
        this.repository = repository;
    }

    public List<Materia> listar() {
        return repository.findAll();
    }

    public Materia obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    public Materia crear(Materia materia) {
        return repository.save(materia);
    }

    public Materia actualizar(Long id, Materia datos) {
        Materia existente = obtener(id);
        existente.setNombre(datos.getNombre());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Materia no encontrada");
        }
        repository.deleteById(id);
    }
}