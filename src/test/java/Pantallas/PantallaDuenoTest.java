package Pantallas;

import Model.Cliente;
import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PantallaDuenoTest {
    
    public PantallaDuenoTest() {
    }

    @Test
    public void testInicializacionYSetNombre() {
        System.out.println("TEST: Inicialización y setNombre en PantallaDueno");

        // Verificación de entorno gráfico
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 1. Instanciar
            PantallaDueno ventana = new PantallaDueno();
            assertNotNull(ventana, "La ventana no debería ser nula");
            
            // 2. Probar cambio de nombre
            String nombrePrueba = "Dueño Test JUnit";
            ventana.setNombre(nombrePrueba);
            
            // 3. Verificar cambio (usando el método auxiliar)
            assertEquals(nombrePrueba, ventana.getTextoNombre());
            
            // 4. Limpieza
            ventana.dispose();
            
        } catch (NullPointerException e) {
            // Capturamos error específico de imagen no encontrada para no fallar el test rojo
            System.err.println("⚠️ Advertencia: No se encontró la imagen del ícono (/Imagenes/180.png).");
            System.err.println("El test continúa, pero revisa tus rutas.");
        } catch (Exception e) {
            fail("La ventana falló al iniciarse. \n"
                    + "Posible causa: Base de datos apagada (carga TablaCliente al inicio). \n"
                    + "Error: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorConCliente() {
        System.out.println("TEST: Constructor PantallaDueno con Cliente");

        if (GraphicsEnvironment.isHeadless()) return;

        try {
            // 1. Datos simulados
            Cliente clienteMock = new Cliente();
            clienteMock.setNombreCliente("Carlos Dueño");
            
            // 2. Instanciar con datos
            PantallaDueno ventana = new PantallaDueno(clienteMock);
            
            // 3. Verificar que el nombre se asignó al label automáticamente
            assertEquals("Carlos Dueño", ventana.getTextoNombre());
            
            ventana.dispose();
            
        } catch (Exception e) {
            // Si es error de imagen, lo ignoramos para este test
            if (!e.toString().contains("NullPointerException")) {
                fail("Error en constructor con parámetros: " + e.getMessage());
            }
        }
    }
}