package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaComentariosTest {
    
    public TablaComentariosTest() {
    }

    @Test
    public void testInicializacionTabla() {
        System.out.println("TEST: Inicialización de TablaComentarios");

        // 1. Verificación de entorno gráfico
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar el panel
            // Esto intentará conectar a la BD y traer los Contactos
            TablaComentarios tabla = new TablaComentarios();
            
            // 3. Verificar que el objeto existe
            assertNotNull(tabla, "El panel de comentarios no se pudo crear.");
            
            // 4. Verificar que no explota al repintar
            tabla.repaint();
            
        } catch (NoClassDefFoundError e) {
            // Este error saldrá si te falta el JAR de iTextPDF en "Test Libraries"
            fail("CRÍTICO: No se encuentra la librería iTextPDF en las librerías de TEST.\n" + e.getMessage());
        } catch (Exception e) {
            fail("Falló la inicialización. \n"
                    + "Causa probable: Base de datos apagada o tabla 'contacto' no existe.\n"
                    + "Error: " + e.getMessage());
        }
    }
}