package it.persistencia;

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

    /**
     * @param pm     - El manejador de persistencia
     * @param id
     * @param nombre
     * @return
     */
    public long adicionarRol(PersistenceManager pm, long id, String nombre) throws Exception {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaRol() + "(id, nombre) VALUES(?, ?)");
        q.setParameters(id, nombre);
        return (long) q.executeUnique();
    }

    /**
     * @param pm     - El manejador de persistencia
     * @param id
     * @param nombre
     * @return
     */
    public long adicionarAdministrador(PersistenceManager pm, long id, String nombre, String correo) {
        Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaAdministrador() + "(id, nombre, correo) VALUES(?, ?, ?)");
        q.setParameters(id, nombre, correo);
        return (long) q.executeUnique();
    }

    /**
     * @param pm     - El manejador de persistencia
     * @param id
     * @param nombre
     * @return
     */
    public long adicionarPaciente(PersistenceManager pm, long id, String nombre, String correo, Timestamp fNacimiento, String estado, String tipoDoc) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaPaciente() + "(id, nombre, correo, f_nacimiento, estado, TIPO_DOC) VALUES(?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, correo, fNacimiento, estado, tipoDoc);
        return (long) q.executeUnique();
    }

    /**
     * @param pm     - El manejador de persistencia
     * @param id
     * @param nombre
     * @return
     */
    public long adicionarGerente(PersistenceManager pm, long id, String nombre, String correo) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaGerente() + "(id, nombre, correo) VALUES(?, ?, ?)");
        q.setParameters(id, nombre, correo);
        return (long) q.executeUnique();
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
    public long adicionarIPS(PersistenceManager pm, long id, String nombre, int capacidad, String localizacion) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIps() + "(id, nombre, capacidad, localizacion) VALUES(?, ?, ?, ?)");
        q.setParameters(id, nombre, capacidad, localizacion);
        return (long) q.executeUnique();
    }

    public long adicionarIpsEnEps(PersistenceManager pm, long ips, long eps) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaEpsIps() + "(id_eps, id_ips) VALUES (?,?)");
        q.setParameters(eps, ips);
        return (long) q.executeUnique();
    }

    /**
     * @param pm
     * @param id
     * @param nombre
     * @param correo
     * @param especialidad
     * @return
     */
    public long adicionarMedico(PersistenceManager pm, long id, String nombre, String correo, String especialidad) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaMedico() + "(id, nombre, correo, especialidad) VALUES(?, ?, ?, ?)");
        q.setParameters(id, nombre, correo, especialidad);
        return (long) q.executeUnique();
    }

    public long adicionarRegistroMedico(PersistenceManager pm, long idMedico, long numRegistro) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaMedicoRegistro() + "(id_medico, num_registro) VALUES(?,?)");
        q.setParameters(idMedico, numRegistro);
        return (long) q.executeUnique();
    }

    public long adicionarMedicoAIps(PersistenceManager pm, long idIps, long idMedico) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIpsMedico() + "(id_ips, id_medico) VALUES(?,?)");
        q.setParameters(idIps, idMedico);
        return (long) q.executeUnique();
    }

    public long adicionarRecepcionista(PersistenceManager pm, long id, String nombre, String correo) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaSecretaria() + "(id, nombre, correo) VALUES(?,?,?)");
        q.setParameters(id, nombre, correo);
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

    public long adicionarServicioIps(PersistenceManager pm, long idIps, long idServ) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIpsServicio() + "(id_ips, id_servicio) VALUES(?,?)");
        q.setParameters(idIps, idServ);
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
