# Servicio de Eliminación de Estudiantes

Microservicio Spring Boot para eliminar estudiantes existentes.

## Estructura del Proyecto

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/studentsdelete/
│   │   │       ├── controller/    # Controladores REST
│   │   │       ├── model/         # Entidades
│   │   │       ├── repository/    # Repositorios JPA
│   │   │       ├── service/       # Lógica de negocio
│   │   │       └── StudentDeleteApplication.java
│   │   └── resources/
│   │       └── application.yml    # Configuración
│   └── test/                      # Pruebas unitarias
├── k8s/                          # Manifiestos de Kubernetes
│   ├── deployment.yaml
│   └── service.yaml
├── Dockerfile                    # Configuración de Docker
├── docker-compose.yml           # Configuración de Docker Compose
├── pom.xml                      # Dependencias Maven
└── README.md
```

## Endpoint

- **DELETE** `/api/students/{id}`
- Puerto: 8085
- Content-Type: application/json

### Path Parameters
- `id`: ID del estudiante a eliminar (Long)

### Response

- **200 OK**: Si el estudiante fue eliminado exitosamente
- **404 Not Found**: Si el estudiante no existe
- **500 Internal Server Error**: Error del servidor

## Configuración

### Application Properties
```yaml
server:
  port: 8085

spring:
  application:
    name: students-delete-service
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:mariadb://localhost:3306/studentsdb}
    username: root
    password: root
```

### Docker
```bash
# Construir imagen
docker build -t students-delete-service .

# Ejecutar contenedor
docker run -p 8085:8085 students-delete-service
```

### Docker Compose
```bash
docker compose up --build
```

### Kubernetes
```bash
kubectl apply -f k8s/
```

## Dependencias Principales

- Spring Boot 3.1.5
- Spring Data JPA
- MariaDB Driver
- Spring Web
- Spring Boot Test

## Desarrollo

### Requisitos
- Java 17
- Maven
- Docker (opcional)
- Kubernetes (opcional)

### Compilar
```bash
./mvnw clean package
```

### Ejecutar Tests
```bash
./mvnw test
```

### Ejecutar Localmente
```bash
./mvnw spring-boot:run
```

## Ejemplo de Uso

### Eliminar un Estudiante
```bash
curl -X DELETE http://localhost:8085/api/students/1
```

## Características

- Eliminación física de registros
- Validación de existencia previa
- Operación idempotente (múltiples llamadas producen el mismo resultado)
- Transaccionalidad garantizada

## Monitoreo y Logs

- Los logs de la aplicación se encuentran en la salida estándar
- Se utiliza el nivel de log INFO por defecto
- Se registran todas las operaciones de eliminación
- Se registran los intentos de eliminación de registros no existentes

## Seguridad

- Validación de ID antes de eliminar
- Manejo seguro de excepciones
- No expone información sensible en las respuestas
- Operación atómica garantizada

## Consideraciones

- La eliminación es permanente (no hay papelera de reciclaje)
- No se implementa borrado en cascada
- Se recomienda implementar soft delete en futuras versiones
- Se sugiere agregar autenticación/autorización 