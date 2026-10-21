package com.uttec.academia.application;

import com.uttec.academia.domain.Cuatrimestre;
import com.uttec.academia.domain.Usuario;
import com.uttec.academia.infrastructure.persistence.CuatrimestreRepository;
import com.uttec.academia.infrastructure.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArcoService {

    private final UsuarioRepository usuarioRepository;
    private final CuatrimestreRepository cuatrimestreRepository;

    public ArcoService(UsuarioRepository usuarioRepository,
                       CuatrimestreRepository cuatrimestreRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuatrimestreRepository = cuatrimestreRepository;
    }

    // A - Acceso
    public Map<String, Object> acceso(Long id) {
        Usuario u = obtenerUsuario(id);

        Map<String, Object> datos = new HashMap<>();
        datos.put("id", u.getId());
        datos.put("nombre", u.getNombre());
        datos.put("email", u.getEmail());
        datos.put("googleSub", u.getGoogleSub());
        datos.put("activo", u.getActivo());
        datos.put("fechaRegistro", u.getFechaRegistro());

        if (u.getCuatrimestre() != null) {
            datos.put("cuatrimestreId", u.getCuatrimestre().getId());
            datos.put("cuatrimestreNumero", u.getCuatrimestre().getNumero());
        } else {
            datos.put("cuatrimestreId", null);
            datos.put("cuatrimestreNumero", null);
        }

        datos.put("derecho", "ACCESO");
        datos.put("mensaje", "Datos personales del titular");
        return datos;
    }

    // R - Rectificación
    public Map<String, Object> rectificacion(Long id, Map<String, Object> body) {
        Usuario u = obtenerUsuario(id);

        if (body.get("nombre") != null) {
            u.setNombre(body.get("nombre").toString());
        }
        if (body.get("email") != null) {
            u.setEmail(body.get("email").toString());
        }
        if (body.get("cuatrimestreId") != null) {
            Long cuatriId = Long.valueOf(body.get("cuatrimestreId").toString());
            Cuatrimestre c = cuatrimestreRepository.findById(cuatriId)
                    .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
            u.setCuatrimestre(c);
        }

        usuarioRepository.save(u);

        Map<String, Object> resp = acceso(id);
        resp.put("derecho", "RECTIFICACION");
        resp.put("mensaje", "Datos actualizados correctamente");
        return resp;
    }

    // C - Cancelación (baja lógica)
    public Map<String, Object> cancelacion(Long id) {
        Usuario u = obtenerUsuario(id);
        u.setActivo(false);
        usuarioRepository.save(u);

        Map<String, Object> resp = new HashMap<>();
        resp.put("id", u.getId());
        resp.put("activo", u.getActivo());
        resp.put("derecho", "CANCELACION");
        resp.put("mensaje", "Cuenta cancelada (baja lógica). Los datos ya no se usan activamente.");
        return resp;
    }

    // O - Oposición
    public Map<String, Object> oposicion(Long id) {
        Usuario u = obtenerUsuario(id);
        u.setActivo(false);
        usuarioRepository.save(u);

        Map<String, Object> resp = new HashMap<>();
        resp.put("id", u.getId());
        resp.put("activo", u.getActivo());
        resp.put("derecho", "OPOSICION");
        resp.put("mensaje", "El titular se opuso al tratamiento de sus datos. Cuenta desactivada.");
        return resp;
    }

    private Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}