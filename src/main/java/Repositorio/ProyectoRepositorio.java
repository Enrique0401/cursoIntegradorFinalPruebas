package Repositorio;

import Builder.ProyectoBuilder;
import Model.Proyectos;
import ServiciosMoroniConexion.BaseDatosConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    public ProyectoRepositorio() {
    }

    // ================================
    // REGISTRAR CLIENTE
    // ================================
    public boolean registrar(Proyectos proyecto) {

        String sql = """
        INSERT INTO proyecto (
            estado, fecha_entrega, nombre,
            progreso, cliente_id, categoria, descripcion
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proyecto.getEstado());
            ps.setTimestamp(2, Timestamp.valueOf(proyecto.getFechaEntrega()));
            ps.setString(3, proyecto.getNombre());
            ps.setInt(4, proyecto.getProgreso());
            ps.setInt(5, proyecto.getIdCliente());
            ps.setString(6, proyecto.getCategoria());
            ps.setString(7, proyecto.getDescripcion());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar proyecto: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // ACTUALIZAR CLIENTE
    // ================================
    public boolean actualizar(Proyectos proyecto) {
        String sql = """
        UPDATE proyecto
        SET categoria = ?, descripcion = ?, estado = ?, 
            fecha_entrega = ?, nombre = ?, progreso = ?, cliente_id = ?
        WHERE id = ?
        """;

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proyecto.getCategoria());
            ps.setString(2, proyecto.getDescripcion());
            ps.setString(3, proyecto.getEstado());
            ps.setTimestamp(4, Timestamp.valueOf(proyecto.getFechaEntrega()));
            ps.setString(5, proyecto.getNombre());
            ps.setInt(6, proyecto.getProgreso());
            ps.setInt(7, proyecto.getIdCliente());
            ps.setInt(8, proyecto.getIdProyecto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar proyecto: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // OBTENER CLIENTES
    // ================================
    public Proyectos obtenerPorId(int id) {
        String sql = "SELECT * FROM proyecto WHERE id = ?";
        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirProyectoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener proyecto por ID: " + e.getMessage());
        }
        return null;
    }

    public Proyectos obtenerPorUsuario(int idCliente) {
        String sql = "SELECT * FROM proyecto WHERE cliente_id = ?";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirProyectoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener proyecto por cliente_id: " + e.getMessage());
        }

        return null;
    }

    public List<Proyectos> obtenerTodos() {
        List<Proyectos> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyecto ORDER BY id";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(construirProyectoDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar proyectos: " + e.getMessage());
        }
        return lista;
    }

    // ================================
    // ELIMINAR CLIENTE
    // ================================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM proyecto WHERE id = ?";

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0; // true si borró, false si no existía

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar proyecto: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // VALIDACIONES
    // ================================
    public boolean idRegistrado(int idCliente) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
        return verificarExistencia(sql, idCliente);
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
    private Proyectos construirProyectoDesdeResultSet(ResultSet rs) throws SQLException {
        return new ProyectoBuilder()
                .conIdProyecto(rs.getInt("id"))
                .conNombreProyecto(rs.getString("nombre"))
                .conCategoriaProyecto(rs.getString("categoria"))
                .conDescripcionProyecto(rs.getString("descripcion"))
                .conEstadoProyecto(rs.getString("estado"))
                .conProgresoProyecto(rs.getInt("progreso"))
                .conFechaEntrega(rs.getTimestamp("fecha_entrega").toLocalDateTime())
                .conIdCliente(rs.getInt("cliente_id"))
                .build();
    }

    public List<Proyectos> verPorEstado(String estado) {
        String sql = "SELECT * FROM proyecto WHERE LOWER(estado) = LOWER(?)";
        List<Proyectos> lista = new ArrayList<>();

        try (Connection conn = conexionDB.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado.trim().toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Proyectos p = new Proyectos();

                    p.setIdProyecto(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setEstado(rs.getString("estado"));
                    p.setIdCliente(rs.getInt("cliente_id"));
                    p.setProgreso(rs.getInt("progreso"));
                    p.setCategoria(rs.getString("categoria"));

                    try {
                        Date fecha = rs.getDate("fecha_entrega");
                        p.setFechaEntrega(
                                fecha != null
                                        ? fecha.toLocalDate().atStartOfDay()
                                        : null
                        );
                    } catch (Exception e) {
                        p.setFechaEntrega(null);
                    }

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Error al obtener proyectos por estado: " + e.getMessage());
        }

        return lista;
    }

}
