package it.persistencia;

import javax.jdo.PersistenceManager;
import java.sql.Date;

class SQLServicio {

    private final static String SQL = PersistenciaEPSAndes.SQL;

    private PersistenciaEPSAndes ep;

    public SQLServicio(PersistenciaEPSAndes ep) {this.ep = ep;}

    public Object reqConsulta1(PersistenceManager pm, Date f_inicio, Date f_fin, int año) {
        String query = "SELECT ips.id, ser.nombre, count(*)";
        query += " FROM ";
        query += ep.getTablaIps() + " AS ips, ";
        query += ep.getTablaIps_Servicios() + " AS ipsr, ";
        query += ep.getTablaServicio() + " AS ser";
        query += " WHERE ips.id = ipsr.id_ips ";
        query += " AND ipsr.id_servicio = ser.id ";
        query += "GROUP BY ips.id, servicio.nombre";
        return null;
    }
}
