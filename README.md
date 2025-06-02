# 📋 TODO App Microservicio

Microservicio en Java Spring Boot para la gestión de tareas (TODOs).  
Permite crear, actualizar, eliminar y listar tareas.  
Soporta comunicación entre microservicios y se encuentra listo para ser ejecutado en Docker.

---

## 🏗️ Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL (o H2 para tests)
- Docker

---

## 🚀 Endpoints API

| Método | URL                     | Descripción                         |
| ------ | ----------------------- | ----------------------------------- |
| GET    | `/tasks`                | Obtener todas las tareas            |
| GET    | `/tasks/{id}`           | Obtener tarea por ID                |
| POST   | `/tasks`                | Crear nueva tarea                   |
| PUT    | `/tasks/{id}`           | Actualizar tarea por ID             |
| DELETE | `/tasks/{id}`           | Eliminar tarea por ID               |
| DELETE | `/tasks/all`            | Eliminar todas las tareas           |
| PUT    | `/tasks/{id}/complete`  | Marcar tarea como completada        |
| PUT    | `/tasks/{id}/uncomplete`| Marcar tarea como no completada     |

---

## 📦 Estructura del proyecto
    todo-app/
    │
    ├── controller/
    ├── dto/
    ├── model/
    ├── repository/
    ├── service/
    ├── resources/
    │ ├── application.properties
    │ └── application-test.properties
    ├── Dockerfile
    ├── docker-compose.yml
    └── pom.xml

---

🐳 Construir imagen

- mvn clean package
- docker build -t todo-app 

---
- 📬 Colección Postman para pruebas
- Dentro de la carpeta raiz encontrarás una colección de Postman (todo-app.postman_collection.json) que puedes importar directamente en Postman para probar todos los endpoints del microservicio.




