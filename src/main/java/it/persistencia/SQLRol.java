package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLRol {

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
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLRol (PersistenciaEPSAndes pe)
	{
		this.pe = pe;
	}

	public long adicionarRol(PersistenceManager pm, long idRol, String nombre) {
		Query q = pm.newQuery("INSERT INTO" + pe.getTablaRol() + "(id, nombre) values(?,?)");
		q.setParameters(idRol, nombre);
		return (long) q.executeUnique();
	}

}
