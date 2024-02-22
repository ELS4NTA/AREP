# AREP-PARCIAL-T1
Parcial Tercio 1 de Arquitecturas Empresariales

Para ejecutar el proyecto se debe seguir los siguientes pasos:

```bash
git clone https://github.com/ELS4NTA/AREP-PARCIAL-T1.git
cd AREP-PARCIAL-T1/
```

Para compilar el proyecto se debe ejecutar el siguiente comando:

```bash
mvn clean compile
```

Para ejecutar el proyecto se debe ejecutar el siguiente comando:

Abra primero una terminal y ejecute el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.FacadeWeb"
```

Abra una segunda terminal y ejecute el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arep.ReflectiveChatWeb"
```

Dirijase a su navegador y escriba la siguiente URL [http://localhost:35000/cliente](http://localhost:35000/cliente)

Y se mostrará la siguiente página web:


Luego puede escribir como ejemplo uno de los siguientes comandos en el formulario:

```java
class(java.lang.Math)
```

```java
invoke(java.lang.System,getenv)
```