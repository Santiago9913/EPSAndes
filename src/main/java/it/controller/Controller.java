package it.controller;

import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import it.negocio.EPSAndes;
import it.negocio.VOMedico;
import it.negocio.VOPaciente;
import it.negocio.VORegistroMedico;
import it.negocio.VORol;
import it.view.View;

public class Controller {

	private static Logger log = Logger.getLogger(Controller.class.getName()); 

	/**
	 * Ruta de las tablas para la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	private static final String AD = "ADMINISTRADOR";
	private static final String PA = "PACIENTE";
	private static final String ME = "MEDICO";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private View view; 

	private JsonObject tableConfig;

	private EPSAndes epsAndes; 

	public Controller() {
		view = new View();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		boolean inicia = false;
		String usuario = "";
		crearConexion();

		while(!inicia) {
			view.printInicioSesion();
			usuario = sc.next();
			view.printMessage("Ingrese codigo:");
			int clave = sc.nextInt();
			inicia = true;
		}

		boolean admin = usuario.toUpperCase().equals(AD) ? true : false;
		boolean pac = usuario.toUpperCase().equals(PA) ? true : false;
		boolean med = usuario.toUpperCase().equals(ME) ? true : false;

		while(!fin) {
			view.printMenu();

			int option = sc.nextInt();

			switch(option) {

			//Permiso Administrador
			case 1: 
				if(admin) {
					view.printMessage("Ingrese el rol que desea registrar...");
					String rol = sc.next();
					agregarRol(rol);
					break; 					
				}

				view.printMessage("¡¡No posee permisos para esta operacion!!");
				break;

				//Permiso Administrador
			case 2:
				if(admin) {


					view.printMessage("Ingrese la informacion del usuario...");
					view.printMessage("Rol: ");
					view.printMessage("a) PACIENTE" + "\n"
							+ "b) MEDICO" + "\n"
							+ "c) GERENTE");
					String rolUsuario = sc.next(); 

					//Si el usuario es PACIENTE 
					if(rolUsuario.toLowerCase().equals("a")) {
						//Se ingresa el nombre
						view.printMessage("Ingrese el nombre: ");
						String nombrePaciente = sc.next(); 

						//Se ingresa el correo y se realiza el check
						view.printMessage("Ingrese el correo: ");
						String correoPaciente = sc.next(); 
						boolean correoCorrecto = false; 
						if(!correoPaciente.contains("@")) {
							while(!correoCorrecto) {
								view.printMessage("Ingrese un correo correcto...");
								correoPaciente = sc.next();
								if(correoPaciente.contains("@")) {
									correoCorrecto = true;
								}
							}

						}

						//Se ingresa la fecha de naciemiento
						view.printMessage("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
						String nacimientoPaciente = sc.next(); 
						String[] fechaSepa = nacimientoPaciente.split("-"); 
						int year = Integer.parseInt(fechaSepa[0]); 
						int mon = Integer.parseInt(fechaSepa[1]);
						int day = Integer.parseInt(fechaSepa[2]);
						Timestamp fechaPaciente = Timestamp.valueOf(LocalDateTime.of(year, mon, day, 0, 0)); 

						//Se ingresa el estado del paciente
						view.printMessage("Ingrese el estado del paciente: ");
						String estadoPaciente = sc.next(); 

						//Se ingresa el tipo de documento del paciente
						view.printMessage("Ingrese el tipo de documento (Pasaporte, Cedula, Tarjeta Identidad) : ");
						String tipoDoc = sc.next().toUpperCase();

						//Se ingresa id (numero doc) del paciente
						view.printMessage("Ingrese el numero de documento del paciente");
						int idPaciente = sc.nextInt(); 


						//Agregar el paciente
						agregarPaciente(idPaciente, nombrePaciente, correoPaciente, fechaPaciente, estadoPaciente, tipoDoc);
					}

					//Si el usuario es MEDICO
					else if(rolUsuario.toLowerCase().equals("b")) {
						//Se ingresa el nombre
						view.printMessage("Ingrese el nombre: ");
						String nombreMedico = sc.next(); 

						//Se ingresa el correo y se realiza el check
						view.printMessage("Ingrese el correo: ");
						String correoMedico = sc.next(); 
						boolean correoCorrecto = false; 
						if(!correoMedico.contains("@")) {
							while(!correoCorrecto) {
								view.printMessage("Ingrese un correo correcto...");
								correoMedico = sc.next();
								if(correoMedico.contains("@")) {
									correoCorrecto = true;
								}
							}

						}

						//Se ingresa la especialidad del medico
						view.printMessage("Ingrese la especialidad del medico: ");
						String especialidadMedico = sc.next(); 

						//Se ingresa id (numero doc) del medico
						view.printMessage("Ingrese el numero de documento del medico: ");
						long idMedico = sc.nextLong(); 

						view.printMessage("Ingrese el numero de registro medico: ");
						long numRegistro = sc.nextLong(); 


						//Agregar el paciente
						agregarMedico(idMedico, nombreMedico, correoMedico, especialidadMedico);
						agregarMedicoRegistro(idMedico, numRegistro); 
					}
					break; 
				}
				view.printMessage("¡¡No posee permisos para esta operacion!!");
				break;

			case 10:	
				fin = true;
				epsAndes.cerrarUP();
				sc.close();
				System.out.println("Conexion Cerrada");
				break;

			}
		}
	}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/

	/**
	 * 
	 */
	public void crearConexion() {
		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		epsAndes = new EPSAndes(tableConfig);
	}

	/**
	 * 
	 * @param rol
	 */
	public void agregarRol(String rol) {
		try {
			String nombreRol = rol.toUpperCase();
			if(nombreRol != null) {
				VORol ro = epsAndes.registrarRol(nombreRol);
				if(ro == null) {
					throw new Exception("No se pudo crear un rol con el nombre: " + nombreRol);
				}
				String resultado = "En registrarRol\n\n";
				resultado += "Rol de usuario adicionado exitosamente: " + ro;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			view.printMessage("Las restricciones fueron violadas");
			view.printErrMessage(e);

		}
	}

	/**
	 * 
	 * @param idPaciente
	 * @param nombrePaciente
	 * @param correoPaciente
	 * @param fechaNacimiento
	 * @param estadoPaciente
	 * @param tipoDoc
	 */
	public void agregarPaciente(long idPaciente, String nombrePaciente, String correoPaciente, Timestamp fechaNacimiento, String estadoPaciente, String tipoDoc) {

		try {
			long id = idPaciente;
			String nombre = nombrePaciente;
			String correo = correoPaciente;
			Timestamp fechaNac = fechaNacimiento;
			String estado = estadoPaciente;
			String tipoDocumento = tipoDoc; 

			if(id != 0 && nombre != null && correo != null && fechaNac != null && tipoDoc != null) {
				VOPaciente pa = epsAndes.registrarPaciente(id, nombre, correo, fechaNac, estado, tipoDocumento);
				if(pa == null) {
					throw new Exception ("No se pudo crear el usuario con el rol: " + "PACIENTE");
				}
				String resultado = "En registrarPaciente\n\n";
				resultado += "Paciente adicionado exitosamente: " + pa;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}

	/**
	 * 
	 * @param idMedico
	 * @param nombre
	 * @param correo
	 * @param especialidad
	 */
	public void agregarMedico(long idMedico, String nombre, String correo, String especialidad) {

		try {
			long id = idMedico;
			String nombreMedico = nombre; 
			String correoMedico = correo; 
			String espec = especialidad;

			if(nombre != null && id != 0 && correoMedico != null) {
				VOMedico me = epsAndes.registrarMedico(id, nombreMedico, correoMedico, espec);
				if(me == null) {
					throw new Exception ("No se pudo crear el medico");
				}
				String resultado = "En registrarMedico\n\n";
				resultado += "Medico adicionado exitosamente: " + me;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @param num
	 */
	public void agregarMedicoRegistro(long id, long num) {
		try {
			long idMedico = id; 
			long numeroReg = num; 

			if(idMedico > 0 && numeroReg > 0 ) {
				VORegistroMedico rm =  epsAndes.registrarRegistroMedico(idMedico, numeroReg);
				if(rm == null) {
					throw new Exception("No se pudo agregar el registro al medico");
				}
				String resultado = "En registrarRegistroMedico\n\n";
				resultado += "Registro Medico adicionado exitosamente: " + rm;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}


	/**
	 * 
	 * @param tipo
	 * @param archConfig
	 * @return
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
}
