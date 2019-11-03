package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SQLServicio {

    private final static String SQL = PersistenciaEPSAndes.SQL;

    private PersistenciaEPSAndes ep;

    public SQLServicio(PersistenciaEPSAndes ep) {
        this.ep = ep;
    }

    public List<Object> reqConsulta1(PersistenceManager pm, Date f_inicio, Date f_fin, int ano) {
        String query = "SELECT ips.id, ser.nombre, count(*)";
        query += " FROM ";
        query += ep.getTablaIps() + " AS ips, ";
        query += ep.getTablaIps_Servicios() + " AS ipsr, ";
        query += ep.getTablaServicio() + " AS ser";
        query += " WHERE ips.id = ipsr.id_ips ";
        query += " AND ipsr.id_servicio = ser.id ";
        query += " GROUP BY ips.id, servicio.nombre ";

        Query q = pm.newQuery(SQL, query);
        return q.executeList();
    }

    public List<Object> reqConsulta2(PersistenceManager pm, Date f_inicio, Date f_fin) {
        String query = "SELECT TOP 20 ser.nombre, count(*) ";
        query += "FROM ORDEN ord, ORDEN_SERVICIOS ordser, SERVICIO ser, HORARIO hor ";
        query += "WHERE ord.id = ordser.id_orden ";
        query += "AND ordser.id_servicio = ser.id ";
        query += "AND ser.id_horario = hor.id ";
        query += "AND hor.hora_inicio < ? ";
        query += "AND hor.hora_fin > ? ";
        query += "GROUP BY ser.nombre ";
        query += "ORDER BY count(*) DESC";

        Query q = pm.newQuery(SQL, query);
        q.setParameters(f_inicio, f_fin);
        return q.executeList();
    }

    public List<Object> reqConsulta5(PersistenceManager pm, Date f_inicio, Date f_fin, int idPac) {
        String query = "SELECT sq.id_paciente, sq.id_servicio, sq.utilizacion_servicio ";
        query += "FROM (SELECT pac.id AS ID_PACIENTE, ords.id_servicio AS ID_SERVICIO, count(*) AS UTILIZACION_SERVICIO ";
        query += "FROM PACIENTE pac, CONSULTA con, ORDENES_SERVICIOS ords, SERVICIO ser, HORARIO hor ";
        query += "WHERE pac.id = con.id_paciente ";
        query += "AND con.id_orden = ords.id_orden ";
        query += "AND ords.id_servicio = ser.id ";
        query += "AND ser.id_horario = hor.id ";
        query += "AND hor.hora_inicio < ? ";
        query += "AND hor.hora_fin > ? ";
        query += "GROUP BY pac.id, ords.id_servicio) sq ";
        query += "WHERE sq.id_paciente = ?";

        Query q = pm.newQuery(SQL, query);
        q.setParameters(f_inicio, f_fin, idPac);
        return q.executeList();
    }

    public List<Object> reqConsulta7(PersistenceManager pm) {
        String query = "";
        Query q = pm.newQuery(SQL, query);
        return q.executeList();
    }

    public void reabrirServicios(PersistenceManager pm, List<Integer> listSer) {
        Iterator<Integer> it = listSer.iterator();
        while (it.hasNext()) {
            String query = "UPDATE SERVICIO\n" +
                    "SET inicio_inhabilitacion = NULL, fin_inhabilitacion = NULL, reservado = 'S'\n" +
                    "WHERE id = ?";
            Query q = pm.newQuery(SQL, query);
            q.setParameters(it.next());
            q.execute();
        }
    }

    public void adicionarCampana(PersistenceManager pm, ArrayList<Integer> servs) {
        Iterator<Integer> it = servs.iterator();
        while (it.hasNext()) {
            String query = "UPDATE SERVICIO " +
                    "SET RESERVADO = 'S' " +
                    "WHERE id = ?";
            Query q = pm.newQuery(SQL, query);
            q.setParameters(it.next());
            q.execute();
        }
    }
}
