package com.uttec.academia.infrastructure.rest;

import com.uttec.academia.application.ForoPreguntaService;
import com.uttec.academia.domain.ForoPregunta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/foro/preguntas")
@CrossOrigin(origins = "*")
public class ForoPreguntaController {

    private final ForoPreguntaService service;

    public ForoPreguntaController(ForoPreguntaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ForoPregunta> listar() {
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
    public ResponseEntity<?> crear(@RequestBody ForoPregunta body) {
        try {
            return ResponseEntity.ok(service.crear(body));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ForoPregunta body) {
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
            return ResponseEntity.ok(Map.of("message", "Pregunta eliminada"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}