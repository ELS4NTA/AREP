# TALLER 2: DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET

## Descripción de la aplicación 📖

Este laboratorio tiene como objetivo el diseño y la estructuración de aplicaciones distribuidas en internet, se implememta un servidor web que permite el acceso a un juego de memoria, el servidor web es capaz de leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes.

Tambien permite realizar consultas a un API externo de [omdbapi](https://www.omdbapi.com/) para obtener información de películas. La implementación es eficiente en cuanto a recursos así que implementa un Caché que permite evitar hacer consultas repetidas al API externo.

![image](https://github.com/ELS4NTA/AREP/assets/99996670/89809a88-dd1a-42d0-907e-5ba7c6b06b3b)

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
git clone https://github.com/ELS4NTA/AREP.git
cd AREP/
git checkout taller-2
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

## Versionado 📌

  ![AREP LAB 02](https://img.shields.io/badge/AREP_LAB_02-v1.0.0-blue)

## Autores ✒️

* **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/)

Este proyecto está bajo la licencia de Creative Commons Reconocimiento-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

* Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
* Las imágenes utilizadas fueron tomadas del autor [Aomam](https://iconscout.com/contributors/aomam).
* Juego de memoria tomado de [Marina Ferreira](https://github.com/marina-ferreira).
