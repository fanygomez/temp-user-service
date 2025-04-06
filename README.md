# User API Project

## Overview


## Features

- **Registro de Usuarios:**  
  Permite registrar usuarios con nombre, correo, contraseña y múltiples teléfonos.
- **Validaciones y Reglas de Negocio:**
  - Validación de formato de correo y contraseña (expresiones regulares configurables).
  - Verificación de unicidad en el correo y en los números de teléfono asociados a cada usuario.
- **Seguridad:**
  - Encriptación de contraseña utilizando BCrypt.
  - Generación de token de acceso mediante JWT.
  - Integración con Spring Security.
- **Persistencia:**
  - Uso de Spring Data JPA con H2 en memoria para desarrollo.
  - Esquema de base de datos
- **Documentación:**
  - API con Swagger/OpenAPI.
- **DevOps:**
  - Dockerfile

## Technologies
- **Lenguaje y Frameworks:**
    - Java 21
    - Spring Boot 3.4.4
- **Persistencia y Base de Datos:**
    - Spring Data JPA
    - H2 (en desarrollo, configurable para otros entornos)
- **Seguridad:**
    - Spring Security
    - JWT (usando JJWT)
- **Documentación:**
    - Springdoc OpenAPI/Swagger UI
- **Contenerización y Orquestación:**
    - Docker
    - Kubernetes
- **Build y CI/CD:**
    - Maven
## Prerequisites
- JDK 21
- Maven 3.x
- Git
- Docker (opcional para despliegue)

## Setup Test

### 1. Clone the Repository
```bash
git clone https://github.com/fanygomez/user-service.git
cd user-service
```
## 2. Build
```
mvn clean install
```

## 2.1 Run 
To start project, run:
```
mvn spring-boot:run
```
API disponible en [http://localhost:8080](http://localhost:8080).
## 2.3 Swagger Documentacion
http://localhost:8080/swagger-ui.html
## 2.4 Acceder a la consola H2
http://localhost:8080/h2-console

Utiliza las siguientes credenciales (por defecto):

    JDBC URL: jdbc:h2:mem:registrationdb
    Username: usr_bci
    Password: test123346

## Set up con Docker compose
docker-compose up --build -d

```
docker-compose up --build -d
```
API disponible en [http://localhost:8080](http://localhost:8080)