--RFC1
SELECT ips., ser.nombre, count()
FROM IPS, IPS_SERVICIOS ipsr, SERVICIOS ser
WHERE ips.id = ipsr.id_IPS
    AND ipsr.id_servicio = ser.id
GROUP BY ips., ser.nombre;

--RFC2
SELECT TOP 20 ser.nombre, count()
FROM A_ORDEN ord, A_ORDEN_SERVICIOS ordser, A_SERVICIO ser
WHERE ord.id = ordser.id_orden
    AND ordser.id_servicio = ser.id
GROUP BY ser.nombre
ORDER BY count(*) DESC;

--RFC3


--RFC4


--RFC5