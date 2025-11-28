package ServiciosMoroniConexion;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseDatosConexionTest {

    @Test
    void testGetInstancia_NotNull() {
        BaseDatosConexion instancia = BaseDatosConexion.getInstancia();
        assertNotNull(instancia, "La instancia del singleton no debe ser null");
    }

    @Test
    void testEstablecerConexion() throws Exception {
        BaseDatosConexion db = BaseDatosConexion.getInstancia();

        Connection conn = db.establecerConexion();

        assertNotNull(conn, "La conexión no debe ser null");
        assertFalse(conn.isClosed(), "La conexión no debe estar cerrada");
    }

    @Test
    void testCerrarConexion() throws Exception {
        BaseDatosConexion db = BaseDatosConexion.getInstancia();

        Connection conn = db.establecerConexion();
        assertNotNull(conn);

        db.cerrarConexion();

        assertTrue(conn.isClosed(), "La conexión debe cerrarse correctamente");
    }
}
