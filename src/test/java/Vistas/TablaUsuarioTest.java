package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaUsuarioTest {
    
    public TablaUsuarioTest() {
    }

    @Test
    public void testInicializacionYActualizacion() {
        System.out.println("TEST: Inicialización de TablaUsuario (Smoke Test)");

        // 1. Verificación de entorno gráfico (evita errores en servidores)
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar el panel
            // Esto ejecuta: Constructor -> initCompnents -> DB Connect -> cargarUsuarios
            TablaUsuario instance = new TablaUsuario();
            
            // 3. Verificar creación exitosa
            assertNotNull(instance, "El panel TablaUsuario no debería ser nulo.");

            // 4. Probar el método del Observer
            // Esto simula que alguien agregó/borró un usuario y la tabla se refresca
            instance.actualizar();
            
            // 5. Forzar validación visual (asegura que no hay errores de Swing)
            instance.validate();
            instance.repaint();
            
            // Si el código llega hasta aquí sin lanzar excepciones, el test es exitoso.

        } catch (Exception e) {
            e.printStackTrace();
            fail("Falló la inicialización de TablaUsuario.\n"
                    + "Causa probable: Base de datos desconectada o error en la consulta SQL.\n"
                    + "Error detallado: " + e.getMessage());
        }
    }
}