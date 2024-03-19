# ApiSytemBlog - SpringBoot

Este es una aplicación simple de Spring Boot en un container de Docker.

La api tiene como función en que el dueño de la aplicación(admin) pueda publicar blog de la temática que prefiera, los
demás roles de usuarios pueda ver sus publicaciones y comentarlas.

Este proyecto está asegurado con Spring Security y JWT(Json Web Token) y maneja excepciones personalizadas mediante
clases que extienden de Exception, RuntimeException y ResponseEntityException.

Los siguientes objetivos que tengo planteado es poder integrar el servicio Cloudinary para subir imágenes a la nube y
guardar su urlSecure en la base de datos, de este modo poder agregarles imágenes a la publicaciónes.

## Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu máquina:

- Docker: [Guía de Instalación](https://docs.docker.com/get-docker/)
- Java Development Kit (JDK) 8 o superior (17
  recomendado): [Descargar JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Maven: [Guía de Instalación](https://maven.apache.org/install.html)

## Cómo Ejecutar

1. Clona este repositorio en tu máquina local:

    ```bash
    git clone https://github.com/nicoramo2s/api-system-blog-springboot.git
    ```

2. Navega hasta el directorio del proyecto:

    ```bash
    cd api-system-blog-springboot
    ```

3. Construye la aplicación Spring Boot usando Maven:

    ```bash
    mvn clean install
    ```

4. Construye la imagen de Docker:

    ```bash
    docker build -t api-system-blog .
    ```

5. Ejecuta el contenedor Docker:

    ```bash
    docker run -p 8080:8080 apisystemblogcontainer
    ```

6. Accede a la aplicación en tu navegador web en [http://localhost:8080](http://localhost:8080).


7. Ingresa a la documentación del proyecto a través de la
   ruta [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Configuración

- Puedes configurar el puerto de la aplicación modificando el archivo `application.properties` ubicado
  en `src/main/resources`.

####

- Puedes agregar variables de entorno para agregar la conexión a la base de datos como:
    - DB_HOST: Host de la base de datos
    - DB_PORT: Puerto de la base de datos
    - DB_NAME: Nombre de la base de datos
    - DB_USERNAME: Usuario de la base de datos
    - DB_PASSWORD: Password de la cuenta
 
- Como recomendacion debes crear los roles en users_roles: ROLE_ADMIN, ROLE_USER. Ademas crear el usuario admin en la base de datos para mayor seguridad.
####

- Opciones de configuración adicionales se pueden encontrar en la documentación de Spring
  Boot: [Spring Boot Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config)

## Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes sugerencias, no dudes en contactarme para que
podamos implementarlo y asi seguir aprendiendo.

¡Desde ya Muchas Gracias!

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.

---

