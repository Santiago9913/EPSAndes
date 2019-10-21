package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLMedico {

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
    public SQLMedico(PersistenciaEPSAndes pe) {
        this.pe = pe;
    }


    public long adicionarOrden(PersistenceManager pm, long id, String desc) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaOrden() + "(id, descripcion) VALUES(?,?)");
        q.setParameters(id, desc);
        return (long) q.executeUnique();
    }

    public long adicionarOrdenConServicio(PersistenceManager pm, long idOrden, long idSer) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaOrdenServicio() + "(id_orden, id_servicio) VALUES(?,?)");
        q.setParameters(idOrden, idSer);
        return (long) q.executeUnique();
    }

    public long adicionarOrdenConMedicamento(PersistenceManager pm, long idOrden, long idMed) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.getTablaOrdenMedicamento() + "(id_orden, id_medicamento) VALUES(?,?)");
        q.setParameters(idOrden, idMed);
        return (long) q.executeUnique();
    }

}
