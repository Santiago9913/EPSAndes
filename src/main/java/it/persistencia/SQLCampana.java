package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Date;
import java.util.ArrayList;

class SQLCampana {

    private final static String SQL = PersistenciaEPSAndes.SQL;

    private PersistenciaEPSAndes pe;

    public SQLCampana(PersistenciaEPSAndes pe) { this.pe = pe; }

    public long adicionarCampana(PersistenceManager pm, long idCampana, int idOrg, int participantes, Date f_inicio, Date f_fin) {
        Query q = pm.newQuery(SQL, "INSERT INTO " +pe.getTablaCamapana()+ " (id, id_organizador, cantidad_inscritos, fecha_inicio, fecha_fin) " +
                "VALUES (?, ?, ?, ?, ?)");
        q.setParameters(idCampana, idOrg, participantes, f_inicio, f_fin);
        return (long) q.executeUnique();
    }
}
