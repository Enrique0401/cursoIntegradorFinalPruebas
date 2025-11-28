package Repositorio;

import Builder.IncidenciaBuilder;
import Model.Incidencia;
import ServiciosMoroniConexion.BaseDatosConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    // ================================
    // REGISTRAR INCIDENCIA
    // ================================
    public boolean registrar(Incidencia incidencia) {
        String sql = """
        INSERT INTO incidencia (
            descripcion, estado,
            fecha, proyecto_id
        ) VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, incidencia.getDescripcionIncidencia());
            ps.setString(2, incidencia.getEstadoInIncidencia());
            ps.setTimestamp(3, Timestamp.valueOf(incidencia.getFechaIncidencia()));
            ps.setInt(4, incidencia.getIdProyecto());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar INCIDENCIA: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // ACTUALIZAR INCIDENCIA
    // ================================
    public boolean actualizar(Incidencia incidencia) {
        String sql = """
        UPDATE cliente
        SET descripcion = ?, estado = ?, fecha = ?, proyecto_id = ?
        WHERE id_cliente = ?
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, incidencia.getDescripcionIncidencia());
            ps.setString(2, incidencia.getEstadoInIncidencia());
            ps.setTimestamp(3, Timestamp.valueOf(incidencia.getFechaIncidencia()));
            ps.setInt(4, incidencia.getIdProyecto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar INCIDENCIA: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // OBTENER INCIDENCIA
    // ================================
    public Incidencia obtenerPorId(int id) {
        String sql = "SELECT * FROM incidencia WHERE id_incidencia = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirIncidenciaDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Incidencia> obtenerTodos() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencia ORDER BY id_incidencia";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(construirIncidenciaDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // ================================
    // ELIMINAR INCIDENCIA
    // ================================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM incidencia WHERE id_incidencia = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar incidencia: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // VALIDACIONES
    // ================================

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
    // CONSTRUCTOR DE INCIDENCIA (desde BD)
    // ================================
    private Incidencia construirIncidenciaDesdeResultSet(ResultSet rs) throws SQLException {
        return new IncidenciaBuilder()
                .conIdIncidencia(rs.getInt("id_incidencia"))
                .conDescripcionIncidencia(rs.getString("descripcion"))
                .conEstadoIncidencia(rs.getString("estado"))
                .conFechaIncidencia(rs.getTimestamp("fecha").toLocalDateTime())
                .conIdProyecto(rs.getInt("proyecto_id"))
                .build();
    }

    /*public Cliente verPorEmail(String email) {
        String sql = "SELECT * FROM Cliente WHERE LOWER(email_cliente) = LOWER(?)";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNombreCliente(rs.getString("nombre_cliente"));
                    cliente.setRucCliente(rs.getString("ruc_cliente"));
                    cliente.setDireccionCliente(rs.getString("direccion_cliente"));
                    cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
                    cliente.setEmailCliente(rs.getString("email_cliente"));
                    cliente.setContrasenaCliente(rs.getString("contrasena_cliente"));
                    cliente.setRol(rs.getString("rol"));

                    // Si la base de datos guarda fecha_registro_cliente
                    try {
                        cliente.setFechaRegistro(rs.getTimestamp("fecha_registro_cliente").toLocalDateTime());
                    } catch (Exception e) {
                        cliente.setFechaRegistro(null);
                    }

                    return cliente;
                }
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error al obtener cliente por email: " + e.getMessage());
        }
        return null;
    }*/
}
