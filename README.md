# Kata 4: Cliente HTTP, Puertos y Adaptadores (Clean Architecture)

## 1. Objetivo de la entrega
Construir un cliente HTTP pequeño, explícito y verificable en Java que consuma un servicio web externo, separe las fronteras de comunicación mediante una arquitectura de **Puertos y Adaptadores**, transforme la respuesta JSON a objetos de dominio mediante DTOs/mappers, y gestione de forma controlada los errores de red y códigos de estado HTTP, cumpliendo con la progresión de la asignatura.

---

## 2. Requisitos técnicos y dependencias
* **Lenguaje:** Java 25 (`openjdk-25.0.1` o compatible con Java 21)
* **Gestor de dependencias:** Maven
* **Librería de parseo JSON:** Google Gson (`version 2.15.0`)
* **Cliente HTTP nativo:** `java.net.http.HttpClient` (incorporado en Java 11+)

---

## 3. Cómo compilar y ejecutar

Puedes compilar y ejecutar el proyecto directamente desde cualquier entorno de desarrollo (como IntelliJ IDEA) con Ctrl + Shift + A y "run" o Mayús+F10.


---

## 4. Estructura de la entrega y clases principales

El proyecto sigue estrictamente los principios de Clean Architecture (Puertos y Adaptadores), separando el dominio, la lógica de aplicación, los adaptadores de infraestructura y el punto de entrada:

software.ulpgc.kata4.application.domain

User.java: Objeto de dominio inmutable (record) que representa los datos limpios de la aplicación.


software.ulpgc.kata4.application.dtos

UserDto.java: Objeto de transferencia de datos utilizado para el parseo directo del JSON externo.


software.ulpgc.kata4.application.ports

UserStore.java: Interfaz que actúa como puerto de servicio, definiendo qué necesita la aplicación sin acoplarse a detalles tecnológicos de red.


software.ulpgc.kata4.application.usecases

GetUserUseCase.java: Caso de uso que orquesta la lógica de negocio conectando el puerto de almacenamiento con la aplicación.


software.ulpgc.kata4.infrastructure.adapters

HttpUserStore.java: Adaptador HTTP que implementa el puerto, construye de forma controlada el endpoint usando HttpClient, procesa la respuesta con Gson y gestiona los errores de red y códigos HTTP.


software.ulpgc.kata4

Main.java: Punto de entrada ejecutable que centraliza la configuración de la URL base y lanza las pruebas de verificación.


---

## 5. Dinámica de repetición deliberada y variación introducida
Siguiendo la dinámica de repetición exigida para reducir la fricción técnica:

Se ha mantenido inalterable la estructura troncal de la arquitectura (dominio, puerto, adaptador HTTP y caso de uso) al igual que en las iteraciones previas.

Variación pequeña introducida: En esta última repetición se ha adaptado la salida por pantalla en el Main para destacar de forma limpia campos concretos y personalizados del usuario (como la edad estimada y un formato de salida más descriptivo), validando la flexibilidad del diseño sin romper el flujo de comunicación original.

---

## 6. Evidencias de verificación y pruebas realizadas
El cliente ha sido verificado mediante escenarios controlados ejecutados directamente desde el Main:

Prueba de éxito (Respuesta correcta):

Se consulta un nombre válido en la API pública de Agify (https://api.agify.io).

El adaptador procesa el JSON recibido, lo transforma al DTO y al objeto de dominio, mostrando por consola los datos obtenidos con éxito.

Prueba de error controlado (Servicio no disponible / URL inválida):

Se simula una petición a una URL inexistente (https://api-invalid-1234567.io).

El sistema captura el fallo de red mediante un bloque try-catch, traduciéndolo a un mensaje de error legible por consola ([NETWORK Error]) sin provocar un cierre abrupto de la aplicación.

Control de códigos de estado HTTP:

Se valida explícitamente que si el servidor devuelve un código de estado distinto de 200 (como errores 4xx o 5xx), el adaptador lo intercepta y lo reporta controladamente.

---

## 7. Enlace al vídeo explicativo
URL del vídeo (máx. 7 minutos): [https://youtu.be/q_1Ppp1lxiY?si=qvDtt8Vylf7otQNX]