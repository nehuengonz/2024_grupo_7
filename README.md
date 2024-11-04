# TallerDeProgra1-GRUPO-7

# Test Subi que te llevo

Este repositorio contiene el testeo del **GRUPO 7** para la aplicación "Subi que te llevo", un sistema de gestión de viajes.

## 2. Descripción General del Sistema

"Subi que te llevo" es una aplicación standalone desarrollada en Java utilizando el patrón Modelo-Vista-Controlador (MVC).  Gestiona una flota de vehículos, conductores, clientes y pedidos de transporte, permitiendo a los usuarios realizar operaciones relacionadas con la administración de viajes, cálculo de tarifas y manejo de información sobre vehículos y conductores.

### 2.1 Usuarios

* **Clientes:** Solicitan servicios de transporte.
* **Administradores:** Gestionan los recursos de la empresa (conductores, vehículos, etc.) y ejecutan los viajes.

## 3. Requerimientos Funcionales

El sistema se divide en cuatro módulos principales:

### 3.1 Módulo Controlador

* Gestiona las sesiones de usuario (login/logout).
* Registra nuevos clientes.
* Gestiona los pedidos de viaje.
* Gestiona la adición de conductores.
* Gestiona la adición de vehículos.
* Gestiona la creación y finalización de viajes.
* Persiste los datos en un archivo binario.

### 3.2 Módulo Modelo de Negocios

Contiene la lógica de negocio para:

* Agregar, obtener información de choferes.
* Agregar, autenticar clientes.
* Agregar, obtener información de vehículos.
* Crear pedidos.
* Crear viajes, asignar choferes y vehículos.
* Pagar y finalizar viajes.

### 3.3 Módulo Modelo de Datos

Define las estructuras de datos y la lógica relacionada con:

* Usuarios (incluyendo el Singleton Administrador).
* Vehículos (tipos, cálculo de puntaje).
* Conductores (tipos, cálculo de sueldo).
* Pedidos (información, validaciones).
* Cálculo del costo de viajes.

### 3.4 Módulo Persistencia

Maneja la persistencia de datos mediante:

* Serialización y deserialización binaria.
* Conversión entre objetos `Empresa` y `EmpresaDTO`.
* Gestión de excepciones relacionadas con la persistencia.


## 4. Requerimientos No Funcionales

* **Lenguaje de Programación:** Java.
* **Rendimiento:**  Manejo eficiente de grandes cantidades de datos.
* **Seguridad:**  Autenticación y manejo seguro de contraseñas.
* **Mantenibilidad:** Código bien estructurado y fácil de modificar.
* **Escalabilidad:** Capacidad de manejar un volumen creciente de datos.
* **Compatibilidad:** Compatibilidad con plataformas que soporten Java.

## 5. Constantes, Mensajes y Excepciones

El documento SRS detalla las constantes, mensajes de usuario y excepciones utilizadas en el sistema.  Ver el SRS para la lista completa.


## 6.  Ejecución de Pruebas

Las pruebas se ejecutaron en el framework Junit.
