package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPaciente {

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
	public SQLPaciente (PersistenciaEPSAndes pe)
	{
		this.pe = pe;
	}

	public long reservarServicio(PersistenceManager pm, long id, long idConsulta) {
		Query q = pm.newQuery(SQL, "INSERT INTO" + pe.getTablaPacienteReserva() + "(id_paciente, id_consulta) VALUES(?,?)");
		q.setParameters(id, idConsulta);
		return (long) q.executeUnique();
	}


}
