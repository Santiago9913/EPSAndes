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
import it.negocio.VOPaciente;
import it.negocio.VORol;
import it.view.View;

public class Controller {

	private static Logger log = Logger.getLogger(Controller.class.getName()); 

	/**
	 * Ruta de las tablas para la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

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
		crearConexion();

		while(!fin) {
			view.printMenu();

			int option = sc.nextInt();

			switch(option) {

			case 1: 
				view.printMessage("Ingrese el rol que desea registrar...");
				String rol = sc.next();
				agregarRol(rol);
				break; 

			case 2:
				view.printMessage("Ingrese la informacion del usuario...");
				view.printMessage("Rol: ");
				String rolUsuario = sc.next().toUpperCase(); 

				if(rolUsuario.toUpperCase().equals("PACIENTE")) {
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
			view.printMessage("Las restricciones fueron violadas");
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
