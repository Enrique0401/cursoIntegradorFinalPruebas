package Model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testConstructorVacio() {
        Cliente c = new Cliente();
        
        assertEquals(0, c.getIdCliente());
        assertNull(c.getNombreCliente());
        assertNull(c.getRucCliente());
        assertNull(c.getDireccionCliente());
        assertNull(c.getTelefonoCliente());
        assertNull(c.getEmailCliente());
        assertNull(c.getContrasenaCliente());
        assertNull(c.getRol());
        assertNull(c.getFechaRegistro());
    }

    @Test
    public void testConstructorConParametros() {
        Cliente c = new Cliente(
                "Juan",
                "12345678901",
                "Av. Lima 123",
                "987654321",
                "correo@ejemplo.com",
                "12345",
                "admin"
        );
        
        assertEquals("Juan", c.getNombreCliente());
        assertEquals("12345678901", c.getRucCliente());
        assertEquals("Av. Lima 123", c.getDireccionCliente());
        assertEquals("987654321", c.getTelefonoCliente());
        assertEquals("correo@ejemplo.com", c.getEmailCliente());
        assertEquals("12345", c.getContrasenaCliente());
        assertEquals("admin", c.getRol());
        assertNotNull(c.getFechaRegistro()); // Se asigna automáticamente
    }

    @Test
    public void testSettersYGetters() {
        Cliente c = new Cliente();
        
        c.setIdCliente(10);
        c.setNombreCliente("Luis");
        c.setRucCliente("00011122233");
        c.setDireccionCliente("Jr. Flores 321");
        c.setTelefonoCliente("999888777");
        c.setEmailCliente("luis@mail.com");
        c.setContrasenaCliente("pass");
        c.setRol("cliente");

        LocalDateTime fecha = LocalDateTime.now();
        c.setFechaRegistro(fecha);

        assertEquals(10, c.getIdCliente());
        assertEquals("Luis", c.getNombreCliente());
        assertEquals("00011122233", c.getRucCliente());
        assertEquals("Jr. Flores 321", c.getDireccionCliente());
        assertEquals("999888777", c.getTelefonoCliente());
        assertEquals("luis@mail.com", c.getEmailCliente());
        assertEquals("pass", c.getContrasenaCliente());
        assertEquals("cliente", c.getRol());
        assertEquals(fecha, c.getFechaRegistro());
    }

    @Test
    public void testMostrarInfo() {
        Cliente c = new Cliente(
                "Ana",
                "55566677788",
                "Calle Sol 321",
                "912345678",
                "ana@mail.com",
                "abc123",
                "usuario"
        );

        String esperado = "Cliente: Ana (ana@mail.com) - Rol: usuario";
        assertEquals(esperado, c.mostrarInfo());
    }
}
