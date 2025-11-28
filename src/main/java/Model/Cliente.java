package Model;

import java.time.LocalDateTime;

public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String rucCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private String emailCliente;
    private String contrasenaCliente;
    private String rol;
    private LocalDateTime fechaRegistro;

    // 🔹 Constructor vacío
    public Cliente() {
    }

    // 🔹 Constructor con parámetros
    public Cliente(String nombreCliente, String rucCliente, String direccionCliente,
            String telefonoCliente, String emailCliente, String contrasenaCliente, String rol) {
        this.nombreCliente = nombreCliente;
        this.rucCliente = rucCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.emailCliente = emailCliente;
        this.contrasenaCliente = contrasenaCliente;
        this.rol = rol;
        this.fechaRegistro = LocalDateTime.now();
    }

    // 🔹 Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }

    public void setContrasenaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // 🔹 Método para mostrar información
    public String mostrarInfo() {
        return String.format("Cliente: %s (%s) - Rol: %s", nombreCliente, emailCliente, rol);
    }
}
