package Builder;

import Model.Proyectos;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProyectoBuilderTest {

    @Test
    public void testBuildProyecto_Completo() {
        LocalDateTime fecha = LocalDateTime.now();

        Proyectos proyecto = new ProyectoBuilder()
                .conIdProyecto(10)
                .conNombreProyecto("Sistema de Inventarios")
                .conCategoriaProyecto("Software")
                .conDescripcionProyecto("Proyecto para control de inventarios")
                .conEstadoProyecto("En progreso")
                .conProgresoProyecto(45)
                .conFechaEntrega(fecha)
                .conIdCliente(100)
                .build();

        assertNotNull(proyecto);

        assertEquals(10, proyecto.getIdProyecto());
        assertEquals("Sistema de Inventarios", proyecto.getNombre());
        assertEquals("Software", proyecto.getCategoria());
        assertEquals("Proyecto para control de inventarios", proyecto.getDescripcion());
        assertEquals("En progreso", proyecto.getEstado());
        assertEquals(45, proyecto.getProgreso());
        assertEquals(fecha, proyecto.getFechaEntrega());
        assertEquals(100, proyecto.getIdCliente());
    }

    @Test
    public void testBuildProyecto_FechaAutoGenerada() {
        Proyectos proyecto = new ProyectoBuilder()
                .conNombreProyecto("Proyecto sin fecha")
                .conEstadoProyecto("Pendiente")
                .conIdCliente(5)
                .build();

        assertNotNull(proyecto);
        assertNotNull(proyecto.getFechaEntrega(), "Debe generarse fecha automáticamente si no se envía.");
    }

    @Test
    public void testBuilderRetornaMismoObjeto() {
        ProyectoBuilder builder = new ProyectoBuilder();

        assertSame(builder, builder.conIdProyecto(1));
        assertSame(builder, builder.conNombreProyecto("A"));
        assertSame(builder, builder.conCategoriaProyecto("B"));
        assertSame(builder, builder.conDescripcionProyecto("C"));
        assertSame(builder, builder.conEstadoProyecto("D"));
        assertSame(builder, builder.conProgresoProyecto(50));
        assertSame(builder, builder.conIdCliente(77));
    }
}
