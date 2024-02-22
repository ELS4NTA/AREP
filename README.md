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

![image](https://github.com/ELS4NTA/AREP-PARCIAL-T1/assets/99996670/a9bb2c32-9388-4780-bc71-5d1e46ee5a4e)

Luego puede escribir como ejemplo uno de los siguientes comandos en el formulario:

```java
class(java.lang.Math)
```

```java
invoke(java.lang.System,getenv)
```

![image](https://github.com/ELS4NTA/AREP-PARCIAL-T1/assets/99996670/693460e7-0fc8-4539-8f6d-94ae93d0cec7)
