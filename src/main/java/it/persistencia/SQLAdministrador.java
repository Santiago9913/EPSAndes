package it.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLAdministrador {


	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaEPSAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaEPSAndes pe;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLAdministrador (PersistenciaEPSAndes pe)
	{
		this.pe = pe;
	}

	/**
	 * 
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long adicionarRol(PersistenceManager pm, long id, String nombre) throws Exception {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaRol() + "(id, nombre) VALUES(?, ?)" );
		q.setParameters(id, nombre); 
		return (long) q.executeUnique();
	}

	/**
	 * 
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long adicionarAdministrador(PersistenceManager pm, long id, String nombre, String correo) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaAdministrador() + "(id, nombre, correo) VALUES(?, ?, ?)" );
		q.setParameters(id, nombre, correo); 
		return (long) q.executeUnique();
	}

	/**
	 * 
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long adicionarPaciente(PersistenceManager pm, long id, String nombre, String correo, Timestamp fNacimiento, String estado, String tipoDoc) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaPaciente() + "(id, nombre, correo, f_nacimiento, estado, TIPO_DOC) VALUES(?, ?, ?, ?, ?, ?)" );
		q.setParameters(id, nombre, correo, fNacimiento, estado, tipoDoc); 
		return (long) q.executeUnique();
	}

	/**
	 * 
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long adicionarGerente(PersistenceManager pm, long id, String nombre, String correo) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaGerente() + "(id, nombre, correo) VALUES(?, ?, ?)" );
		q.setParameters(id, nombre, correo); 
		return (long) q.executeUnique();
	}

	/**
	 * 
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long adicionarIPS(PersistenceManager pm, long id, String nombre, int capacidad, String localizacion) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaGerente() + "(id, nombre, capacidad, localizacion) VALUES(?, ?, ?, ?)" );
		q.setParameters(id, nombre, capacidad, localizacion); 
		return (long) q.executeUnique();
	}

	/**
	 * 
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

	/**
	 * 
	 * @param pm
	 * @param id
	 * @param capacidad
	 * @param nombre
	 * @return
	 */
	public long adicionarServicio(PersistenceManager pm, long id, int capacidad, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaServicio() + "(id, capacidad, nombre) VALUES(?,?,?)");
		q.setParameters(id, capacidad, nombre); 
		return (long) q.executeUnique();
	}

	public long consultarServicioCaracteristica(PersistenceManager pm, String caracteristica, String aBuscar) {
		Query q = pm.newQuery(SQL, "SELECT * "
				+  "FROM A_SERVICIO s"
				+  "WHERE " + aBuscar + " = " + caracteristica);
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
