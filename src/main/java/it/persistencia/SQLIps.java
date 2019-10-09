package it.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLIps {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes pea;

	public SQLIps (PersistenciaEPSAndes pea) {
		this.pea = pea;
	}

	public int rfc1(PersistenceManager pm, Timestamp f1, Timestamp f2) {
		String sql = "SELECT ips., ser.nombre, count() ";
		sql += "FROM IPS, IPS_SERVICIOS ipsr, SERVICIOS ser ";
		sql += "WHERE ips.id = ipsr.id_IPS ";
		sql += "AND ipsr.id_servicio = ser.id ";
		sql += "GROUP BY ips., ser.nombre ";
		Query q = pm.newQuery(SQL, sql);
		int r = (Integer) q.execute();
		return r;
	}
}
