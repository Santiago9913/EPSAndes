package it.persistencia;

import javax.jdo.PersistenceManager;
import java.sql.Date;

class SQLServicio {

    private final static String SQL = PersistenciaEPSAndes.SQL;

    private PersistenciaEPSAndes ep;

    public SQLServicio(PersistenciaEPSAndes ep) {this.ep = ep;}

    public Object reqConsulta1(PersistenceManager pm, Date f_inicio, Date f_fin, int año) {
        return null;
    }
}
