package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaIncidenciasTest {
    
    public TablaIncidenciasTest() {
    }

    @Test
    public void testInicializacionYCarga() {
        System.out.println("TEST: Inicialización de TablaIncidencias (Smoke Test)");

        // Evitar errores en servidores sin pantalla
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        try {
            // 1. Instanciar (Conecta a BD y carga Proyectos e Incidencias)
            TablaIncidencias tabla = new TablaIncidencias();
            
            assertNotNull(tabla, "El panel no debería ser nulo");

            // 2. Verificar que el ComboBox se llenó
            // Debería tener al menos 1 elemento ("-- Todos los proyectos --")
            assertTrue(tabla.getCantidadItemsCombo() > 0, 
                    "El ComboBox de proyectos debería tener ítems cargados.");

            // 3. Probar el método del Observer
            tabla.actualizar();
            // Si llega aquí sin error, la actualización funciona.
            
            // 4. Verificar repintado
            tabla.repaint();

        } catch (Exception e) {
            fail("Falló la inicialización de TablaIncidencias.\n"
                    + "Causa probable: Base de datos apagada o tablas vacías.\n"
                    + "Error: " + e.getMessage());
        }
    }
}