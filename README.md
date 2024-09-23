# Aplicación para Registro y gestion de Usuarios con Spring Boot

## Descripción

Esta aplicación es un sistema de registro y autenticación de usuarios desarrollado con Spring Boot 3.0. Utiliza Spring Security para la gestión de la seguridad y JSON Web Tokens (JWT) para la autenticación. La aplicación proporciona endpoints para el registro de usuarios, inicio de sesión, y gestión de usuario.

## Características

- Registro de usuarios
- Autenticación mediante JWT
- Gestión de usuario
- Validación de datos de entrada
- Documentación de API con Swagger

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.0
- Spring Security
- JSON Web Token (JWT)
- Spring Data JPA
- H2 Database (para desarrollo)
- Swagger (SpringDoc OpenAPI) para documentación de API
- Maven

## Configuración del Proyecto

### Prerrequisitos

- JDK 17
- Maven 3.6+

### Pasos de Configuración

1. Clonar el repositorio:
   ```
   git clone https://github.com/serosc95/apiUsers.git
   ```

2. Navegar al directorio del proyecto:
   ```
   cd apiUsers
   ```

3. Compilar el proyecto:
   ```
   mvn clean install
   ```

4. Ejecutar la aplicación:
   ```
   mvn spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`.

## Iniciar sesion
- Credenciales de usuario default para el login
`{
    "email": "admin@gmail.com",
    "password" : "Admin123"
}`

## Estructura del Proyecto

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── nisumexercise
│   │           └── apiUsers
│   │               ├── config
│   │               │   └── security
│   │               ├── controller
│   │               ├── dto
│   │               │   └── response
│   │               ├── entity
│   │               ├── exception
│   │               ├── repository
│   │               ├── service
│   │               │   └── impl
│   │               └── ApiUsersApplication.java
│   └── resources
│       └── application.properties
└── test
```

## Endpoints de la API

### Registro de Usuario
- **POST** `/api/user`
  - Crea un nuevo usuario (requiere autenticación)

### Autenticación
- **POST** `/api/login`
  - Inicia sesión y devuelve un token JWT
- **POST** `/api/refresh`
  - Genera un nuevo token de acceso usando un refresh token válido (requiere autenticación)

### Gestión de Usuario
- **GET** `/api/user`
  - Obtiene todos los usuarios (requiere autenticación)
- **PUT** `/api/user/{id}`
  - Actualiza la información de un usuario (requiere autenticación)

Para más detalles sobre los endpoints y sus parámetros, consulta la documentación de Swagger en `http://localhost:8080/swagger-ui.html` cuando la aplicación esté en ejecución.

## Seguridad

La aplicación utiliza Spring Security para manejar la autenticación y autorización. Los tokens JWT se utilizan para mantener el estado de la sesión del usuario. Asegúrate de proteger adecuadamente la clave secreta utilizada para firmar los tokens JWT.

## Pruebas

Para ejecutar las pruebas unitarias e integración:

```
mvn test
```

## Contacto

Oscar De leon - [@Linkedin](https://www.linkedin.com/in/oscardleon/) - oscardleon95@gmail.com
