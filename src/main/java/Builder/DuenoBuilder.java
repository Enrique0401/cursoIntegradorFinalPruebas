package Builder;

import Model.Dueno;
import java.time.LocalDateTime;

public class DuenoBuilder {

    private int idDueno;
    private String nombreDueno;
    private String dniDueno;
    private String telefonoDueno;
    private String emailDueno;
    private String contrasenaDueno;
    private LocalDateTime fechaRegistro;

    public DuenoBuilder conIdDueno(int idDueno) {
        this.idDueno = idDueno;
        return this;
    }

    public DuenoBuilder conNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
        return this;
    }

    public DuenoBuilder conDniDueno(String dniDueno) {
        this.dniDueno = dniDueno;
        return this;
    }

    public DuenoBuilder conTelefonoDueno(String telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
        return this;
    }

    public DuenoBuilder conEmailDueno(String emailDueno) {
        this.emailDueno = emailDueno;
        return this;
    }

    public DuenoBuilder conContrasenaDueno(String contrasenaDueno) {
        this.contrasenaDueno = contrasenaDueno;
        return this;
    }

    public DuenoBuilder conFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public Dueno build() {
        return new Dueno(idDueno, nombreDueno, dniDueno, telefonoDueno,
                emailDueno, contrasenaDueno, fechaRegistro);
    }
}
