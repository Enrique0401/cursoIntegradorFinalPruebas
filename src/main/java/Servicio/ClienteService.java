package Servicio;

import Model.Cliente;
import Observer.EntidadObservableSingleton;
import Repositorio.ClienteRepositorio;

import javax.swing.JOptionPane;
import java.util.List;

public class ClienteService implements IClienteService {

    private final ClienteRepositorio repositorio;

    public ClienteService(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public Cliente buscarPorId(int id) {
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
    public boolean actualizar(Cliente cliente) {
        if (!validarAntesDeActualizar(cliente)) {
            return false;
        }

        boolean exito = repositorio.actualizar(cliente);
        if (exito) {
            JOptionPane.showMessageDialog(null, "✅ Cliente actualizado correctamente.");
            EntidadObservableSingleton.getInstancia().notificarObservadores();
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el cliente.");
        }
        return exito;
    }

    @Override
    public boolean registrar(Cliente cliente) {
        if (!validarAntesDeRegistrar(cliente)) {
            return false;
        }

        boolean fueExitoso = repositorio.registrar(cliente);
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
    private boolean validarAntesDeRegistrar(Cliente cliente) {
        if (!validarCampos(cliente)) {
            return false;
        }

        if (repositorio.emailRegistrado(cliente.getEmailCliente())) {
            JOptionPane.showMessageDialog(null, "❌ El correo ya está registrado.");
            return false;
        }

        if (repositorio.telefonoRegistrado(cliente.getTelefonoCliente())) {
            JOptionPane.showMessageDialog(null, "❌ El teléfono ya está registrado.");
            return false;
        }

        return true;
    }

    private boolean validarAntesDeActualizar(Cliente cliente) {
        if (!validarCampos(cliente)) {
            return false;
        }

        int id = cliente.getIdCliente();

        /* Nota: tus métodos emailRegistrado/telefonoRegistrado buscan existencia global.
           Si quieres permitir que el cliente mantenga su mismo email/telefono, deberías
           implementar métodos que excluyan su propio id (ej. existeEmail(email, id)).
           Por ahora validamos de forma simple: si existe y no pertenece al mismo id -> error. */
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
    }

    // ============================================================
    // 🔐 ELIMINAR CON CONFIRMACIÓN Y VALIDACIÓN DE CREDENCIALES
    // ============================================================
    public boolean eliminarConConfirmacion(int id, String contrasena) {
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
    }
}
