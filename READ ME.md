# Academia UTTEC - API REST (Rebeca Ortega)

Proyecto Spring Boot con 2 controllers.

## Controllers
1. HealthController
   - GET /api/health
2. UsuarioController
   - POST /api/usuarios/registro
   - POST /api/usuarios/login

## Cómo ejecutar
1. Abrir el proyecto en NetBeans
2. Ejecutar AcademiaApplication.java
3. Abrir http://localhost:8080/api/health

## Pruebas
- Navegador: GET /api/health
- Postman: registro y login

# Academia UTTEC - Backend


## Estructura del proyecto

com.uttec.academia
 ├── domain                  → Entidades JPA
 ├── application             → Lógica de negocio / servicios
 ├── infrastructure
 │    ├── persistence       → Repositorios
 │    ├── rest              → Controllers
 │    └── security          → Seguridad
 └── config                 → Configuraciones