package it.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import it.negocio.*;

public class SQLAdministrador {


    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra ac� para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaEPSAndes.SQL;

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia general de la aplicaci�n
     */
    private PersistenciaEPSAndes pe;


    /* ****************************************************************
     * 			M�todos
     *****************************************************************/

    /**
     * Constructor
     *
     * @param pe - El Manejador de persistencia de la aplicaci�n
     */
    public SQLAdministrador(PersistenciaEPSAndes pe) {
        this.pe = pe;
    }


    public long adicionarEPS(PersistenceManager pm, long id, String nombre) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaEps() + "(id, nombre) VALUES(?,?)");
        q.setParameters(id, nombre);
        return (long) q.executeUnique();
    }

    public List<EPS> darListaEps(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaEps());
        q.setResultClass(EPS.class);
        return q.executeList();
    }

    public List<IPS> darListaIps(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaIps());
        q.setResultClass(IPS.class);
        return q.executeList();
    }

    public List<Servicio> darListaServicios(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaServicio());
        q.setResultClass(Servicio.class);
        return q.executeList();
    }

    public List<Medicamento> darListaMedicamentos(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaMedicamento());
        q.setResultClass(Medicamento.class);
        return q.executeList();
    }

    public List<Paciente> darListaPacientes(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaPaciente());
        q.setResultClass(Paciente.class);
        return q.executeList();
    }

    /**
     * @param pm     - El manejador de persistencia
     * @param id
     * @param nombre
     * @return
     */
    public long adicionarIPS(PersistenceManager pm, long id, String nombre, long idEps, int capacidad, String localizacion) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIps() + "(ID, NOMBRE,  ID_EPS, CAPACIDAD, LOCALIZACION) VALUES(?,?,?,?,?)");
        q.setParameters(id, nombre, idEps, capacidad, localizacion);
        return (long) q.executeUnique();
    }

    public long adicionarUsuario(PersistenceManager pm, long id, long idCampana, Date fechaNac, String nombre, String correo, String tipoDocumento, String tipoUsuario) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaUsuario() + " (ID, ID_EPS_GERENTE, ID_EPS_ADMIN, ID_IPS_RECEPCIONISTA, ID_CAMPANA_USUARIO, FECHA_NACIMIENTO, NOMBRE, CORREO, TIPO_DOCUMENTO, TIPO_USUARIO) "
                + " VALUES (?,?,?,?,?,?,?,?,?,?) ");
        q.setParameters(id, null, null, null, idCampana, nombre, correo, tipoDocumento, tipoUsuario);
        return (long) q.executeUnique();
    }


    public long adicionarPaciente(PersistenceManager pm, long numDocumento, long idEps, String estado) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaUsuario() + "(ID_USUARIO, ID_EPS, ESTADO_SALUD) VALUES (?,?,?)");
        q.setParameters(numDocumento, idEps, estado);
        return (long) q.executeUnique();
    }


    public long adicionarMedico(PersistenceManager pm, long numDoc, long numRegistro, String especialidad) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaMedico() + "(ID_USUARIO, NUMREG, ESPECIALIDAD) VALUES(?, ?, ?)");
        q.setParameters(numDoc, numRegistro, especialidad);
        return (long) q.executeUnique();
    }

    public long adicionarMedicoAIps(PersistenceManager pm, long numDoc, long idIps) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIps_Medico() + "(ID_IPS, ID_MEDICO) VALUES(?,?)");
        q.setParameters(idIps, numDoc);
        return (long) q.executeUnique();
    }

    public long deshabilitarServicio(PersistenceManager pm, String nombre, Date inicio, Date fin) {
        Query q = pm.newQuery(SQL, "UPDATE " + pe.getTablaServicio() + " SET INICIO_INHABILITACION = ?, FIN_INHABILITACION = ?, INHABILITADO = ? WHERE NOMBRE = ?");
        q.setParameters(inicio, fin, "S", nombre);
        return (long) q.executeUnique();
    }

    /**
     * @param pm
     * @param id
     * @param capacidad
     * @param nombre
     * @return
     */
    public long adicionarServicio(PersistenceManager pm, long id, int capacidad, String nombre) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaServicio() + "(id, capacidad, nombre) VALUES(?,?,?)");
        q.setParameters(id, capacidad, nombre);
        return (long) q.executeUnique();
    }

    public long consultarServicioCaracteristica(PersistenceManager pm, String caracteristica, String aBuscar) {
        Query q = pm.newQuery(SQL, "SELECT * "
                + "FROM A_SERVICIO s"
                + "WHERE " + aBuscar + " = " + caracteristica);
        return (long) q.executeUnique();

    }

    public List<Integer> darCantidadServiciosIPS(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT ips., ser.nombre, count()" +
                "FROM IPS, IPS_SERVICIOS ipsr, SERVICIOS ser" +
                "WHERE ips.id = ipsr.id_IPS" +
                "AND ipsr.id_servicio = ser.id" +
                "GROUP BY ips.*, ser.nombre");
        return q.executeResultList();
    }

    public List<String> dar20ServiciosSolicitados(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT TOP 20 ser.nombre, count(*)" +
                "FROM A_ORDEN ord, A_ORDEN_SERVICIOS ordser, A_SERVICIO ser" +
                "WHERE ord.id = ordser.id_orden" +
                "AND ordser.id_servicio = ser.id" +
                "GROUP BY ser.nombre" +
                "ORDER BY count(*) DESC)");
        return q.executeResultList();
    }


}
