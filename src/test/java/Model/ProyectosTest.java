package Model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProyectosTest {

    @Test
    public void testSetAndGetIdProyecto() {
        Proyectos p = new Proyectos();
        p.setIdProyecto(10);
        assertEquals(10, p.getIdProyecto());
    }

    @Test
    public void testSetAndGetCategoria() {
        Proyectos p = new Proyectos();
        p.setCategoria("Desarrollo Web");
        assertEquals("Desarrollo Web", p.getCategoria());
    }

    @Test
    public void testSetAndGetDescripcion() {
        Proyectos p = new Proyectos();
        p.setDescripcion("Sistema para gestión de clientes");
        assertEquals("Sistema para gestión de clientes", p.getDescripcion());
    }

    @Test
    public void testSetAndGetEstado() {
        Proyectos p = new Proyectos();
        p.setEstado("En Progreso");
        assertEquals("En Progreso", p.getEstado());
    }

    @Test
    public void testSetAndGetFechaEntrega() {
        Proyectos p = new Proyectos();
        LocalDateTime fecha = LocalDateTime.now();
        p.setFechaEntrega(fecha);
        assertEquals(fecha, p.getFechaEntrega());
    }

    @Test
    public void testSetAndGetNombre() {
        Proyectos p = new Proyectos();
        p.setNombre("Proyecto Moroni");
        assertEquals("Proyecto Moroni", p.getNombre());
    }

    @Test
    public void testSetAndGetProgreso() {
        Proyectos p = new Proyectos();
        p.setProgreso(75);
        assertEquals(75, p.getProgreso());
    }

    @Test
    public void testSetAndGetIdCliente() {
        Proyectos p = new Proyectos();
        p.setIdCliente(4);
        assertEquals(4, p.getIdCliente());
    }

    @Test
    public void testConstructorConParametros() {
        LocalDateTime fecha = LocalDateTime.now();

        Proyectos p = new Proyectos(
                1,
                "Infraestructura",
                "Instalación de servidores",
                "Completado",
                fecha,
                "Proyecto Servidores",
                100,
                7
        );

        assertEquals(1, p.getIdProyecto());
        assertEquals("Infraestructura", p.getCategoria());
        assertEquals("Instalación de servidores", p.getDescripcion());
        assertEquals("Completado", p.getEstado());
        assertEquals(fecha, p.getFechaEntrega());
        assertEquals("Proyecto Servidores", p.getNombre());
        assertEquals(100, p.getProgreso());
        assertEquals(7, p.getIdCliente());
    }

    @Test
    public void testMostrarInfo() {
        Proyectos p = new Proyectos();
        p.setNombre("Sistema Escolar");
        p.setEstado("Pendiente");
        p.setCategoria("Educación");

        String esperado = String.format("Proyecto: %s (%s) - Rol: %s",
                "Sistema Escolar", "Pendiente", "Educación");

        assertEquals(esperado, p.mostrarInfo());
    }
}
