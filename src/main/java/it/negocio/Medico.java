package it.negocio;

import java.util.Date;

public class Medico extends Usuario implements VOUsuario, VOMedico {

    private long numeroRegistro;
    private String tipoMedico;

    public Medico() {
        super();
        this.tipoMedico = "";
        this.numeroRegistro = 0;
    }

    public Medico(String nombre, Date fechaNacimiento, long id, String correo, String tipoDocumento, String tipoUsuario, long numeroRegistro, String tipoMedico) {
        super(nombre, fechaNacimiento, id, correo, tipoDocumento, tipoUsuario);
        this.tipoMedico = tipoMedico;
        this.numeroRegistro = numeroRegistro;
    }


    public long getNumeroRegistro() {
        return this.numeroRegistro;
    }


    public String getTipoMedico() {
        return this.tipoMedico;
    }

    public void setNumeroRegistro(long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public void setTipoMedico(String tipoMedico) {
        this.tipoMedico = tipoMedico;
    }

    @Override
    public String toString() {
        return "Tipo Usuario = " + tipoUsuario + " [ " + "Nombre: " + nombre + ", " + "ID: " + id + ", " + "Tipo Doc: " + tipoDocumento + ", " + "Correo: " + correo +
                "Numero Registro: " + numeroRegistro + ", " + "Tipo Medico: " + tipoMedico + " ]";
    }
}
