package it.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import it.negocio.*;
import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class PersistenciaEPSAndes {


    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Logger para escribir la traza de la ejecuci�n
     */
    private static Logger log = Logger.getLogger(PersistenciaEPSAndes.class.getName());

    /**
     * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
     */
    public final static String SQL = "javax.jdo.query.SQL";

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    /**
     * Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON
     */
    private static PersistenciaEPSAndes instance;

    /**
     * F�brica de Manejadores de persistencia, para el manejo correcto de las transacciones
     */
    private PersistenceManagerFactory pmf;

    /**
     * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
     * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
     */
    private List<String> tablas;

    /**
     * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
     */
    private SQLUtil sqlUtil;

    private SQLAdministrador sqlAdministrador;

    private SQLGerente sqlGerente;

    private SQLMedico sqlMedico;

    private SQLPaciente sqlPaciente;

    private SQLSecretaria sqlSecretaria;

    private SQLCampana sqlCampana;

    private SQLServicio sqlServicio;

    /**
     *
     */
    private PersistenciaEPSAndes() {

        pmf = JDOHelper.getPersistenceManagerFactory("EPSAndes");
        crearClasesSQL();

        tablas = new LinkedList<String>();
        tablas.add("EPSAndes_sequence");
        tablas.add("A_ADMINISTRADOR");
        tablas.add("A_HOSPITALIZACION");
        tablas.add("A_MEDICO_URGENCIAS");
        tablas.add("A_TERAPIA");
        tablas.add("A_IPS");
        tablas.add("A_ORDEN");
        tablas.add("A_URGENCIA");
        tablas.add("A_ROL");
        tablas.add("A_CONSULTA");
        tablas.add("A_IPS_HORARIOS");
        tablas.add("A_ORDEN_MEDICAMENTO");
        tablas.add("A_EPS");
        tablas.add("A_IPS_MEDICOS");
        tablas.add("A_ORDEN_SERVICIOS");
        tablas.add("A_EPS_ADMINISTRADORES");
        tablas.add("A_IPS_SECRETARIAS");
        tablas.add("A_PACIENTE");
        tablas.add("A_EPS_GERENTES");
        tablas.add("A_IPS_SERVICIOS");
        tablas.add("A_PACIENTE_RESERVAS");
        tablas.add("A_EPS_IPS");
        tablas.add("A_MEDICAMENTO");
        tablas.add("A_PACIENTE_URGENCIAS");
        tablas.add("A_EPS_PACIENTES");
        tablas.add("A_MEDICO");
        tablas.add("A_PROCEDIMIENTOS");
        tablas.add("A_EXAMEN_DIAGNOSTICO");
        tablas.add("A_MEDICO_CONSULTA");
        tablas.add("A_SECRETARIA");
        tablas.add("A_GERENTE");
        tablas.add("A_MEDICO_PACIENTES");
        tablas.add("A_SERVICIO");
        tablas.add("A_HORARIO");
        tablas.add("A_MEDICO_REGISTRO");
        tablas.add("A_SERVICIO_HORARIO");
    }

    /**
     * @param tableConifg
     */
    private PersistenciaEPSAndes(JsonObject tableConifg) {

        crearClasesSQL();
        tablas = leerNombresTablas(tableConifg);

        String unidadPersistencia = tableConifg.get("unidadPersistencia").getAsString();
        log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
        pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
    }

    /**
     * @return
     */
    public static PersistenciaEPSAndes getInstance() {
        if (instance == null) {
            instance = new PersistenciaEPSAndes();
        }

        return instance;
    }

    /**
     * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
     *
     * @param tableConfig - El objeto JSON con los nombres de las tablas
     * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
     */
    public static PersistenciaEPSAndes getInstance(JsonObject tableConfig) {
        if (instance == null) {
            instance = new PersistenciaEPSAndes(tableConfig);
        }
        return instance;
    }

    /**
     * Cierra la conexi�n con la base de datos
     */
    public void cerrarUnidadPersistencia() {
        pmf.close();
        instance = null;
    }

    /**
     * Genera una lista con los nombres de las tablas de la base de datos
     *
     * @param tableConfig - El objeto Json con los nombres de las tablas
     * @return La lista con los nombres del secuenciador y de las tablas
     */
    private List<String> leerNombresTablas(JsonObject tableConfig) {
        JsonArray nombres = tableConfig.getAsJsonArray("tablas");

        List<String> resp = new LinkedList<String>();
        for (JsonElement nom : nombres) {
            resp.add(nom.getAsString());
        }

        return resp;
    }

    /**
     *
     */
    private void crearClasesSQL() {
        sqlAdministrador = new SQLAdministrador(this);
        sqlMedico = new SQLMedico(this);
        sqlPaciente = new SQLPaciente(this);
        sqlSecretaria = new SQLSecretaria(this);
        sqlUtil = new SQLUtil(this);
        sqlCampana = new SQLCampana(this);
    }

    /**
     * @return La cadena de caracteres con el nombre del secuenciador de parranderos
     */
    public String darSeqEPS() {
        return tablas.get(0);
    }

    /**
     * @return
     */
    public String getTablaEps() {
        return tablas.get(1);
    }

    /**
     * @return
     */
    public String getTablaIps() {
        return tablas.get(2);
    }

    /**
     * @return
     */
    public String getTablaHorario() {
        return tablas.get(3);
    }

    /**
     * @return
     */
    public String getTablaOrden() {
        return tablas.get(4);
    }

    /**
     * @return
     */
    public String getTablaServicio() {
        return tablas.get(5);
    }

    /**
     * @return
     */
    public String getTablaMedicamento() {
        return tablas.get(6);
    }

    /**
     * @return
     */
    public String getTablaUsuario() {
        return tablas.get(7);
    }

    /**
     * @return
     */
    public String getTablaMedico() {
        return tablas.get(8);
    }

    /**
     * @return
     */
    public String getTablaIps_Medico() {
        return tablas.get(9);
    }

    /**
     * @return
     */
    public String getTablaIps_Servicios() {
        return tablas.get(10);
    }

    /**
     * @return
     */
    public String getTablaOrden_Medicamento() {
        return tablas.get(11);
    }

    /**
     * @return
     */
    public String getTablaPaciente() {
        return tablas.get(12);
    }

    /**
     * @return
     */
    public String getTablaUrgencia() {
        return tablas.get(13);
    }

    /**
     * @return
     */
    public String getTablaConsulta() {
        return tablas.get(14);
    }

    /**
     * @return
     */
    public String getTablaMedicos_Pacientes() {
        return tablas.get(15);
    }

    public String getTablaCamapana() {
        return tablas.get(16);
    }

    public String getTablaOrdenes_Servicios() {
        return tablas.get(17);
    }


    /**
     * Transacci�n para el generador de secuencia de Parranderos
     * Adiciona entradas al log de la aplicaci�n
     *
     * @return El siguiente n�mero del secuenciador de Parranderos
     */
    private long nextval() {
        long resp = sqlUtil.nextval(pmf.getPersistenceManager());
        log.trace("Generando secuencia: " + resp);
        return resp;
    }

    /**
     * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
     *
     * @param e - La excepci�n que ocurrio
     * @return El mensaje de la excepci�n JDO
     */
    private String darDetalleException(Exception e) {
        String resp = "";
        if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
            JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
            return je.getNestedExceptions()[0].getMessage();
        }
        return resp;
    }






    /* ****************************************************************
     * 			M�todos para manejar USUARIO
     *****************************************************************/


//    public Paciente registrarPaciente(long id, String nombre, String correo, Timestamp fNacimiento, String estado, String tipoDoc) {
//        PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx = pm.currentTransaction();
//        try {
//            tx.begin();
//            long idPac = id;
//            long tuplasInsertadas = sqlAdministrador.adicionarPaciente(pm, idPac, nombre, correo, fNacimiento, estado, tipoDoc);
//            tx.commit();
//
//            log.trace("Insercion de paciente: " + nombre + ": " + tuplasInsertadas + "tuplas insertadad");
//            return new Paciente(idPac, nombre, correo, fNacimiento, estado, tipoDoc);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
//            return null;
//        } finally {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            pm.close();
//        }
//    }


    public EPS registrarEPS(String nombre) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idEPS = nextval();
            long tuplasInsertadas = sqlAdministrador.adicionarEPS(pm, idEPS, nombre);
            tx.commit();

            log.trace("Insercion de tipo de bebida: " + nombre + ": " + tuplasInsertadas + "tuplas insertadad");
            return new EPS(idEPS, nombre);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public List<EPS> darListaEps() {
        return sqlAdministrador.darListaEps(pmf.getPersistenceManager());
    }

    public List<IPS> darListaIps() {
        return sqlAdministrador.darListaIps(pmf.getPersistenceManager());
    }

    public List<Servicio> darListaServicios() {
        return sqlAdministrador.darListaServicios(pmf.getPersistenceManager());
    }

    public List<Medicamento> darListaMedicamentos() {
        return sqlAdministrador.darListaMedicamentos(pmf.getPersistenceManager());
    }

    public List<Paciente> darListaPacientes() {
        return sqlAdministrador.darListaPacientes(pmf.getPersistenceManager());
    }

    public Usuario registrarUsuario(String rol, String nombre, Timestamp fechaNacimiento, String tipoDocumento, long numDoc, String correo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            long usuarioInsertado = sqlAdministrador.adicionarUsuario(pm, rol, nombre, fechaNacimiento, tipoDocumento, numDoc, correo);
            tx.commit();

            log.trace("Insercion de usuario: " + numDoc + ": " + usuarioInsertado + " Usuario insertado");
            return new Usuario(nombre, fechaNacimiento, numDoc, correo, tipoDocumento, rol);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Paciente registrarPaciente(long numDocumento, long idEps, String estado) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            long pacienteInsertado = sqlAdministrador.adicionarPaciente(pm, numDocumento, idEps, estado);
            tx.commit();

            log.trace("Insercion de paciente: " + numDocumento + ": " + pacienteInsertado + "Paciente insertado");
            return new Paciente(numDocumento, idEps, estado);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Medico registrarMedico(long numDoc, long numRegistro, String tipo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            long medicoInsertado = sqlAdministrador.adicionarMedico(pm, numDoc, numRegistro, tipo);
            tx.commit();

            log.trace("Insercion de medico: " + numRegistro + ": " + medicoInsertado + " Medico insertado");
            return new Medico(numDoc, numRegistro, tipo);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public MedicoIps registrarMedicoAIps(long numDoc, long idIps) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            long medicoIpsInsertado = sqlAdministrador.adicionarMedicoAIps(pm, numDoc, idIps);
            tx.commit();

            log.trace("Insercion de medico: " + numDoc + ", en Ips: " + idIps);
            return new MedicoIps(idIps, numDoc);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public IPS registrarIPS(String nombre, long idEps, int capacidad, String localizacion) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idIPS = nextval();
            long tuplasInsertadas = sqlAdministrador.adicionarIPS(pm, idIPS, nombre, idEps, capacidad, localizacion);
            tx.commit();

            log.trace("Insercion de tipo de bebida: " + nombre + ": " + tuplasInsertadas + "tuplas insertadad");
            return new IPS(idIPS, nombre, idEps, capacidad, localizacion);

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }


//    public Medico registrarMedico(long id, String nombre, String correo, String especialidad) {
//        PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx = pm.currentTransaction();
//        try {
//            tx.begin();
//            long idMed = id;
//            long tuplasInsertadas = sqlAdministrador.adicionarMedico(pm, idMed, nombre, correo, especialidad);
//            tx.commit();
//
//            log.trace("Insercion de tipo de bebida: " + nombre + ": " + tuplasInsertadas + "tuplas insertadad");
//            return new Medico(idMed, nombre, correo, especialidad);
//
//        } catch (Exception e) {
//            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
//            return null;
//
//        } finally {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            pm.close();
//        }
//    }


    public Servicio registrarServicio(String nombre, int capacidad) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idServicio = nextval();
            long tuplasInsertadas = sqlAdministrador.adicionarServicio(pm, idServicio, capacidad, nombre);
            tx.commit();

            log.trace("Insercion de servicio: " + nombre + ": " + tuplasInsertadas + "tuplas insertadad");
            return new Servicio(idServicio, capacidad, nombre);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }


    public Orden registrarOrden(String desc) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idConsulta = nextval();
            long tuplas = sqlMedico.adicionarOrden(pm, idConsulta, desc);
            tx.commit();

            log.trace("Insercion de orden: " + tuplas);
            return new Orden(desc, idConsulta);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }


    public Campana registrarCampana(int idOrg, int participantes, Date f_inicio, Date f_fin) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idCampana = nextval();
            long tuplasInsertadas = sqlCampana.adicionarCampana(pmf.getPersistenceManager(), idCampana, idOrg, participantes, f_inicio, f_fin);
            tx.commit();

            log.trace("Inserción de campaña: " + tuplasInsertadas);
            return new Campana(idCampana, participantes, f_inicio, f_fin);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: "+e.getMessage()+"\n"+darDetalleException(e));
            return null;
        }
        finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Object reqConsulta1(Date f_inicio, Date f_fin, int año) {
        return sqlServicio.reqConsulta1(pmf.getPersistenceManager(), f_inicio, f_fin, año);
    }
}
