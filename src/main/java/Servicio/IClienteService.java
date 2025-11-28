package Servicio;

import Model.Cliente;
import java.util.List;

public interface IClienteService {

    List<Cliente> obtenerTodos();

    Cliente buscarPorId(int id);

    boolean eliminar(int idCliente);

    boolean actualizar(Cliente cliente);

    boolean registrar(Cliente cliente);
}
