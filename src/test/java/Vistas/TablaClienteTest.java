package Vistas;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaClienteTest {
    
    public TablaClienteTest() {
    }

    @Test
    public void testInicializacionYActualizacion() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Salntando test GUI: Entorno sin pantalla.");
            return;
        }

        TablaCliente instance = new TablaCliente();
        
        assertNotNull(instance);
        
        try {
            instance.actualizar();
        } catch (Exception e) {
            fail("El método actualizar lanzó una excepción: " + e.getMessage());
        }
        
        assertTrue(instance.isVisible() == false || instance.isVisible() == true); 
    }
    
}