package Servicio;

import Model.Cliente;
import Repositorio.ClienteRepositorio;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IClienteServiceTest {

    // We use the Interface as the data type, but instantiate the REAL implementation
    private IClienteService service;
    private ClienteRepositorio repositorio;
    private Cliente clientePrueba;
    
    // Variables for unique data to avoid DB conflicts
    private String emailTest;
    private String rucTest;
    private String telefonoTest;

    public IClienteServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        // 1. Instantiate the REAL classes
        repositorio = new ClienteRepositorio();
        service = new ClienteService(repositorio); // Assuming ClienteService implements IClienteService
        
        // 2. Generate random data to ensure the test always runs fresh
        long random = System.currentTimeMillis();
        emailTest = "test_interface_" + random + "@junit.com";
        // RUC (11 digits) and Phone (9 digits starting with 9)
        rucTest = String.valueOf(random).substring(0, 11); 
        telefonoTest = "9" + String.valueOf(random).substring(5, 13);
        
        // 3. Create base object
        clientePrueba = new Cliente();
        clientePrueba.setNombreCliente("Cliente Interface Test");
        clientePrueba.setEmailCliente(emailTest);
        clientePrueba.setTelefonoCliente(telefonoTest);
        clientePrueba.setRucCliente(rucTest);
        clientePrueba.setContrasenaCliente("123456");
    }

    @AfterEach
    public void tearDown() {
        // Attempt to clean up
        try {
            Cliente guardado = repositorio.obtenerPorEmail(emailTest);
            if (guardado != null) {
                repositorio.eliminar(guardado.getIdCliente());
            }
        } catch (Exception e) {
            System.out.println("Could not clean up data (possibly already deleted or FK constraint)");
        }
    }

    @Test
    public void testRegistrar() {
        System.out.println("TEST: Registrar (Please close the JOptionPane)");
        
        boolean result = service.registrar(clientePrueba);
        
        assertTrue(result, "Register should return true");
        assertNotNull(repositorio.obtenerPorEmail(emailTest), "Cliente must exist in DB");
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: Obtener Todos (Close the JOptionPane)");
        
        service.registrar(clientePrueba);
        
        List<Cliente> result = service.obtenerTodos();
        
        assertNotNull(result);
        assertFalse(result.isEmpty(), "List should not be empty");
    }

    @Test
    public void testBuscarPorId() {
        System.out.println("TEST: Buscar Por ID (Close the JOptionPane)");
        
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(emailTest);
        
        Cliente result = service.buscarPorId(guardado.getIdCliente());
        
        assertNotNull(result);
        assertEquals(emailTest, result.getEmailCliente());
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: Actualizar (Close the JOptionPane)");
        
        // 1. Register
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(emailTest);
        
        // 2. Modify
        guardado.setNombreCliente("Nombre Modificado Interface");
        
        // 3. Update
        boolean result = service.actualizar(guardado);
        
        // 4. Verify
        assertTrue(result);
        Cliente actualizado = service.buscarPorId(guardado.getIdCliente());
        assertEquals("Nombre Modificado Interface", actualizado.getNombreCliente());
    }

    @Test
    public void testEliminar() {
        System.out.println("TEST: Eliminar (Close the JOptionPane)");
        
        // 1. Register
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(emailTest);
        
        // 2. Delete
        boolean result = service.eliminar(guardado.getIdCliente());
        
        // 3. Verify
        assertTrue(result);
        assertNull(service.buscarPorId(guardado.getIdCliente()));
    }
}