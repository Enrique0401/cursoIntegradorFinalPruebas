package Servicio;

import Model.Contacto;
import Repositorio.ContactoRepositorio;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoServiceTest {
    
    private ContactoService service;
    private ContactoRepositorio repositorio;
    
    public ContactoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        repositorio = new ContactoRepositorio();
        service = new ContactoService(repositorio);
    }
    
    @Test
    public void testObtenerTodos() {
        List<Contacto> result = service.obtenerTodos();
        
        assertNotNull(result);
        System.out.println("Se encontraron " + result.size() + " contactos en la base de datos.");
    }
    
}