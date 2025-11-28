package Repositorio;

import Builder.SeguimientoBuilder;
import Model.Seguimiento;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeguimientoRepositorioTest {
    
    private SeguimientoRepositorio repositorio;
    private Seguimiento seguimientoPrueba;
    
    // ==========================================
    // CONFIGURACIÓN
    // ==========================================
    // IMPORTANTE: Debe existir un PROYECTO con este ID en tu BD
    private final int ID_PROYECTO_PRUEBA = 1; 
    
    private final String DESC_PRUEBA = "Seguimiento Test JUnit";

    public SeguimientoRepositorioTest() {
    }
    
    @BeforeEach
    public void setUp() {
        repositorio = new SeguimientoRepositorio();
        
        // Limpieza preventiva
        limpiarDatosDePrueba();
        
        // Construcción del objeto
        seguimientoPrueba = new SeguimientoBuilder()
                .conDescripcionSeguimiento(DESC_PRUEBA)
                .conPorcentajeAvance(10)
                .conFechaRegistro(LocalDateTime.now())
                .conIdProyecto(ID_PROYECTO_PRUEBA)
                .build();
    }
    
    @AfterEach
    public void tearDown() {
        limpiarDatosDePrueba();
    }

    // Helper: Borra el dato de prueba buscando por descripción
    private void limpiarDatosDePrueba() {
        List<Seguimiento> lista = repositorio.obtenerTodos();
        for (Seguimiento s : lista) {
            if (s.getDescripcion() != null && s.getDescripcion().equals(DESC_PRUEBA)) {
                repositorio.eliminar(s.getIdSeguimiento());
            }
        }
    }
    
    // Helper: Recupera el ID generado en la BD
    private int obtenerIdInsertado() {
        List<Seguimiento> lista = repositorio.obtenerTodos();
        for (Seguimiento s : lista) {
            if (s.getDescripcion() != null && s.getDescripcion().equals(DESC_PRUEBA)) {
                return s.getIdSeguimiento();
            }
        }
        return -1;
    }

    @Test
    public void testRegistrar() {
        System.out.println("TEST: registrar");
        
        boolean resultado = repositorio.registrar(seguimientoPrueba);
        
        assertTrue(resultado, "El registro debería ser exitoso (true)");
        assertNotEquals(-1, obtenerIdInsertado(), "El seguimiento debería aparecer en la BD");
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: actualizar");
        
        // 1. Registrar
        repositorio.registrar(seguimientoPrueba);
        int id = obtenerIdInsertado();
        
        // 2. Modificar objeto
        seguimientoPrueba.setIdSeguimiento(id); // Setear ID recuperado
        seguimientoPrueba.setPorcentajeAvance(100); // Cambiar avance
        
        // 3. Ejecutar actualización
        boolean resultado = repositorio.actualizar(seguimientoPrueba);
        
        // 4. Verificar
        assertTrue(resultado, "Actualizar debe devolver true");
        
        // Verificamos buscando en la lista (ya que no tienes metodo obtenerPorId activo)
        List<Seguimiento> lista = repositorio.obtenerTodos();
        Seguimiento actualizado = lista.stream()
                .filter(s -> s.getIdSeguimiento() == id)
                .findFirst()
                .orElse(null);
        
        assertNotNull(actualizado);
        assertEquals(100, actualizado.getPorcentajeAvance());
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: obtenerTodos");
        
        repositorio.registrar(seguimientoPrueba);
        
        List<Seguimiento> lista = repositorio.obtenerTodos();
        
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        
        boolean encontrado = lista.stream()
                .anyMatch(s -> s.getDescripcion().equals(DESC_PRUEBA));
        
        assertTrue(encontrado, "El seguimiento insertado debe estar en la lista");
    }

    @Test
    public void testEliminar() {
        System.out.println("TEST: eliminar");
        
        repositorio.registrar(seguimientoPrueba);
        int id = obtenerIdInsertado();
        
        boolean resultado = repositorio.eliminar(id);
        
        assertTrue(resultado, "Eliminar debe devolver true");
        assertEquals(-1, obtenerIdInsertado(), "El seguimiento ya no debe existir");
    }

    /**
     * NOTA: Estos tests validan métodos que consultan la tabla CLIENTE.
     * Si tu intención era validar un Cliente, funciona. 
     * Si era validar un Seguimiento, la lógica en tu Repositorio es incorrecta.
     */
    @Test
    public void testEmailRegistrado() {
        System.out.println("TEST: emailRegistrado (Tabla Cliente)");
        
        // Probamos con un email que seguramente no existe
        boolean resultado = repositorio.emailRegistrado("email_imposible_xyz_123@test.com");
        assertFalse(resultado);
    }

    @Test
    public void testTelefonoRegistrado() {
        System.out.println("TEST: telefonoRegistrado (Tabla Cliente)");
        
        // Probamos con un teléfono inexistente
        boolean resultado = repositorio.telefonoRegistrado("0000000000");
        assertFalse(resultado);
    }
}