package Model;

import java.time.LocalDateTime;

public class Dueno {

    private int idDueno;
    private String nombreDueno;
    private String dniDueno;
    private String telefonoDueno;
    private String emailDueno;
    private String contrasenaDueno;
    private LocalDateTime fechaRegistro;

    public Dueno() {
    }

    public Dueno(int idDueno, String nombreDueno, String dniDueno, String telefonoDueno,
            String emailDueno, String contrasenaDueno, LocalDateTime fechaRegistro) {
        this.idDueno = idDueno;
        this.nombreDueno = nombreDueno;
        this.dniDueno = dniDueno;
        this.telefonoDueno = telefonoDueno;
        this.emailDueno = emailDueno;
        this.contrasenaDueno = contrasenaDueno;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getDniDueno() {
        return dniDueno;
    }

    public void setDniDueno(String dniDueno) {
        this.dniDueno = dniDueno;
    }

    public String getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(String telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }

    public String getEmailDueno() {
        return emailDueno;
    }

    public void setEmailDueno(String emailDueno) {
        this.emailDueno = emailDueno;
    }

    public String getContrasenaDueno() {
        return contrasenaDueno;
    }

    public void setContrasenaDueno(String contrasenaDueno) {
        this.contrasenaDueno = contrasenaDueno;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Dueño: " + nombreDueno + " (DNI: " + dniDueno + ")";
    }
}
