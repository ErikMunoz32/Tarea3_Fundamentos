package com.tarea.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.tarea.mock.entidades.Perro;
import com.tarea.mock.excepciones.PerroNoEncontradoException;
import com.tarea.mock.repositorios.PerroRepository;
import com.tarea.mock.servicios.PerroComunitarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PerroComunitarioServiceTest {

    PerroRepository mockRepository;
    PerroComunitarioService service;

    @BeforeEach
    public void inicializarPrueba() {
        // Mock del repositorio
        mockRepository = Mockito.mock(PerroRepository.class);
        // Servicio a probar
        service = new PerroComunitarioService(mockRepository);
    }

    @Test
    public void deberiaDevolverPerroCuandoElPerroExiste() {
        // Simula que el repositorio devuelve un perro
        Perro perroSimulado = new Perro("Fido", 4);
        when(mockRepository.buscarPorNombre("Fido")).thenReturn(perroSimulado);

        // Ejecuta el método del servicio
        Perro resultado = service.obtenerPerroPorNombre("Fido");

        // Verifica los resultados
        assertEquals("Fido", resultado.getNombre());
        assertEquals(4, resultado.getEdad());
    }

    @Test
    public void deberiaLanzarPerroNoEncontradoExceptioCuandoElPerroNoExiste() {
        // Simula que el repositorio no encuentra el perro
        when(mockRepository.buscarPorNombre("Rex")).thenReturn(null);

        // Verifica que se lanza la excepción
        assertThrows(PerroNoEncontradoException.class, () -> {
            service.obtenerPerroPorNombre("Rex");
        });
    }

    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsNull() {
        // Verifica que se lanza la excepción
        assertThrows(IllegalArgumentException.class, () -> {
            service.obtenerPerroPorNombre(null);
        });
    }

    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsVacio() {
        // Verifica que se lanza la excepción
        assertThrows(IllegalArgumentException.class, () -> {
            service.obtenerPerroPorNombre("");
        });
    }

    @Test
    public void deberiaConsultarRepositorioUnaSolaVezCuandoElPerroExiste() {
        // Simula que el repositorio devuelve un perro
        Perro perroSimulado = new Perro("Fido", 4);
        when(mockRepository.buscarPorNombre("Fido")).thenReturn(perroSimulado);

        // Ejecuta el método del servicio
        service.obtenerPerroPorNombre("Fido");

        // Verifica que el método buscarPorNombre se llamó exactamente una vez
        verify(mockRepository, times(1)).buscarPorNombre("Fido");
    }
}
