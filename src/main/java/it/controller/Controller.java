package it.controller;

import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import it.negocio.EPSAndes;
import it.negocio.VOEPS;
import it.negocio.VOGerente;
import it.negocio.VOIPS;
import it.negocio.VOMedico;
import it.negocio.VOPaciente;
import it.negocio.VORegistroMedico;
import it.negocio.VORol;
import it.negocio.VOSecretaria;
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
	private static final String RE = "RECEPCIONISTA"; 

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
			usuario = sc.next().toUpperCase();
			view.printMessage("Ingrese codigo:");
			int clave = sc.nextInt();
			if(usuario.equals("ADMINISTRADOR") || usuario.equals(PA) || usuario.equals(ME) || usuario.equals(RE)) 
				inicia = true;				

		}

		boolean admin = usuario.toUpperCase().equals(AD) ? true : false;
		boolean pac = usuario.toUpperCase().equals(PA) ? true : false;
		boolean med = usuario.toUpperCase().equals(ME) ? true : false;
		boolean recp = usuario.equals(RE) ? true : false;

		while(!fin) {
			if(admin) {
				view.printMenuAdmin();	
				int optionAdmin = sc.nextInt();

				//Vista de administrador
				switch(optionAdmin) {

				case 1: 

					view.printMessage("Ingrese el rol que desea registrar...");
					String rol = sc.next();
					agregarRol(rol.toUpperCase());
					break; 

					//Permiso Administrador
				case 2:
					view.printMessage("Ingrese la informacion del usuario...");
					view.printMessage("Rol: ");
					view.printRoles();
					String rolUsuario = sc.next(); 

					//Si el usuario es PACIENTE 
					if(rolUsuario.toLowerCase().equals("a")) {
						//Se ingresa el nombre
						view.printMessage("Ingrese el nombre y apellido: ");
						String nombrePaciente = sc.next(); 
						nombrePaciente = nombrePaciente.concat(sc.nextLine());

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
						agregarPaciente(idPaciente, nombrePaciente.toUpperCase(), correoPaciente.toUpperCase(), fechaPaciente, estadoPaciente.toUpperCase(), tipoDoc.toUpperCase());
					}

					//Si el usuario es MEDICO
					else if(rolUsuario.toLowerCase().equals("b")) {
						//Se ingresa el nombre
						view.printMessage("Ingrese el nombre y apellido: ");
						String nombreMedico = sc.next();
						nombreMedico = nombreMedico.concat(sc.nextLine());

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


						//Agregar el medico
						agregarMedico(idMedico, nombreMedico.toUpperCase(), correoMedico.toUpperCase(), especialidadMedico.toUpperCase());
						//Agregar el registro medico 
						agregarMedicoRegistro(idMedico, numRegistro); 		
					}

					else if(rolUsuario.toLowerCase().endsWith("c")) {
						view.printMessage("Ingrese el nombre y apellido: ");
						String nombreGerente = sc.next();
						nombreGerente = nombreGerente.concat(sc.nextLine());

						//Se ingresa el correo y se realiza el check
						view.printMessage("Ingrese el correo: ");
						String correoGerente= sc.next(); 
						boolean correoCorrecto = false; 
						if(!(correoGerente.contains("@") && correoGerente.contains(".com"))) {
							while(!correoCorrecto) {
								view.printMessage("Ingrese un correo correcto...");
								correoGerente = sc.next();
								if(correoGerente.contains("@") && correoGerente.contains(".com")) {
									correoCorrecto = true;
								}
							}
						}

						//Se ingresa id 
						view.printMessage("Ingrese el numero de documento: ");
						long idGerente = sc.nextLong(); 

						agregarGerente(idGerente, nombreGerente.toUpperCase(), correoGerente.toUpperCase());


					}

					//Si el usuario es recepcionista
					else if(rolUsuario.toLowerCase().equals("d")) {
						view.printMessage("Ingrese el nombre y apellido: ");
						String nombreRecepcionista = sc.next();
						nombreRecepcionista = nombreRecepcionista.concat(sc.nextLine());

						//Se ingresa el correo y se realiza el check
						view.printMessage("Ingrese el correo: ");
						String correoRecepcionista = sc.next(); 
						boolean correoCorrecto = false; 
						if(!(correoRecepcionista.contains("@") && correoRecepcionista.contains(".com"))) {
							while(!correoCorrecto) {
								view.printMessage("Ingrese un correo correcto...");
								correoRecepcionista = sc.next();
								if(correoRecepcionista.contains("@") && correoRecepcionista.contains(".com")) {
									correoCorrecto = true;
								}
							}
						}

						//Se ingresa id 
						view.printMessage("Ingrese el numero de documento: ");
						long idRecepcionista = sc.nextLong(); 

						agregarRecepcionista(idRecepcionista, nombreRecepcionista.toUpperCase(), correoRecepcionista.toUpperCase());
					}
					break; 

					//Agregar una EPS
				case 3: 
					view.printMessage("Ingrese el nombre de la EPS que desea agregar");
					String nombreEPS = sc.next();
					nombreEPS = nombreEPS.concat(sc.nextLine());

					//Agrega la eps
					agregarEPS(nombreEPS.toUpperCase());
					break;
				case 4:
					view.printMessage("Ingrese el nombre de la IPS:  ");
					String nombreIPS = sc.next();
					nombreIPS = nombreIPS.concat(sc.nextLine());

					view.printMessage("Ingrese la capacidad de la IPS: ");
					int capacidad = sc.nextInt();

					view.printMessage("Ingrese la localizacion de la IPS");
					String loc = sc.next();
					loc = loc.concat(sc.nextLine());

					//Agrega la IPS
					agregarIPS(nombreIPS.toUpperCase(), capacidad, loc.toUpperCase());



					//Cierra la conexion 
				case 10:	
					fin = true;
					epsAndes.cerrarUP();
					sc.close();
					System.out.println("Conexion Cerrada");
					break;
				}
			}
			else if (med) {
				view.printMenuMedico();	
				int option = sc.nextInt();
				
				switch (option) {
				case 1:
					view.printMessage("Ingrese la descipci�n de la orden:  ");
					String desc = sc.next();
					
					view.printMessage("�Adicionar servicios adicionales? (Y/N) ");
					String yn1 = sc.next();
					String servicio = null;
					Timestamp horario = null;
					boolean sa = false;
					if (yn1.equalsIgnoreCase("Y")) {
						sa = true;
						view.printMessage("Ingrese el nombre del servicio");
						servicio = sc.next();
						view.printMessage("Ingrese el horario (MM-dd-hh-mm): ");
						String horar = sc.next(); 
						String[] fechaSepa = horar.split("-"); 
						int mon = Integer.parseInt(fechaSepa[0]);
						int day = Integer.parseInt(fechaSepa[1]);
						int hor = Integer.parseInt(fechaSepa[2]);
						int min = Integer.parseInt(fechaSepa[3]);
						horario = Timestamp.valueOf(LocalDateTime.of(2019, mon, day, hor, min));
					}
					
					view.printMessage("�Adicionar una receta?");
					String yn2 = sc.next();
					ArrayList<String> meds = new ArrayList<>();
					boolean ar = false;
					if (yn2.equalsIgnoreCase("Y")) {
						ar = true;
						view.printMessage("Ingrese el n�mero de medicamentos");
						int noMed = sc.nextInt();
						int temp = 0;
						while (temp < noMed) {
							view.printMessage("Ingrese medicamento #" + (temp+1) );
							meds.add(sc.next());
							temp++;
						}
					}
					
					epsAndes.registrarOrden(desc, horario, servicio, meds);
					break;
				case 0:	
					fin = true;
					epsAndes.cerrarUP();
					sc.close();
					System.out.println("Conexion Cerrada");
					break;
				}
			}
			else if (pac) {
				view.printMenuPaciente();	
				int option = sc.nextInt();
				switch (option) {
				case 1:
					view.printMessage("Horarios disponibles: ");
					epsAndes.consultarHorarios();
					view.printMessage("Ingrese el horario para reservar (MM-dd-hh-mm): ");
					String horar = sc.next(); 
					String[] fechaSepa = horar.split("-"); 
					int mon = Integer.parseInt(fechaSepa[0]);
					int day = Integer.parseInt(fechaSepa[1]);
					int hor = Integer.parseInt(fechaSepa[2]);
					int min = Integer.parseInt(fechaSepa[3]);
					Timestamp horario = Timestamp.valueOf(LocalDateTime.of(2019, mon, day, hor, min));
					epsAndes.reservarConsulta(horario);
					break;
				}
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

	public void agregarGerente(long id, String nombre, String correo) {
		try {
			long idGerente = id; 
			String nomGerente = nombre; 
			String correoGerente = correo; 

			if(id > 0 && nomGerente != null && correoGerente != null) {
				VOGerente re = epsAndes.registrarGerente(idGerente, nomGerente, correoGerente); 
				if(re == null) {
					throw new Exception("No se pudo agregar recepcionista");
				}
				String resultado = "En registrarRecepcionista\n\n";
				resultado += "Recepcionista adicionado exitosamente: " + re;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}

	public void agregarRecepcionista(long id, String nombre, String correo) {
		try {
			long idRecep = id; 
			String nomRecep = nombre; 
			String correoRecep = correo; 

			if(id > 0 && nomRecep != null && correoRecep != null) {
				VOSecretaria re = epsAndes.registrarRecepcionista(idRecep, nomRecep, correoRecep); 
				if(re == null) {
					throw new Exception("No se pudo agregar recepcionista");
				}
				String resultado = "En registrarRecepcionista\n\n";
				resultado += "Recepcionista adicionado exitosamente: " + re;
				resultado += "\n Operacion terminada"; 
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}

	public void agregarEPS(String nombre) {
		try {
			String nom = nombre; 
			if(nom != null) {
				VOEPS eps = epsAndes.registrarEPS(nom);
				if(eps == null) {
					throw new Exception("No se pudo agregar EPS");
				}
				String resultado = "En registrarEPS\n\n";
				resultado += "EPS adicionado exitosamente: " + eps;
				resultado += "\n Operacion terminada";
				view.printMessage(resultado);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			view.printErrMessage(e);
		}
	}

	public void agregarIPS(String nombre, int capacidad, String localizacion) {
		try {
			String nom = nombre;
			int cap = capacidad;
			String loc = localizacion;

			if(nom != null && cap > 0 && loc != null) {
				VOIPS ips = epsAndes.registrarIPS(nom, loc, cap);
				if(ips == null) {
					throw new Exception("No se puedo agregar IPS");
				}
				String resultado = "En registrarIPS\n\n";
				resultado += "EPS adicionado exitosamente: " + ips;
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
			log.info ("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
}
