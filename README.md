# Proyecto Nequi - Backend Reactivo

Este es un proyecto backend desarrollado con **Java 17**, **Spring WebFlux** y **MongoDB Atlas**. Implementa un enfoque de **programación reactiva**, arquitectura limpia y está preparado para despliegue en contenedores Docker y servicios en la nube como Render.

---

## Descripción

Este servicio está diseñado para gestionar funcionalidades de backend con una arquitectura escalable y mantenible. Está construido con un enfoque **reactivo** utilizando **Spring WebFlux**, lo que lo hace eficiente para aplicaciones con alta concurrencia.

---

## Tecnologías y herramientas

- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.x con Spring WebFlux
- **Gestor de dependencias:** Gradle 7.6
- **Base de Datos:** MongoDB (Mongo Atlas - en la nube)
- **Contenedores:** Docker
- **Despliegue:** Render y Docker Hub
- **Infraestructura como Código (IaC):** Terraform
- **Arquitectura:** Arquitectura Limpia (Clean Architecture)

---

## Estructura del Proyecto

El proyecto sigue los principios de la **Arquitectura Limpia**, organizando el código en capas independientes:

- `domain/` – Modelos de dominio y lógica de negocio pura
- `application/` – Casos de uso
- `infrastructure/` – Adaptadores como persistencia, servicios externos
- `presentation/entry-points/` – Controladores expuestos (WebFlux)

---

## Ejecutar en ambiente local como proyecto

1. Clonar el repositorio desde GitHub.

```bash
git clone https://github.com/RvdyOn3/Nequi.git
```

2. Establecer la variable de entorno para MongoDB:
Puedes establecerlo de la siguiente manera o desde tu IDE en configuración del arranque del proyecto.
```bash
export SPRING_DATA_MONGODB_URI=mongodb+srv://developer:Nequi2025.@nequi.gya2kfx.mongodb.net/?retryWrites=true&w=majority&appName=nequi
```

3. Construir el proyecto con Gradle.
```bash
./gradlew clean build
```

4. Ejecutar la aplicación.
```bash
./gradlew bootRun
```
La aplicación estará disponible en http://localhost:8080/

---

## Ejecutar con Docker
1. Construir la imagen docker.
```bash
docker build -t nequi .
```

2. Ejecutar el contenedor.
```bash
docker run -e SPRING_DATA_MONGODB_URI='mongodb+srv://developer:Nequi2025.@nequi.gya2kfx.mongodb.net/?retryWrites=true&w=majority&appName=nequi' -p 8080:8080 nequi
```

---
## Implementar MongoDB con IAC
1. Ubícate en la carpeta iac.
2. Ejecutar los siguientes comandos:

```bash
terraform init
terraform plan
terraform apply
```

3. Con el comando "apply" te pedirá que confirmes la ejecución.

Nota: Al ejecutar terraform ocurrirá un error ya que la BD y el cluster ya existen porque son referenciados con las llaves publicas, privadas y organización configurado en "terraform.tfvars". Puedes actualizar las credenciales para poder desplegar la BD.

---

## Despliegue

1. El proyecto se encuentra en el repositorio de GiHub: https://github.com/RvdyOn3/Nequi
2. La contenedor listo para su uso se encuentra en Docker Hub: https://hub.docker.com/r/rvasquezi/nequi
3. El proyecto se encuentra desplegado y listo para usar en Render: https://nequi-jrby.onrender.com