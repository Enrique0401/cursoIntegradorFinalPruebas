package Builder;

import Model.Proyectos;
import java.time.LocalDateTime;

public class ProyectoBuilder {

    private int idProyecto;
    private String nombreProyecto;
    private String categoriaProyecto;
    private String descripcionProyecto;
    private String estadoProyecto;
    private int progresoProyecto;
    private LocalDateTime fechaEntrega;
    private int idCliente;

    // 🔹 Métodos tipo "con" para asignar valores
    public ProyectoBuilder conIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
        return this;
    }

    public ProyectoBuilder conNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
        return this;
    }

    public ProyectoBuilder conCategoriaProyecto(String categoriaProyecto) {
        this.categoriaProyecto = categoriaProyecto;
        return this;
    }

    public ProyectoBuilder conDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
        return this;
    }

    public ProyectoBuilder conEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
        return this;
    }

    public ProyectoBuilder conProgresoProyecto(int progresoProyecto) {
        this.progresoProyecto = progresoProyecto;
        return this;
    }

    public ProyectoBuilder conFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }
    
    public ProyectoBuilder conIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    // 🔹 Método final para construir el objeto
    public Proyectos build() {
        Proyectos proy = new Proyectos();
        proy.setIdProyecto(idProyecto);
        proy.setCategoria(categoriaProyecto);
        proy.setDescripcion(descripcionProyecto);
        proy.setEstado(estadoProyecto);
        proy.setNombre(nombreProyecto);
        proy.setProgreso(progresoProyecto);
        proy.setFechaEntrega(fechaEntrega != null ? fechaEntrega : LocalDateTime.now());
        proy.setIdCliente(idCliente);
        return proy;
    }
}
