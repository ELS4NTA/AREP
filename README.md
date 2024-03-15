# TALLER 7: APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

## Descripción de la aplicación 📖

Este laboratorio explora el uso un certificado `SSL` para asegurar la comunicación entre el cliente y el servidor a través de `HTTPS`. La aplicación permite a los usuarios autenticarse y ver una lista de dulces. Utiliza el framework [SparkJava](https://sparkjava.com/). Se ejecuta en dos máquinas EC2 de [AWS](https://aws.amazon.com/), una para el servidor de autenticación y otra para el servidor de la aplicación.


## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven y Java 17 para poder ejecutar el proyecto.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/AREP-LAB-07.git
cd AREP-LAB-07/
```

## Ejecutando la aplicación ⚙️

Para compilar el proyecto se debe ejecutar el siguiente comando:

```bash
mvn clean compile
```

Para ejecutar el proyecto se debe ejecutar el siguiente comando:

Abra primero una terminal y ejecute el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.LoginApp"
```

Abra una segunda terminal y ejecute el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.CandyApp"
```

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [https://localhost:5000](https://localhost:5000) para ver la aplicación en funcionamiento. Podrá usar las credenciales de la siguiente tabla para ingresar a la aplicación.

| Usuario    | Contraseña |
|------------|------------|
| Bob        | password1  |
| Alice      | password2  |
| Eve        | password3  |
| Charlie    | password4  |

## Generando Javadoc 📦

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site
```

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Arquitecura de la aplicación 📐

**LoginApp**

La aplicación de autenticación es un servidor que recibe peticiones `HTTPS` en el puerto `5000`. Tiene un certificado `SSL` que le permite recibir las peticiones. El servidor recibe peticiones `POST` en la ruta `/login`. Se comunica con **CandyApp** para obtener la lista de dulces a través de una conexión segura usando el componente `SecureURLReader`.

**CandyApp**

La aplicación de dulces es un servidor que recibe peticiones `HTTPS` en el puerto `6000`. Tiene un certificado `SSL` que le permite recibir las peticiones. El servidor recibe peticiones `GET` en la ruta `/candies`.

## Versionado 📌

  ![AREP LAB 07](https://img.shields.io/badge/AREP_LAB_07-v1.0.0-blue)

## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/deed.es)

Este proyecto está bajo la licencia de Creative Commons Atribución-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
- Al monitor que revisó el laboratorio.
