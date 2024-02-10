# TALLER 3: MICROFRAMEWORKS WEB

## Descripción de la aplicación 📖

Este laboratorio explora la arquitectura del microframework WEB denominado sparkweb [sparkjava](https://sparkjava.com/). Este micro framework permite construir aplicaciones web de manera simple usando funciones lambda, se contruye un servidor web que permite una funcionalidad similar a la de sparkjava.

También permite el acceso a un juego de memoria junto con un creador de batidos de frutas, el servidor web es capaz de leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes.

Adiconal a esto permite realizar consultas a un API externo de [omdbapi](https://www.omdbapi.com/) para obtener información de películas. La implementación es eficiente en cuanto a recursos así que implementa un Caché que permite evitar hacer consultas repetidas al API externo.

Disfruta creando diferentes batidos de frutas y jugando a encontrar las parejas de cartas en el juego de memoria.

## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven y Java 17 para poder ejecutar el proyecto.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/AREP-LAB-03.git
cd AREP-LAB-03/

```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean compile exec:java '-Dexec.mainClass=edu.eci.arep.App'

```

El anterior comando limpiará las contrucciones previas, compilará y empaquetará el código en un jar y luego ejecutará la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:35000/index.html](http://localhost:35000/index.html) para ver la aplicación en funcionamiento.

## Ejecutando las pruebas ⚙️

Para ejecutar las pruebas, ejecute el siguiente comando:

```bash
mvn test

```

## Generando Javadoc 📦

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site

```

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Cómo se desarrollarían aplicaciones con este proyecto 🧩

Para desarrollar aplicaciones con este proyecto:

Se puede seleccionar la ruta especifica de los archivos que se desean leer.

```java
SantaSpark.staticFileLocation(/* Ruta estatica de archivos */);
```

Se puede seleccionar conexiones get y post para el servidor web recibe una URI a la función lambda que se ejecutará cuando se reciba una solicitud en esa URI.

```java
SantaSpark.get(/* Ruta */, (requestURI) -> {
    /* Codigo de la ruta */
    return /* Respuesta */;
});

SantaSpark.post(/* Ruta */, (requestURI) -> {
    /* Codigo de la ruta */
    return /* Respuesta */;
});
```

## Arquitecura de la aplicación 📐

Esta aplicación es un cliente web que se ejecuta en el navegador y utiliza JSON para el formato de mensajes. Actúa como un servidor de fachada, encapsulando llamadas a servicios web externos a través de HTTP.

La fachada de servicios implementa un caché para evitar llamadas duplicadas, almacenando las respuestas como cadenas y comparándolas.

Los modulos de la aplicación son los siguientes:

* **Cache**: Contiene la implementación del caché de la aplicación para peliculas.
* **HttpServer**: Contiene la implementación del servidor web de la aplicación.
* **HttpMovieConnection**: Contiene la implementación del servicio de conexión a la API externa.
* **SantaSpark**: Contiene la implementación del microframework web.

Se hace uso de patrones de diseño como **Singleton** en diferentes partes de la aplicación para garantizar que solo exista una instancia de la clase.

## Versionado 📌

  ![AREP LAB 03](https://img.shields.io/badge/AREP_LAB_03-v1.0.0-blue)

## Autores ✒️

* **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/)

Este proyecto está bajo la licencia de Creative Commons Reconocimiento-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

* Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
* Imagen de [Freepik](https://www.freepik.es/vector-gratis/fondo-frutas-diseno-plano-realista_36837337.htm#query=Fondo%20naranja%20con%20variedad%20de%20frutas%20y%20envases%20en%20dise%C3%B1o%20plano&position=3&from_view=search&track=ais&uuid=fe5c8865-b622-4dce-931a-1c68eb78156f)
* Las imágenes utilizadas fueron tomadas del autor [Aomam](https://iconscout.com/contributors/aomam).
* Juego de memoria tomado de [Marina Ferreira](https://github.com/marina-ferreira).
