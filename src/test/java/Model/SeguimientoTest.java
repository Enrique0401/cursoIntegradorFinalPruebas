package Model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeguimientoTest {

    @Test
    public void testSetAndGetIdSeguimiento() {
        Seguimiento s = new Seguimiento();
        s.setIdSeguimiento(5);
        assertEquals(5, s.getIdSeguimiento());
    }

    @Test
    public void testSetAndGetDescripcion() {
        Seguimiento s = new Seguimiento();
        s.setDescripcion("Avance en el módulo de reportes");
        assertEquals("Avance en el módulo de reportes", s.getDescripcion());
    }

    @Test
    public void testSetAndGetFechaAvance() {
        Seguimiento s = new Seguimiento();
        LocalDateTime fecha = LocalDateTime.now();
        s.setFechaAvance(fecha);
        assertEquals(fecha, s.getFechaAvance());
    }

    @Test
    public void testSetAndGetPorcentajeAvance() {
        Seguimiento s = new Seguimiento();
        s.setPorcentajeAvance(60);
        assertEquals(60, s.getPorcentajeAvance());
    }

    @Test
    public void testSetAndGetIdProyecto() {
        Seguimiento s = new Seguimiento();
        s.setIdProyecto(12);
        assertEquals(12, s.getIdProyecto());
    }

    @Test
    public void testConstructorConParametros() {
        LocalDateTime fecha = LocalDateTime.now();

        Seguimiento s = new Seguimiento(
                1,
                "Pruebas unitarias completadas",
                fecha,
                80,
                4
        );

        assertEquals(1, s.getIdSeguimiento());
        assertEquals("Pruebas unitarias completadas", s.getDescripcion());
        assertEquals(fecha, s.getFechaAvance());
        assertEquals(80, s.getPorcentajeAvance());
        assertEquals(4, s.getIdProyecto());
    }
}
