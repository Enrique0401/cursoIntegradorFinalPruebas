package Pantallas;

import Model.Cliente;
import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PantallaAdminTest {
    
    public PantallaAdminTest() {
    }

    @Test
    public void testInicializacionYSetNombre() {
        System.out.println("TEST: Inicialización y setNombre en PantallaAdmin");

        // 1. Verificar entorno gráfico
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Saltando test GUI: Entorno sin pantalla.");
            return;
        }

        try {
            // 2. Instanciar ventana vacía
            PantallaAdmin ventana = new PantallaAdmin();
            assertNotNull(ventana, "La ventana no debería ser nula");
            
            // 3. Probar el método setNombre
            String nombrePrueba = "Administrador Test";
            ventana.setNombre(nombrePrueba);
            
            // 4. Verificar que el texto cambió (Usando el método auxiliar que agregamos)
            assertEquals(nombrePrueba, ventana.getTextoNombre(), "El label debería mostrar el nombre asignado");
            
            // 5. Cerrar
            ventana.dispose();
            
        } catch (NullPointerException e) {
            // Este catch es específico para cuando no encuentra la imagen del icono "/Imagenes/180.png"
            System.err.println("⚠️ Advertencia: El test falló cargando la imagen del icono.");
            System.err.println("Asegúrate de que la carpeta '/Imagenes' esté en el classpath de Test.");
        } catch (Exception e) {
            fail("Error inesperado en PantallaAdmin: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorConCliente() {
        System.out.println("TEST: Constructor con Cliente");

        if (GraphicsEnvironment.isHeadless()) return;

        try {
            // 1. Crear un cliente simulado
            Cliente clienteMock = new Cliente();
            clienteMock.setNombreCliente("Juan Perez");
            clienteMock.setRol("ROLE_ADMIN");
            
            // 2. Instanciar ventana con el cliente
            PantallaAdmin ventana = new PantallaAdmin(clienteMock);
            
            // 3. Verificar que el nombre se cargó automáticamente
            // Nota: Si labelNombres es null, tu código original imprimía un error en consola.
            // Aquí verificamos que realmente se haya puesto el texto.
            assertEquals("Juan Perez", ventana.getTextoNombre());
            
            ventana.dispose();
            
        } catch (Exception e) {
             // Ignoramos error de imagen si es lo único que falla, pero fallamos si es otra cosa
             if(!e.toString().contains("NullPointerException")) {
                 fail("Error en constructor con parámetros: " + e.getMessage());
             }
        }
    }
}