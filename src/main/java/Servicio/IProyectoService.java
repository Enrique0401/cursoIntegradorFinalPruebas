package Servicio;

import Model.Proyectos;
import java.util.List;

public interface IProyectoService {

    List<Proyectos> obtenerTodos();

    Proyectos buscarPorId(int id);

    boolean eliminar(int idProyecto);

    boolean actualizar(Proyectos proyecto);

    boolean registrar(Proyectos proyecto);
}
