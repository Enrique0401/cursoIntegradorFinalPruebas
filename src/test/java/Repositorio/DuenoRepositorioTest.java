package Repositorio;

import Builder.DuenoBuilder;
import Model.Dueno;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuenoRepositorioTest {
    
    private DuenoRepositorio repositorio;
    private Dueno duenoPrueba;
    
    // Datos de prueba constantes para facilitar la limpieza
    private final String EMAIL_PRUEBA = "test_junit@correo.com";
    private final String DNI_PRUEBA = "99999999";

    public DuenoRepositorioTest() {
    }
    
    @BeforeEach
    public void setUp() {
        repositorio = new DuenoRepositorio();
        
        // 1. Construimos un objeto Dueño para las pruebas usando tu Builder
        duenoPrueba = new DuenoBuilder()
                .conNombreDueno("Usuario Test JUnit")
                .conDniDueno(DNI_PRUEBA)
                .conTelefonoDueno("123456789")
                .conEmailDueno(EMAIL_PRUEBA)
                .conContrasenaDueno("123456")
                .conFechaRegistro(LocalDateTime.now())
                .build();
                
        // Aseguramos que la BD esté limpia de este usuario antes de empezar
        limpiarDatosDePrueba();
    }
    
    @AfterEach
    public void tearDown() {
        // Borramos el usuario de prueba después de cada test para no ensuciar la BD
        limpiarDatosDePrueba();
    }

    /**
     * Método auxiliar para borrar el usuario de prueba de la BD
     */
    private void limpiarDatosDePrueba() {
        Dueno existente = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        if (existente != null) {
            repositorio.eliminar(existente.getIdDueno());
        }
    }

    @Test
    public void testRegistrar() {
        System.out.println("TEST: registrar");
        
        // Ejecución
        boolean resultado = repositorio.registrar(duenoPrueba);
        
        // Verificación
        assertTrue(resultado, "El registro debería devolver true");
        assertTrue(repositorio.emailRegistrado(EMAIL_PRUEBA), "El email debería existir en la BD");
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: actualizar");
        
        // 1. Primero lo registramos para poder actualizarlo
        repositorio.registrar(duenoPrueba);
        Dueno duenoGuardado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        assertNotNull(duenoGuardado, "El dueño debe existir para actualizarse");

        // 2. Modificamos datos
        duenoGuardado.setNombreDueno("Nombre Actualizado");
        duenoGuardado.setTelefonoDueno("987654321");

        // 3. Ejecutamos actualizar
        boolean resultado = repositorio.actualizar(duenoGuardado);

        // 4. Verificamos
        assertTrue(resultado, "La actualización debería devolver true");
        
        Dueno duenoActualizado = repositorio.obtenerPorId(duenoGuardado.getIdDueno());
        assertEquals("Nombre Actualizado", duenoActualizado.getNombreDueno());
        assertEquals("987654321", duenoActualizado.getTelefonoDueno());
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("TEST: obtenerPorId");
        
        // Pre-condición: insertar dato
        repositorio.registrar(duenoPrueba);
        Dueno original = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        
        // Ejecución
        Dueno encontrado = repositorio.obtenerPorId(original.getIdDueno());
        
        // Verificación
        assertNotNull(encontrado);
        assertEquals(original.getIdDueno(), encontrado.getIdDueno());
        assertEquals(original.getEmailDueno(), encontrado.getEmailDueno());
    }

    @Test
    public void testObtenerPorEmail() {
        System.out.println("TEST: obtenerPorEmail");
        
        repositorio.registrar(duenoPrueba);
        
        Dueno resultado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        
        assertNotNull(resultado);
        assertEquals(duenoPrueba.getDniDueno(), resultado.getDniDueno());
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: obtenerTodos");
        
        repositorio.registrar(duenoPrueba);
        
        List<Dueno> lista = repositorio.obtenerTodos();
        
        assertNotNull(lista);
        assertTrue(lista.size() > 0, "La lista debería tener al menos un elemento");
        
        // Verificamos si nuestra lista contiene al usuario de prueba (usando stream para buscar)
        boolean encontrado = lista.stream()
                .anyMatch(d -> d.getEmailDueno().equals(EMAIL_PRUEBA));
        
        assertTrue(encontrado, "El usuario insertado debería estar en la lista de todos");
    }

    @Test
    public void testEliminar() {
        System.out.println("TEST: eliminar");
        
        // 1. Registrar
        repositorio.registrar(duenoPrueba);
        Dueno duenoGuardado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        
        // 2. Eliminar
        boolean resultado = repositorio.eliminar(duenoGuardado.getIdDueno());
        
        // 3. Verificar
        assertTrue(resultado, "Eliminar debería devolver true");
        assertNull(repositorio.obtenerPorId(duenoGuardado.getIdDueno()), "El usuario ya no debería existir");
    }

    @Test
    public void testValidacionesExistencia() {
        System.out.println("TEST: validaciones (email y dni)");
        
        repositorio.registrar(duenoPrueba);
        
        // Casos positivos (existen)
        assertTrue(repositorio.emailRegistrado(EMAIL_PRUEBA));
        assertTrue(repositorio.dniRegistrado(DNI_PRUEBA));
        
        // Casos negativos (no existen)
        assertFalse(repositorio.emailRegistrado("noexiste@fake.com"));
        assertFalse(repositorio.dniRegistrado("00000000"));
    }
}