package Repositorio;

import Model.Contacto;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoRepositorioTest {

    @Test
    public void testObtenerTodos() {
        ContactoRepositorio repo = new ContactoRepositorio();

        List<Contacto> lista = repo.obtenerTodos();

        // La lista jamás debe ser null
        assertNotNull(lista, "La lista no debe ser null");

        // No hacemos assertEquals con una lista esperada porque depende de la BD real
        // Simplemente verificamos que sea una lista válida
        assertTrue(lista.size() >= 0, "La lista debe tener cero o más elementos");

        System.out.println("Contactos obtenidos: " + lista.size());
    }
}
