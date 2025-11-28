package Model;

import java.time.LocalDateTime;


public class Seguimiento {
    private int idSeguimiento;
    private String descripcion;
    private LocalDateTime fechaAvance;
    private int porcentajeAvance;
    private int idProyecto;

    public Seguimiento() {
    }

    public Seguimiento(int idSeguimiento, String descripcion, LocalDateTime fechaAvance, int porcentajeAvance, int idProyecto) {
        this.idSeguimiento = idSeguimiento;
        this.descripcion = descripcion;
        this.fechaAvance = fechaAvance;
        this.porcentajeAvance = porcentajeAvance;
        this.idProyecto = idProyecto;
    }

    public int getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaAvance() {
        return fechaAvance;
    }

    public void setFechaAvance(LocalDateTime fechaAvance) {
        this.fechaAvance = fechaAvance;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

  
}
