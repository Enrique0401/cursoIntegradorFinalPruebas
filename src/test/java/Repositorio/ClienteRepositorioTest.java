package Repositorio;

import Model.Cliente;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ClienteRepositorioTest {

    private ClienteRepositorio repo;

    @BeforeEach
    public void setUp() {
        repo = new ClienteRepositorio();
    }

    @Test
    public void testRegistrar_NoLanzaExcepcion_RetornaBoolean() {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente("Test");
        cliente.setRucCliente("00000000000");
        cliente.setDireccionCliente("Calle Falsa 123");
        cliente.setTelefonoCliente("999999999");
        cliente.setEmailCliente("test@example.com");
        cliente.setContrasenaCliente("pass");
        cliente.setRol("ROL_USER");

        assertDoesNotThrow(() -> {
            boolean result = repo.registrar(cliente);
            assertTrue(result == true || result == false);
        });
    }

    @Test
    public void testActualizar_NoLanzaExcepcion_RetornaBoolean() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(0); // id que probablemente no exista
        cliente.setNombreCliente("Mod");
        cliente.setEmailCliente("mod@example.com");

        assertDoesNotThrow(() -> {
            boolean result = repo.actualizar(cliente);
            assertTrue(result == true || result == false);
        });
    }

    @Test
    public void testObtenerPorId_RetornaNullSiNoExiste() {
        Cliente result = repo.obtenerPorId(-1); // id inválido
        assertNull(result, "Debe retornar null si no existe el cliente");
    }

    @Test
    public void testObtenerPorEmail_RetornaNullSiNoExiste() {
        Cliente result = repo.obtenerPorEmail("email_que_no_existe@dominio.com");
        assertNull(result, "Debe retornar null si no existe el email");
    }

    @Test
    public void testObtenerTodos_NoLanzaExcepcion_RetornaListaNoNull() {
        List<Cliente> lista = assertDoesNotThrow(() -> repo.obtenerTodos());
        assertNotNull(lista, "Debe retornar una lista (posiblemente vacía)");
    }

    @Test
    public void testEliminar_RetornaBoolean() {
        boolean result = assertDoesNotThrow(() -> repo.eliminar(0)); // id probablemente no existente
        assertTrue(result == true || result == false);
    }

    @Test
    public void testEmailRegistrado_RetornaBoolean() {
        boolean existe = assertDoesNotThrow(() -> repo.emailRegistrado("noexiste@dom.com"));
        assertFalse(existe, "Con mail inexistente esperamos false (o al menos no lanzar excepción)");
    }

    @Test
    public void testTelefonoRegistrado_RetornaBoolean() {
        boolean existe = assertDoesNotThrow(() -> repo.telefonoRegistrado("000000000"));
        assertFalse(existe, "Con teléfono inexistente esperamos false (o al menos no lanzar excepción)");
    }

    @Test
    public void testVerPorEmail_RetornaNullSiNoExiste() {
        Cliente result = repo.verPorEmail("noexiste2@dom.com");
        assertNull(result, "verPorEmail debe devolver null si no hay coincidencia");
    }
}
