package it.controller;

import java.io.FileReader;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import javax.sound.midi.Soundbank;
import javax.swing.JOptionPane;

import it.negocio.*;
import javafx.util.converter.TimeStringConverter;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import it.view.View;
import sun.util.resources.ro.CurrencyNames_ro_RO;

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
    private static final String OR = "ORGANIZADOR CAMPANA";

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/

    private View view;

    private JsonObject tableConfig;

    private EPSAndes epsAndes;

    private List<EPS> listaEPS;

    private List<IPS> listaIPS;

    private List<Servicio> listaServicios;

    private List<Servicio> listaServiciosReservados;

    private List<Medicamento> listaMedicamentos;

    private List<Paciente> listaPacientes;

    private List<Horario> listaHorarios;

    public Controller() {
        view = new View();
        listaEPS = new ArrayList<>();
        listaIPS = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaMedicamentos = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        listaServiciosReservados = new ArrayList<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean fin = false;
        boolean inicia = false;

        String usuario = "";
        crearConexion();
        listaEPS = darListaEps();
        listaIPS = darListaIps();
        listaMedicamentos = darListaMedicamentos();
        listaPacientes = darListaPacientes();

        while (!inicia) {
            view.printInicioSesion();
            usuario = sc.next().toUpperCase();
            view.printMessage("Ingrese codigo:");
            int clave = sc.nextInt();
            if (usuario.toUpperCase().equals(AD) || usuario.toUpperCase().equals(PA) || usuario.toUpperCase().equals(ME) || usuario.toUpperCase().equals(RE))
                inicia = true;

        }

        boolean admin = usuario.toUpperCase().equals(AD);
        boolean pac = usuario.toUpperCase().equals(PA);
        boolean med = usuario.toUpperCase().equals(ME);
        boolean recp = usuario.equals(RE);

        while (!fin) {
            listaEPS = darListaEps();
            if (admin) {
                view.printMenuAdmin();
                int optionAdmin = sc.nextInt();

                //Vista de administrador
                switch (optionAdmin) {

                    //Registrar un usuario
                    case 1:
                        view.printMessage("Ingrese la informacion del usuario...");
                        view.printMessage("Rol: ");
                        view.printRoles();
                        String rolUsuario = sc.next();

                        //Crear usuario paciente
                        if (rolUsuario.toLowerCase().equals("a")) {
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombrePaciente = sc.next();
                            nombrePaciente = nombrePaciente.concat(sc.nextLine());

                            view.printMessage("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
                            String fechaNacimientoPaciente = sc.next();
                            String[] splitFechaPaciente = fechaNacimientoPaciente.split("-");
                            int year = Integer.parseInt(splitFechaPaciente[0]);
                            int mon = Integer.parseInt(splitFechaPaciente[1]);
                            int day = Integer.parseInt(splitFechaPaciente[2]);
                            Timestamp fechaPaciente = Timestamp.valueOf(LocalDateTime.of(year, mon, day, 0, 0));

                            view.printMessage("Ingrese el tipo de documento: ");
                            String tipoDocPaciente = sc.next();
                            tipoDocPaciente = tipoDocPaciente.concat(sc.nextLine());

                            view.printMessage("Ingrese el numero de documento: ");
                            long numDocumentoPaciente = sc.nextInt();

                            view.printMessage("Ingrese el correo: ");
                            String correoPaciente = sc.next();

                            view.printMessage("Ingrese el estado de salud: ");
                            String estadoSaludPaciente = sc.next();
                            estadoSaludPaciente = estadoSaludPaciente.concat(sc.nextLine());

                            view.printMessage("Seleccione la Eps a la que sera inscrito: ");
                            for (EPS eps : listaEPS) {
                                int i = 1;
                                view.printMessage(i + ". " + eps.getNombre());
                                i++;
                            }
                            String epsPaciente = sc.next();
                            epsPaciente = epsPaciente.concat(sc.nextLine());

                            agregarUsuario((int) numDocumentoPaciente, 0, null, nombrePaciente.toUpperCase(), correoPaciente.toUpperCase(), tipoDocPaciente.toUpperCase(), PA);
                            agregarPaciente(numDocumentoPaciente, getidEps(epsPaciente.toUpperCase()), estadoSaludPaciente.toUpperCase());

                            break;

                        }

                        //crear usuario medico
                        else if (rolUsuario.toLowerCase().equals("b")) {
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombreMedico = sc.next();
                            nombreMedico = nombreMedico.concat(sc.nextLine());

                            view.printMessage("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
                            String fechaNacimientoMedico = sc.next();
                            String[] splitFechaMedico = fechaNacimientoMedico.split("-");
                            int year = Integer.parseInt(splitFechaMedico[0]);
                            int mon = Integer.parseInt(splitFechaMedico[1]);
                            int day = Integer.parseInt(splitFechaMedico[2]);
                            Timestamp fechaMedico = Timestamp.valueOf(LocalDateTime.of(year, mon, day, 0, 0));

                            view.printMessage("Ingrese el tipo de documento: ");
                            String tipoDocMedico = sc.next();
                            tipoDocMedico = tipoDocMedico.concat(sc.nextLine());

                            view.printMessage("Ingrese el numero de documento: ");
                            long numDocumentoMedico = sc.nextInt();

                            view.printMessage("Ingrese el correo: ");
                            String correoMedico = sc.next();

                            view.printMessage("Ingrese el numero de registro medico: ");
                            long numregistroMedico = sc.nextInt();

                            view.printMessage("Ingrese la especialidad : ");
                            String especialidadMedico = sc.next();
                            especialidadMedico = especialidadMedico.concat(sc.nextLine());

                            view.printMessage("Seleccione la(s) Ips a la que sera inscrito: ");
                            view.printIpsLista(darListaIps());
                            view.printMessage("Cantidad de Ips a las que sera agregado: ");
                            int cantidadMedicoIps = sc.nextInt();
                            long[] ipsMedicoId = new long[cantidadMedicoIps];
                            int contadorIpsMedicoId = 0;
                            String ipsMedico = "";

                            while (contadorIpsMedicoId < ipsMedicoId.length) {
                                view.printMessage("Ingrese el nombre: ");
                                ipsMedico = sc.next();
                                ipsMedico = ipsMedico.concat(sc.nextLine());
                                ipsMedicoId[contadorIpsMedicoId] = getidIps(ipsMedico);
                                contadorIpsMedicoId++;
                            }

                            agregarUsuario((int)numDocumentoMedico, 0, null, nombreMedico, correoMedico, tipoDocMedico, ME);
                            agregarMedico(numDocumentoMedico, numregistroMedico, especialidadMedico.toUpperCase());
                            for (long lo : ipsMedicoId) {
                                agregarMedicoAIps(numregistroMedico, lo);
                            }


                            break;

                        } else if (rolUsuario.toLowerCase().endsWith("c")) {
                            break;


                        }

                        //Si el usuario es recepcionista
                        else if (rolUsuario.toLowerCase().equals("d")) {

                            break;
                        }

                        //Si el usuario es Organizador Campaña
                        else if (rolUsuario.toLowerCase().equals("e")) {
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombreOrganizador = sc.next();
                            nombreOrganizador = nombreOrganizador.concat(sc.nextLine());

                            view.printMessage("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
                            String fechaNacimientoOrganizador = sc.next();
                            String[] splitFechaOrganizador = fechaNacimientoOrganizador.split("-");
                            int year = Integer.parseInt(splitFechaOrganizador[0]);
                            int mon = Integer.parseInt(splitFechaOrganizador[1]);
                            int day = Integer.parseInt(splitFechaOrganizador[2]);
                            Timestamp fechaOrganizador = Timestamp.valueOf(LocalDateTime.of(year, mon, day, 0, 0));

                            view.printMessage("Ingrese el tipo de documento: ");
                            String tipoDocOrganizador = sc.next();
                            tipoDocOrganizador = tipoDocOrganizador.concat(sc.nextLine());

                            view.printMessage("Ingrese el numero de documento: ");
                            long numDocumentoOrganizador = sc.nextInt();

                            view.printMessage("Ingrese el correo: ");
                            String correoOrganizador = sc.next();

                            agregarUsuario((int)numDocumentoOrganizador, 0, null, nombreOrganizador, correoOrganizador, tipoDocOrganizador, OR);

                            break;
                        }

                        //Agregar una EPS
                    case 2:
                        view.printMessage("Ingrese el nombre de la EPS que desea agregar");
                        String nombreEPS = sc.next();
                        nombreEPS = nombreEPS.concat(sc.nextLine());

                        agregarEPS(nombreEPS.toUpperCase());
                        break;
                    //Agregar un IPS
                    case 3:
                        view.printMessage("Ingrese el nombre de la Ips: ");
                        String nombreIps = sc.next();
                        nombreIps = nombreIps.concat(sc.nextLine());

                        view.printMessage("Ingrese a la Eps que presta servicio: ");
                        view.printEpsList(darListaEps());
                        String prestaEps = sc.next();
                        prestaEps = prestaEps.concat(sc.nextLine());
                        long prestaEpsId = getidEps(prestaEps.toUpperCase());

                        view.printMessage("Ingrese la capacidad de la Ips: ");
                        int capacidadIps = sc.nextInt();

                        view.printMessage("Ingrese la direccion de la Ips: ");
                        String direccionIps = sc.next();
                        direccionIps = direccionIps.concat(sc.nextLine());

                        agregarIPS(nombreIps.toUpperCase(), prestaEpsId, capacidadIps, direccionIps.toUpperCase());
                        break;

                    //Agregar un servicio
                    case 4:
                        view.printMessage("Ingrese el servicio que desea agregar");
                        view.printServiciosMenu();
                        String respSer = sc.next();
                        String servicioNombre = "";
                        if (respSer.equalsIgnoreCase("a")) {
                            servicioNombre = "Procedimiento Medico".toUpperCase();
                        } else if (respSer.equalsIgnoreCase("b")) {
                            servicioNombre = "Hospitalizacion".toUpperCase();
                        } else if (respSer.equalsIgnoreCase("c")) {
                            servicioNombre = "Examen Diagnostico".toUpperCase();
                        } else if (respSer.equalsIgnoreCase("d")) {
                            servicioNombre = "Terapia".toUpperCase();
                        }

                        view.printMessage("Ingrese en que horario atiende este servicio: ");
//                        view.printHorarios();
                        int idHorarioServicio = sc.nextInt();

                        view.printMessage("ingrese la capacidad del servicio");
                        int capacidadServicio = sc.nextInt();

                        view.printMessage("Ingrese a cuantas Ips se agrega este servicio: ");
                        int cantidadServiciosIps = sc.nextInt();


                        break;
                    //Agregar camapaña
                    case 5:
                        System.out.println("Ingrese la cantidad de personas a inscribir");
                        int c_personas = sc.nextInt();
                        System.out.println("Ingrese el numero de servicios de la campana");
                        int numSer = sc.nextInt();
                        while (numSer <= 0) {
                            System.out.println("Numero de servicios inválido, ingrese un numero valido");
                            numSer = sc.nextInt();
                        }
                        ArrayList<String> servs = new ArrayList<>();
                        int i = 0;
                        while (numSer > i++) {
                            System.out.println("Ingrese el nombre del servicio #" + i);
                            servs.add(sc.next());
                        }
                        System.out.println("Ingrese el nombre de la EPS");
                        String eps = sc.next();
                        view.printMessage("Ingrese el periodo de tiempo: (Ej.: mm1-dd1/mm2-dd2)");
                        String input = sc.next();
                        String[] periodos = input.split("/");
                        String[] periodo1 = periodos[0].split("-");
                        String[] periodo2 = periodos[1].split("-");
                        int m1 = Integer.parseInt(periodo1[0]);
                        int d1 = Integer.parseInt(periodo1[1]);
                        Date f_inicio = Date.valueOf(LocalDate.of(2019, m1, d1));
                        int m2 = Integer.parseInt(periodo2[0]);
                        int d2 = Integer.parseInt(periodo2[1]);
                        Date f_fin = Date.valueOf(LocalDate.of(2019, m2, d2));
                        while (f_inicio.compareTo(f_fin) > 0 || f_inicio.compareTo(f_fin) == 0) {
                            view.printMessage("La primera fecha debe ser menor que la segunda fecha");
                            view.printMessage("Vuelva a ingresar el periodo de tiempo: (Ej.: mm1-dd1/mm2-dd2)");
                            input = sc.next();
                            periodos = input.split("/");
                            periodo1 = periodos[0].split("-");
                            periodo2 = periodos[1].split("-");
                            m1 = Integer.parseInt(periodo1[1]);
                            d1 = Integer.parseInt(periodo1[2]);
                            f_inicio = Date.valueOf(LocalDate.of(2019, m1, d1));
                            m2 = Integer.parseInt(periodo2[1]);
                            d2 = Integer.parseInt(periodo2[2]);
                            f_fin = Date.valueOf(LocalDate.of(2019, m2, d2));
                        }
                        //Registro del usuario del organizador
                        System.out.println("Ingrese la identificacion del organizador");
                        int idOrg = sc.nextInt();
                        System.out.println("Ingrese el nombre del organizador");
                        String nOrg = sc.next();
                        System.out.println("Ingrese el correo del organizador");
                        String cOrg = sc.next();
                        System.out.println("Ingrese el tipo de documento del organizador");
                        String tdOrg = sc.next();
                        Usuario org = new Usuario(idOrg, nOrg, cOrg, tdOrg, "ORGANIZADOR CAMPANA");
                        registrarCampana(org, c_personas, servs, f_inicio, f_fin, eps);
                        break;

                    // Cancelar servicio de la camppaña
                    case 6:


                        break;
                    // Deshabilitar servicio
                    case 7:
                        view.printMessage("Indique la Ips den donde desea deshabilitar el servicio: ");
                        view.printIpsLista(listaIPS);
                        view.printMessage("Ingrese el nombre de la Ips: ");
                        String nombreIpsDesh = sc.next();
                        nombreIpsDesh = nombreIpsDesh.concat(sc.nextLine());
                        long ipsIdDesh = getidIps(nombreIpsDesh.toUpperCase());
                        System.out.println();

                        view.printMessage("Servicios en Ips: ");
                        view.printListaServicios(darListaServicios(ipsIdDesh));
                        view.printMessage("Seleccion el Id del servicio: ");
                        long idServicioDesh = sc.nextInt();
                        System.out.println();


                        view.printMessage("Ingrese la fecha en la que inicia la inhabilitacion (dd/mm/yyyy): ");
                        String inicioInhabilitacion = sc.next();
                        view.printMessage("Ingrese el inicio en la que finaliza la inhabilitacion (dd/mm/yyyy): ");
                        String finInhabilitacion = sc.next();
                        String[] parseInicio = inicioInhabilitacion.split("/");
                        String[] parseFin = finInhabilitacion.split("/");
                        int diaInicioInh = Integer.parseInt(parseInicio[0]);
                        int mesInicioInh = Integer.parseInt(parseInicio[1]);
                        int anoInicioInh = Integer.parseInt(parseInicio[2]);
                        int diaFinInh = Integer.parseInt(parseFin[0]);
                        int mesFinInh = Integer.parseInt(parseFin[1]);
                        int anoFinInh = Integer.parseInt(parseFin[2]);

                        Date inicioInhabilitacionServicio = Date.valueOf(LocalDate.of(anoInicioInh, mesInicioInh, diaInicioInh));
                        Date finInhabilitacionServicio = Date.valueOf(LocalDate.of(anoFinInh, mesFinInh, diaFinInh));


                        deshabilitarServicio(idServicioDesh, ipsIdDesh, inicioInhabilitacionServicio, finInhabilitacionServicio);
                        break;

                    // Registrar reapertura
                    case 8:
                        System.out.println("Ingrese la cantidad de servicios a reabrir: ");
                        int cReab = sc.nextInt();
                        Hashtable<Integer, ArrayList<Integer>> ht = new Hashtable<>();
                        i = 0;
                        while (cReab > i++) {
                            System.out.println("Ingrese el id de la IPS del servicio #"+i+" y su id asi: (IPS/Servicio)");
                            input = sc.next();
                            String[] ipsSer = input.split("/");
                            int idIps = Integer.parseInt(ipsSer[0]);
                            int idSer = Integer.parseInt(ipsSer[1]);
                            if (ht.containsKey(idIps))
                                ht.get(idIps).add(idSer);
                            else {
                                ArrayList<Integer> serList = new ArrayList<>();
                                serList.add(idSer);
                                ht.put(idIps, serList);
                            }
                        }
                        reabrirServicios(ht);

                        break;
                    // RFC 1
                    case 9:
                        view.printMessage("Ingrese el periodo de tiempo: (Ej.: mm1-dd1-aaaa1/mm2-dd2-aaa2)");
                        input = sc.next();
                        periodos = input.split("/");
                        periodo1 = periodos[0].split("-");
                        periodo2 = periodos[1].split("-");
                        m1 = Integer.parseInt(periodo1[0]);
                        d1 = Integer.parseInt(periodo1[1]);
                        int a1 = Integer.parseInt(periodo1[2]);
                        f_inicio = Date.valueOf(LocalDate.of(a1, m1, d1));
                        m2 = Integer.parseInt(periodo2[0]);
                        d2 = Integer.parseInt(periodo2[1]);
                        int a2 = Integer.parseInt(periodo2[2]);
                        f_fin = Date.valueOf(LocalDate.of(a2, m2, d2));
                        while (f_inicio.compareTo(f_fin) > 0 || f_inicio.compareTo(f_fin) == 0) {
                            view.printMessage("La primera fecha debe ser menor que la segunda fecha");
                            view.printMessage("Vuelva a ingresar el periodo de tiempo: (Ej.: mm1-dd1/mm2-dd2)");
                            input = sc.next();
                            periodos = input.split("/");
                            periodo1 = periodos[0].split("-");
                            periodo2 = periodos[1].split("-");
                            m1 = Integer.parseInt(periodo1[1]);
                            d1 = Integer.parseInt(periodo1[2]);
                            f_inicio = Date.valueOf(LocalDate.of(2019, m1, d1));
                            m2 = Integer.parseInt(periodo2[1]);
                            d2 = Integer.parseInt(periodo2[2]);
                            f_fin = Date.valueOf(LocalDate.of(2019, m2, d2));
                        }
                        reqConsulta1(f_inicio, f_fin);
                        break;

                    //RFC 2
                    case 10:
                        view.printMessage("Ingrese el periodo de tiempo: (Ej.: mm1-dd1-aaaa1/mm2-dd2-aaa2)");
                        input = sc.next();
                        periodos = input.split("/");
                        periodo1 = periodos[0].split("-");
                        periodo2 = periodos[1].split("-");
                        m1 = Integer.parseInt(periodo1[0]);
                        d1 = Integer.parseInt(periodo1[1]);
                        a1 = Integer.parseInt(periodo1[2]);
                        f_inicio = Date.valueOf(LocalDate.of(a1, m1, d1));
                        m2 = Integer.parseInt(periodo2[0]);
                        d2 = Integer.parseInt(periodo2[1]);
                        a2 = Integer.parseInt(periodo2[2]);
                        f_fin = Date.valueOf(LocalDate.of(a2, m2, d2));
                        while (f_inicio.compareTo(f_fin) > 0 || f_inicio.compareTo(f_fin) == 0) {
                            view.printMessage("La primera fecha debe ser menor que la segunda fecha");
                            view.printMessage("Vuelva a ingresar el periodo de tiempo: (Ej.: mm1-dd1/mm2-dd2)");
                            input = sc.next();
                            periodos = input.split("/");
                            periodo1 = periodos[0].split("-");
                            periodo2 = periodos[1].split("-");
                            m1 = Integer.parseInt(periodo1[1]);
                            d1 = Integer.parseInt(periodo1[2]);
                            f_inicio = Date.valueOf(LocalDate.of(2019, m1, d1));
                            m2 = Integer.parseInt(periodo2[1]);
                            d2 = Integer.parseInt(periodo2[2]);
                            f_fin = Date.valueOf(LocalDate.of(2019, m2, d2));
                        }
                        reqConsulta2(f_inicio, f_fin);
                        break;
                    // RFC 3
                    case 11:
                        break;

                    // RFC 4
                    case 12:
                        view.printMessage("Ingrese bajo que caracteristica desea buscar: ");
                        view.printListaOpcionesConsulta();
                        view.printMessage("Seleccione una opcion: ");
                        String opcionRFC4 = sc.next().toLowerCase();

                        if (opcionRFC4.equals("a")) {
                            view.printMessage("Ingrese la fecha de inicio (dd/mm/yyyy): ");
                            String fechaInicioRFC4 = sc.next();
                            view.printMessage("Ingrese la fecha de fin (dd/mm/yyyy): ");
                            String fechaFinRFC4 = sc.next();

                            String[] parseInicioRFC4 = fechaInicioRFC4.split("/");
                            String[] parseFinRFC4 = fechaFinRFC4.split("/");
                            int anoInicioRFC4 = Integer.parseInt(parseInicioRFC4[2]);
                            int mesInicioRFC4 = Integer.parseInt(parseInicioRFC4[1]);
                            int diaInicioRFC4 = Integer.parseInt(parseInicioRFC4[0]);
                            int anoFinRFC4 = Integer.parseInt(parseFinRFC4[2]);
                            int mesFinRFC4 = Integer.parseInt(parseFinRFC4[1]);
                            int diaFinRFC4 = Integer.parseInt(parseFinRFC4[0]);

                            Date inicioRFC4 = Date.valueOf(LocalDate.of(anoInicioRFC4, mesInicioRFC4, diaInicioRFC4));
                            Date finRFC4 = Date.valueOf(LocalDate.of(anoFinRFC4, mesFinRFC4, diaFinRFC4));
                            System.out.println();
                            view.printMessage("Los servicios son: ");

                            List<Servicio> rfc4 = buscarServiciosPorFechas(inicioRFC4, finRFC4);
                            for (Servicio serRfc4 : rfc4) {
                                view.printMessage(serRfc4.toString());
                            }
                            break;
                        } else if (opcionRFC4.equals("b")) {
//                            view.printMessage("Indique el servicio sobre el que quier consultar: ");
//                            view.printListaServicios(listaServicios);
//                            view.printMessage("Ingrese el nombre: ");
//                            String servicioRFC4 = sc.next();
//                            servicioRFC4 = servicioRFC4.concat(sc.nextLine());
//                            long idSerRFC4 = getIdServicioGeneral(servicioRFC4.toUpperCase());
//                            System.out.println();
//                            view.printMessage("Las Ips son son: ");
//
//                            List<IPS> ipsRFC4 = darServicioEnIps(idSerRFC4);
//
//                            for (IPS ipRfc4 : ipsRFC4) {
//                                view.printMessage(ipRfc4.toString());
//                            }
//                            break;
                        }


                        break;

                    // RFC 5
                    case 13:
                        view.printMessage("Ingrese el periodo de tiempo: (Ej.: mm1-dd1-aaaa1/mm2-dd2-aaa2)");
                        input = sc.next();
                        periodos = input.split("/");
                        periodo1 = periodos[0].split("-");
                        periodo2 = periodos[1].split("-");
                        m1 = Integer.parseInt(periodo1[0]);
                        d1 = Integer.parseInt(periodo1[1]);
                        a1 = Integer.parseInt(periodo1[2]);
                        f_inicio = Date.valueOf(LocalDate.of(a1, m1, d1));
                        m2 = Integer.parseInt(periodo2[0]);
                        d2 = Integer.parseInt(periodo2[1]);
                        a2 = Integer.parseInt(periodo2[2]);
                        f_fin = Date.valueOf(LocalDate.of(a2, m2, d2));
                        while (f_inicio.compareTo(f_fin) > 0 || f_inicio.compareTo(f_fin) == 0) {
                            view.printMessage("La primera fecha debe ser menor que la segunda fecha");
                            view.printMessage("Vuelva a ingresar el periodo de tiempo: (Ej.: mm1-dd1/mm2-dd2)");
                            input = sc.next();
                            periodos = input.split("/");
                            periodo1 = periodos[0].split("-");
                            periodo2 = periodos[1].split("-");
                            m1 = Integer.parseInt(periodo1[1]);
                            d1 = Integer.parseInt(periodo1[2]);
                            f_inicio = Date.valueOf(LocalDate.of(2019, m1, d1));
                            m2 = Integer.parseInt(periodo2[1]);
                            d2 = Integer.parseInt(periodo2[2]);
                            f_fin = Date.valueOf(LocalDate.of(2019, m2, d2));
                        }
                        System.out.println("Ingrese el id del paciente a consultar");
                        int idPac = sc.nextInt();
                        reqConsulta5(f_inicio, f_fin, idPac);
                        break;
                    //RFC 6
                    case 14:
                        break;

                    // RFC 7
                    case 15:

                        break;
                    // RFC 8
                    case 16:
                        break;

                    //Cierra la conexion
                    case 0:
                        fin = true;
                        epsAndes.cerrarUP();
                        sc.close();
                        System.out.println("Conexion Cerrada");
                        break;
                }

            } else if (med) {
                view.printMenuMedico();
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        view.printMessage("Ingrese la descipcion de la orden:  ");
                        String desc = sc.next();
                        desc = desc.concat(sc.nextLine());

                        view.printMessage("Adicionar servicios adicionales? (Y/N) ");
                        String yn1 = sc.next();
                        boolean sa = false;
                        long idSer_Or = 0;

                        //Para agregar un servicio en la consulta
                        if (yn1.equalsIgnoreCase("Y")) {
                            sa = true;
                            view.printMessage("Ingrese el nombre del servicio");
                            String servicio = sc.next();
                            servicio = servicio.concat(sc.nextLine());

//                            if (containsServicio(servicio.toUpperCase())) {
//                                idSer_Or = getIdServicio(servicio.toUpperCase());
//                            } else {
//                                view.printMessage("El servicio no existe, asegurese de ingresarlo correctamente...");
//                                break;
//                            }

                        }

                        //Para adicionar una receta a la orden
                        view.printMessage("Adicionar una receta? (Y/N)");
                        String yn2 = sc.next();
                        long[] meds = null;
                        boolean ar = false;
                        if (yn2.equalsIgnoreCase("Y")) {
                            ar = true;
                            view.printMessage("Ingrese cuantos medicamentos va a recetar: ");
                            int cantMed = sc.nextInt();
                            meds = new long[cantMed];
                            int countMed = 0;

                            view.printMessage("Ingrese los nombres de los medicamentos");
                            while (countMed < cantMed) {
                                String medicamento = sc.next();
                                medicamento = medicamento.concat(sc.nextLine());

                                if (containsMedicamento(medicamento.toUpperCase())) {
                                    meds[countMed] = getIdMedicamento(medicamento.toUpperCase());
                                    countMed++;
                                } else {
                                    view.printMessage("Asegurese de que el medicamento este disponible en la eps");
                                    break;
                                }
                            }
                        }

                        if (!ar && !sa) {
                            VOOrden or = adicionarOrden(desc);
                        } else if (sa && !ar) {
                            VOOrden ordenSe = adicionarOrden(desc);

                        } else if (!sa && ar) {
                            VOOrden ordenAr = adicionarOrden(desc);

                        }
                        break;
                    case 2:
                        fin = true;
                        epsAndes.cerrarUP();
                        sc.close();
                        System.out.println("Conexion Cerrada");
                        break;
                }
            } else if (pac) {
                view.printMenuPaciente();
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        view.printMessage("Ingrese su numero de documento: ");
                        long idPaciente = sc.nextInt();


                    case 0:
                        fin = true;
                        epsAndes.cerrarUP();
                        sc.close();
                        System.out.println("Conexion Cerrada");
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


    public List<EPS> darListaEps() {
        try {
            List<EPS> lista = epsAndes.darListaEps();
            return lista;
        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;
    }

    public List<IPS> darListaIps() {
        try {
            List<IPS> lista = epsAndes.darListaIps();
            return lista;
        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;
    }

    public List<Servicio> darListaServicios(long idIps) {
        try {
            if (idIps > 0) {
                List<Servicio> lista = epsAndes.darListaServicios(idIps);
                return lista;
            }
        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;
    }

    public List<Servicio> darListaServiciosReservados(long idIps) {
        try {
            List<Servicio> lista = epsAndes.darListaServiciosReservados(idIps);
            return lista;
        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;
    }

    public List<Medicamento> darListaMedicamentos() {
        try {
            List<Medicamento> lista = epsAndes.darListaMedicamentos();
            return lista;

        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;
    }

    public List<Paciente> darListaPacientes() {
        try {
            List<Paciente> lista = epsAndes.darListaPacientes();
            return lista;
        } catch (Exception e) {
            view.printMessage("Las restricciones fueron violadas");
            view.printErrMessage(e);
        }
        return null;

    }


    private void agregarUsuario(int numDoc, int idC, Timestamp fechaNacimiento, String nombre, String correo, String tipoDocumento, String rol) {
        try {
            if (rol != null && nombre != null && fechaNacimiento != null && tipoDocumento != null && numDoc > 0 && correo != null) {
                VOUsuario us = epsAndes.registrarUsuario(numDoc, 0, null, nombre, correo, tipoDocumento, rol);
                if (us == null) {
                    throw new Exception("No se pudo crear el usuario");
                }
                String resultado = "En registrar usuario \n\n";
                resultado += "Usuario adicionado exitosamente: " + us;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    private void agregarPaciente(long numDocumento, long idEps, String estado) {
        try {
            if (numDocumento > 0 && idEps > 0 && estado != null) {
                VOPaciente pa = epsAndes.registrarPaciente(numDocumento, idEps, estado);
                if (pa == null) {
                    throw new Exception("no se pudo crear paciente");
                }
                String resultado = "En registrar paciente \n\n";
                resultado += "Paciente adicionado exitosamente: " + pa;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    private void agregarMedico(long numDoc, long numRegistro, String tipo) {
        try {
            if (numRegistro > 0 && tipo != null && numDoc > 0) {
                VOMedico me = epsAndes.registrarMedico(numDoc, numRegistro, tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    private void agregarMedicoAIps(long numDoc, long idIps) {
        try {
            if (numDoc > 0 && idIps > 0) {
                VOMedicoIps mip = epsAndes.registrarMedicoAIps(numDoc, idIps);
                if (mip == null) {
                    throw new Exception("No se pudo agregar el medico a la Ips");
                }
                String resultado = "En registrar Medico A Ips \n\n";
                resultado += "Medico: " + numDoc + " Registrado en Ips: " + idIps;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);

            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }


//    public void agregarGerente(long id, String nombre, String correo) {
//        try {
//            long idGerente = id;
//            String nomGerente = nombre;
//            String correoGerente = correo;
//
//            if (id > 0 && nomGerente != null && correoGerente != null) {
//                VOUsuario re = epsAndes.registrarGerente(idGerente, nomGerente, correoGerente);
//                if (re == null) {
//                    throw new Exception("No se pudo agregar recepcionista");
//                }
//                String resultado = "En registrarRecepcionista\n\n";
//                resultado += "Recepcionista adicionado exitosamente: " + re;
//                resultado += "\n Operacion terminada";
//                view.printMessage(resultado);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            view.printErrMessage(e);
//        }
//    }

//    public void agregarRecepcionista(long id, String nombre, String correo) {
//        try {
//            long idRecep = id;
//            String nomRecep = nombre;
//            String correoRecep = correo;
//
//            if (id > 0 && nomRecep != null && correoRecep != null) {
//                VOUsuario re = epsAndes.registrarRecepcionista(idRecep, nomRecep, correoRecep);
//                if (re == null) {
//                    throw new Exception("No se pudo agregar recepcionista");
//                }
//                String resultado = "En registrarRecepcionista\n\n";
//                resultado += "Recepcionista adicionado exitosamente: " + re;
//                resultado += "\n Operacion terminada";
//                view.printMessage(resultado);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            view.printErrMessage(e);
//        }
//    }

    public void agregarEPS(String nombre) {
        try {
            String nom = nombre;
            if (nom != null) {
                VOEPS eps = epsAndes.registrarEPS(nom);
                if (eps == null) {
                    throw new Exception("No se pudo agregar EPS");
                }
                String resultado = "En registrarEPS\n\n";
                resultado += "EPS adicionado exitosamente: " + eps;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    public VOIPS agregarIPS(String nombre, long idEps, int capacidad, String localizacion) {
        try {

            if (nombre != null && idEps > 0 && capacidad > 0 && localizacion != null) {
                VOIPS ips = epsAndes.registrarIPS(nombre, idEps, capacidad, localizacion);
                if (ips == null) {
                    throw new Exception("No se puedo agregar IPS");
                }
                String resultado = "En registrarIPS\n\n";
                resultado += "IPS adicionado exitosamente: " + ips;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
                return ips;
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }

        return null;
    }


    private void deshabilitarServicio(long idServicio, long idIps, Date inicio, Date fin) {
        try {
            if (idServicio > 0 && idIps > 0 && inicio != null && fin != null) {
                boolean ser = epsAndes.deshabilitarServicio(idServicio, idIps, inicio, fin);
                if (ser == false) {
                    throw new Exception("No se pudo actualizar la informacion");
                }
                String resultado = "En deshabilitar servicio \n\n";
                resultado += "Servicio actualizado exitosamente: " + ser;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    private List<Servicio> buscarServiciosPorFechas(Date inicio, Date fin) {
        try {
            List<Servicio> servicios = epsAndes.buscarServiciosPorFecha(inicio, fin);
            if (servicios == null) {
                throw new Exception("No se logro buscar los servicios");
            }
            return servicios;
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
        return null;
    }

    private List<IPS> darServicioEnIps(long idServicio) {
        try {
            if (idServicio > 0) {
                List<IPS> list = epsAndes.darServicioEnIps(idServicio);
                if (list == null) {
                    throw new Exception("No se pudo encontrar en que Ips se prestan los servicios");
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
        return null;
    }

    public VOOrden adicionarOrden(String desc) {
        try {
            String descripcion = desc;
            if (desc != null) {
                VOOrden orden = epsAndes.registrarOrden(descripcion);
                if (orden == null) {
                    throw new Exception("No se puede agregar la consulta");
                }
                String resultado = "En registrarConsulta\n\n";
                resultado += "Orden adicionado exitosamente: " + orden;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
                return orden;
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }

        return null;
    }

    //Requerimiento funcional 10
    public Campana registrarCampana(Usuario org, int cant, ArrayList<String> servs, Date inicio, Date fin, String eps) {
        try {
            Campana c = epsAndes.registrarCampana(org, cant, servs, inicio, fin, eps);
            if (c == null)
                throw new Exception("La campana es nula");
            Usuario u = epsAndes.registrarUsuario(org.getId(), org.getIdCampana(), null, org.getNombre(), org.getCorreo(), org.getTipoDocumento(), org.getTipoUsuario());
            if (u == null)
                throw new Exception("El organizador es nulo");
            String resultado = "En registrar campana \n\n";
            resultado += "Campana adicionada exitosamente: " + c;
            resultado += "\n Operacion terminada";
            view.printMessage(resultado);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
            return null;
        }
    }

    //Requerimiento funcional 13 (Registrar la reapertura de los servicios de salud)
    public void reabrirServicios(Hashtable<Integer, ArrayList<Integer>> listSer) {
        try {
            epsAndes.reabrirServicios(listSer);
            String resultado = "Se han reabierto los servicios: ";
            Enumeration<Integer> it = listSer.keys();
            Integer next = null;
            while ( (next = it.nextElement())  != null) {
                System.out.println("(IPS: " + next + ", ");
                ArrayList<Integer> values = listSer.get(next);
                for (Integer currVal: values) {
                    System.out.println("SERVICIO: " + currVal + ", ");
                }
            }
            resultado += "\n Operación exitosa";
            view.printMessage(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    public void reqConsulta1(Date f_inicio, Date f_fin) {
        try {
            LinkedList<Object[]> req = epsAndes.reqConsulta1(f_inicio, f_fin);
            Iterator<Object[]> it = req.iterator();
            while (it.hasNext()) {
                Object[] currTuple = it.next();
                System.out.print("ID_IPS: " + currTuple[0]);
                System.out.print("  CANTIDAD DE SERVICIOS: " + currTuple[1] + " \n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    public void reqConsulta2(Date f_inicio, Date f_fin) {
        try {
            LinkedList<Object[]> req = epsAndes.reqConsulta2(f_inicio, f_fin);
            Iterator<Object[]> it = req.iterator();
            while (it.hasNext()) {
                Object[] currTuple = it.next();
                System.out.print("SERVICIO: " + currTuple[0]);
                System.out.println("    CANTIDAD DE SOLICITUDES: " + currTuple[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }


    public void reqConsulta5(Date f_inicio, Date f_fin, int idPac) {
        try {
            LinkedList<Object[]> req = epsAndes.reqConsulta5(f_inicio, f_fin, idPac);
            Iterator<Object[]> it = req.iterator();
            while (it.hasNext()) {
                Object[] currTuple = it.next();
                System.out.print("ID_SERVICIO: " + currTuple[0]);
                System.out.println("    UTILIZACION: " + currTuple[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    public void reqConsulta7() {
        try {
            LinkedList<Object> req = epsAndes.reqConsulta7();
            Iterator<Object> it = req.iterator();
            while (it.hasNext()) {
                Object currId = it.next();
                System.out.println("ID_PACIENTE: " + currId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
    }

    /**
     * @param
     * @param word
     * @return
     */
    private boolean containsEPS(String word) {
        boolean contains = false;
        listaEPS = darListaEps();
        Iterator<EPS> it = listaEPS.iterator();

        while (!contains) {
            EPS ep = it.next();
            System.out.println(ep.getNombre());
            if (ep.getNombre().equals(word)) {
                contains = true;
            }
        }
        return contains;
    }

    private boolean containsIPS(String name) {
        boolean contains = false;
        listaIPS = darListaIps();
        Iterator<IPS> it = listaIPS.iterator();

        while (!contains) {
            IPS ip = it.next();
            System.out.println(ip.getNombre());
            if (ip.getNombre().equals(name)) {
                contains = true;
            }
        }
        return contains;
    }

    private boolean containsServicio(String servicio, long idIps) {
        boolean contains = false;
        listaServicios = darListaServicios(idIps);
        Iterator<Servicio> it = listaServicios.iterator();

        while (!contains) {
            Servicio ser = it.next();
            if (ser.getNombre().equals(servicio)) {
                contains = true;
            }
        }

        return contains;
    }

    public boolean containsMedicamento(String nombre) {
        boolean contains = false;
        listaMedicamentos = darListaMedicamentos();
        Iterator<Medicamento> it = listaMedicamentos.iterator();

        while (!contains) {
            Medicamento med = it.next();
            if (med.getNombre().equals(nombre)) {
                contains = true;
            }
        }

        return contains;
    }

    public long getIdMedicamento(String nombre) {

        long id = 0;
        boolean contains = false;
        listaMedicamentos = darListaMedicamentos();
        Iterator<Medicamento> it = listaMedicamentos.iterator();

        while (!contains) {
            Medicamento med = it.next();
            if (med.getNombre().equals(nombre)) {
                id = med.getId();
                contains = true;
            }
        }

        return id;
    }

    private long getIdServicio(String servicio, long idIps) {

        boolean contains = false;
        long id = 0;
        listaServicios = darListaServicios(idIps);
        Iterator<Servicio> it = darListaServicios(idIps).iterator();

        while (!contains) {
            Servicio ser = it.next();
            if (ser.getNombre().equals(servicio)) {
                contains = true;
                id = ser.getId();
            }
        }

        return id;
    }


    private Long getidEps(String name) {
        boolean contains = false;
        long id = 0;
        listaEPS = darListaEps();
        Iterator<EPS> it = listaEPS.iterator();

        while (!contains) {
            EPS ep = it.next();
            System.out.println(ep.getNombre());
            if (ep.getNombre().equals(name)) {
                id = ep.getId();
                contains = true;
            }
        }
        return id;
    }

    private Long getidIps(String name) {
        boolean contains = false;
        long id = 0;
        listaIPS = darListaIps();
        Iterator<IPS> it = listaIPS.iterator();

        while (!contains) {
            IPS ip = it.next();
            System.out.println(ip.getNombre());
            if (ip.getNombre().equals(name)) {
                id = ip.getId();
                contains = true;
            }
        }
        return id;
    }


    /**
     * @param tipo
     * @param archConfig
     * @return
     */
    private JsonObject openConfig(String tipo, String archConfig) {
        JsonObject config = null;
        try {
            Gson gson = new Gson();
            FileReader file = new FileReader(archConfig);
            JsonReader reader = new JsonReader(file);
            config = gson.fromJson(reader, JsonObject.class);
            log.info("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
        } catch (Exception e) {
            //			e.printStackTrace ();
            log.info("NO se encontr� un archivo de configuraci�n v�lido");
            JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
        }
        return config;
    }
}
