# ☕ Príncipe de Colores - Backend API

API RESTful desarrollada en **Spring Boot** para gestionar el inventario, usuarios y transacciones de la tienda de jabones.

## 🚀 Tecnologías
* **Java 17 / 21**
* **Spring Boot 3** (Web, JPA, Data).
* **Base de Datos:** H2 Database (Modo Archivo / Persistente).
* **Herramientas:** Lombok, Maven/Gradle.

## ⚙️ Configuración

La base de datos está configurada para persistir los datos en un archivo local dentro de la carpeta del proyecto.

### application.properties
spring.datasource.url=jdbc:h2:file:./data/principdb
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

Método,Endpoint,Descripción
GET,/api/products,Obtener catálogo
POST,/api/auth/login,Login Administrador
POST,/api/clientes/login,Login Clientes
POST,/api/carrito/agregar,Agregar item (requiere ID cliente)
GET,/api/carrito,Ver carrito (requiere ID cliente)

▶️ Ejecución
Clonar el repositorio.

Abrir en IntelliJ IDEA o Eclipse.

Ejecutar la clase principal PrincipedecoloresApiApplication.java.

El servidor iniciará en http://localhost:8080.
