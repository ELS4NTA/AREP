# AREP-PARCIAL-T2

Parcial Tercio 2 de Arquitecturas Empresariales

## Descripción de la aplicación 📖

Esta aplicación presenta un sistema de microservicios que tiene un servicio con el nombre Math Services para computar las funciones de búsqueda. El servicio de las funciones de búsqueda estan desplegadas en dos de EC2. Adicionalmente, se implementa un service proxy que recibe las solicitudes de llamado desde los clientes  y se las delega a las dos instancias del servicio de búsqueda usando un algoritmo de balanceo de cargas round-robin. El proxy esta desplegado en otra máquina EC2. Éste tiene un cliente Web con un formulario que recibe el valor y de manera asíncrona e invoca el servicio en el PROXY. Visite la sección de [Arquitecura de la aplicación](#arquitecura-de-la-aplicación-📐).

[Examen AREP T2](https://github.com/ELS4NTA/AREP/assets/99996670/c8aaab58-8296-4614-9dd7-eb485f1b7cd8)

## Comenzando 🚀

Para ejecutar el proyecto se debe seguir los siguientes pasos:

### Requisitos 📋

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven y Java 17 para poder ejecutar el proyecto.

> [!NOTE]
> Si desea relizar la instalación en AWS, vea la sección de [Instalación en AWS](#instalación-en-aws-☁)

### Instalación 🔧

#### Instalación en AWS ☁

Ejecute los siguientes comandos para instalar git, maven y java en su máquina EC2.

```bash
sudo yum install -y git
```

```bash
sudo yum install -y java-17-amazon-corretto-devel
```

```bash
sudo wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
```

#### Instalación en local 💻

Realice los siguientes pasos para clonar el proyecto.

```bash
git clone https://github.com/ELS4NTA/AREP.git
cd AREP/
git checkout parcial-tercio-2
```

## Ejecutando la aplicación local ⚙️

Para ejecutar la aplicación, ejecute los siguientes comandos en la raíz del proyecto.

En una terminal ejecute el siguiente comando para compilar el proyecto:

```bash
mvn clean compile
```

El anterior comando limpiará las contrucciones previas, compilará y luego ejecutará en distintos contenedores los servicios de la aplicación.

Una vez compilado ejecute el servidor proxy en una terminal con el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.ProxyServer"
```

En otra terminal ejecute el siguiente comando para ejecutar el servicio de math para usar búsqueda lineal y binario:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.MathService"
```

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: [http://localhost:8080](http://localhost:8080) para ver la aplicación en funcionamiento.

## Arquitecura de la aplicación 📐

La aplicación tiene los siguientes componentes:

**Aplicación web ProxyServer:**

- Está compuesta por un cliente web que presenta un formulario para usar los servicios de búsqueda lineal o binaria.
- Cuando un usuario envía un mensaje, el cliente web lo envía al servicio REST.
- El servicio REST procesa el mensaje y actualiza la pantalla del cliente web con la información devuelta en formato JSON.
- Implementa un algoritmo de balanceo de cargas de Round Robin para distribuir la carga entre tres instancias del servicio `MathService`.

**MathService:**

- Servicio que ofrece la funcionalidad de búsqueda lineal y binaria por una lista ingresada por el usuario.

![image](https://github.com/ELS4NTA/AREP-PARCIAL-T2/assets/99996670/a221feba-9a5e-4172-b43f-710c2a979b00)


## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)
