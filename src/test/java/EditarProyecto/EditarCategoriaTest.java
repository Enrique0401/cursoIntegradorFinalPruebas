package EditarProyecto;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EditarCategoriaTest {
    
    public EditarCategoriaTest() {
    }

    @Test
    public void testInicializacionYSeleccion() {
        System.out.println("TEST: Inicialización de EditarCategoria");

        // Evitar ejecución en servidores sin pantalla
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        try {
            // DATOS DE PRUEBA
            int idProyectoFake = 100;
            String categoriaPrueba = "Pintura";

            // 1. Instanciar la ventana pasándole una categoría específica
            EditarCategoria ventana = new EditarCategoria(idProyectoFake, categoriaPrueba);
            
            // 2. Verificar que no sea null
            assertNotNull(ventana, "La ventana debería crearse correctamente");

            // 3. Verificar que el ComboBox seleccionó automáticamente "Pintura"
            // (Usando el método auxiliar que agregamos)
            assertEquals(categoriaPrueba, ventana.getCategoriaSeleccionada(), 
                    "El ComboBox debería tener seleccionada la categoría pasada al constructor");
            
            // 4. Hacer visible y cerrar (Smoke Test visual)
            ventana.setVisible(true);
            assertTrue(ventana.isVisible());
            ventana.dispose();

        } catch (Exception e) {
            fail("Falló la inicialización de la ventana: " + e.getMessage());
        }
    }
    
    @Test
    public void testInicializacionSinCategoria() {
        System.out.println("TEST: Inicialización sin categoría previa");
        
        if (GraphicsEnvironment.isHeadless()) return;

        // Probamos con null, debería quedar en el default o no explotar
        EditarCategoria ventana = new EditarCategoria(100, null);
        
        assertNotNull(ventana);
        
        // Verificamos que al menos tenga algo seleccionado (el default "--Selecciona--")
        assertNotNull(ventana.getCategoriaSeleccionada());
        
        ventana.dispose();
    }
}