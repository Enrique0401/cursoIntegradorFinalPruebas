package Repositorio;

import ServiciosMoroniConexion.BaseDatosConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servicio de autenticación para Cliente y Dueño. Valida credenciales según el
 * tipo de cuenta.
 */
public class AuthService {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    /**
     * Valida credenciales de un Cliente.
     */
    public boolean validarCredencialesCliente(String email, String contrasena) {
        String sql = "SELECT 1 FROM Cliente WHERE LOWER(email_cliente) = LOWER(?) AND contrasena_cliente = ?";
        return validar(sql, email, contrasena);
    }

    /**
     * Valida credenciales de un Dueño.
     */
    public boolean validarCredencialesDueno(String email, String contrasena) {
        String sql = "SELECT 1 FROM Dueno WHERE LOWER(email_dueno) = LOWER(?) AND contrasena_dueno = ?";
        return validar(sql, email, contrasena);
    }

    /**
     * Método genérico para validar credenciales.
     */
    private boolean validar(String sql, String email, String contrasena) {
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());
            ps.setString(2, contrasena.trim());

            try (ResultSet rs = ps.executeQuery()) {
                boolean existe = rs.next();
                System.out.println("🔍 Validando: " + email + " → Existe: " + existe);
                return existe;
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error al validar credenciales: " + e.getMessage());
            return false;
        }
    }
}
