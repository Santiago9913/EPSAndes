package persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class PersistenciaEPSAndes {


	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
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
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaEPSAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	/**
	 * 
	 */
	private SQLAdministrador sqlAdministrador; 

	/**
	 * 
	 */
	private PersistenciaEPSAndes() {

		pmf = JDOHelper.getPersistenceManagerFactory("EPSAndes");
		crearClasesSQL();

		tablas = new LinkedList<String>(); 
		tablas.add("EPSAndes_sequence"); 
		tablas.add("A_ADMINISTRADOR");			tablas.add("A_HOSPITALIZACION");		tablas.add("A_MEDICO_URGENCIAS");    tablas.add("A_TERAPIA");
		tablas.add("A_IPS");					tablas.add("A_ORDEN");				 	tablas.add("A_URGENCIA");			 tablas.add("A_ROL"); 
		tablas.add("A_CONSULTA");				tablas.add("A_IPS_HORARIOS");			tablas.add("A_ORDEN_MEDICAMENTO");  
		tablas.add("A_EPS");					tablas.add("A_IPS_MEDICOS");			tablas.add("A_ORDEN_SERVICIOS");
		tablas.add("A_EPS_ADMINISTRADORES");	tablas.add("A_IPS_SECRETARIAS");		tablas.add("A_PACIENTE");
		tablas.add("A_EPS_GERENTES");			tablas.add("A_IPS_SERVICIOS");			tablas.add("A_PACIENTE_RESERVAS");
		tablas.add("A_EPS_IPS");  				tablas.add("A_MEDICAMENTO");			tablas.add("A_PACIENTE_URGENCIAS");
		tablas.add("A_EPS_PACIENTES");			tablas.add("A_MEDICO");					tablas.add("A_PROCEDIMIENTOS");
		tablas.add("A_EXAMEN_DIAGNOSTICO");		tablas.add("A_MEDICO_CONSULTA");		tablas.add("A_SECRETARIA");
		tablas.add("A_GERENTE");				tablas.add("A_MEDICO_PACIENTES");		tablas.add("A_SERVICIO");
		tablas.add("A_HORARIO");				tablas.add("A_MEDICO_REGISTRO");		tablas.add("A_SERVICIO_HORARIO");
	}

	/**
	 * 
	 * @param tableConifg
	 */
	private PersistenciaEPSAndes(JsonObject tableConifg) {

		crearClasesSQL();
		tablas = leerNombreTablas(tableConifg);

		String unidadPersistencia = tableConifg.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}

	/**
	 * 
	 * @return
	 */
	public static PersistenciaEPSAndes getInstance() {
		if(instance == null) {
			instance = new PersistenciaEPSAndes();
		}

		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaEPSAndes(tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * 
	 */
	private void crearClasesSQL() {

	}


}
