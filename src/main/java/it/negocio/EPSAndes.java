package it.negocio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import it.persistencia.PersistenciaEPSAndes;;

public class EPSAndes {
	private static Logger log = Logger.getLogger(EPSAndes.class.getName());

	private PersistenciaEPSAndes ep;

	public EPSAndes() {
		ep = PersistenciaEPSAndes.getInstance();
	}

	public EPSAndes(JsonObject tableConfig) {
		ep = PersistenciaEPSAndes.getInstance(tableConfig);
	}

	public void cerrarUP() {
		ep.cerrarUnidadPersistencia();
	}

	// M�todos

	public Rol registrarRol(String nombre) {
		log.info ("Registrando rol de usuario: " + nombre);
		Rol newRol = ep.registrarRol(nombre);
		log.info("Registrando rol de usuario: " + newRol);
		return newRol;
	}

	public Paciente registrarPaciente(long id, String nombre, String correo, Timestamp fNac, String estado, String tipoDoc) {
		log.info("Registrando paciente: " + nombre);
		Paciente pac = ep.registrarPaciente(id, nombre, correo, fNac, estado, tipoDoc);
		log.info("Registrando paciente: " + pac);
		return pac;
	}

	public Medico registrarMedico(long id, String nombre, String correo, String especialidad) {
		log.info("Registrando m�dico: " + nombre);
		Medico med = ep.registrarMedico(id, nombre, correo, especialidad);
		log.info("Registrando m�dico: " + med);
		return med;
	}

	public RegistroMedico registrarRegistroMedico(long idMedico, long numRegistro) {
		log.info("Registrando registro_medico: " + numRegistro + " , " + idMedico);
		RegistroMedico regM = ep.registrarRegistroMedico(idMedico, numRegistro);
		log.info("Registrando tupla: " + "( " + idMedico +" , " + numRegistro + " )");
		return regM;
	}

	public Secretaria registrarRecepcionista(long id, String nombre, String correo) {
		log.info("Registrando recepcionista: " + id + " , " + nombre + " , " + correo);
		Secretaria recep = ep.registrarRecepcionista(id, nombre, correo); 
		log.info("Registrando recepcionista: " + nombre);
		return recep; 
	}
	public Administrador registrarAdministrador(String nombre, String correo) {
		log.info("Registrando administrador: " + nombre);
		Administrador admin = ep.registrarAdministrador( nombre, correo);
		log.info("Registrando administrador: " + admin);
		return admin;
	}

	public Gerente registrarGerente(long id, String nombre, String correo) {
		log.info("Registrando gerente: " + nombre);
		Gerente ger = ep.registrarGerente(id, nombre, correo);
		log.info("Registrando gerente: " + ger);
		return ger;
	}

	public EPS registrarEPS(String nombre) {
		log.info("Registrando EPS: " + nombre);
		EPS eps = ep.registrarEPS(nombre);
		log.info("Registrando EPS: " + eps);
		return eps;
	}

	public IPS registrarIPS(String nombre, String localizacion, int cantidad) {
		log.info("Registrando IPS: " + nombre);
		IPS ips = ep.registrarIPS(nombre, cantidad, localizacion);
		log.info("Registrando IPS: " + ips);
		return ips;
	}

	public Servicio registrarServicio(int capacidad, List<Horario> horarios, String nombre) {
		log.info("Registrando Servicio: " + nombre);
		Servicio ser = ep.registrarServicio(nombre, capacidad);
		log.info("Registrando Servicio: " + ser);
		return ser;
	}

	public void registrarOrden(String desc, Timestamp horario, String servicio, ArrayList<String> meds) {
		// TODO Auto-generated method stub
		
	}

	public void registrarConsulta(String servicio, Timestamp horario) {
		// TODO Auto-generated method stub
		
	}

	public void consultarHorarios() {
		// TODO Auto-generated method stub
		
	}

	public void reservarConsulta(Timestamp horario) {
		// TODO Auto-generated method stub
		
	}

	public int rfc1(Timestamp fecha1, Timestamp fecha2) {
		// TODO Auto-generated method stub
		log.info("Consultando cantidad de servicios prestados desde " + fecha1.toString() + " hasta " + fecha2.toString());
		int ser = ep.rfc1(fecha1, fecha2);
		log.info("Saliendo de consultar cantidad de servicios prestados");
		return ser;
	}
}
