package Servicio;

import Model.Seguimiento;
import java.util.List;

public interface ISeguimientoService {

    List<Seguimiento> obtenerTodos();

    //Seguimiento buscarPorId(int id);

    boolean eliminar(int idSeguimiento);

    boolean actualizar(Seguimiento seguimiento);

    boolean registrar(Seguimiento seguimiento);
}
