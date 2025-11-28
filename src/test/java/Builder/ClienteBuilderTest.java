package Builder;

import Model.Cliente;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteBuilderTest {

    @Test
    public void testBuilderSettersReturnSameInstance() {
        ClienteBuilder builder = new ClienteBuilder();

        ClienteBuilder b1 = builder.conIdCliente(10);
        ClienteBuilder b2 = builder.conNombreCliente("Juan");
        ClienteBuilder b3 = builder.conRucCliente("12345678901");
        ClienteBuilder b4 = builder.conDireccionCliente("Av. Siempre Viva");
        ClienteBuilder b5 = builder.conTelefonoCliente("987654321");
        ClienteBuilder b6 = builder.conEmailCliente("correo@gmail.com");
        ClienteBuilder b7 = builder.conContrasenaCliente("1234");
        ClienteBuilder b8 = builder.conRol("Cliente");
        ClienteBuilder b9 = builder.conFechaRegistro(LocalDateTime.now());

        // Todas las llamadas deben devolver el mismo builder (fluidez)
        assertSame(builder, b1);
        assertSame(builder, b2);
        assertSame(builder, b3);
        assertSame(builder, b4);
        assertSame(builder, b5);
        assertSame(builder, b6);
        assertSame(builder, b7);
        assertSame(builder, b8);
        assertSame(builder, b9);
    }

    @Test
    public void testBuildCreatesClienteCorrectly() {
        LocalDateTime fecha = LocalDateTime.now();

        Cliente cliente = new ClienteBuilder()
                .conIdCliente(1)
                .conNombreCliente("Carlos")
                .conRucCliente("11122233344")
                .conDireccionCliente("Calle Falsa 123")
                .conTelefonoCliente("999888777")
                .conEmailCliente("carlos@test.com")
                .conContrasenaCliente("clave123")
                .conRol("ADMIN")
                .conFechaRegistro(fecha)
                .build();

        assertEquals(1, cliente.getIdCliente());
        assertEquals("Carlos", cliente.getNombreCliente());
        assertEquals("11122233344", cliente.getRucCliente());
        assertEquals("Calle Falsa 123", cliente.getDireccionCliente());
        assertEquals("999888777", cliente.getTelefonoCliente());
        assertEquals("carlos@test.com", cliente.getEmailCliente());
        assertEquals("clave123", cliente.getContrasenaCliente());
        assertEquals("ADMIN", cliente.getRol());
        assertEquals(fecha, cliente.getFechaRegistro());
    }

    @Test
    public void testBuildAssignsCurrentDateIfFechaNull() {
        Cliente cliente = new ClienteBuilder()
                .conIdCliente(2)
                .conNombreCliente("Ana")
                .build();

        assertNotNull(cliente.getFechaRegistro());
    }
}
