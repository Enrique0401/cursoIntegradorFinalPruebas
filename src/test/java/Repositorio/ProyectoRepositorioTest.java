package Repositorio;

import Builder.ProyectoBuilder;
import Model.Proyectos;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProyectoRepositorioTest {
    
    private ProyectoRepositorio repositorio;
    private Proyectos proyectoPrueba;
    
    // ==========================================
    // CONFIGURACIÓN DE DATOS DE PRUEBA
    // ==========================================
    // DEBE EXISTIR un cliente con este ID en tu tabla 'cliente'
    private final int ID_CLIENTE_PRUEBA = 1; 
    
    private final String NOMBRE_PRUEBA = "Proyecto JUnit Test";
    private final String ESTADO_PRUEBA = "En Progreso";

    public ProyectoRepositorioTest() {
    }
    
    @BeforeEach
    public void setUp() {
        repositorio = new ProyectoRepositorio();
        
        // Limpiamos por si quedó basura de una ejecución anterior fallida
        limpiarDatosDePrueba();
        
        // Construimos el objeto
        proyectoPrueba = new ProyectoBuilder()
                .conNombreProyecto(NOMBRE_PRUEBA)
                .conCategoriaProyecto("Desarrollo Software")
                .conDescripcionProyecto("Proyecto creado automáticamente por el Test")
                .conEstadoProyecto(ESTADO_PRUEBA)
                .conProgresoProyecto(0)
                .conFechaEntrega(LocalDateTime.now().plusDays(30)) // Entrega en 30 días
                .conIdCliente(ID_CLIENTE_PRUEBA)
                .build();
    }
    
    @AfterEach
    public void tearDown() {
        // Borramos el proyecto de prueba al finalizar cada test
        limpiarDatosDePrueba();
    }
    
    // Helper para buscar y borrar el proyecto de prueba
    private void limpiarDatosDePrueba() {
        List<Proyectos> lista = repositorio.obtenerTodos();
        for (Proyectos p : lista) {
            if (p.getNombre() != null && p.getNombre().equals(NOMBRE_PRUEBA)) {
                repositorio.eliminar(p.getIdProyecto());
            }
        }
    }

    // Helper para recuperar el ID generado automáticamente por la BD
    private int obtenerIdInsertado() {
        List<Proyectos> lista = repositorio.obtenerTodos();
        for (Proyectos p : lista) {
            if (p.getNombre().equals(NOMBRE_PRUEBA)) {
                return p.getIdProyecto();
            }
        }
        return -1;
    }

    @Test
    public void testRegistrar() {
        System.out.println("TEST: registrar");
        
        boolean resultado = repositorio.registrar(proyectoPrueba);
        
        assertTrue(resultado, "El registro debería devolver true");
        assertNotEquals(-1, obtenerIdInsertado(), "El proyecto debería existir en la BD");
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: actualizar");
        
        // 1. Insertamos
        repositorio.registrar(proyectoPrueba);
        int id = obtenerIdInsertado();
        
        // 2. Modificamos el objeto local
        proyectoPrueba.setIdProyecto(id); // Importante: setear el ID
        proyectoPrueba.setDescripcion("Descripción Actualizada");
        proyectoPrueba.setProgreso(50);
        
        // 3. Ejecutamos actualización
        boolean resultado = repositorio.actualizar(proyectoPrueba);
        
        // 4. Verificamos en BD
        assertTrue(resultado);
        Proyectos actualizado = repositorio.obtenerPorId(id);
        assertEquals("Descripción Actualizada", actualizado.getDescripcion());
        assertEquals(50, actualizado.getProgreso());
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("TEST: obtenerPorId");
        
        repositorio.registrar(proyectoPrueba);
        int id = obtenerIdInsertado();
        
        Proyectos resultado = repositorio.obtenerPorId(id);
        
        assertNotNull(resultado);
        assertEquals(NOMBRE_PRUEBA, resultado.getNombre());
    }

    @Test
    public void testObtenerPorUsuario() {
        System.out.println("TEST: obtenerPorUsuario (Cliente ID)");
        
        repositorio.registrar(proyectoPrueba);
        
        // Buscamos proyectos del cliente 1 (o el que definiste)
        Proyectos resultado = repositorio.obtenerPorUsuario(ID_CLIENTE_PRUEBA);
        
        assertNotNull(resultado, "Debería encontrar al menos un proyecto para este cliente");
        // Nota: Si el cliente tiene muchos proyectos, este método solo devuelve el primero que encuentra
        // según la lógica de tu Repositorio actual (rs.next() -> return).
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: obtenerTodos");
        
        repositorio.registrar(proyectoPrueba);
        
        List<Proyectos> lista = repositorio.obtenerTodos();
        
        assertFalse(lista.isEmpty());
        boolean encontrado = lista.stream().anyMatch(p -> p.getNombre().equals(NOMBRE_PRUEBA));
        assertTrue(encontrado);
    }

    @Test
    public void testEliminar() {
        System.out.println("TEST: eliminar");
        
        repositorio.registrar(proyectoPrueba);
        int id = obtenerIdInsertado();
        
        boolean resultado = repositorio.eliminar(id);
        
        assertTrue(resultado);
        assertNull(repositorio.obtenerPorId(id), "El proyecto ya no debe existir");
    }

    @Test
    public void testVerPorEstado() {
        System.out.println("TEST: verPorEstado");
        
        repositorio.registrar(proyectoPrueba); // Estado: "En Progreso"
        
        List<Proyectos> lista = repositorio.verPorEstado(ESTADO_PRUEBA);
        
        assertNotNull(lista);
        boolean encontrado = lista.stream().anyMatch(p -> p.getNombre().equals(NOMBRE_PRUEBA));
        assertTrue(encontrado, "Debería encontrar el proyecto buscando por su estado");
    }
    
    // NOTA: Estos tests validan contra la tabla CLIENTE, no Proyecto.
    @Test
    public void testValidacionesCliente() {
        System.out.println("TEST: Validaciones (idRegistrado en tabla Cliente)");
        
        // Verifica que el método detecte que el cliente existe
        boolean existe = repositorio.idRegistrado(ID_CLIENTE_PRUEBA);
        assertTrue(existe, "El ID del cliente de prueba debería existir en la tabla cliente");
        
        // Verifica un ID que seguro no existe
        boolean noExiste = repositorio.idRegistrado(-9999);
        assertFalse(noExiste);
    }
}