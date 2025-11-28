package Model;

import java.time.LocalDateTime;

public class Incidencia {

    private int idIncidencia;
    private String descripcionIncidencia;
    private String estadoInIncidencia;
    private LocalDateTime fechaIncidencia;
    private int idProyecto;

    // 🔹 Constructor vacío
    public Incidencia() {
    }

    public Incidencia(int idIncidencia, String descripcionIncidencia, String estadoInIncidencia, LocalDateTime fechaIncidencia, int idProyecto) {
        this.idIncidencia = idIncidencia;
        this.descripcionIncidencia = descripcionIncidencia;
        this.estadoInIncidencia = estadoInIncidencia;
        this.fechaIncidencia = fechaIncidencia;
        this.idProyecto = idProyecto;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public String getEstadoInIncidencia() {
        return estadoInIncidencia;
    }

    public void setEstadoInIncidencia(String estadoInIncidencia) {
        this.estadoInIncidencia = estadoInIncidencia;
    }

    public LocalDateTime getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(LocalDateTime fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    

    // 🔹 Método para mostrar información
    public String mostrarInfo() {
        return String.format("Incidencia: %s (%s) - Rol: %s", idIncidencia, descripcionIncidencia);
    }
}
