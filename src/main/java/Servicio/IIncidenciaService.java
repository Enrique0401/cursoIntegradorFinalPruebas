package Servicio;

import Model.Incidencia;
import java.util.List;

public interface IIncidenciaService {

    List<Incidencia> obtenerTodos();

    Incidencia buscarPorId(int id);

    boolean eliminar(int idIncidencia);

    boolean actualizar(Incidencia incidencia);

    boolean registrar(Incidencia incidencia);
}
