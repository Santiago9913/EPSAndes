package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLMedico {

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
	public SQLMedico (PersistenciaEPSAndes pe)
	{
		this.pe = pe;
	}


	public long adicionarOrden(PersistenceManager pm, long id, long idUrgencia) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaConsulta() + "(id, id_urgencia) VALUES(?, ?) ");
		q.setParameters(id, idUrgencia);
		return (long) q.executeUnique();
	}

}
