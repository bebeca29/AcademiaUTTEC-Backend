package com.uttec.academia.infrastructure.rest;

import com.uttec.academia.application.ArcoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/arco")
@CrossOrigin(origins = "*")
public class ArcoController {

    private final ArcoService arcoService;

    public ArcoController(ArcoService arcoService) {
        this.arcoService = arcoService;
    }

    // A - Acceso
    @GetMapping("/{id}")
    public ResponseEntity<?> acceso(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(arcoService.acceso(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // R - Rectificación
    @PutMapping("/{id}")
    public ResponseEntity<?> rectificacion(@PathVariable Long id,
                                           @RequestBody Map<String, Object> body) {
        try {
            return ResponseEntity.ok(arcoService.rectificacion(id, body));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // C - Cancelación
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelacion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(arcoService.cancelacion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // O - Oposición
    @PutMapping("/{id}/oposicion")
    public ResponseEntity<?> oposicion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(arcoService.oposicion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}