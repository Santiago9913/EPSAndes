package it.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.sql.Array;
import java.sql.Date;
import java.util.*;

class SQLServicio {

    private final static String SQL = PersistenciaEPSAndes.SQL;

    private PersistenciaEPSAndes ep;

    public SQLServicio(PersistenciaEPSAndes ep) {this.ep = ep;}

    public List<Object> reqConsulta1(PersistenceManager pm, Date f_inicio, Date f_fin) {
        String query = "SELECT ipser.id_ips AS ID_IPS, count(*) AS CANT_SERVICIOS ";
        query += "FROM IPS_SERVICIOS ipser, ";
        query += " WHERE  ";

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

    public void reabrirServicios(PersistenceManager pm, Hashtable<Integer, ArrayList<Integer>> listSer) {
        Enumeration<Integer> keys = listSer.keys();
        Integer next = null;
        while ( (next = keys.nextElement()) != null ) {
            ArrayList<Integer> currServs = listSer.get(next);
            for (Integer current: currServs) {
                String query = "UPDATE IPS_SERVICIOS " +
                        "SET INHABILITADO = 'N', " +
                        "INICIO_INHABILITACION = NULL, " +
                        "FIN_INHABILITACION = NULL " +
                        "WHERE id_ips = ? AND id_servicio = ?";
                Query q = pm.newQuery(SQL, query);
                q.setParameters(next, current);
                q.execute();
            }
        }
    }

    public void adicionarCampana(PersistenceManager pm, ArrayList<String> servs, String eps) {
        try {
            //Obtiene el id de la EPS
            String idQuery = "SELECT ID " +
                    "FROM " + ep.getTablaEps() +
                    " WHERE NOMBRE = ?";
            Query idQ = pm.newQuery(SQL, idQuery);
            idQ.setParameters(eps);
            int idEps = (int) idQ.execute();

            //Obtiene las ips correspondientes a la eps
            String sQuery = "SELECT ID" +
                    "FROM " + ep.getTablaIps() +
                    " WHERE ID_EPS = ?";
            Query sq = pm.newQuery(SQL, sQuery);
            sq.setParameters(idEps);
            ArrayList<Integer> idsIps = (ArrayList<Integer>) sq.execute(SQL, sq);

            //Obtiene los id de los servicios
            ArrayList<Integer> idsServ = new ArrayList<>();
            Iterator<String> itServ = servs.iterator();
            while (itServ.hasNext()) {
                String servQuery = "SELECT ID " +
                        "FROM " + ep.getTablaServicio() +
                        " WHERE nombre = ?";
                Query servQ = pm.newQuery(SQL, servQuery);
                String currName = itServ.next();
                servQ.setParameters(currName);
                idsServ.add((int)servQ.execute());
            }

            //Actualiza IPS_SERVICIOS
            Iterator<Integer> it = idsServ.iterator();
            while (it.hasNext()) {
                //Fija los parametros y el parametro inicial del servicio actual
                Map<String, Object> params = new HashMap<>();
                int currServ = it.next();
                params.put("servParam", currServ);

                String query = "UPDATE " + ep.getTablaIps_Servicios() +
                        " SET RESERVADO = 'S' ";
                for (int i = 0; i < idsIps.size(); i++) {
                    String currIpsParam = "ipsParam"+i;
                    if (i == 0) {
                        query += "WHERE (id_servicio = servParam AND id_ips = " + currIpsParam + ") ";
                    }
                    else {
                        query += "OR (id_servicio = servParam AND id_ips = " + currIpsParam + ") ";
                    }
                    params.put(currIpsParam, idsIps.get(i));
                }
                query += "LIMIT 1 ";
                Query q = pm.newQuery(SQL, query);
                q.executeWithMap(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
