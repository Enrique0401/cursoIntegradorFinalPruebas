package Builder;

import Model.Incidencia;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaBuilderTest {

    @Test
    public void testBuildIncidencia_Completo() {
        // Arrange
        LocalDateTime fecha = LocalDateTime.now();

        Incidencia incidencia = new IncidenciaBuilder()
                .conIdIncidencia(5)
                .conDescripcionIncidencia("Error al cargar el módulo de reportes")
                .conEstadoIncidencia("Pendiente")
                .conFechaIncidencia(fecha)
                .conIdProyecto(20)
                .build();

        // Assert
        assertNotNull(incidencia);
        assertEquals(5, incidencia.getIdIncidencia());
        assertEquals("Error al cargar el módulo de reportes", incidencia.getDescripcionIncidencia());
        assertEquals("Pendiente", incidencia.getEstadoInIncidencia());
        assertEquals(fecha, incidencia.getFechaIncidencia());
        assertEquals(20, incidencia.getIdProyecto());
    }

    @Test
    public void testBuildIncidencia_FechaAutoGenerada() {
        // Arrange
        Incidencia incidencia = new IncidenciaBuilder()
                .conIdIncidencia(1)
                .conDescripcionIncidencia("Fallo en el login")
                .conEstadoIncidencia("Abierto")
                .conIdProyecto(99)
                .build(); // sin fecha a propósito

        // Assert
        assertNotNull(incidencia);
        assertNotNull(incidencia.getFechaIncidencia(), "La fecha debe autogenerarse si no se envía.");
    }

    @Test
    public void testBuilderRetornaMismoObjeto() {
        IncidenciaBuilder builder = new IncidenciaBuilder();
        assertSame(builder, builder.conIdIncidencia(10));
        assertSame(builder, builder.conDescripcionIncidencia("desc"));
        assertSame(builder, builder.conEstadoIncidencia("estado"));
        assertSame(builder, builder.conIdProyecto(2));
    }
}
