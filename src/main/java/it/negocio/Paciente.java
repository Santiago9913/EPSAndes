package it.negocio;

import java.sql.Timestamp;
import java.util.Date;

public class Paciente extends Usuario implements VOUsuario, VOPaciente {

    public String estadoSalud;

    public long epsAoscidad;

    public Paciente() {
        super();
        this.estadoSalud = "";
        epsAoscidad = 0;
    }

    public Paciente(String nombre, Date fechaNacimiento, long id, String correo, String tipoDocumento, String tipoUsuario, String estadoSalud, long idEps) {
        super(nombre, fechaNacimiento, id, correo, tipoDocumento, tipoUsuario);
        this.estadoSalud = estadoSalud;
        epsAoscidad = idEps;
    }

    public Paciente(long numDocumento, long idEps, String estado) {
        this.id = numDocumento;
        this.epsAoscidad = idEps;
        this.estadoSalud = estado;
    }


    public String getEstadoSalud() {
        return this.estadoSalud;
    }

    @Override
    public String toString() {
        return "Tipo Usuario = " + tipoUsuario + " [ " + "Nombre: " + nombre + ", " + "ID: " + id + ", " + "Tipo Doc: " + tipoDocumento + ", " + "Correo: " + correo + "Estado Salud: " + estadoSalud + " ]";
    }
}
