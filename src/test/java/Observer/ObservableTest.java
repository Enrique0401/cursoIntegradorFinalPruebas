package Observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObservableTest {

    @Test
    public void testAgregarObservador() {
        EntidadObservable observable = new EntidadObservable();

        Observador o = () -> {}; // observador dummy
        observable.agregarObservador(o);

        assertEquals(1, observable.getCantidadObservadores());
    }

    @Test
    public void testEliminarObservador() {
        EntidadObservable observable = new EntidadObservable();

        Observador o = () -> {};
        observable.agregarObservador(o);
        observable.eliminarObservador(o);

        assertEquals(0, observable.getCantidadObservadores());
    }

    @Test
    public void testNotificarObservadores() {
        EntidadObservable observable = new EntidadObservable();

        final boolean[] notificado = {false};

        Observador o = () -> notificado[0] = true;

        observable.agregarObservador(o);
        observable.notificarObservadores();

        assertTrue(notificado[0]);
    }
}
