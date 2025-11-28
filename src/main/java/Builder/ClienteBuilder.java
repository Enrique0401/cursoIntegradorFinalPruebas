package Builder;

import Model.Cliente;
import java.time.LocalDateTime;

public class ClienteBuilder {

    private int idCliente;
    private String nombreCliente;
    private String rucCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private String emailCliente;
    private String contrasenaCliente;
    private String rol;
    private LocalDateTime fechaRegistro;

    // 🔹 Métodos tipo "con" para asignar valores
    public ClienteBuilder conIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ClienteBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ClienteBuilder conRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
        return this;
    }

    public ClienteBuilder conDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
        return this;
    }

    public ClienteBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public ClienteBuilder conEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
        return this;
    }

    public ClienteBuilder conContrasenaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
        return this;
    }

    public ClienteBuilder conRol(String rol) {
        this.rol = rol;
        return this;
    }

    public ClienteBuilder conFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    // 🔹 Método final para construir el objeto
    public Cliente build() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNombreCliente(nombreCliente);
        cliente.setRucCliente(rucCliente);
        cliente.setDireccionCliente(direccionCliente);
        cliente.setTelefonoCliente(telefonoCliente);
        cliente.setEmailCliente(emailCliente);
        cliente.setContrasenaCliente(contrasenaCliente);
        cliente.setRol(rol);
        cliente.setFechaRegistro(fechaRegistro != null ? fechaRegistro : LocalDateTime.now());
        return cliente;
    }
}
