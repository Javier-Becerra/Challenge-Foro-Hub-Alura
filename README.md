# 📦Foro Hub

Foro Hub es un sistema de foro desarrollado con **Spring Boot**, **Spring Security**, **JWT**, **JPA/Hibernate** y **MySQL**. Permite a los usuarios registrarse, iniciar sesión, crear y listar tópicos de cursos de manera segura mediante tokens JWT.  

---

## 🛠 Tecnologías

- Java 25 
- Spring Boot 4  
- Spring Security + JWT  
- Spring Data JPA 
- MySQL
- Flyway / Hibernate
- Maven  
- Lombok  

---

## ⚙ Configuración de la base de datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS forohub;
USE forohub;
```
Verifica que la configuración de conexión sea la correcta en application.properties:
```
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:3306/forohub
spring.datasource.username=${DB_USER:root}
spring.datasource.password=${DB_PASSWORD:1234}
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Uso de la API

Abre el proyecto en IntelliJ (o cualquier IDE compatible con Maven).

Ejecuta la clase principal: ForoHubApplication.java.

Probar endpoints usando Postman, Insomnia o curl.


- Autenticación (Público)

Para interactuar con la API, primero debes obtener un token válido:
```
POST /loginJSON{
  "login": "admin",
  "password": "123456"
}
```
Respuesta: Un JSON con el string del token JWT que deberás usar en las siguientes peticiones como Bearer Token.

- Gestión de Tópicos

Todos los endpoints a continuación requieren el encabezado:  
`Authorization: Bearer <TOKEN_JWT>`

| Método | Endpoint | Acción | Cuerpo de la Petición (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/topicos` | Listar todos los tópicos | No requiere |
| **GET** | `/topicos/{id}` | Ver detalle de un tópico | No requiere |
| **POST** | `/topicos` | Crear un nuevo tópico | `{"titulo": "...", "mensaje": "...", "autor": "...", "curso": "..."}` |
| **PUT** | `/topicos/{id}` | Actualizar un tópico | `{"titulo": "...", "mensaje": "...", "autor": "...", "curso": "..."}` |
| **DELETE** | `/topicos/{id}` | Eliminar un tópico | No requiere |

## 🔒 Seguridad

Implementa BCrypt para el hashing de contraseñas.
Protección de rutas mediante filtros de Spring Security.
Validación de integridad de datos con Jakarta Validation.

## 👤 Usuarios de prueba

```
INSERT INTO usuarios (login, password) VALUES
('admin', '$2a$10$Dow1G8Fz8r0y1hJ0GhQnE..j5Zsq8ZG6J5e0Z8HCxVXx5GOS1pG2O');
-- Nota: La contraseña encriptada equivale a '123456'
```

## 📄 Licencia

Este proyecto fue desarrollado como parte de un desafío para Alura Latam y Oracle Next Education. Libre para uso con fines de aprendizaje.
Este proyecto es de uso educativo y libre para aprendizaje.
