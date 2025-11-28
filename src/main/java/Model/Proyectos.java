package Model;

import java.time.LocalDateTime;


public class Proyectos {
    private int idProyecto;
    private String categoria;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaEntrega;
    private String nombre;
    private int progreso;
    private int idCliente;

    public Proyectos() {
    }

    public Proyectos(int idProyecto, String categoria, String descripcion, String estado, LocalDateTime fechaEntrega, String nombre, int progreso, int idCliente) {
        this.idProyecto = idProyecto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.nombre = nombre;
        this.progreso = progreso;
        this.idCliente = idCliente;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String mostrarInfo() {
        return String.format("Proyecto: %s (%s) - Rol: %s", nombre, estado, categoria);
    }

    public String idProyecto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
