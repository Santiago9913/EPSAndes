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

    public List<Servicio> darListaServicios(PersistenceManager pm, long idIps) {
        Query q = pm.newQuery(SQL, "SELECT DISTINCT S.ID, ISS.CAPACIDAD, S.NOMBRE, ISS.INHABILITADO, ISS.INICIO_INHABILITACION, ISS.FIN_INHABILITACION, ISS.RESERVADO " +
                "FROM IPS_SERVICIOS ISS, IPS IP, SERVICIO S " +
                "WHERE S.ID = ISS.ID_SERVICIO AND ISS.ID_IPS = ?");
        q.setParameters(idIps);
        q.setResultClass(Servicio.class);
        return q.executeList();
    }

    public List<Servicio> darListaServiciosReservados(PersistenceManager pm, long idIps) {
        Query q = pm.newQuery(SQL, "SELECT DISTINCT S.ID, ISS.CAPACIDAD, S.NOMBRE, ISS.INHABILITADO, ISS.INICIO_INHABILITACION, ISS.FIN_INHABILITACION, ISS.RESERVADO " +
                "FROM IPS_SERVICIOS ISS, IPS IP, SERVICIO S " +
                "WHERE S.ID = ISS.ID_SERVICIO AND ISS.ID_IPS = ? AND ISS.RESERVADO = 'S'");
        q.setParameters(idIps);
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

    public List<Campana> darListaCampanas(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.getTablaCamapana());
        q.setResultClass(Campana.class);
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

    public List<Servicio> buscarServiciosPorFechas(PersistenceManager pm, Date inicio, Date fin) {
        Query q = pm.newQuery(SQL, "SELECT DISTINCT S.ID, ISS.CAPACIDAD, S.NOMBRE, ISS.INHABILITADO, ISS.INICIO_INHABILITACION, ISS.FIN_INHABILITACION, ISS.RESERVADO " +
                "FROM IPS_SERVICIOS ISS, IPS IP, SERVICIO S, ORDENES_SERVICIOS OS, ORDEN O  " +
                "WHERE S.ID = OS.ID_SERVICIO AND S.ID = ISS.ID_SERVICIO AND (OS.ID_ORDEN = O.ID AND O.FECHA BETWEEN ? AND ?)");
        q.setParameters(inicio, fin);
        q.setResultClass(Servicio.class);
        return q.executeList();
    }

    public List<IPS> darServicioEnIps(PersistenceManager pm, long idServicio) {
        Query q = pm.newQuery(SQL, "SELECT DISTINCT I.ID, I.ID_EPS, I.NOMBRE, I.CAPACIDAD, I.LOCALIZACION " +
                "FROM IPS I, IPS_SERVICIOS ISS, SERVICIO S  " +
                "WHERE S.ID = ? and ISS.ID_IPS =I.ID ");
        q.setParameters(idServicio);
        q.setResultClass(IPS.class);
        return q.executeList();
    }

    public List<Servicio> calcularIndice(PersistenceManager pm, long idServicio){
        Query q = pm.newQuery(SQL, "select s.id, s.nombre, (count(os.id_servicio) / (select count(*) from ordenes_servicios))*100 porcentajeUso " +
                "from ordenes_servicios os, servicio s " +
                "where os.id_servicio = ? and s.id = os.id_servicio " +
                "group by os.id_servicio, s.id, s.nombre");
        q.setParameters(idServicio);
        q.setResultClass(Servicio.class);
        return q.executeList();
    }


    public List<Servicio> darServiciosCampana(PersistenceManager pm, long idCampana){
        Query q = pm.newQuery(SQL, "select distinct s.id, iss.id_ips, iss.capacidad, s.nombre, iss.inhabilitado, iss.inicio_inhabilitacion, iss.fin_inhabilitacion, iss.reservado " +
                "from campana c, eps e, ips ip, servicio s, ips_servicios iss " +
                "where (e.id_campana = ?) and (ip.id_eps = e.id) and (iss.id_ips = ip.id )and (iss.id_servicio = s.id) and (iss.reservado = 'S')");
        q.setParameters(idCampana);
        q.setResultClass(Servicio.class);
        return q.executeList();
    }

    public List<Servicio> darListaServiciosTotales(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from servicio");
        q.setResultClass(Servicio.class);
        return q.executeList();
    }

    public long adicionarMedicoAIps(PersistenceManager pm, long numDoc, long idIps) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaIps_Medico() + "(ID_IPS, ID_MEDICO) VALUES(?,?)");
        q.setParameters(idIps, numDoc);
        return (long) q.executeUnique();
    }

    public long deshabilitarServicio(PersistenceManager pm, long idServicio, long idIps, Date inicio, Date fin) {
        Query q = pm.newQuery(SQL, "UPDATE IPS_SERVICIOS " +
                "SET INICIO_INHABILITACION = ? , FIN_INHABILITACION = ? , INHABILITADO = 'S' " +
                "WHERE ID_IPS = ? AND ID_SERVICIO = ?");
        q.setParameters(inicio, fin, idServicio, idIps);
        return (long) q.executeUnique();
    }

    public long cancelarServicioCampana(PersistenceManager pm, long idServicio, long idIps){
        Query q = pm.newQuery(SQL,"update ips_servicios " +
                "set reservado = 'N' " +
                "where id_servicio = ? and id_ips = ? " );
        q.setParameters(idServicio,idIps);
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
