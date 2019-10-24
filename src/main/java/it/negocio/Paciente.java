package it.negocio;

import java.sql.Timestamp;
import java.util.Date;

public class Paciente extends Usuario implements VOUsuario, VOPaciente {

	public String estadoSalud;

	public Paciente(){
		super();
		this.estadoSalud = "";
	}

	public Paciente(String nombre, Date fechaNacimiento, long id, String correo, String tipoDocumento, String tipoUsuario, String estadoSalud){
		super(nombre, fechaNacimiento, id, correo, tipoDocumento, tipoUsuario);
		this.estadoSalud = estadoSalud;
	}


	public String getEstadoSalud() {
		return this.estadoSalud;
	}

	@Override
	public String toString() {
		return "Tipo Usuario = " + tipoUsuario + " [ " + "Nombre: " + nombre + ", " +  "ID: " + id + ", " + "Tipo Doc: " +  tipoDocumento + ", " + "Correo: " +  correo  + "Estado Salud: " + estadoSalud + " ]";
	}
}
