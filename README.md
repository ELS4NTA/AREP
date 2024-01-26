# TALLER 1: APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS,MAVEN, GIT)

Una aplicación para consultar la información de películas de cine. La aplicación recibe una frase de búsqueda del título, por ejemplo “Guardians of the galaxy” y deberá mostrar el los datos de la película correspondiente.

Utiliza el API gratuito de [omdbapi](https://www.omdbapi.com/). La implementación es eficiente en cuanto a recursos así que implementa un Caché que permite evitar hacer consultas repetidas al API externo.

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
git clone https://github.com/ELS4NTA/AREP-LAB-01.git
cd AREP-LAB-01/
```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="edu.eci.arep.App"
```

El anterior comando limpiará las contrucciones previas, compilará y empaquetará el código en un jar y luego ejecutará la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:35000/](http://localhost:35000/) para ver la aplicación en funcionamiento.

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

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección project reports y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Descripción de la aplicación 📖

Esta aplicación es un cliente web multiusuario que se ejecuta en el navegador y utiliza JSON para el formato de mensajes. Actúa como un servidor de fachada, encapsulando llamadas a servicios web externos a través de HTTP.

La fachada de servicios implementa un caché para evitar llamadas duplicadas, almacenando las respuestas como cadenas y comparándolas.

El cache de la aplicación usa el patrón de diseño Singleton para garantizar que solo exista una instancia de la clase Cache.

Los modulos de la aplicación son los siguientes:

* **Cache**: Contiene la implementación del caché de la aplicación.
* **HttpServer**: Contiene la implementación del servidor web de la aplicación.
* **HttpMovieConnection**: Contiene la implementación del servicio de conexión a la API externa.

## Cómo extender 🧩

Para extender la aplicación se puede abstraer el servicio de conexión a la API externa en una interfaz y crear una implementación para cada servicio externo que se desee utilizar.

Un ejemplo de esto puede ser el siguiente:

```java
public interface HttpConnectionService {
  String getResponse(String url) throws IOException;
}
```

```java
public class HttpConnectionServiceImpl implements HttpConnectionService {
  @Override
  public String getResponse(String url) throws IOException {
    ...
  }
}
```

```java
public class App {
  public static void main(String[] args) {
    // Crear una instancia de Cliente e inyectar el servicio de conexión
    HttpServer server = new HttpServer(/* Crear una instancia de HttpConnectionService*/);
    ...
  }
}
```

## Versionado 📌

  ![AREP LAB 01](https://img.shields.io/badge/AREP_LAB_01-v1.0.0-blue)

## Autores ✒️

* **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este proyecto está bajo la Licencia (MIT) - ver el archivo [LICENSE](LICENSE) para ver más detalles.

## Agradecimientos 🎁

* Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
