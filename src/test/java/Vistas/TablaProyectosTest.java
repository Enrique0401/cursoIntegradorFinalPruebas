package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaProyectosTest {
    
    public TablaProyectosTest() {
    }

    @Test
    public void testInicializacionYCarga() {
        System.out.println("TEST: Inicialización de TablaProyectos (Smoke Test)");

        // 1. Evitar ejecución en servidores sin pantalla
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar el panel
            // Esto ejecuta: Constructor -> Conexión BD -> Cargar Clientes -> Cargar Proyectos
            TablaProyectos tabla = new TablaProyectos();
            
            // 3. Verificaciones básicas
            assertNotNull(tabla, "El panel TablaProyectos no debería ser nulo");

            // 4. Verificar que el ComboBox de clientes se llenó
            // Debería tener al menos 1 elemento ("Todos los clientes")
            assertTrue(tabla.getCantidadClientesCombo() > 0, 
                    "El ComboBox de clientes debería tener ítems cargados.");

            // 5. Probar el método actualizar (Simular evento Observer)
            tabla.actualizar();
            
            // 6. Verificar repintado final
            tabla.repaint();

        } catch (Exception e) {
            fail("Falló la inicialización de TablaProyectos.\n"
                    + "Causa probable: Base de datos apagada o tablas 'cliente'/'proyecto' vacías/inexistentes.\n"
                    + "Error: " + e.getMessage());
        }
    }
}