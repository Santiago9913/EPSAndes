package persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
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
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	private SQLAdministrador sqlAdministrador; 

	private SQLConsulta sqlConsulta; 

	private SQLEps sqlEps; 

	private SQLEpsAdministrador sqlEpsAdministrador; 

	private SQLEpsGerentes sqlEpsGerente;

	private SQLEpsIps sqlEpsIps; 

	private SQLEpsPacientes sqlEpsPacientes; 

	private SQLExamenDiagnostico sqlExamenDiagnostico; 

	private SQLGerente sqlGerente; 

	private SQLHorario sqlHorario; 

	private SQLHospitalizacion sqlHospitalizacion; 

	private SQLIps sqlIps; 

	private SQLIpsHorario sqlIpsHorario; 

	private SQLIpsMedico sqlIpsMedico; 

	private SQLIpsSecretaria sqlIpsSecretaria; 

	private SQLIpsServicios sqlIpsServicio; 

	private SQLMedicamento sqlMedicamento; 

	private SQLMedico sqlMedico; 

	private SQLMedicoConsulta sqlMedicoConsulta; 

	private SQLMedicoPaciente sqlMedicoPaciente; 

	private SQLMedicoUrgencia sqlMedicoUrgencia; 

	private SQLMedicoRegistro sqlMedicoRegistro; 

	private SQLOrden sqlOrden; 

	private SQLOrdenMedicamento sqlOrdenMedicamento; 

	private SQLOrdenServicio sqlOrdenServicio; 

	private SQLPaciente sqlPaciente; 

	private SQLPacienteReserva sqlPacienteReserva;

	private SQLPacienteUrgencia sqlPacienteUrgencia;

	private SQLProcedimiento sqlProcedimiento;

	private SQLRegistroMedico sqlRegistroMedico;

	private SQLRol sqlRol;

	private SQLSecretaria sqlSecretaria; 

	private SQLServicio sqlServicio; 

	private SQLServicioHorario sqlServicioHorario;

	private SQLTerapia sqlTerapia;

	private SQLUrgencia sqlUrgencia;


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
		tablas = leerNombresTablas(tableConifg);

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
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
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
	 * Cierra la conexi�n con la base de datos
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
		sqlAdministrador = new SQLAdministrador(this);
		sqlConsulta = new SQLConsulta(this);
		sqlEps = new SQLEps(this); 
		sqlEpsAdministrador = new SQLEpsAdministrador(this);
		sqlEpsGerente = new SQLEpsGerentes(this); 
		sqlEpsIps = new SQLEpsIps(this); 
		sqlEpsPacientes = new SQLEpsPacientes(this); 
		sqlExamenDiagnostico = new SQLExamenDiagnostico(this); 
		sqlGerente = new SQLGerente(this); 
		sqlHorario = new SQLHorario(this); 
		sqlHospitalizacion = new SQLHospitalizacion(this); 
		sqlIps = new SQLIps(this);
		sqlIpsHorario = new SQLIpsHorario(this);
		sqlIpsMedico = new SQLIpsMedico(this);
		sqlIpsSecretaria = new SQLIpsSecretaria(this);
		sqlIpsServicio = new SQLIpsServicios(this);
		sqlMedicamento = new SQLMedicamento(this);
		sqlMedico = new SQLMedico(this); 
		sqlMedicoConsulta = new SQLMedicoConsulta(this); 
		sqlMedicoPaciente = new SQLMedicoPaciente(this); 
		sqlMedicoUrgencia = new SQLMedicoUrgencia(this); 
		sqlOrden = new SQLOrden(this); 
		sqlOrdenMedicamento = new SQLOrdenMedicamento(this); 
		sqlOrdenServicio = new SQLOrdenServicio(this);
		sqlPaciente = new SQLPaciente(this); 
		sqlPacienteReserva = new SQLPacienteReserva(this);
		sqlPacienteUrgencia = new SQLPacienteUrgencia(this); 
		sqlProcedimiento = new SQLProcedimiento(this); 
		sqlRegistroMedico = new SQLRegistroMedico(this); 
		sqlRol = new SQLRol(this); 
		sqlSecretaria = new SQLSecretaria(this); 
		sqlServicio = new SQLServicio(this); 
		sqlServicioHorario = new SQLServicioHorario(this);
		sqlTerapia = new SQLTerapia(this); 
		sqlUrgencia = new SQLUrgencia(this); 
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaAdministrador() {
		return tablas.get (1);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaConsulta() {
		return tablas.get(3);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaEps() {
		return tablas.get(4);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaEpsAdministrador() {
		return tablas.get(5);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaEpsGerente() {
		return tablas.get(6);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaEpsIps() {
		return tablas.get(7);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaEpsPacientes() {
		return tablas.get(8);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaExamenDiagnostico() {
		return tablas.get(9);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaGerente() {
		return tablas.get(10);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaHorario() {
		return tablas.get(11);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaHospitalizacion() {
		return tablas.get(12);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaIps() {
		return tablas.get(2);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaIpsHorario() {
		return tablas.get(14);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaIpsMedico() {
		return tablas.get(15);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaIpsSecretaria() {
		return tablas.get(16);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaIpsServicio() {
		return tablas.get(17);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedicamento() {
		return tablas.get(18);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedico() {
		return tablas.get(19);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedicoConsulta() {
		return tablas.get(20);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedicoPaciente() {
		return tablas.get(21);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedicoRegistro() {
		return tablas.get(22);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaMedicoUrgencia() {
		return tablas.get(23);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaOrden() {
		return tablas.get(13);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaOrdenMedicamento() {
		return tablas.get(25);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaOrdenServicio() {
		return tablas.get(26);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaPaciente() {
		return tablas.get(27);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaPacienteReserva() {
		return tablas.get(28);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaPacienteUrgencia() {
		return tablas.get(29);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaProcedimiento() {
		return tablas.get(30);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaRegistroMedico() {
		return tablas.get(22);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaRol() {
		return tablas.get(35);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaSecretaria() {
		return tablas.get(31);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaServicio() {
		return tablas.get(32);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaServicioHorario() {
		return tablas.get(33);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaTerapia() {
		return tablas.get(34);
	}

	/**
	 * 
	 * @return
	 */
	public String getTablaUrgencia() {
		return tablas.get(24);
	}

	/**
	 * Transacci�n para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de Parranderos
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}


	public Rol registrarRol(String nombre) {

	}


}
