package Servicio;

import Model.Cliente;
import Repositorio.ClienteRepositorio;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepositorio repositorio;
    private Cliente clientePrueba;

    // Datos de prueba
    private final String EMAIL_PRUEBA = "test_service_junit@correo.com";
    private final String DNI_RUC_PRUEBA = "10999999999";

    public ClienteServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        repositorio = new ClienteRepositorio();
        service = new ClienteService(repositorio);

        limpiarDatosDePrueba();

        clientePrueba = new Cliente();
        clientePrueba.setNombreCliente("Cliente Test Service");
        clientePrueba.setEmailCliente(EMAIL_PRUEBA);
        clientePrueba.setTelefonoCliente("999888777");
        clientePrueba.setRucCliente(DNI_RUC_PRUEBA);
        clientePrueba.setContrasenaCliente("123456");
    }

    @AfterEach
    public void tearDown() {
        limpiarDatosDePrueba();
    }

    private void limpiarDatosDePrueba() {
        Cliente existente = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        if (existente != null) {
            repositorio.eliminar(existente.getIdCliente());
        }
    }

    @Test
    public void testRegistrarExitoso() {
        System.out.println("TEST: Registrar Exitoso");

        boolean resultado = service.registrar(clientePrueba);

        assertTrue(resultado, "El registro debería ser exitoso");
        assertNotNull(repositorio.obtenerPorEmail(EMAIL_PRUEBA), "El cliente debe existir en BD");
    }

    @Test
    public void testValidacionEmailIncorrecto() {
        System.out.println("TEST: Validación Email (Se espera JOptionPane)");

        clientePrueba.setEmailCliente("correo_sin_arroba");

        boolean resultado = service.registrar(clientePrueba);
        
        assertFalse(resultado, "El registro debe fallar si el email no tiene @");
    }

    @Test
    public void testValidacionTelefonoIncorrecto() {
        System.out.println("TEST: Validación Teléfono (Se espera JOptionPane)");
        
        clientePrueba.setTelefonoCliente("123456");
        
        boolean resultado = service.registrar(clientePrueba);
        
        assertFalse(resultado, "El registro debe fallar con teléfono inválido");
    }

    @Test
    public void testActualizar() {
        System.out.println("TEST: Actualizar Exitoso");
        
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        assertNotNull(guardado);

        guardado.setNombreCliente("Nombre Modificado");
        guardado.setTelefonoCliente("987654321"); // Válido

        boolean resultado = service.actualizar(guardado);

        assertTrue(resultado);
        Cliente actualizado = service.buscarPorId(guardado.getIdCliente());
        assertEquals("Nombre Modificado", actualizado.getNombreCliente());
    }

    @Test
    public void testObtenerTodos() {
        System.out.println("TEST: Obtener Todos");
        
        service.registrar(clientePrueba);
        
        List<Cliente> lista = service.obtenerTodos();
        
        assertNotNull(lista);
        boolean encontrado = lista.stream()
                .anyMatch(c -> c.getEmailCliente().equals(EMAIL_PRUEBA));
        assertTrue(encontrado);
    }

    @Test
    public void testEliminarSimple() {
        System.out.println("TEST: Eliminar Simple");
        
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        
        boolean resultado = service.eliminar(guardado.getIdCliente());
        
        assertTrue(resultado);
        assertNull(service.buscarPorId(guardado.getIdCliente()));
    }

    @Test
    public void testEliminarConConfirmacion() {
        System.out.println("TEST: Eliminar con Confirmación (REQUIERE CLIC EN 'SÍ')");
        
        service.registrar(clientePrueba);
        Cliente guardado = repositorio.obtenerPorEmail(EMAIL_PRUEBA);
        
        boolean resultado = service.eliminarConConfirmacion(guardado.getIdCliente(), "123456");

        assertTrue(resultado, "Debes presionar 'SÍ' en el diálogo para que este test pase");
        assertNull(service.buscarPorId(guardado.getIdCliente()));
    }
}