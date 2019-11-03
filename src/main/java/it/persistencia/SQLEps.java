package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLEps {

    private final static String SQL = PersistenciaEPSAndes.SQL;
    private PersistenciaEPSAndes pe;

    public SQLEps(PersistenciaEPSAndes pe) {
        this.pe = pe;
    }

    public void adicionarCampana(PersistenceManager pm, String eps, long idCampana) {
        String query = "UPDATE " + pe.getTablaEps() +
                       " SET ID_CAMPANA = ? " +
                       "WHERE NOMBRE = ?";
        Query q = pm.newQuery(SQL, query);
        q.setParameters(idCampana, eps);
        q.execute();
    }
}
