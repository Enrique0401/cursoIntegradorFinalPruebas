package Repositorio;

import Model.Contacto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ServiciosMoroniConexion.BaseDatosConexion;

public class ContactoRepositorio {

    private final BaseDatosConexion conexionDB = BaseDatosConexion.getInstancia();

    public List<Contacto> obtenerTodos() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contacto ORDER BY fecha_envio DESC";

        try (Connection conn = conexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contacto c = new Contacto();
                c.setIdContacto(rs.getInt("id_contacto"));
                c.setNombre(rs.getString("nombre"));
                c.setEmpresa(rs.getString("empresa"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setServicio(rs.getString("servicio"));
                c.setMensaje(rs.getString("mensaje"));
                c.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener contactos: " + e.getMessage());
        }

        return lista;
    }
}
