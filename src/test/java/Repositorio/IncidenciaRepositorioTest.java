package Repositorio;

import Builder.IncidenciaBuilder;
import Model.Incidencia;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaRepositorioTest {
    
    private IncidenciaRepositorio repositorio;
    private Incidencia incidenciaPrueba;
    
    // CONSTANTES DE PRUEBA
    // IMPORTANTE: Asegúrate de que existe un proyecto con este ID en tu BD
    // o el test fallará por restricción de llave foránea.
    private final int ID_PROYECTO_PRUEBA = 1; 
    private final String DESC_PRUEBA = "Incidencia Test JUnit Descripción";
    
    public IncidenciaRepositorioTest() {
    }
    
    @BeforeEach
    public void setUp() {
        repositorio = new IncidenciaRepositorio();
        
        // Limpiamos rastros anteriores si quedaron (por descripción)
        limpiarDatosDePrueba();

        // Construimos el objeto base
        incidenciaPrueba = new IncidenciaBuilder()
                .conDescripcionIncidencia(DESC_PRUEBA)
                .conEstadoIncidencia("Abierto")
                .conFechaIncidencia(LocalDateTime.now())
                .conIdProyecto(ID_PROYECTO_PRUEBA) 
                .build();
    }
    
    @AfterEach
    public void tearDown() {
        limpiarDatosDePrueba();
    }
    
    /**
     * Método auxiliar para borrar la incidencia de prueba de la BD
     * Como no tenemos email o DNI único, buscamos por la descripción específica.
     */
    private void limpiarDatosDePrueba() {
        List<Incidencia> lista = repositorio.obtenerTodos();
        for (Incidencia i : lista) {
            if (i.getDescripcionIncidencia() != null && 
                i.getDescripcionIncidencia().equals(DESC_PRUEBA)) {
                repositorio.eliminar(i.getIdIncidencia());
            }
        }
    }
    
    /**
     * Auxiliar para obtener el ID real generado en BD después de insertar
     */
    private int obtenerIdDePruebaInsertada() {
        List<Incidencia> lista = repositorio.obtenerTodos();
        for (Incidencia i : lista) {
            if (i.getDescripcionIncidencia().equals(DESC_PRUEBA)) {
                return i.getIdIncidencia();
            }
        }
        return -1;
    }

    @Test
    public void testRegistrar() {
        System.out.println("TEST: registrar");
        
        boolean resultado = repositorio.registrar(incidenciaPrueba);
        
        assertTrue(resultado, "El registro debería devolver true");
        assertTrue(obtenerIdDePruebaInsertada() != -1, "La incidencia debería encontrarse en la BD");
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: actualizar");
        
        // 1. Registrar
        repositorio.registrar(incidenciaPrueba);
        int id = obtenerIdDePruebaInsertada();
        assertNotEquals(-1, id, "Debe existir el registro para actualizar");
        
        // 2. Preparar datos nuevos
        incidenciaPrueba.setIdIncidencia(id); // Importante: setear el ID recuperado
        incidenciaPrueba.setDescripcionIncidencia(DESC_PRUEBA); // Mantenemos desc para poder borrarla luego
        incidenciaPrueba.setEstadoInIncidencia("Cerrado"); // Cambiamos estado
        
        // 3. Ejecutar actualizar
        boolean resultado = repositorio.actualizar(incidenciaPrueba);
        
        // 4. Verificar
        assertTrue(resultado, "Actualizar debería retornar true");
        
        Incidencia actualizada = repositorio.obtenerPorId(id);
        assertEquals("Cerrado", actualizada.getEstadoInIncidencia());
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("TEST: obtenerPorId");
        
        repositorio.registrar(incidenciaPrueba);
        int id = obtenerIdDePruebaInsertada();
        
        Incidencia resultado = repositorio.obtenerPorId(id);
        
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdIncidencia());
        assertEquals(DESC_PRUEBA, resultado.getDescripcionIncidencia());
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: obtenerTodos");
        
        repositorio.registrar(incidenciaPrueba);
        
        List<Incidencia> lista = repositorio.obtenerTodos();
        
        assertNotNull(lista);
        assertFalse(lista.isEmpty(), "La lista no debería estar vacía");
        
        boolean encontrada = lista.stream()
                .anyMatch(i -> i.getDescripcionIncidencia().equals(DESC_PRUEBA));
        
        assertTrue(encontrada, "La incidencia insertada debe aparecer en la lista");
    }

    @Test
    public void testEliminar() {
        System.out.println("TEST: eliminar");
        
        repositorio.registrar(incidenciaPrueba);
        int id = obtenerIdDePruebaInsertada();
        
        boolean resultado = repositorio.eliminar(id);
        
        assertTrue(resultado, "Eliminar debería retornar true");
        assertNull(repositorio.obtenerPorId(id), "La incidencia ya no debe existir");
    }
}