DROP TABLE EPS CASCADE CONSTRAINTS;
DROP TABLE IPS CASCADE CONSTRAINTS;
DROP TABLE HORARIO CASCADE CONSTRAINTS;
DROP TABLE ORDEN CASCADE CONSTRAINTS;
DROP TABLE SERVICIO CASCADE CONSTRAINTS;
DROP TABLE MEDICAMENTO CASCADE CONSTRAINTS;
DROP TABLE USUARIO CASCADE CONSTRAINTS;
DROP TABLE MEDICO CASCADE CONSTRAINTS;
DROP TABLE IPS_MEDICOS CASCADE CONSTRAINTS;
DROP TABLE IPS_SERVICIOS CASCADE CONSTRAINTS;
DROP TABLE ORDENES_MEDICAMENTOS CASCADE CONSTRAINTS;
DROP TABLE PACIENTE CASCADE CONSTRAINTS;
DROP TABLE URGENCIA CASCADE CONSTRAINTS;
DROP TABLE CONSULTA CASCADE CONSTRAINTS;
DROP TABLE MEDICOS_PACIENTES CASCADE CONSTRAINTS;
DROP TABLE CAMPANA CASCADE CONSTRAINTS; 
DROP TABLE ORDENES_SERVICIOS;
DROP TABLE CAMPANA_HORARIO;
DROP TABLE IPS_HORARIO;
DROP TABLE SERVICIO_HORARIO; 


DELETE FROM EPS;
DELETE FROM IPS;
DELETE FROM HORARIO;
DELETE FROM ORDEN;
DELETE FROM SERVICIO; 
DELETE FROM MEDICAMENTO;
DELETE FROM USUARIO;
DELETE FROM MEDICO;
DELETE FROM IPS_MEDICOS;
DELETE FROM IPS_SERVICIOS;
DELETE FROM ORDEN_MEDICAMENTOS;
DELETE FROM PACIENTE;
DELETE FROM URGENCIA;
DELETE FROM CONSULTA;
DELETE FROM MEDICOS_PACIENTES;
DELETE FROM IPS_SERVICIOS; 

COMMIT;