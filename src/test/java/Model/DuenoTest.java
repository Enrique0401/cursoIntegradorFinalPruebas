package Model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuenoTest {

    @Test
    public void testSetAndGetIdDueno() {
        Dueno dueno = new Dueno();
        dueno.setIdDueno(5);
        assertEquals(5, dueno.getIdDueno());
    }

    @Test
    public void testSetAndGetNombreDueno() {
        Dueno dueno = new Dueno();
        dueno.setNombreDueno("Carlos Ramirez");
        assertEquals("Carlos Ramirez", dueno.getNombreDueno());
    }

    @Test
    public void testSetAndGetDniDueno() {
        Dueno dueno = new Dueno();
        dueno.setDniDueno("12345678");
        assertEquals("12345678", dueno.getDniDueno());
    }

    @Test
    public void testSetAndGetTelefonoDueno() {
        Dueno dueno = new Dueno();
        dueno.setTelefonoDueno("987654321");
        assertEquals("987654321", dueno.getTelefonoDueno());
    }

    @Test
    public void testSetAndGetEmailDueno() {
        Dueno dueno = new Dueno();
        dueno.setEmailDueno("dueno@correo.com");
        assertEquals("dueno@correo.com", dueno.getEmailDueno());
    }

    @Test
    public void testSetAndGetContrasenaDueno() {
        Dueno dueno = new Dueno();
        dueno.setContrasenaDueno("password123");
        assertEquals("password123", dueno.getContrasenaDueno());
    }

    @Test
    public void testSetAndGetFechaRegistro() {
        Dueno dueno = new Dueno();
        LocalDateTime fecha = LocalDateTime.now();
        dueno.setFechaRegistro(fecha);
        assertEquals(fecha, dueno.getFechaRegistro());
    }

    @Test
    public void testToString() {
        Dueno dueno = new Dueno();
        dueno.setNombreDueno("Luis Torres");
        dueno.setDniDueno("11112222");

        String esperado = "Dueño: Luis Torres (DNI: 11112222)";
        assertEquals(esperado, dueno.toString());
    }

    @Test
    public void testConstructorConParametros() {
        LocalDateTime fecha = LocalDateTime.now();
        Dueno dueno = new Dueno(1, "Mario López", "87654321", "999888777",
                "mario@correo.com", "pass123", fecha);

        assertEquals(1, dueno.getIdDueno());
        assertEquals("Mario López", dueno.getNombreDueno());
        assertEquals("87654321", dueno.getDniDueno());
        assertEquals("999888777", dueno.getTelefonoDueno());
        assertEquals("mario@correo.com", dueno.getEmailDueno());
        assertEquals("pass123", dueno.getContrasenaDueno());
        assertEquals(fecha, dueno.getFechaRegistro());
    }
}
