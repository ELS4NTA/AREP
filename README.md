# TALLER 5: TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

## Descripción de la aplicación 📖

Este laboratorio se profundiza los conceptos de modulación por medio de virtualización usando Docker. Utiliza el framework [SparkJava](https://sparkjava.com/) para crear un servidor web que se encarga de realizar operaciones matemáticas. La aplicación se compone de un servicio, este es un servicio que realiza operaciones matemáticas como el seno, coseno, una palabra palindrome y la magnitud de un vector.

![image](https://github.com/ELS4NTA/AREP/assets/99996670/bb3eab40-486a-4f1e-8f90-b806f52d8886)

## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
- [Docker](https://www.docker.com/) - Motor de contenedores

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven, Docker y Java 17 para poder ejecutar el proyecto.

## Ejecutando un contenedor Docker 🐳

Para ejecutar la aplicación en un contenedor Docker, ejecute el  comandos:

```docker
docker run -d -p 8080:46000 --name arep-lab-05 els4nta/arep-lab-05
```

El anterior comando descargará la imagen de Docker del proyecto y luego ejecutará un contenedor con la aplicación.

![image](https://github.com/ELS4NTA/AREP/assets/99996670/f872a38c-a428-4886-aa60-c4e964f0008f)

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:8080](http://localhost:8080) para ver la aplicación en funcionamiento.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/AREP.git
cd AREP/
gti checkout taller-5
```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean compile exec:java '-Dexec.mainClass=edu.eci.arep.App'
```

El anterior comando limpiará las construcciones previas, compilará y luego ejecutará la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:4567](http://localhost:4567) para ver la aplicación en funcionamiento.

## Generando Javadoc 📦

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site
```

Después de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego busque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Creando y ejecutando un contenedor con Docker 🐳

Para crear una imagen de Docker de la aplicación, ejecute el siguiente comando:

```docker
docker build -t arep-lab-05 .
```

Este comando creará una imagen de Docker con el nombre `arep-lab-05`.

![image](https://github.com/ELS4NTA/AREP/assets/99996670/31142cfb-5d38-462f-9e96-0f4b03b1d115)

Para ejecutar la aplicación en un contenedor Docker, ejecute el  comandos:

```docker
docker run -d -p 8080:46000 --name arep-lab-05 arep-lab-05
```

![image](https://github.com/ELS4NTA/AREP/assets/99996670/70d6fefd-59ba-490c-91c3-42a5558d4d05)

El anterior comando ejecutará un contenedor con la aplicación mapeando el puerto 46000 del contenedor al puerto 8080 del host. Le pondrá el nombre `arep-lab-05` al contenedor usando la imagen `arep-lab-05`. Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:8080](http://localhost:8080) para ver la aplicación en funcionamiento.

## Arquitectura de la aplicación 📐

Los módulos de la aplicación son los siguientes:

- **CalculatorService**: Es el servicio que se encarga de realizar las operaciones como seno, coseno, una palabra palindrome y la magnitud de un vector.

## Versionado 📌

  ![AREP LAB 05](https://img.shields.io/badge/AREP_LAB_05-v1.0.0-blue)

## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/)

Este proyecto está bajo la licencia de Creative Commons Reconocimiento-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
- Al monitor que revisó el laboratorio.
