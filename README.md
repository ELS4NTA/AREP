# TALLER 6: PATRONES ARQUITECTURALES EN AMAZON WEB SERVICES

## Descripción de la aplicación 📖

Este laboratorio explora patrones arquitecutrales en Amazon Web Services (AWS) y su implementación en aplicaciones web. La aplicación web es un servicio de log que almacena cadenas de texto y las muestra en una página web. Utiliza el framework [SparkJava](https://sparkjava.com/) para crear servicios REST y el motor de base de datos [MongoDB](https://www.mongodb.com/) para almacenar datos. Estos se ejecutan en contenedores Docker y se despliega en una instancia EC2 de AWS.


https://github.com/ELS4NTA/AREP-LAB-06/assets/99996670/57d031da-e44a-4ad4-a87d-64313853845a


## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
- [Docker](https://www.docker.com/) - Motor de contenedores

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven, Docker y Java 17 para poder ejecutar el proyecto.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/AREP-LAB-06.git
cd AREP-LAB-06/
```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute los siguientes comandos en la raíz del proyecto.

```bash
mvn clean install
docker-compose up -d
```

El anterior comando limpiará las contrucciones previas, compilará y luego ejecutará en distintos contenedores los servicios de la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:8080](http://localhost:8080) para ver la aplicación en funcionamiento.

## Generando Javadoc 📦

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site
```

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Arquitecura de la aplicación 📐

La aplicación que describes tiene varios componentes interconectados

**Aplicación web APP-LB-RoundRobin:**

- Está compuesta por un cliente web y al menos un servicio REST.
- El cliente web tiene un campo de entrada de texto y un botón.
- Cuando un usuario envía un mensaje, el cliente web lo envía al servicio REST.
- El servicio REST procesa el mensaje y actualiza la pantalla del cliente web con la información devuelta en formato JSON.
- Implementa un algoritmo de balanceo de cargas de Round Robin para distribuir la carga entre tres instancias del servicio LogService.

**LogService:**

- Es un servicio REST que recibe cadenas de texto.
- Almacena estas cadenas en la base de datos MongoDB.
- Responde con un objeto JSON que contiene las 10 últimas cadenas almacenadas y sus fechas correspondientes.

**Servicio MongoDB:**

- Es una instancia de MongoDB que se ejecuta dentro de un contenedor Docker en una máquina virtual EC2. Su función principal es almacenar datos las cadenas de texto que recibe el servicio LogService.

![image](https://github.com/ELS4NTA/AREP-LAB-06/assets/99996670/a172b6c2-c7d3-4bab-8012-e537fc0f0340)

## Versionado 📌

  ![AREP LAB 06](https://img.shields.io/badge/AREP_LAB_06-v1.0.0-blue)

## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/deed.es)

Este proyecto está bajo la licencia de Creative Commons Atribución-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
- Al monitor que revisó el laboratorio.
