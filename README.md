# TALLER 9: Large Lenguage Models (LLMs)

[![Open In Colab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/github/ELS4NTA/AREP-LAB-09/blob/main/tallerllm.ipynb)

## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

- [Python 3.x](https://www.python.org/downloads/) - Lenguaje de programación

> [!IMPORTANT]
> Es necesario tener instalado Python para poder ejecutar el proyecto.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/AREP-LAB-09.git
cd AREP-LAB-09/
```

Cree un entorno virtual python e instale las dependencias del proyecto.

#### Crear un entorno virtual 🌴

```bash
python -m venv .venv
```

#### Activar el entorno virtual 🔌

Windows
  
```bash
.venv\Scripts\activate 
```

Linux y macOS

```bash
source .venv/bin/activate
```

#### Instalar dependencias 📦

```bash
.venv\Scripts\python.exe -m pip install --upgrade pip
.venv\Scripts\python.exe -m pip install -r requirements.txt
```

## Descripción de la aplicación 📖

Este laboratorio se centra en el uso de [Lang Chain](https://python.langchain.com/docs/get_started/introduction), [Pinecone](https://www.pinecone.io/) y [OpenAI](https://openai.com/) para desarrollar los siguientes desafíos:

### Enviar solicitudes a Chatgpt y recuperar respuestas

Se utiliza la biblioteca Lang Chain para interactuar con OpenAI y enviar las solicitudes a Chatgpt. Se configura un modelo de lenguaje de aprendizaje profundo (LLM) de OpenAI para generar respuestas a partir de las solicitudes enviadas.

```bash
python chatgpt.py
```

### Escribir un RAG simple utilizando una base de datos de vectores en memoria

Creación de un Recuperador de Respuestas Generativas (RAG) simple utilizando una base de datos de vectores en memoria para almacenar y recuperar información relevante para responder a preguntas.

```bash
python memoryrag.py
```

### Crear un RAG simple utilizando Pinecone

Creación de un Recuperador de Respuestas Generativas (RAG) simple utilizando Pinecone para almacenar y recuperar información relevante para responder a preguntas.

```bash
python pineconerag.py
```

## Construido con 🛠️

- [Python](https://www.python.org/) - Lenguaje de programación.
- [Lang Chain](https://python.langchain.com/docs/get_started/introduction) - Biblioteca de Python para interactuar con OpenAI.
- [Pinecone](https://www.pinecone.io/) - Servicio de indexación y búsqueda de vectores.
- [OpenAI](https://openai.com/) - Plataforma de inteligencia artificial.

## Versionado 📌

  ![AREP LAB 09](https://img.shields.io/badge/AREP_LAB_09-v1.0.0-blue)

## Autores ✒️

- **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este proyecto está bajo la licencia del MIT. Consulte el archivo [LICENSE](LICENSE) para obtener más información.

## Agradecimientos 🎁

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por compartir sus conocimientos.
- Al monitor que revisó el laboratorio.
