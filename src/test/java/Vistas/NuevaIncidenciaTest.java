package Vistas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.GraphicsEnvironment;

public class NuevaIncidenciaTest {
    
    public NuevaIncidenciaTest() {
    }

    @Test
    public void testInicializacionVentana() {
        System.out.println("TEST: Inicialización de Ventana NuevaIncidencia");

        // Verificamos si el entorno soporta interfaz gráfica (evita errores en servidores sin pantalla)
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Salntando test GUI: Entorno headless detectado.");
            return;
        }

        try {
            // 1. Intentamos instanciar la ventana.
            // Esto ejecutará el constructor, conectará a la BD y cargará los proyectos.
            NuevaIncidencia ventana = new NuevaIncidencia();
            
            // 2. Verificaciones básicas
            assertNotNull(ventana, "La ventana no debería ser nula");
            assertEquals("Pendiente", ventana.getItemComboEstadoInicial(), "El estado inicial debería ser Pendiente (o el primero de tu lista)");
            
            // 3. Probamos visibilidad
            ventana.setVisible(true);
            assertTrue(ventana.isVisible(), "La ventana debería ser visible");
            
            // 4. Cerramos para no dejar ventanas abiertas
            ventana.dispose();
            
        } catch (Exception e) {
            fail("La ventana falló al iniciarse. Posible error de Base de Datos: " + e.getMessage());
        }
    }
}