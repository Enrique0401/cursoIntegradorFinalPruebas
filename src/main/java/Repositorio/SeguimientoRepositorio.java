package Repositorio;

import Builder.SeguimientoBuilder;
import Model.Seguimiento;
import ServiciosMoroniConexion.BaseDatosConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguimientoRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    // ================================
    // REGISTRAR Seguimiento
    // ================================
    public boolean registrar(Seguimiento seguimiento) {

        String sql = """
        INSERT INTO seguimiento (
            descripcion, fecha_avance, 
            porcentaje_avance, proyecto_id)
        VALUES  (?, ?, ?, ?)
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seguimiento.getDescripcion());
            ps.setTimestamp(2, Timestamp.valueOf(seguimiento.getFechaAvance()));
            ps.setInt(3, seguimiento.getPorcentajeAvance());
            ps.setInt(4, seguimiento.getIdProyecto());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar seguimiento: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // ACTUALIZAR SEGUIMIENTO
    // ================================
    public boolean actualizar(Seguimiento seguimiento) {
        String sql = """
        UPDATE seguimiento
        SET descripcion = ?, porcentaje_avance = ?,
            proyecto_id = ?, fecha_avance = ?
        WHERE id_seguimiento = ?
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, seguimiento.getDescripcion());
            ps.setInt(2, seguimiento.getPorcentajeAvance());
            ps.setInt(3, seguimiento.getIdProyecto());
            ps.setTimestamp(4, Timestamp.valueOf(seguimiento.getFechaAvance()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar seguimiento: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // OBTENER SEGUIMIENTO
    // ================================
    /*public Seguimiento obtenerPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirClienteDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }*/

    /*public Seguimiento obtenerPorEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE LOWER(email_cliente) = LOWER(?)";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirClienteDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por email: " + e.getMessage());
        }
        return null;
    }*/

    public List<Seguimiento> obtenerTodos() {
        List<Seguimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguimiento ORDER BY id_seguimiento";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(construirSeguimientoDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar seguimiento: " + e.getMessage());
        }
        return lista;
    }

    // ================================
    // ELIMINAR SEGUIMIENTO
    // ================================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM seguimiento WHERE id_seguimiento = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar seguimiento: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // VALIDACIONES
    // ================================
    public boolean emailRegistrado(String email) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE LOWER(email_cliente) = LOWER(?)";
        return verificarExistencia(sql, email);
    }

    public boolean telefonoRegistrado(String telefono) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE telefono_cliente = ?";
        return verificarExistencia(sql, telefono);
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
    // CONSTRUCTOR DE SEGUIMIENTO (desde BD)
    // ================================
    private Seguimiento construirSeguimientoDesdeResultSet(ResultSet rs) throws SQLException {
        return new SeguimientoBuilder()
                .conIdSeguimiento(rs.getInt("id_seguimiento"))
                .conDescripcionSeguimiento(rs.getString("descripcion"))
                .conPorcentajeAvance(rs.getInt("porcentaje_avance"))
                .conIdProyecto(rs.getInt("proyecto_id"))
                .conFechaRegistro(rs.getTimestamp("fecha_avance").toLocalDateTime())
                .build();
    }

}
