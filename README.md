# Tarea 3: Pruebas Unitarias con Objetos Mock

## Descripción

Esta tarea tiene como objetivo aplicar los conocimientos adquiridos sobre la creación de pruebas unitarias, utilizando **objetos mock**.

El profesor proporcionó un **archivo comprimido (ZIP)** que contenía el código base de un sistema en desarrollo para la gestión de perros comunitarios. El sistema incluye los siguientes componentes:

- **`PerroRepository.java`**: Un repositorio que permite buscar perros por su nombre.
- **`PerroComunitarioService.java`**: Un servicio que utiliza el repositorio para obtener información de un perro y realizar validaciones, como verificar si el perro existe o si los parámetros de búsqueda son válidos.

La tarea consistió en implementar las pruebas unitarias especificadas en la clase `PerroComunitarioServiceTest.java`, asegurando y validando el correcto funcionamiento del servicio .

## Convención de Nombres para las Pruebas

El nombre de las pruebas sigue la convención BDD (*Behavior-Driven Development*), utilizando el formato **debería - cuando**. A continuación, se resumen los tests implementados y sus respectivos objetivos:

### 1. `deberiaDevolverPerroCuandoElPerroExiste`

**Objetivo:** Verificar que el servicio devuelve un perro cuando se le pasa un nombre válido y el repositorio lo encuentra.  
**Pasos a implementar:**  
- Simular que el repositorio devuelve un perro con nombre `"Fido"` y edad `4`.
- Verificar que el nombre y la edad del perro devuelto sean correctos.

---

### 2. `deberiaLanzarPerroNoEncontradoExceptionCuandoElPerroNoExiste`

**Objetivo:** Verificar que el servicio lanza una excepción `PerroNoEncontradoException` cuando el repositorio no encuentra el perro.  
**Pasos a implementar:**  
- Simular que el repositorio no encuentra un perro con el nombre `"Rex"`.  
- Verificar que se lance la excepción `PerroNoEncontradoException`.

---

### 3. `deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsNull`

**Objetivo:** Verificar que el servicio lanza una excepción `IllegalArgumentException` cuando el nombre proporcionado es `null`.  
**Pasos a implementar:**  
- Llamar al servicio con un nombre `null`.  
- Verificar que se lance la excepción `IllegalArgumentException`.

---

### 4. `deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsVacio`

**Objetivo:** Verificar que el servicio lanza una excepción `IllegalArgumentException` cuando el nombre proporcionado está vacío.  
**Pasos a implementar:**  
- Llamar al servicio con una cadena vacía como nombre.  
- Verificar que se lance la excepción `IllegalArgumentException`.

---

### 5. `deberiaConsultarRepositorioUnaSolaVezCuandoElPerroExiste`

**Objetivo:** Verificar que el servicio consulta el repositorio una sola vez cuando el perro existe, para evitar accesos innecesarios.  
**Pasos a implementar:**  
- Simular que el repositorio devuelve un perro con nombre `"Fido"`.  
- Verificar que el método `buscarPorNombre` sea llamado exactamente **una vez**, utilizando el siguiente código:  

```java
verify(mockRepository, times(1)).buscarPorNombre("Fido");

