package ServiciosMoroniConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatosConexion {

    private static BaseDatosConexion instancia;
    private Connection conexion;

    private final String usuario = "root";
    private final String contrasena = "1234";
    private final String baseDatos = "serviciosmoroni";
    private final String host = "localhost";
    private final String puerto = "3306";
    private final String cadena = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos + "?useSSL=false&serverTimezone=UTC";

    private BaseDatosConexion() {
        try {
            // Registrar el driver de MySQL (opcional en versiones modernas)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            System.out.println("Conexion a la base de datos MySQL establecida correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontro el driver de MySQL: " + e.getMessage());
        }
    }

    // Singleton thread-safe
    public static synchronized BaseDatosConexion getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatosConexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(cadena, usuario, contrasena);
                System.out.println("Conexion reestablecida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener conexion: " + e.getMessage());
        }
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("🔒 Conexion cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    public Connection establecerConexion() {
        try {
            return DriverManager.getConnection(cadena, usuario, contrasena);
        } catch (SQLException e) {
            System.err.println("Error al establecer nueva conexion: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        BaseDatosConexion conexion = BaseDatosConexion.getInstancia();
        conexion.getConexion();
        // conexion.cerrarConexion();
    }
}
