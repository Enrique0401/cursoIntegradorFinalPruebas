package Pantallas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NewLoginTest {
    
    public NewLoginTest() {
    }

    @Test
    public void testInicializacionLogin() {
        System.out.println("TEST: Inicialización de Login (Smoke Test)");

        // 1. Evitar errores si no hay pantalla (Entornos de servidor)
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar la ventana
            // Esto ejecutará initComponents() y validacion() (Conexión a BD)
            NewLogin login = new NewLogin();
            
            // 3. Validaciones básicas
            assertNotNull(login, "El objeto Login no se pudo crear (posible error de imágenes o componentes)");
            
            // 4. Probar visibilidad
            // Si hay un error grave en el hilo de Swing, esto suele fallar
            login.setVisible(true);
            assertTrue(login.isVisible(), "La ventana de Login debería ser visible");
            
            // 5. Verificar títulos (asegura que los componentes se iniciaron)
            assertEquals("Moroni | Login", login.getTitle());
            
            // 6. Cerrar para limpiar memoria
            login.dispose();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error crítico al iniciar el Login. \n"
                    + "Posibles causas: \n"
                    + "1. Base de datos apagada (Revisa validacion()). \n"
                    + "2. Ruta de imagen '/Imagenes/logo.png' no encontrada. \n"
                    + "Detalle: " + e.getMessage());
        }
    }
}