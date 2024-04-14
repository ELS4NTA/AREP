# TALLER 8: MICROSERVICIOS

## Descripción de la aplicación 📖

Este laboratorio consiste en una aplicación web que permite a los usuarios autenticarse, por medio de [JWT](https://jwt.io/) para acceder a una página que contienen publicaciones al estilo de twitter. Los usuarios pueden realizar publicaciones, las cuales se almacenan en una base de datos no relacional [MongoDB](https://www.mongodb.com/). La aplicación se despliega en [AWS](https://aws.amazon.com/) y se utiliza [Amazon Cognito](https://aws.amazon.com/cognito/) para la autenticación de los usuarios. Además, se utilizan funciones [AWS Lambda](https://aws.amazon.com/lambda/) para almacenar y obtener los datos de la base de datos. El [Amazon API Gateway](https://aws.amazon.com/api-gateway/) se encarga de enrutar las solicitudes de los usuarios a los componentes adecuados de la aplicación. Ver la arquitectura de la aplicación [aquí](#arquitecura-de-la-aplicación-).

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
git clone https://github.com/ELS4NTA/AREP-LAB-08.git
cd AREP-LAB-08/
```

## Ejecutando la aplicación ⚙️

Para crear un monolito para realizar pruebas locales, ejecute el siguiente comando en la raíz del proyecto.

```bash
mvn clean package
docker-compose up -d
```

El anterior comando limpiará las contrucciones previas, compilará y luego ejecutará en distintos contenedores los servicios de la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [https://localhost:8080](https://localhost:8080) para ver la aplicación en funcionamiento. Podrá usar las credenciales de la siguiente tabla para ingresar a la aplicación.

| Usuario    | Contraseña |
|------------|------------|
| angie      | An6ie02    |
| daniel     | ELS4NTA    |

## Generando Javadoc 📦

Para generar la documentación de la aplicación, debe ejecute el siguiente comando.

```bash
mvn javadoc:javadoc
```

El anterior comando crea un archivo con la documentación de la aplicación, esta la puede encontrar en `target/site/apidocs/index.html`.

## Arquitecura de la aplicación 📐

Los componentes principales de la arquitectura son los siguientes:

**Amazon API Gateway**: La API Gateway es un servicio de AWS que enruta las solicitudes de los usuarios a los componentes adecuados de la aplicación.

**AWS Lambda**: Lambda es un servicio de AWS que permite ejecutar código sin aprovisionar o administrar servidores. En este caso, Lambda se utiliza para ejecutar las funciones sin servidor de la aplicación.

**Amazon S3**: S3 es un servicio de almacenamiento de objetos de AWS que se utiliza para almacenar los datos de la aplicación.

**MongoDB**: MongoDB es una base de datos de documentos no relacionales que se utiliza para almacenar los datos de la aplicación.

**Amazon Cognito**: Cognito es un servicio de administración de identidad y acceso de AWS que se utiliza para autenticar a los usuarios de la aplicación.

Las funciones sin servidor de la aplicación se activan cuando los usuarios realizan solicitudes a la API Gateway.

![arquitecturataller8](https://github.com/An6ie02/AREP-TALLER-08/assets/99996670/a0ec606e-f438-4ff1-b432-57594fa4fae4)

## Despliegue en AWS 🚀

En el siguiente video se muestra el despliegue de la aplicación en AWS:

- Se puede observar la base de datos MongoDB en la máquina virtual en EC2.
- Se muestran las pruebas de las funciones lambda junto con el correcto almacenamiento de los datos en la base de datos. Las funciones lambda son: `stream-post` la cual almacena un nuevo dato en la base de datos y `stream-get` la cual obtiene todos los datos almacenados en la base de datos.
- Estas funciones son invocadas por el API Gateway, el cual se encarga de enrutar las solicitudes de los usuarios a los componentes adecuados de la aplicación.
- Finalmente, se muestra la autenticación de los usuarios en la aplicación mediante Amazon Cognito, en caso de que se quiera acceder directamente a la aplicación sin autenticación, se redirige a la página de login.

https://github.com/An6ie02/AREP-TALLER-08/assets/100453879/257e3300-e85c-4c62-a3b9-78f6a473dfd2

## Versionado 📌

  ![AREP LAB 08](https://img.shields.io/badge/AREP_LAB_08-v1.0.0-blue)\
  [![Static Badge](https://img.shields.io/badge/AREP_LAB_08_DESPLIEGUE-v1.0.0-blue)](https://github.com/An6ie02/AREP-TALLER-08/tree/deploy)

## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)
- **Angie Mojica** - [An6ie02](https://github.com/An6ie02)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/deed.es)

Este proyecto está bajo la licencia de Creative Commons Atribución-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos 🎁

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
- Al monitor que revisó el laboratorio.
