package com.uttec.academia.application;

import com.uttec.academia.domain.Cuatrimestre;
import com.uttec.academia.infrastructure.persistence.CuatrimestreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuatrimestreService {

    private final CuatrimestreRepository repository;

    public CuatrimestreService(CuatrimestreRepository repository) {
        this.repository = repository;
    }

    public List<Cuatrimestre> listar() {
        return repository.findAll();
    }

    public Cuatrimestre obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
    }

    public Cuatrimestre crear(Cuatrimestre c) {
        return repository.save(c);
    }

    public Cuatrimestre actualizar(Long id, Cuatrimestre datos) {
        Cuatrimestre c = obtener(id);
        c.setNumero(datos.getNumero());
        return repository.save(c);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cuatrimestre no encontrado");
        }
        repository.deleteById(id);
    }
}