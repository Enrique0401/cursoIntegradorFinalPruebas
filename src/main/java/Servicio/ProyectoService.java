package Servicio;

import Model.Proyectos;
import Observer.EntidadObservableSingleton;
import Repositorio.ProyectoRepositorio;

import javax.swing.JOptionPane;
import java.util.List;

public class ProyectoService implements IProyectoService {

    private final ProyectoRepositorio repositorio;

    public ProyectoService(ProyectoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Proyectos> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public Proyectos buscarPorId(int id) {
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
    public boolean actualizar(Proyectos proyecto) {
        /*if (!validarAntesDeActualizar(proyecto)) {
            return false;
        }*/

        boolean exito = repositorio.actualizar(proyecto);
        if (exito) {
            JOptionPane.showMessageDialog(null, "✅ Cliente actualizado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el cliente.");
        }
        return exito;
    }

    @Override
    public boolean registrar(Proyectos proyecto) {
        /*if (!validarAntesDeRegistrar(proyecto)) {
            return false;
        }*/

        boolean fueExitoso = repositorio.registrar(proyecto);
        if (fueExitoso) {
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo registrar el cliente en la base de datos.");
        }
        return fueExitoso;
    }

    // ============================================================
    // 🔍 VALIDACIONES DE NEGOCIO
    // ============================================================
    /*private boolean validarAntesDeRegistrar(Proyectos proyecto) {
        if (!validarCampos(proyecto)) {
            return false;
        }

        if (repositorio.idRegistrado(proyecto.getIdProyecto())) {
            JOptionPane.showMessageDialog(null, "❌ El correo ya está registrado.");
            return false;
        }

        return true;
    }

    private boolean validarAntesDeActualizar(Proyectos proyecto) {

        // 1. Validar que los campos básicos no estén vacíos (si usas ese método)
        if (!validarCampos(proyecto)) {
            return false;
        }

        // 2. Obtener el ID del proyecto
        int id = proyecto.getIdProyecto();

        // 3. Verificar que el ID exista en la base de datos
        if (!idRegistrado(id)) {
            JOptionPane.showMessageDialog(null,
                    "❌ El ID del proyecto no existe. No se puede actualizar.");
            return false;
        }

        return true; // Todo OK
    }

    private boolean validarCampos(Cliente cliente) {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isEmpty()
                || cliente.getEmailCliente() == null || cliente.getEmailCliente().isEmpty()
                || cliente.getContrasenaCliente() == null || cliente.getContrasenaCliente().isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ Todos los campos obligatorios deben estar completos.");
            return false;
        }

        if (!cliente.getEmailCliente().contains("@")) {
            JOptionPane.showMessageDialog(null, "❌ El correo debe contener '@'.");
            return false;
        }

        if (cliente.getTelefonoCliente() != null && !cliente.getTelefonoCliente().isEmpty()
                && !cliente.getTelefonoCliente().matches("9\\d{8}")) {
            JOptionPane.showMessageDialog(null, "❌ El teléfono debe comenzar con 9 y tener 9 dígitos.");
            return false;
        }

        if (cliente.getRucCliente() != null && !cliente.getRucCliente().isEmpty()
                && !cliente.getRucCliente().matches("\\d{11}")) {
            JOptionPane.showMessageDialog(null, "❌ El RUC debe tener 11 dígitos numéricos.");
            return false;
        }

        return true;
    }*/

    // ============================================================
    // 🔐 ELIMINAR CON CONFIRMACIÓN Y VALIDACIÓN DE CREDENCIALES
    // ============================================================
    public boolean eliminarProyectoConConfirmacion(int idProyecto) {

        // 1. Verificar si existe
        Proyectos proyecto = repositorio.obtenerPorId(idProyecto);
        if (proyecto == null) {
            JOptionPane.showMessageDialog(null, "❌ Proyecto no encontrado.");
            return false;
        }

        // 2. Confirmación
        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar este proyecto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return false;
        }

        // 3. Intentar eliminar
        boolean eliminado = repositorio.eliminar(idProyecto);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "✅ Proyecto eliminado exitosamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar el proyecto.");
        }

        return eliminado;
    }

}
