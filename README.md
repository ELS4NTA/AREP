# TALLER 4: ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

## Descripción de la aplicación 📖

Este laboratorio explora la creación de un prototipo mínimo que demuestra capcidades reflexivas de JAVA y permite cargar un bean (POJO) y derivar una aplicación Web a partir de él similar a la de Spring.

Además contiene una mini implementación del microframework WEB denominado [sparkjava](https://sparkjava.com/). Este micro framework permite construir aplicaciones web de manera simple usando funciones lambda.

También permite el acceso a un juego de memoria junto con un creador de batidos de frutas y una simple tienda, el servidor web es capaz de leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes.

Permite realizar consultas a un API externo de [omdbapi](https://www.omdbapi.com/) para obtener información de películas. La implementación es eficiente en cuanto a recursos así que implementa un Caché que permite evitar hacer consultas repetidas al API externo.

Adicionalmente, la aplicación permite el acceso a un servicio de tienda de batidos, donde se pueden los precios de los batidos y el total de la compra.

Disfruta creando diferentes batidos de frutas y jugando a encontrar las parejas de cartas en el juego de memoria.

![image](https://github.com/ELS4NTA/AREP-LAB-04/assets/99996670/dd58723e-9a2d-49e3-8548-2c8d7acac704)

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
git clone https://github.com/ELS4NTA/AREP-LAB-04.git
cd AREP-LAB-04/

```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean compile exec:java '-Dexec.mainClass=edu.eci.arep.App'

```

El anterior comando limpiará las contrucciones previas, compilará y luego ejecutará la aplicación.

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

Crear componentes con la anonotación `@Component`.

```java
@Component
public class MyComponent {
    // ...
}
```

Crear los listeners con la anotación `@RequestMapping`.

```java
@RequestMapping(path = "/hello", method = HttpMethod.GET)
public static String hello() {
    return "Hello World!";
}
```

## Arquitecura de la aplicación 📐

Esta aplicación es un cliente web que se ejecuta en el navegador y utiliza JSON para el formato de mensajes. Actúa como un servidor de fachada, encapsulando llamadas a servicios web externos a través de HTTP.

La fachada de servicios implementa un caché para evitar llamadas duplicadas, almacenando las respuestas como cadenas y comparándolas.

Los modulos de la aplicación son los siguientes:

* **Cache**: Contiene la implementación del caché de la aplicación para peliculas.
* **HttpServer**: Contiene la implementación del servidor web de la aplicación.
* **HttpMovieConnection**: Contiene la implementación del servicio de conexión a la API externa.
* **FruitShopService**: Contiene la implementación del servicio de la tienda de batidos.
* **SantaSpark**: Contiene la implementación del microframework web.
* **SantaSpring**: Contiene la implementación para cargar los componentes y los listeners.
* **ComponentLoader**: Contiene la implementación para cargar los componentes por medio de reflexión con la anotación `@ComponentScan`.

Se hace uso de patrones de diseño como **Singleton** en diferentes partes de la aplicación para garantizar que solo exista una instancia de la clase.

## Versionado 📌

  ![AREP LAB 04](https://img.shields.io/badge/AREP_LAB_04-v1.0.0-blue)

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
* [Cart](https://iconscout.com/icons/cart) by [Font Awesome](https://iconscout.com/contributors/font-awesome)
