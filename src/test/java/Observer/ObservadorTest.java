package Observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObservadorTest {
    
    public ObservadorTest() {
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: Contrato de interfaz Observador");

        ObservadorImpl observador = new ObservadorImpl();

        assertFalse(observador.fueActualizado, "Inicialmente no debería estar actualizado");

        observador.actualizar();

        assertTrue(observador.fueActualizado, "El método actualizar() debió cambiar el estado a true");
    }

    public class ObservadorImpl implements Observador {
        public boolean fueActualizado = false;

        @Override
        public void actualizar() {
            this.fueActualizado = true;
        }
    }
}