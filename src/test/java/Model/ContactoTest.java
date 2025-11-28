package Model;

import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoTest {

    @Test
    public void testGetAndSetIdContacto() {
        Contacto contacto = new Contacto();
        contacto.setIdContacto(10);
        assertEquals(10, contacto.getIdContacto());
    }

    @Test
    public void testGetAndSetNombre() {
        Contacto contacto = new Contacto();
        contacto.setNombre("Juan Perez");
        assertEquals("Juan Perez", contacto.getNombre());
    }

    @Test
    public void testGetAndSetEmpresa() {
        Contacto contacto = new Contacto();
        contacto.setEmpresa("TechCorp");
        assertEquals("TechCorp", contacto.getEmpresa());
    }

    @Test
    public void testGetAndSetEmail() {
        Contacto contacto = new Contacto();
        contacto.setEmail("correo@ejemplo.com");
        assertEquals("correo@ejemplo.com", contacto.getEmail());
    }

    @Test
    public void testGetAndSetTelefono() {
        Contacto contacto = new Contacto();
        contacto.setTelefono("987654321");
        assertEquals("987654321", contacto.getTelefono());
    }

    @Test
    public void testGetAndSetServicio() {
        Contacto contacto = new Contacto();
        contacto.setServicio("Desarrollo Web");
        assertEquals("Desarrollo Web", contacto.getServicio());
    }

    @Test
    public void testGetAndSetMensaje() {
        Contacto contacto = new Contacto();
        contacto.setMensaje("Necesito una cotización.");
        assertEquals("Necesito una cotización.", contacto.getMensaje());
    }

    @Test
    public void testGetAndSetFechaEnvio() {
        Contacto contacto = new Contacto();
        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        contacto.setFechaEnvio(fecha);
        assertEquals(fecha, contacto.getFechaEnvio());
    }
}
