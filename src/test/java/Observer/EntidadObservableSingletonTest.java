package Observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntidadObservableSingletonTest {

    @Test
    public void testGetInstanciaNoEsNull() {
        EntidadObservable instancia = EntidadObservableSingleton.getInstancia();
        assertNotNull(instancia);
    }

    @Test
    public void testGetInstanciaSiempreEsLaMisma() {
        EntidadObservable instancia1 = EntidadObservableSingleton.getInstancia();
        EntidadObservable instancia2 = EntidadObservableSingleton.getInstancia();

        assertSame(instancia1, instancia2);
    }

    @Test
    public void testInstanciaEsDeTipoCorrecto() {
        EntidadObservable instancia = EntidadObservableSingleton.getInstancia();
        assertTrue(instancia instanceof EntidadObservable);
    }
}
