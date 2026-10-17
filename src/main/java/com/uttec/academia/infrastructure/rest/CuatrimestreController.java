package com.uttec.academia.infrastructure.rest;

import com.uttec.academia.application.CuatrimestreService;
import com.uttec.academia.domain.Cuatrimestre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuatrimestres")
@CrossOrigin(origins = "*")
public class CuatrimestreController {

    private final CuatrimestreService service;

    public CuatrimestreController(CuatrimestreService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cuatrimestre> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtener(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cuatrimestre body) {
        try {
            return ResponseEntity.ok(service.crear(body));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cuatrimestre body) {
        try {
            return ResponseEntity.ok(service.actualizar(id, body));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok(Map.of("message", "Eliminado"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}