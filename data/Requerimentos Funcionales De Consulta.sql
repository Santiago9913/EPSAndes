--RFC1
SELECT ips.id, ser.nombre, count(*)
FROM IPS AS ips, IPS_SERVICIOS AS ipsr, SERVICIOS AS ser
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

SELECT u.*, s.nombre, c.fecha, ips.nombre
FROM Paciente p, Consulta c, Ordenes_Servicios os, Usuario u, Servicio s, Ips ips
WHERE
	c.id_paciente = p.id_usuario
	AND p.id_usuario = u.id				    
	AND c.cumplida = 'S'
	AND c.id_orden = os.id_orden
	AND os.id_servicio = s.id
	AND os.id_ips = ips.id						   
	
--RFC10						    

--Selecciona los nombres de servicios que no fueron prestados y sus ips que no los prestaron							  
SELECT ser.nombre as servicio, ips.nombre as ips
FROM (
	-- Servicios no reservados + servicios reservados y no cumplidos							    							    
	SELECT a.id_servicio as id_servicio
	FROM ( -- Servicios reservados pero no cumplidos
		SELECT b.id_servicio as id_servicio
		FROM Ordenes_Servicios b, Consulta c
		WHERE b.id_orden = c.id_orden
		AND c.cumplida = 'N'
	) a
	UNION
	SELECT b.id_servicio
	FROM ( -- Servicios que nisiquiera fueron reservados
		SELECT s.id as id_servicio
		FROM Servicio s
		WHERE s.id NOT IN (SELECT id_servicio FROM Ordenes_Servicios) 
    ) b
) servs, Ips_Servicios ipsServ, IPS ips, Servicio ser
WHERE servs.id_servicio	= ser.id
	AND ser.id = ipsServ.id_servicio						    
	AND ipsServ.id_ips = ips.id;				    

--Selecciona las fechas en las que los servicios no fueron utilizados
SELECT c.fecha
FROM Consulta c
WHERE c.cumplida = 'N';

--Selecciona los afiliados que no utilizaron servicios							    
SELECT u.nombre
FROM Usuario u, Paciente p, Consulta c
WHERE p.id_usuario = u.id
	AND p.id_usuario NOT IN (SELECT id_paciente FROM Consulta);							  
							    
--RFC11

--Servicio Mas Consumido por Semana
select Año, Semana, max(cant)
from (
    select s.nombre, to_char(o.fecha - 7/24,'IYYY') Año , to_char(o.fecha - 7/24,'IW') Semana ,count(s.id) cant
    from ordenes_servicios os, servicio s, orden o
    where os.id_servicio = s.id
    and os.id_orden = o.id
    group by  s.nombre, o.fecha, s.id, to_char(o.fecha - 7/24,'IYYY'), to_char(o.fecha- 7/24,'IW')
    order by o.fecha
) m 
group by Año, Semana
order by Semana;

--Servicio Menos Consumido por Semana
select Año, Semana, max(cant)
from (
    select s.nombre, to_char(o.fecha - 7/24,'IYYY') Año , to_char(o.fecha - 7/24,'IW') Semana ,count(s.id) cant
    from ordenes_servicios os, servicio s, orden o
    where os.id_servicio = s.id
    and os.id_orden = o.id
    group by  s.nombre, o.fecha, s.id, to_char(o.fecha - 7/24,'IYYY'), to_char(o.fecha- 7/24,'IW')
    order by o.fecha
) m 
group by Año, Semana
order by Semana;

--Ips Mas Solicitada
select  Año, Semana,  max(cant)
from (
    select i.nombre IPS, to_char(o.fecha - 7/24,'IYYY') Año , to_char(o.fecha - 7/24,'IW') Semana ,count(os.id_ips) cant
    from ordenes_servicios os, servicio s, orden o, IPS i
    where os.id_servicio = s.id
    and os.id_orden = o.id
    and os.id_ips = i.id 
    group by  i.nombre, o.fecha, s.id, to_char(o.fecha - 7/24,'IYYY'), to_char(o.fecha- 7/24,'IW')
    order by o.fecha
) m 
group by  Año, Semana
order by Semana;

--Ips Menos Solicitada
select  Año, Semana,  min(cant)
from (
    select i.nombre IPS, to_char(o.fecha - 7/24,'IYYY') Año , to_char(o.fecha - 7/24,'IW') Semana ,count(os.id_ips) cant
    from ordenes_servicios os, servicio s, orden o, IPS i
    where os.id_servicio = s.id
    and os.id_orden = o.id
    and os.id_ips = i.id 
    group by  i.nombre, o.fecha, s.id, to_char(o.fecha - 7/24,'IYYY'), to_char(o.fecha- 7/24,'IW')
    order by o.fecha
) m 
group by  Año, Semana
order by Semana;

--RFC12
