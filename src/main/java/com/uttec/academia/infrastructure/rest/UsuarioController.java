package com.uttec.academia.infrastructure.rest;

import com.uttec.academia.application.UsuarioService;
import com.uttec.academia.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            return ResponseEntity.ok(Map.of(
                    "message", "Usuario registrado correctamente",
                    "id", nuevo.getId(),
                    "nombre", nuevo.getNombre(),
                    "email", nuevo.getEmail()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String password = body.get("password");

            Usuario usuario = usuarioService.login(email, password);

            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "id", usuario.getId(),
                    "nombre", usuario.getNombre(),
                    "email", usuario.getEmail(),
                    "cuatrimestre", usuario.getCuatrimestre() != null ? usuario.getCuatrimestre() : 0
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}