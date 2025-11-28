package Repositorio;

import Builder.ClienteBuilder;
import Model.Cliente;
import ServiciosMoroniConexion.BaseDatosConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    // ================================
    // REGISTRAR CLIENTE
    // ================================
    public boolean registrar(Cliente cliente) {
        if (emailRegistrado(cliente.getEmailCliente()) || telefonoRegistrado(cliente.getTelefonoCliente())) {
            System.err.println("⚠️ El email o teléfono ya está registrado.");
            return false;
        }

        String sql = """
        INSERT INTO cliente (
            nombre_cliente, ruc_cliente, direccion_cliente,
            telefono_cliente, email_cliente, contrasena_cliente, rol
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getRucCliente());
            ps.setString(3, cliente.getDireccionCliente());
            ps.setString(4, cliente.getTelefonoCliente());
            ps.setString(5, cliente.getEmailCliente());
            ps.setString(6, cliente.getContrasenaCliente());
            ps.setString(7, cliente.getRol() != null ? cliente.getRol() : "ROL_USER");

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // ACTUALIZAR CLIENTE
    // ================================
    public boolean actualizar(Cliente cliente) {
        String sql = """
        UPDATE cliente
        SET nombre_cliente = ?, ruc_cliente = ?, direccion_cliente = ?,
            telefono_cliente = ?, email_cliente = ?, contrasena_cliente = ?, rol = ?
        WHERE id_cliente = ?
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getRucCliente());
            ps.setString(3, cliente.getDireccionCliente());
            ps.setString(4, cliente.getTelefonoCliente());
            ps.setString(5, cliente.getEmailCliente());
            ps.setString(6, cliente.getContrasenaCliente());
            ps.setString(7, cliente.getRol());
            ps.setInt(8, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // OBTENER CLIENTES
    // ================================
    public Cliente obtenerPorId(int id) {
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
    }

    public Cliente obtenerPorEmail(String email) {
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
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY id_cliente";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(construirClienteDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // ================================
    // ELIMINAR CLIENTE
    // ================================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
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
    // CONSTRUCTOR DE CLIENTE (desde BD)
    // ================================
    private Cliente construirClienteDesdeResultSet(ResultSet rs) throws SQLException {
        return new ClienteBuilder()
                .conIdCliente(rs.getInt("id_cliente"))
                .conNombreCliente(rs.getString("nombre_cliente"))
                .conRucCliente(rs.getString("ruc_cliente"))
                .conDireccionCliente(rs.getString("direccion_cliente"))
                .conTelefonoCliente(rs.getString("telefono_cliente"))
                .conEmailCliente(rs.getString("email_cliente"))
                .conContrasenaCliente(rs.getString("contrasena_cliente"))
                .conRol(rs.getString("rol"))
                .build();
    }

    public Cliente verPorEmail(String email) {
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
    }
}
