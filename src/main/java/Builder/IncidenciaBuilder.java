package Builder;

import Model.Incidencia;
import java.time.LocalDateTime;

public class IncidenciaBuilder {

    private int idIncidencia;
    private String descripcionIncidencia;
    private String estadoIncidencia;
    private LocalDateTime fechaIncidencia;
    private int idProyecto;


    // 🔹 Métodos tipo "con" para asignar valores
    public IncidenciaBuilder conIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
        return this;
    }

    public IncidenciaBuilder conDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
        return this;
    }

    public IncidenciaBuilder conEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
        return this;
    }

    public IncidenciaBuilder conFechaIncidencia(LocalDateTime fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
        return this;
    }
    
    public IncidenciaBuilder conIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
        return this;
    }

    // 🔹 Método final para construir el objeto
    public Incidencia build() {
        Incidencia incidencia = new Incidencia();
        incidencia.setIdIncidencia(idIncidencia);
        incidencia.setDescripcionIncidencia(descripcionIncidencia);
        incidencia.setEstadoInIncidencia(estadoIncidencia);
        incidencia.setFechaIncidencia(fechaIncidencia != null ? fechaIncidencia : LocalDateTime.now());
        incidencia.setIdProyecto(idProyecto);
        return incidencia;
    }
}
