package Servicio;

import Model.Seguimiento;
import Observer.EntidadObservableSingleton;
import Repositorio.SeguimientoRepositorio;

import javax.swing.JOptionPane;
import java.util.List;

public class SeguimientoService implements ISeguimientoService {

    private final SeguimientoRepositorio repositorio;

    public SeguimientoService(SeguimientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Seguimiento> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public boolean eliminar(int idCliente) {
        boolean eliminado = repositorio.eliminar(idCliente);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "✅ El seguimiento fue eliminado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar el seguimiento.");
        }
        return eliminado;
    }

    @Override
    public boolean actualizar(Seguimiento seguimiento) {
        boolean exito = repositorio.actualizar(seguimiento);
        if (exito) {
            JOptionPane.showMessageDialog(null, "✅ Cliente actualizado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el cliente.");
        }
        return exito;
    }

    @Override
    public boolean registrar(Seguimiento seguimiento) {
        boolean fueExitoso = repositorio.registrar(seguimiento);
        if (fueExitoso) {
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo registrar el SEGUIMIENTO en la base de datos.");
        }
        return fueExitoso;
    }

    // ============================================================
    // 🔍 VALIDACIONES DE NEGOCIO
    // ============================================================
    /*private boolean validarAntesDeRegistrar(Seguimiento seguimiento) {
        if (!validarCampos(seguimiento)) {
            return false;
        }

        if (repositorio.emailRegistrado(seguimiento.getEmailCliente())) {
            JOptionPane.showMessageDialog(null, "❌ El correo ya está registrado.");
            return false;
        }

        if (repositorio.telefonoRegistrado(seguimiento.getTelefonoCliente())) {
            JOptionPane.showMessageDialog(null, "❌ El teléfono ya está registrado.");
            return false;
        }

        return true;
    }*/

    /*private boolean validarAntesDeActualizar(Seguimiento seguimiento) {
        if (!validarCampos(seguimiento)) {
            return false;
        }

        int id = seguimiento.getIdCliente();

        
        Cliente porEmail = repositorio.obtenerPorEmail(cliente.getEmailCliente());
        if (porEmail != null && porEmail.getIdCliente() != id) {
            JOptionPane.showMessageDialog(null, "❌ El correo ya está en uso por otro cliente.");
            return false;
        }

        // Para teléfono similar:
        // (tu repositorio no tiene obtenerPorTelefono, así que reutilizamos telefonoRegistrado y comprobamos id)
        if (cliente.getTelefonoCliente() != null && !cliente.getTelefonoCliente().isEmpty()) {
            List<Cliente> todos = repositorio.obtenerTodos();
            for (Cliente c : todos) {
                if (c.getTelefonoCliente() != null
                        && c.getTelefonoCliente().equals(cliente.getTelefonoCliente())
                        && c.getIdCliente() != id) {
                    JOptionPane.showMessageDialog(null, "❌ El teléfono ya está en uso por otro cliente.");
                    return false;
                }
            }
        }

        return true;
    }*/

    /*private boolean validarCampos(Seguimiento seguimiento) {
        if (seguimiento.getNombreCliente() == null || seguimiento.getNombreCliente().isEmpty()
                || seguimiento.getEmailCliente() == null || seguimiento.getEmailCliente().isEmpty()
                || seguimiento.getContrasenaCliente() == null || seguimiento.getContrasenaCliente().isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ Todos los campos obligatorios deben estar completos.");
            return false;
        }

        if (!seguimiento.getEmailCliente().contains("@")) {
            JOptionPane.showMessageDialog(null, "❌ El correo debe contener '@'.");
            return false;
        }

        if (seguimiento.getTelefonoCliente() != null && !seguimiento.getTelefonoCliente().isEmpty()
                && !seguimiento.getTelefonoCliente().matches("9\\d{8}")) {
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
    /*public boolean eliminarConConfirmacion(int id, String contrasena) {
        Cliente cli = repositorio.obtenerPorId(id);
        if (cli == null) {
            JOptionPane.showMessageDialog(null, "❌ Cliente no encontrado.");
            return false;
        }

        String passAlmacenada = cli.getContrasenaCliente();
        if (passAlmacenada == null || !passAlmacenada.equals(contrasena)) {
            JOptionPane.showMessageDialog(null, "❌ Credenciales incorrectas.");
            return false;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar tu cuenta?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return false;
        }

        boolean eliminado = repositorio.eliminar(id);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "✅ Cuenta eliminada exitosamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar la cuenta.");
        }

        return eliminado;
    }*/
}
