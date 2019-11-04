--RFC1
SELECT ips., ser.nombre, count()
FROM IPS, IPS_SERVICIOS ipsr, SERVICIOS ser
WHERE ips.id = ipsr.id_IPS
    AND ipsr.id_servicio = ser.id
GROUP BY ips., ser.nombre;

--RFC2
SELECT TOP 20 ser.nombre, count(*)
FROM ORDEN ord, ORDEN_SERVICIOS ordser, SERVICIO ser, HORARIO hor
WHERE ord.id = ordser.id_orden
    AND ordser.id_servicio = ser.id
    AND ser.id_horario = hor.id
    AND hor.hora_inicio < ?
    AND hor.hora_fin > ?
GROUP BY ser.nombre
ORDER BY count(*) DESC;

--RFC3
select s.id, s.nombre, (os.id_servicio / (select count(*) from ordenes_servicios))*100 porcentajeUso
from ordenes_servicios os, servicio s
where os.id_servicio = 1 and s.id = os.id_servicio


--RFC4
--variante a
SELECT DISTINCT S.ID, ISS.CAPACIDAD, S.NOMBRE, ISS.INHABILITADO, ISS.INICIO_INHABILITACION, ISS.FIN_INHABILITACION, ISS.RESERVADO
FROM IPS_SERVICIOS ISS, IPS IP, SERVICIO S, ORDENES_SERVICIOS OS, ORDEN O 
WHERE S.ID = OS.ID_SERVICIO AND S.ID = ISS.ID_SERVICIO AND (OS.ID_ORDEN = O.ID AND O.FECHA BETWEEN ? AND ?

--variante b
SELECT DISTINCT I.ID, I.ID_EPS, I.NOMBRE, I.CAPACIDAD, I.LOCALIZACION
FROM IPS I, IPS_SERVICIOS ISS, SERVICIO S
WHERE S.ID = ? and ISS.ID_IPS =I.ID


--RFC5
SELECT sq.id_paciente, sq.id_servicio, sq.utilizacion_servicio
FROM (SELECT pac.id AS ID_PACIENTE, ords.id_servicio AS ID_SERVICIO, count(*) AS UTILIZACION_SERVICIO
	FROM PACIENTE pac, CONSULTA con, ORDENES_SERVICIOS ords, SERVICIO ser, HORARIO hor
	WHERE pac.id = con.id_paciente
		AND con.id_orden = ords.id_orden
		AND ords.id_servicio = ser.id
		AND ser.id_horario = hor.id
		AND hor.hora_inicio < ?
		AND hor.hora_fin > ?
	GROUP BY pac.id, ords.id_servicio) sq
WHERE sq.id_paciente = ?

--RFC6


--RFC7
SELECT 

--RFC8