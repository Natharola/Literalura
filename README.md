# 📚 LiterAlura

Literalura es una aplicación Java basada en Spring Boot que permite buscar, registrar y listar libros y autores usando datos obtenidos desde la API de [Gutendex](https://gutendex.com/).

## 🚀 Funcionalidades principales

- Buscar libros por título utilizando la API de Gutendex.
- Registrar libros en una base de datos local.
- Evitar registros duplicados de libros o autores.
- Listar todos los libros registrados.
- Listar todos los autores registrados.
- Filtrar libros por idioma.
- Listar autores vivos en un año determinado.

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3.3
- Spring Data JPA
- Hibernate
- PostgreSQL (u otra base de datos compatible)
- Maven
- Gutendex API

## 🧾 Estructura del proyecto

com.aluracursos.literalura
├── controller
├── model
├── repository
├── service
├── principal
└── LiteraluraApplication.java



## ⚙️ Configuración del proyecto

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/literalura.git


Configura tu archivo application.properties con los datos de tu base de datos:
properties

Copiar esoss datos y colocar usuario y contraseña correspondientes de su Postgre 
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Ejecuta la aplicación desde tu IDE o por consola:


La aplicación se ejecuta en consola (modo texto). Sigue las instrucciones del menú para interactuar.

📦 Endpoints (si agregas API REST en el futuro)
Actualmente la aplicación es de consola, pero puedes extenderla fácilmente con controladores REST si lo deseas.


🧠 Créditos
Proyecto realizado como parte del curso Java + Spring JPA de Alura Latam.
