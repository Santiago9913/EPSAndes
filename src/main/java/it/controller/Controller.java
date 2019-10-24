package it.controller;

import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import it.negocio.*;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

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

    private List<EPS> listaEPS;

    private List<IPS> listaIPS;

    private List<Servicio> listaServicios;

    private List<Medicamento> listaMedicamentos;

    private List<Paciente> listaPacientes;

    public Controller() {
        view = new View();
        listaEPS = new ArrayList<>();
        listaIPS = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaMedicamentos = new ArrayList<>();
        listaPacientes = new ArrayList<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean fin = false;
        boolean inicia = false;

        String usuario = "";
        crearConexion();
        listaEPS = darListaEps();
        listaIPS = darListaIps();
        listaServicios = darListaServicios();
        listaMedicamentos = darListaMedicamentos();
        listaPacientes = darListaPacientes();
        for (EPS eps : listaEPS) {
            System.out.println(eps.toString());
        }
        while (!inicia) {
            view.printInicioSesion();
            usuario = sc.next().toUpperCase();
            view.printMessage("Ingrese codigo:");
            int clave = sc.nextInt();
            if (usuario.equals("ADMINISTRADOR") || usuario.equals(PA) || usuario.equals(ME) || usuario.equals(RE))
                inicia = true;

        }

        boolean admin = usuario.toUpperCase().equals(AD) ? true : false;
        boolean pac = usuario.toUpperCase().equals(PA) ? true : false;
        boolean med = usuario.toUpperCase().equals(ME) ? true : false;
        boolean recp = usuario.equals(RE) ? true : false;

        while (!fin) {
            listaEPS = darListaEps();
            if (admin) {
                view.printMenuAdmin();
                int optionAdmin = sc.nextInt();

                //Vista de administrador
                switch (optionAdmin) {
                    //Ingresa un rol al sistema(ADMINISTRADOR, PACIEMTE, MEDICO, GERENTE, SECRETARIA)
                    case 1:

                        break;
                    //Permiso Administrador
                    case 2:
                        view.printMessage("Ingrese la informacion del usuario...");
                        view.printMessage("Rol: ");
                        view.printRoles();
                        String rolUsuario = sc.next();

                        //Si el usuario es PACIENTE
                        if (rolUsuario.toLowerCase().equals("a")) {
                            //Se ingresa el nombre
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombrePaciente = sc.next();
                            nombrePaciente = nombrePaciente.concat(sc.nextLine());

                            //Se ingresa el correo y se realiza el check
                            view.printMessage("Ingrese el correo: ");
                            String correoPaciente = sc.next();
                            boolean correoCorrecto = false;
                            if (!correoPaciente.contains("@")) {
                                while (!correoCorrecto) {
                                    view.printMessage("Ingrese un correo correcto...");
                                    correoPaciente = sc.next();
                                    if (correoPaciente.contains("@")) {
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
//                            agregarPaciente(idPaciente, nombrePaciente.toUpperCase(), correoPaciente.toUpperCase(), fechaPaciente, estadoPaciente.toUpperCase(), tipoDoc.toUpperCase());
                        }

                        //Si el usuario es MEDICO
                        else if (rolUsuario.toLowerCase().equals("b")) {
                            //Se ingresa el nombre
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombreMedico = sc.next();
                            nombreMedico = nombreMedico.concat(sc.nextLine());

                            //Se ingresa el correo y se realiza el check
                            view.printMessage("Ingrese el correo: ");
                            String correoMedico = sc.next();
                            boolean correoCorrecto = false;
                            if (!correoMedico.contains("@")) {
                                while (!correoCorrecto) {
                                    view.printMessage("Ingrese un correo correcto...");
                                    correoMedico = sc.next();
                                    if (correoMedico.contains("@")) {
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


                            view.printMessage("Ingrese a cuantas IPS presta servicio el medico: ");
                            int cantidadMedIPS = sc.nextInt();
                            int countIpsMed = 0;


                            long[] ipsMedico = new long[cantidadMedIPS];
                            view.printMessage("Ingrese los/el nombre de la IPS");

                            while (countIpsMed < cantidadMedIPS) {
                                String ipsMed = sc.next();
                                ipsMed = ipsMed.concat(sc.nextLine());

                                if (containsIPS(ipsMed.toUpperCase())) {
                                    if (listaIPS.isEmpty()) {
                                        view.printMessage("No hay ninguna IPS inscrita");
                                        break;
                                    }
                                    ipsMedico[countIpsMed] = getidIps(ipsMed.toUpperCase());
                                    countIpsMed++;
                                } else {
                                    view.printMessage("Asegurese de que la IPS se encuentra en la base de datos");
                                    break;
                                }
                            }

                            //Agregar el medico
//                            agregarMedico(idMedico, nombreMedico.toUpperCase(), correoMedico.toUpperCase(), especialidadMedico.toUpperCase());
                            //Agregar el registro medico
//                            agregarMedicoRegistro(idMedico, numRegistro);

                            //Agregar medico a una IPS ya existente
                            for (long ips : ipsMedico) {
                                agregarMedicoAIps(ips, idMedico);
                            }

                        } else if (rolUsuario.toLowerCase().endsWith("c")) {
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombreGerente = sc.next();
                            nombreGerente = nombreGerente.concat(sc.nextLine());

                            //Se ingresa el correo y se realiza el check
                            view.printMessage("Ingrese el correo: ");
                            String correoGerente = sc.next();
                            boolean correoCorrecto = false;
                            if (!(correoGerente.contains("@") && correoGerente.contains(".com"))) {
                                while (!correoCorrecto) {
                                    view.printMessage("Ingrese un correo correcto...");
                                    correoGerente = sc.next();
                                    if (correoGerente.contains("@") && correoGerente.contains(".com")) {
                                        correoCorrecto = true;
                                    }
                                }
                            }

                            //Se ingresa id
                            view.printMessage("Ingrese el numero de documento: ");
                            long idGerente = sc.nextLong();

//                            agregarGerente(idGerente, nombreGerente.toUpperCase(), correoGerente.toUpperCase());


                        }

                        //Si el usuario es recepcionista
                        else if (rolUsuario.toLowerCase().equals("d")) {
                            view.printMessage("Ingrese el nombre y apellido: ");
                            String nombreRecepcionista = sc.next();
                            nombreRecepcionista = nombreRecepcionista.concat(sc.nextLine());

                            //Se ingresa el correo y se realiza el check
                            view.printMessage("Ingrese el correo: ");
                            String correoRecepcionista = sc.next();
                            boolean correoCorrecto = false;
                            if (!(correoRecepcionista.contains("@") && correoRecepcionista.contains(".com"))) {
                                while (!correoCorrecto) {
                                    view.printMessage("Ingrese un correo correcto...");
                                    correoRecepcionista = sc.next();
                                    if (correoRecepcionista.contains("@") && correoRecepcionista.contains(".com")) {
                                        correoCorrecto = true;
                                    }
                                }
                            }

                            //Se ingresa id
                            view.printMessage("Ingrese el numero de documento: ");
                            long idRecepcionista = sc.nextLong();

//                            agregarRecepcionista(idRecepcionista, nombreRecepcionista.toUpperCase(), correoRecepcionista.toUpperCase());
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
                    //Agregar un IPS
                    case 4:
                        view.printMessage("Ingrese el nombre de la IPS:  ");
                        String nombreIPS = sc.next();
                        nombreIPS = nombreIPS.concat(sc.nextLine());

                        view.printMessage("Ingrese la capacidad de la IPS: ");
                        int capacidad = sc.nextInt();

                        view.printMessage("Ingrese la localizacion de la IPS");
                        String loc = sc.next();
                        loc = loc.concat(sc.nextLine());

                        VOIPS ipsAgregada = agregarIPS(nombreIPS.toUpperCase(), capacidad, loc.toUpperCase());

                        view.printMessage("Indique a cuantas Eps presta servicio: ");
                        int cantidad = sc.nextInt();
                        int count = 0;
                        String eps = "";
                        long[] listaIngresado = new long[cantidad];
                        view.printMessage("Ingrese los nombres de las eps");
                        while (count < cantidad) {
                            eps = sc.next();
                            eps = eps.concat(sc.nextLine()).toUpperCase();
                            if (containsEPS(eps)) {
                                listaIngresado[count] = getidEps(eps);
                                count++;
                            } else {
                                view.printMessage("Asegurese de que la eps exista en la BD...");
                                break;
                            }
                        }

                        for (long ing : listaIngresado) {
//                            agregarIpsAEps(ipsAgregada.getId(), ing);
                        }

                        break;

                    //Agregar un servicio
                    case 5:
                        view.printMessage("Ingresw el servicio que desea agregar");
                        view.printServiciosMenu();
                        String respSer = sc.next();
                        String servicio = "";
                        if (respSer.equalsIgnoreCase("a")) {
                            servicio = "Procedimiento Medico";
                        } else if (respSer.equalsIgnoreCase("b")) {
                            servicio = "Hospitalizacion";
                        } else if (respSer.equalsIgnoreCase("c")) {
                            servicio = "Examen Diagnostico";
                        } else if (respSer.equalsIgnoreCase("d")) {
                            servicio = "Terapia";
                        }

                        view.printMessage("Ingrese la capacidad del servicio");
                        int capacidadServicio = sc.nextInt();


                        VOServicio ser = agregarServicio(servicio.toUpperCase(), capacidadServicio);

                        view.printMessage("ingrese a cuantas IPS presta servicio");
                        int presta = sc.nextInt();
                        int countSer = 0;
                        view.printMessage("ingrese a cuales IPS presta el servicio");
                        long[] ipsIds = new long[presta];

                        while (countSer < presta) {
                            String ipsServicio = sc.next();
                            ipsServicio = ipsServicio.concat(sc.nextLine());
                            if (containsIPS(ipsServicio)) {
                                ipsIds[countSer] = getidIps(ipsServicio);
                                countSer++;
                            }
                        }

                        for (Long idIps : ipsIds) {
                            agregarServicioAIps(idIps, ser.getId());
                        }
                        break;


                    case 6:
                        view.printMessage("Ingrese el periodo de tiempo: (Ej.: mm-dd/mm-dd)");
                        String input = sc.next();
                        String[] periodos = input.split("/");
                        String[] periodo1 = periodos[0].split("-");
                        String[] periodo2 = periodos[1].split("-");
                        int m1 = Integer.parseInt(periodo1[1]);
                        int d1 = Integer.parseInt(periodo1[2]);
                        Timestamp fecha1 = Timestamp.valueOf(LocalDateTime.of(2019, m1, d1, 0, 0));
                        int m2 = Integer.parseInt(periodo2[1]);
                        int d2 = Integer.parseInt(periodo2[2]);
                        Timestamp fecha2 = Timestamp.valueOf(LocalDateTime.of(2019, m2, d2, 0, 0));
                        while (fecha1.compareTo(fecha2) > 0 || fecha1.compareTo(fecha2) == 0) {
                            view.printMessage("La primera fecha debe ser menor que la segunda fecha");
                            view.printMessage("Vuelva a ingresar el periodo de tiempo: (Ej.: YYYY-mm-dd/YYYY-mm-dd)");
                            input = sc.next();
                            periodos = input.split("/");
                            periodo1 = periodos[0].split("-");
                            periodo2 = periodos[1].split("-");
                            m1 = Integer.parseInt(periodo1[1]);
                            d1 = Integer.parseInt(periodo1[2]);
                            fecha1 = Timestamp.valueOf(LocalDateTime.of(2019, m1, d1, 0, 0));
                            m2 = Integer.parseInt(periodo2[1]);
                            d2 = Integer.parseInt(periodo2[2]);
                            fecha2 = Timestamp.valueOf(LocalDateTime.of(2019, m2, d2, 0, 0));
                        }
                        int resp = epsAndes.rfc1(fecha1, fecha2);
                        view.printMessage("Cantidad de servicios prestados por cada ips: " + resp);
                        break;

                    //Cierra la conexion
                    case 11:
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

                            if (containsServicio(servicio.toUpperCase())) {
                                idSer_Or = getIdServicio(servicio.toUpperCase());
                            } else {
                                view.printMessage("El servicio no existe, asegurese de ingresarlo correctamente...");
                                break;
                            }

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
                            for (long medLoop : meds) {
                                adicionarOrdenConReceta(ordenAr.getId(), medLoop);
                            }
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

    public List<Servicio> darListaServicios() {
        try {
            List<Servicio> lista = epsAndes.darListaServicios();
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


//    /**
//     * @param idPaciente
//     * @param nombrePaciente
//     * @param correoPaciente
//     * @param fechaNacimiento
//     * @param estadoPaciente
//     * @param tipoDoc
//     */
//    public void agregarPaciente(long idPaciente, String nombrePaciente, String correoPaciente, Timestamp fechaNacimiento, String estadoPaciente, String tipoDoc) {
//
//        try {
//            long id = idPaciente;
//            String nombre = nombrePaciente;
//            String correo = correoPaciente;
//            Timestamp fechaNac = fechaNacimiento;
//            String estado = estadoPaciente;
//            String tipoDocumento = tipoDoc;
//
//            if (id != 0 && nombre != null && correo != null && fechaNac != null && tipoDoc != null) {
//                VOPaciente pa = epsAndes.registrarPaciente(id, nombre, correo, fechaNac, estado, tipoDocumento);
//                if (pa == null) {
//                    throw new Exception("No se pudo crear el usuario con el rol: " + "PACIENTE");
//                }
//                String resultado = "En registrarPaciente\n\n";
//                resultado += "Paciente adicionado exitosamente: " + pa;
//                resultado += "\n Operacion terminada";
//                view.printMessage(resultado);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            view.printErrMessage(e);
//        }
//    }

//    /**
//     * @param idMedico
//     * @param nombre
//     * @param correo
//     * @param especialidad
//     */
//    public void agregarMedico(long idMedico, String nombre, String correo, String especialidad) {
//
//        try {
//            long id = idMedico;
//            String nombreMedico = nombre;
//            String correoMedico = correo;
//            String espec = especialidad;
//
//            if (nombre != null && id != 0 && correoMedico != null) {
//                VOMedico me = epsAndes.registrarMedico(id, nombreMedico, correoMedico, espec);
//                if (me == null) {
//                    throw new Exception("No se pudo crear el medico");
//                }
//                String resultado = "En registrarMedico\n\n";
//                resultado += "Medico adicionado exitosamente: " + me;
//                resultado += "\n Operacion terminada";
//                view.printMessage(resultado);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            view.printErrMessage(e);
//        }
//    }


    public void agregarMedicoAIps(long idIps, long idMedico) {
        try {
            long ips = idIps;
            long medico = idMedico;

            if (ips > 0 && medico > 0) {
                VOMedicoIps im = epsAndes.registrarMedicoAIps(ips, medico);
                if (im == null) {
                    throw new Exception("No se pudo agregar el medico a la IPS");
                }
                String resultado = "En registrarMedicoAIps\n\n";
                resultado += "Medico adicionado a IPS exitosamente: " + im;
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

    public VOIPS agregarIPS(String nombre, int capacidad, String localizacion) {
        try {
            String nom = nombre;
            int cap = capacidad;
            String loc = localizacion;

            if (nom != null && cap > 0 && loc != null) {
                VOIPS ips = epsAndes.registrarIPS(nom, loc, cap);
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


    public VOServicio agregarServicio(String servicio, int capacidad) {
        try {
            String nombre = servicio;
            int cap = capacidad;

            if (nombre != null && cap > 0) {
                VOServicio ser = epsAndes.registrarServicio(cap, nombre);
                if (ser == null) {
                    throw new Exception("No se puede agregar Servicio");
                }
                String resultado = "En registrarIPS\n\n";
                resultado += "Servicio adicionado exitosamente: " + ser;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
                return ser;
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }

        return null;
    }

    public void agregarServicioAIps(long idIps, long idServicio) {
        try {
            long idI = idIps;
            long idSer = idServicio;

            if (idI > 0 && idSer > 0) {
                VOServicioIps ips_Servicio = epsAndes.registrarServicioIps(idI, idSer);
                if (ips_Servicio == null) {
                    throw new Exception("No se puede agregar Servicio en la IPS");
                }
                String resultado = "En registrarIPS\n\n";
                resultado += "Servicio adicionado exitosamente en IPS: " + ips_Servicio;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.printErrMessage(e);
        }
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


    public void adicionarOrdenConReceta(long idOrden, long idMedicamento) {
        try {
            long orden = idOrden;
            long med = idMedicamento;

            if (orden > 0 && med > 0) {
                VOOrdenMedicamento om = epsAndes.registrarOrdenConMedicamento(orden, med);
                if (om == null) {
                    throw new Exception("No se pudo agregar la orden con el servicio");
                }
                String resultado = "En registrarOrdenConMedicamento\n\n";
                resultado += "Orden con medicamento adicionada exitosamente: " + om;
                resultado += "\n Operacion terminada";
                view.printMessage(resultado);
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

    private boolean containsServicio(String servicio) {
        boolean contains = false;
        listaServicios = darListaServicios();
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

    private long getIdServicio(String servicio) {

        boolean contains = false;
        long id = 0;
        listaServicios = darListaServicios();
        Iterator<Servicio> it = darListaServicios().iterator();

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
