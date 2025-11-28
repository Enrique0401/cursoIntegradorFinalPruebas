package Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntidadObservableTest {

    private EntidadObservable instance;

    public EntidadObservableTest() {
    }

    @BeforeEach
    public void setUp() {
        // Inicializamos una nueva instancia antes de cada test
        instance = new EntidadObservable();
    }

    /**
     * Clase auxiliar (Mock) para verificar si recibe notificaciones.
     */
    class ObservadorMock implements Observador {
        boolean fueActualizado = false;

        @Override
        public void actualizar() {
            this.fueActualizado = true;
        }
    }

    @Test
    public void testAgregarObservador() {
        Observador o = new ObservadorMock();
        
        instance.agregarObservador(o);
        
        assertEquals(1, instance.getCantidadObservadores());
    }
    
    @Test
    public void testAgregarObservadorNulo() {
        // Tu código tiene una protección contra nulls, verificamos que funcione
        instance.agregarObservador(null);
        
        assertEquals(0, instance.getCantidadObservadores());
    }
    
    @Test
    public void testNoAgregarDuplicados() {
        Observador o = new ObservadorMock();
        
        instance.agregarObservador(o);
        instance.agregarObservador(o); // Intentamos agregar el mismo objeto otra vez
        
        // Debería seguir siendo 1, ya que tu código usa !observadores.contains(o)
        assertEquals(1, instance.getCantidadObservadores());
    }

    @Test
    public void testEliminarObservador() {
        Observador o = new ObservadorMock();
        instance.agregarObservador(o);
        
        instance.eliminarObservador(o);
        
        assertEquals(0, instance.getCantidadObservadores());
    }

    @Test
    public void testNotificarObservadores() {
        ObservadorMock o1 = new ObservadorMock();
        ObservadorMock o2 = new ObservadorMock();
        
        instance.agregarObservador(o1);
        instance.agregarObservador(o2);
        
        // Ejecutamos la notificación
        instance.notificarObservadores();
        
        // Verificamos que el método actualizar() se ejecutó en ambos objetos
        assertTrue(o1.fueActualizado, "El observador 1 debió ser notificado");
        assertTrue(o2.fueActualizado, "El observador 2 debió ser notificado");
    }

    @Test
    public void testGetCantidadObservadores() {
        assertEquals(0, instance.getCantidadObservadores());
        
        instance.agregarObservador(new ObservadorMock());
        assertEquals(1, instance.getCantidadObservadores());
        
        instance.agregarObservador(new ObservadorMock());
        assertEquals(2, instance.getCantidadObservadores());
    }
}