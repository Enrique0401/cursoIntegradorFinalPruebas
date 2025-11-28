package Builder;

import Model.Dueno;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuenoBuilderTest {

    @Test
    public void testBuildDueno() {
        LocalDateTime fecha = LocalDateTime.now();

        Dueno dueno = new DuenoBuilder()
                .conIdDueno(10)
                .conNombreDueno("Carlos Ramirez")
                .conDniDueno("12345678")
                .conTelefonoDueno("987654321")
                .conEmailDueno("carlos@gmail.com")
                .conContrasenaDueno("secret123")
                .conFechaRegistro(fecha)
                .build();

        assertNotNull(dueno);
        assertEquals(10, dueno.getIdDueno());
        assertEquals("Carlos Ramirez", dueno.getNombreDueno());
        assertEquals("12345678", dueno.getDniDueno());
        assertEquals("987654321", dueno.getTelefonoDueno());
        assertEquals("carlos@gmail.com", dueno.getEmailDueno());
        assertEquals("secret123", dueno.getContrasenaDueno());
        assertEquals(fecha, dueno.getFechaRegistro());
    }

    @Test
    public void testBuildDueno_FechaAutoAsignada() {
        // Arrange
        Dueno dueno = new DuenoBuilder()
                .conIdDueno(1)
                .conNombreDueno("María Lopez")
                .conDniDueno("87654321")
                .conTelefonoDueno("900111222")
                .conEmailDueno("maria@gmail.com")
                .conContrasenaDueno("pass123")
                .build();

        // Assert
        assertNotNull(dueno.getFechaRegistro(), "La fecha debe autogenerarse");
    }
}
