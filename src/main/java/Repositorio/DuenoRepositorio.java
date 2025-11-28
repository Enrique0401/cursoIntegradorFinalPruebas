package Repositorio;

import Builder.DuenoBuilder;
import Model.Dueno;
import ServiciosMoroniConexion.BaseDatosConexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DuenoRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    // ================================
    // REGISTRAR DUEÑO
    // ================================
    public boolean registrar(Dueno dueno) {
        if (emailRegistrado(dueno.getEmailDueno()) || dniRegistrado(dueno.getDniDueno())) {
            System.err.println("⚠️ El email o DNI ya está registrado.");
            return false;
        }

        String sql = """
        INSERT INTO dueno (
            nombre_dueno, dni_dueno, telefono_dueno,
            email_dueno, contrasena_dueno
        ) VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dueno.getNombreDueno());
            ps.setString(2, dueno.getDniDueno());
            ps.setString(3, dueno.getTelefonoDueno());
            ps.setString(4, dueno.getEmailDueno());
            ps.setString(5, dueno.getContrasenaDueno());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar dueño: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // ACTUALIZAR DUEÑO
    // ================================
    public boolean actualizar(Dueno dueno) {
        String sql = """
        UPDATE dueno
        SET nombre_dueno = ?, dni_dueno = ?, telefono_dueno = ?,
            email_dueno = ?, contrasena_dueno = ?
        WHERE id_dueno = ?
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dueno.getNombreDueno());
            ps.setString(2, dueno.getDniDueno());
            ps.setString(3, dueno.getTelefonoDueno());
            ps.setString(4, dueno.getEmailDueno());
            ps.setString(5, dueno.getContrasenaDueno());
            ps.setInt(6, dueno.getIdDueno());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar dueño: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // OBTENER DUEÑOS
    // ================================
    public Dueno obtenerPorId(int id) {
        String sql = "SELECT * FROM dueno WHERE id_dueno = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirDuenoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener dueño por ID: " + e.getMessage());
        }
        return null;
    }

    public Dueno obtenerPorEmail(String email) {
        String sql = "SELECT * FROM dueno WHERE LOWER(email_dueno) = LOWER(?)";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirDuenoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener dueño por email: " + e.getMessage());
        }
        return null;
    }

    public List<Dueno> obtenerTodos() {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT * FROM dueno ORDER BY id_dueno";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(construirDuenoDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar dueños: " + e.getMessage());
        }
        return lista;
    }

    // ================================
    // ELIMINAR DUEÑO
    // ================================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM dueno WHERE id_dueno = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar dueño: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // VALIDACIONES
    // ================================
    public boolean emailRegistrado(String email) {
        String sql = "SELECT COUNT(*) FROM dueno WHERE LOWER(email_dueno) = LOWER(?)";
        return verificarExistencia(sql, email);
    }

    public boolean dniRegistrado(String dni) {
        String sql = "SELECT COUNT(*) FROM dueno WHERE dni_dueno = ?";
        return verificarExistencia(sql, dni);
    }

    private boolean verificarExistencia(String sql, Object valor) {
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, valor);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al verificar existencia: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // CONSTRUCTOR DE OBJETO (BUILDER)
    // ================================
    private Dueno construirDuenoDesdeResultSet(ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp("fecha_registro");
        LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;

        return new DuenoBuilder()
                .conIdDueno(rs.getInt("id_dueno"))
                .conNombreDueno(rs.getString("nombre_dueno"))
                .conDniDueno(rs.getString("dni_dueno"))
                .conTelefonoDueno(rs.getString("telefono_dueno"))
                .conEmailDueno(rs.getString("email_dueno"))
                .conContrasenaDueno(rs.getString("contrasena_dueno"))
                .conFechaRegistro(fecha)
                .build();
    }

    public Dueno verPorEmail(String email) {
        String sql = "SELECT * FROM Dueno WHERE LOWER(email_dueno) = LOWER(?)";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Dueno dueno = new Dueno();
                    dueno.setIdDueno(rs.getInt("id_dueno"));
                    dueno.setNombreDueno(rs.getString("nombre_dueno"));
                    dueno.setDniDueno(rs.getString("dni_dueno"));
                    dueno.setTelefonoDueno(rs.getString("telefono_dueno"));
                    dueno.setEmailDueno(rs.getString("email_dueno"));
                    dueno.setContrasenaDueno(rs.getString("contrasena_dueno"));

                    // Si existe columna fecha_registro_dueno
                    try {
                        dueno.setFechaRegistro(rs.getTimestamp("fecha_registro_dueno").toLocalDateTime());
                    } catch (Exception e) {
                        dueno.setFechaRegistro(null);
                    }

                    return dueno;
                }
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error al obtener dueño por email: " + e.getMessage());
        }
        return null;
    }
}
