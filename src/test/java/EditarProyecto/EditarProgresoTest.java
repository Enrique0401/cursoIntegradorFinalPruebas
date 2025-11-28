package EditarProyecto;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EditarProgresoTest {
    
    public EditarProgresoTest() {
    }

    @Test
    public void testInicializacionValores() {
        System.out.println("TEST: Inicialización de EditarProgreso (Sincronización)");

        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        // CASO 1: Valor intermedio
        int progresoPrueba = 45;
        int idFake = 99;

        // Instanciamos
        EditarProgreso ventana = new EditarProgreso(idFake, progresoPrueba);
        
        assertNotNull(ventana);

        // Verificamos que el Spinner tenga el 45
        assertEquals(progresoPrueba, ventana.getValorSpinner(), 
                "El Spinner debería iniciar con el valor pasado al constructor");

        // Verificamos que el Slider tenga el 45
        assertEquals(progresoPrueba, ventana.getValorSlider(), 
                "El Slider debería iniciar con el valor pasado al constructor");
        
        // Cerramos
        ventana.dispose();
    }

    @Test
    public void testValoresLimite() {
        System.out.println("TEST: Valores límite (0 y 100)");
        
        if (GraphicsEnvironment.isHeadless()) return;

        // CASO 2: Límites
        EditarProgreso vCero = new EditarProgreso(1, 0);
        assertEquals(0, vCero.getValorSpinner());
        assertEquals(0, vCero.getValorSlider());
        vCero.dispose();

        EditarProgreso vCien = new EditarProgreso(1, 100);
        assertEquals(100, vCien.getValorSpinner());
        assertEquals(100, vCien.getValorSlider());
        vCien.dispose();
    }
}