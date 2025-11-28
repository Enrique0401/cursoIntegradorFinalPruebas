package Servicio;

import Model.Incidencia;
import Observer.EntidadObservableSingleton;
import Repositorio.IncidenciaRepositorio;

import javax.swing.JOptionPane;
import java.util.List;

public class IncidenciaService implements IIncidenciaService {

    private final IncidenciaRepositorio repositorio;

    public IncidenciaService(IncidenciaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Incidencia> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public Incidencia buscarPorId(int id) {
        return repositorio.obtenerPorId(id);
    }

    @Override
    public boolean eliminar(int idCliente) {
        boolean eliminado = repositorio.eliminar(idCliente);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "✅ El cliente fue eliminado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar el cliente.");
        }
        return eliminado;
    }

    @Override
    public boolean actualizar(Incidencia incidencia) {
        boolean exito = repositorio.actualizar(incidencia);
        if (exito) {
            JOptionPane.showMessageDialog(null, "✅ Incidencia actualizado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el incidencia.");
        }
        return exito;
    }

    @Override
    public boolean registrar(Incidencia incidencia) {
//        if (!validarAntesDeRegistrar(incidencia)) {
//            return false;
//        }

        boolean fueExitoso = repositorio.registrar(incidencia);
        if (fueExitoso) {
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo registrar el incidencia en la base de datos.");
        }
        return fueExitoso;
    }

}
