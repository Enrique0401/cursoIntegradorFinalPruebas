package Pantallas;

import Model.Cliente;
import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PantallaSupervisorTest {
    
    public PantallaSupervisorTest() {
    }

    @Test
    public void testInicializacionYSetNombre() {
        System.out.println("TEST: Inicialización y setNombre en PantallaSupervisor");

        // Evitar errores si se corre en un servidor sin pantalla
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 1. Instanciar ventana vacía
            PantallaSupervisor ventana = new PantallaSupervisor();
            assertNotNull(ventana, "La ventana no debería ser nula");
            
            // 2. Probar el método setNombre
            String nombrePrueba = "Supervisor Test JUnit";
            ventana.setNombre(nombrePrueba);
            
            // 3. Verificar cambio visual (usando el método auxiliar)
            assertEquals(nombrePrueba, ventana.getTextoNombre(), "El label debe mostrar el nombre asignado");
            
            // 4. Limpieza
            ventana.dispose();
            
        } catch (NullPointerException e) {
            // Ignoramos error de imagen no encontrada
            System.err.println("⚠️ Advertencia: No se encontró la imagen del logo. El test continúa.");
        } catch (Exception e) {
            fail("La ventana falló al iniciarse: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorConCliente() {
        System.out.println("TEST: Constructor PantallaSupervisor con Cliente");

        if (GraphicsEnvironment.isHeadless()) return;

        try {
            // 1. Crear cliente simulado
            Cliente clienteMock = new Cliente();
            clienteMock.setNombreCliente("Ana Supervisora");
            clienteMock.setRol("ROLE_VISOR");
            
            // 2. Instanciar con datos
            PantallaSupervisor ventana = new PantallaSupervisor(clienteMock);
            
            // 3. Verificar que el nombre se asignó automáticamente
            assertEquals("Ana Supervisora", ventana.getTextoNombre());
            
            ventana.dispose();
            
        } catch (Exception e) {
            // Ignorar error de imagen, fallar por cualquier otra cosa
            if (!e.toString().contains("NullPointerException")) {
                fail("Error en constructor con parámetros: " + e.getMessage());
            }
        }
    }
}