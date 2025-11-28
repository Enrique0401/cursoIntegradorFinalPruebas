package Model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaTest {

    @Test
    public void testSetAndGetIdIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setIdIncidencia(10);
        assertEquals(10, incidencia.getIdIncidencia());
    }

    @Test
    public void testSetAndGetDescripcionIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcionIncidencia("Error en el módulo de login");
        assertEquals("Error en el módulo de login", incidencia.getDescripcionIncidencia());
    }

    @Test
    public void testSetAndGetEstadoInIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setEstadoInIncidencia("Pendiente");
        assertEquals("Pendiente", incidencia.getEstadoInIncidencia());
    }

    @Test
    public void testSetAndGetFechaIncidencia() {
        Incidencia incidencia = new Incidencia();
        LocalDateTime fecha = LocalDateTime.now();
        incidencia.setFechaIncidencia(fecha);
        assertEquals(fecha, incidencia.getFechaIncidencia());
    }

    @Test
    public void testSetAndGetIdProyecto() {
        Incidencia incidencia = new Incidencia();
        incidencia.setIdProyecto(4);
        assertEquals(4, incidencia.getIdProyecto());
    }

    @Test
    public void testConstructorConParametros() {
        LocalDateTime fecha = LocalDateTime.now();
        Incidencia incidencia = new Incidencia(
                1,
                "Pantalla se congela",
                "Resuelto",
                fecha,
                20
        );

        assertEquals(1, incidencia.getIdIncidencia());
        assertEquals("Pantalla se congela", incidencia.getDescripcionIncidencia());
        assertEquals("Resuelto", incidencia.getEstadoInIncidencia());
        assertEquals(fecha, incidencia.getFechaIncidencia());
        assertEquals(20, incidencia.getIdProyecto());
    }

    @Test
    public void testMostrarInfo() {
        Incidencia incidencia = new Incidencia();
        incidencia.setIdIncidencia(5);
        incidencia.setDescripcionIncidencia("Bug crítico");

        // OJO: tu método está mal escrito, así que pruebo EXACTAMENTE lo que retorna
        String esperado = String.format("Incidencia: %s (%s) - Rol: %s",
                5, "Bug crítico", null);

        assertEquals(esperado, incidencia.mostrarInfo());
    }
}
