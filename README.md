# 🚀 Kafka Project

Un sistema básico de mensajería con **Apache Kafka** usando Spring Boot, donde se simula el envío y procesamiento de mensajes entre un **Productor (Producer)** y un **Consumidor (Consumer)**. Los datos compartidos están en un módulo común (`common`) y se gestionan mediante servicios REST con Postman.

---

## 🧩 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Kafka** (integración con Apache Kafka)
- **Lombok** (para reducir código boilerplate)
- **Maven** (gestión de dependencias)
- **Docker & Docker Compose** (orquestación de Kafka y Zookeeper)

---


## 📦 Funcionalidad principal

- El **Producer** expone un endpoint REST (POST) para recibir datos.
- Al recibir una petición, serializa el objeto y lo envía a un tópico de Kafka (`messages-topic`).
- El **Consumer** escucha ese tópico y procesa cada mensaje recibido (lo imprime o guarda).
- Ambos servicios usan clases comunes definidas en `common` para mantener consistencia de datos.

---
## 🎯¿Para que sirve este proyecto?
- Aprender cómo integrar Spring Boot con Kafka.
- Entender el patrón Productor-Consumidor.
- Simular microservicios comunicándose mediante eventos.
- Usarlo como base para sistemas de notificaciones, logs distribuidos o procesamiento asíncrono.



