package Builder;

import Model.Seguimiento;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeguimientoBuilderTest {

    @Test
    public void testFluidezDelBuilder() {
        SeguimientoBuilder builder = new SeguimientoBuilder();

        // Cada método debe retornar el MISMO objeto builder
        assertSame(builder, builder.conIdSeguimiento(10));
        assertSame(builder, builder.conDescripcionSeguimiento("Prueba"));
        assertSame(builder, builder.conPorcentajeAvance(50));
        assertSame(builder, builder.conIdProyecto(3));
        assertSame(builder, builder.conFechaRegistro(LocalDateTime.now()));
    }

    @Test
    public void testBuildCompleto() {
        LocalDateTime fecha = LocalDateTime.now();

        Seguimiento seguimiento = new SeguimientoBuilder()
                .conIdSeguimiento(1)
                .conDescripcionSeguimiento("Avance inicial")
                .conPorcentajeAvance(20)
                .conIdProyecto(5)
                .conFechaRegistro(fecha)
                .build();

        assertEquals(1, seguimiento.getIdSeguimiento());
        assertEquals("Avance inicial", seguimiento.getDescripcion());
        assertEquals(20, seguimiento.getPorcentajeAvance());
        assertEquals(5, seguimiento.getIdProyecto());
        assertEquals(fecha, seguimiento.getFechaAvance());
    }

    @Test
    public void testBuildSinFechaUsaFechaActual() {
        Seguimiento seguimiento = new SeguimientoBuilder()
                .conIdSeguimiento(2)
                .conDescripcionSeguimiento("Sin fecha enviada")
                .conPorcentajeAvance(10)
                .conIdProyecto(1)
                .build();

        assertNotNull(seguimiento.getFechaAvance());
    }
}
