package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaSeguimientosTest {
    
    public TablaSeguimientosTest() {
    }

    @Test
    public void testInicializacionYCarga() {
        System.out.println("TEST: Inicialización de TablaSeguimientos (Smoke Test)");

        // 1. Evitar ejecución si no hay entorno gráfico
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar (Carga Seguimientos y Proyectos desde BD)
            TablaSeguimientos tabla = new TablaSeguimientos();
            
            assertNotNull(tabla, "El panel no debería ser nulo");

            // 3. Verificar que el ComboBox de proyectos se llenó
            // Debería tener al menos 1 elemento ("-- Seleccione un Proyecto --")
            assertTrue(tabla.getCantidadProyectosCombo() > 0, 
                    "El ComboBox de proyectos debería tener ítems cargados.");

            // 4. Probar el ciclo de actualización (Observer)
            tabla.actualizar();
            
            // 5. Verificar integridad visual básica
            tabla.repaint();

        } catch (Exception e) {
            fail("Falló la inicialización de TablaSeguimientos.\n"
                    + "Causa probable: Base de datos apagada.\n"
                    + "Error: " + e.getMessage());
        }
    }
}