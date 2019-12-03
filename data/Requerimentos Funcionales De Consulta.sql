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


--RFC4


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
SELECT sq2.id_paciente
FROM (SELECT a.id_paciente id_paciente, b.id_servicio id_servicio, count(*) AS rtda
    FROM CONSULTA a, ORDENES_SERVICIOS b
    WHERE a.id_orden = b.id_orden
    GROUP BY a.id_paciente, b.id_servicio) sq2
GROUP BY sq2.id_paciente, sq2.id_servicio
HAVING count(sq2.id_servicio) >= 3
    AND sum(sq2.rtda) >= 12;

--RFC8

(Iteración 3)

--RFC9

SELECT p.*, b.id_servicio, a.fecha, b.id_ips
FROM Paciente p, Consulta a, Ordenes_Servicios b
WHERE
	a.id_paciente = p.id_paciente
	a.cumplida = 'S'
	AND a.id_orden = b.id_orden
	
--RFC10

SELECT a.id_servicio AS ServicioNoCumplido, b.id_servicio AS ServicioNoUtilizado, 
FROM
(
	SELECT b.id_servicio AS id_servicio, b.fecha AS fecha, b.ips AS ips, c.id_paciente AS id_paciente
	FROM Ordenes_Servicios b, Consulta c
	WHERE b.id_orden = c.id_orden
	AND c.cumplida = 'N'
) a,

(	
	SELECT id_servicio AS id_servicio
	FROM Servicio
	WHERE id_servicio NOT IN (Ordenes_Servicios)
) b,

(	SELECT id_usuario AS id_usuario
	FROM Paciente
	WHERE id.usuario NOT IN (Consulta)
) c

--RFC11

--RFC12