# 📦Foro Hub

Foro Hub es un sistema de foro desarrollado con **Spring Boot**, **Spring Security**, **JWT**, **JPA/Hibernate** y **MySQL**. Permite a los usuarios registrarse, iniciar sesión, crear y listar tópicos de cursos de manera segura mediante tokens JWT.  

---

## 🛠 Tecnologías

- Java 25 
- Spring Boot 4  
- Spring Security + JWT  
- Spring Data JPA / Hibernate  
- MySQL  
- Maven  
- Lombok  

---

## ⚙ Configuración de la base de datos

Crear la base de datos y las tablas ejecutando este script SQL en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS forohub;
USE forohub;

CREATE TABLE usuarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE topicos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  autor VARCHAR(50) NOT NULL,
  curso VARCHAR(100) NOT NULL,
  status ENUM('NO_RESPONDIDO','RESPONDIDO','CERRADO') NOT NULL DEFAULT 'NO_RESPONDIDO'
);

INSERT INTO usuarios (login, password) VALUES
('admin', '$2a$10$Dow1G8Fz8r0y1hJ0GhQnE..j5Zsq8ZG6J5e0Z8HCxVXx5GOS1pG2O'),
('maria', '$2a$10$Dow1G8Fz8r0y1hJ0GhQnE..j5Zsq8ZG6J5e0Z8HCxVXx5GOS1pG2O'),
('juan',  '$2a$10$Dow1G8Fz8r0y1hJ0GhQnE..j5Zsq8ZG6J5e0Z8HCxVXx5GOS1pG2O');

INSERT INTO topicos (titulo, mensaje, fecha_creacion, autor, curso, status) VALUES
('Primer tema', 'Este es un mensaje de prueba', NOW(), 'admin', 'Java Basico', 'NO_RESPONDIDO'),
('Segundo tema', 'Otro mensaje de prueba', NOW(), 'maria', 'Spring Boot', 'NO_RESPONDIDO');

Configura tu archivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

**Cambia username y password según tus credenciales de MySQL.**
```

## 🚀 Ejecución del proyecto

Abre el proyecto en IntelliJ (o cualquier IDE compatible con Maven).

Ejecuta la clase principal: ForoHubApplication.java.

Probar endpoints usando Postman, Insomnia o curl.

```
🔹 Login
POST /login
Content-Type: application/json

{
  "login": "admin",
  "password": "123456"
}

Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
🔹 Crear tópico
POST /topicos
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "titulo": "Nuevo tema",
  "mensaje": "Contenido del tema",
  "autor": "admin",
  "curso": "Spring Boot"
}
🔹 Listar tópicos
GET /topicos
Authorization: Bearer <TOKEN>
🔹 Eliminar tópico
DELETE /topicos/{id}
Authorization: Bearer <TOKEN>
```

## 🔒 Seguridad

Autenticación mediante JWT.
Contraseñas encriptadas con BCrypt.
Endpoints protegidos excepto /login.

## 👤 Usuarios de prueba

Usuario	Contraseña
admin	123456
maria	123456
juan	123456

## 📌 Notas

JWT expira por defecto en 2 horas.
Hibernate genera automáticamente los IDs de los tópicos y usuarios.
Ajusta application.properties según tu entorno local.

## 📄 Licencia

Este proyecto es de uso educativo y libre para aprendizaje.
