package Builder;

import Model.Seguimiento;
import java.time.LocalDateTime;

public class SeguimientoBuilder {
    private int idSeguimiento;
    private String descripcionSeguimiento;
    private int porcentajeAvance;
    private int idProyecto;
    private LocalDateTime fechaAvance;

    // 🔹 Métodos tipo "con" para asignar valores
    public SeguimientoBuilder conIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
        return this;
    }

    public SeguimientoBuilder conDescripcionSeguimiento(String descripcionSeguimiento) {
        this.descripcionSeguimiento = descripcionSeguimiento;
        return this;
    }

    public SeguimientoBuilder conPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
        return this;
    }

    public SeguimientoBuilder conIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
        return this;
    }

    public SeguimientoBuilder conFechaRegistro(LocalDateTime fechaAvance) {
        this.fechaAvance = fechaAvance;
        return this;
    }

    // 🔹 Método final para construir el objeto
    public Seguimiento build() {
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setIdSeguimiento(idSeguimiento);
        seguimiento.setDescripcion(descripcionSeguimiento);
        seguimiento.setPorcentajeAvance(porcentajeAvance);
        seguimiento.setIdProyecto(idProyecto);
        seguimiento.setFechaAvance(fechaAvance != null ? fechaAvance : LocalDateTime.now());
        return seguimiento;
    }
}
