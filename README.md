# Academia UTTEC - API REST (Rebeca Ortega)

Proyecto Spring Boot con 2 controllers.

## Controllers

1. HealthController

   * GET /api/health
2. UsuarioController

   * POST /api/usuarios/registro
   * POST /api/usuarios/login

## Cómo ejecutar

1. Abrir el proyecto en NetBeans
2. Ejecutar AcademiaApplication.java
3. Abrir http://localhost:8080/api/health

## Pruebas

* Navegador: GET /api/health
* Postman: registro y login

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



\## Derechos ARCO (Protección de datos personales)



Este proyecto contempla los derechos ARCO establecidos en la legislación mexicana

de protección de datos personales (LFPDPPP), aplicados a la información de los

estudiantes registrados en la plataforma Academia UTTEC.



\### ¿Qué son los derechos ARCO?



| Derecho | Significado | Cómo se aplica en el sistema |

|---------|-------------|------------------------------|

| \*\*A\*\*cceso | Conocer qué datos personales se almacenan | `GET /api/usuarios/{id}` — el usuario consulta su perfil |

| \*\*R\*\*ectificación | Corregir datos inexactos o incompletos | `PUT /api/usuarios/{id}` — el usuario actualiza nombre, email, cuatrimestre, etc. |

| \*\*C\*\*ancelación | Solicitar la eliminación de sus datos | `DELETE /api/usuarios/{id}` o baja lógica con `activo = false` |

| \*\*O\*\*posición | Oponerse al tratamiento de sus datos para fines específicos | Campo de preferencias / estado `activo` para limitar el uso de su cuenta |



\### Datos personales tratados



En la tabla `usuarios` se almacenan:



\- Nombre

\- Correo electrónico

\- Identificador de Google (`google\_sub`)

\- Cuatrimestre

\- Fecha de registro

\- Estado activo/inactivo



\### Endpoints relacionados



```text

GET    /api/usuarios/{id}     → Acceso

PUT    /api/usuarios/{id}     → Rectificación

DELETE /api/usuarios/{id}     → Cancelación

PUT    /api/usuarios/{id}     → Oposición (activo: false)

