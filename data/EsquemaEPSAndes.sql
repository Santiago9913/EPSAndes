CREATE SEQUENCE EPSAndes_sequence; 

-- Creacion tabla A_EPS y especificacion de sus restricciones
CREATE TABLE A_EPS(
  ID        NUMBER,
  NOMBRE    VARCHAR(255) NOT NULL, 
  CONSTRAINT A_EPS_PK PRIMARY KEY (ID)
); 

-- Creacion tabla A_EPS_ADMINISTRADORES y especificacion de sus restricciones
CREATE TABLE A_EPS_ADMINISTRADORES(
  ID_EPS              NUMBER, 
  ID_ADMINISTRADOR    NUMBER, 
  CONSTRAINT A_EPS_ADMINISTRADORES_PK PRIMARY KEY (ID_EPS, ID_ADMINISTRADOR)
);

ALTER TABLE A_EPS_ADMINISTRADORES
  ADD CONSTRAINT FK_ID_ADMINISTRADOR FOREIGN KEY (ID_ADMINISTRADOR)
  REFERENCES A_ADMINISTRADOR(ID)
  ENABLE; 
    
ALTER TABLE A_EPS_ADMINISTRADORES
  ADD CONSTRAINT FK_ID_EPS_ADMIN FOREIGN KEY (ID_EPS)
  REFERENCES A_EPS(ID)
  ENABLE; 
    
    
--  Creacion tabla A_EPS_IPS y especificacion de sus restricciones
CREATE TABLE A_EPS_IPS(
  ID_EPS      NUMBER, 
  ID_IPS      NUMBER NOT NULL, 
  CONSTRAINT  A_EPS_IPS_PK PRIMARY KEY (ID_EPS, ID_IPS)
);

ALTER TABLE A_EPS_IPS
  ADD CONSTRAINT FK_ID_IPS FOREIGN KEY (ID_IPS)
  REFERENCES A_IPS
  ENABLE;
  
ALTER TABLE A_EPS_IPS
  ADD CONSTRAINT FK_ID_EPS_IP FOREIGN KEY (ID_EPS)
  REFERENCES A_EPS(ID)
  ENABLE; 

-- Creacion tabla A_EPS_PACIENTE y especificacion de sus restricciones
CREATE TABLE A_EPS_PACIENTES( 
    ID_EPS      NUMBER, 
    ID_PACIENTE   NUMBER NOT NULL,
    CONSTRAINT A_EPS_PACIENTES_PK PRIMARY KEY (ID_EPS, ID_PACIENTE)
);

ALTER TABLE A_EPS_PACIENTES
  ADD CONSTRAINT FK_ID_PACIENTE FOREIGN KEY (ID_PACIENTE)
  REFERENCES A_PACIENTE(ID)
  ENABLE;
  
ALTER TABLE A_EPS_PACIENTES
  ADD CONSTRAINT FK_ID_EPS_PAC FOREIGN KEY (ID_EPS)
  REFERENCES A_EPS(ID)
  ENABLE; 

-- Creacion tabla A_EPS_GERENTES y especificacion de sus restricciones
CREATE TABLE A_EPS_GERENTES (
  ID_EPS        NUMBER,
  ID_GERENTE  NUMBER,
  CONSTRAINT A_EPS_GERENTES_PK PRIMARY KEY (ID_EPS, ID_GERENTE)
 );
 
ALTER TABLE A_EPS_GERENTES
  ADD CONSTRAINT FK_ID_GERENTE FOREIGN KEY (ID_GERENTE)
  REFERENCES A_GERENTE(ID)
  ENABLE;
  
ALTER TABLE A_EPS_GERENTES
  ADD CONSTRAINT FK_ID_EPS_GER FOREIGN KEY (ID_EPS)
  REFERENCES A_EPS(ID)
  ENABLE; 
  
-- Creacion tabla A_ADMINISTRADOR y especificacion de sus restricciones
CREATE TABLE A_ADMINISTRADOR(
  ID        NUMBER,
  NOMBRE    VARCHAR(255) NOT NULL,
  CORREO    VARCHAR(255) NOT NULL UNIQUE,
  CONSTRAINT A_ADMINISTRADOR_PK PRIMARY KEY (ID)
);

-- CREACION tabla A_PACIENTE y especificacion de sus restricciones
CREATE TABLE A_PACIENTE(
  ID            NUMBER, 
  NOMBRE        VARCHAR(255) NOT NULL,
  CORREO        VARCHAR(255) NOT NULL UNIQUE, 
  F_NACIMIENTO  DATE NOT NULL, 
  ESTADO        VARCHAR(255), 
  TIPO_DOC      VARCHAR(255),
  CONSTRAINT A_PACIENTE_PK PRIMARY KEY (ID)
);

ALTER TABLE A_PACIENTE
  ADD CONSTRAINT P_TIPO_DOC CHECK(TIPO_DOC IN(
    'PASAPORTE', 
    'CEDULA',
    'TARJETA DE IDENTIDAD'
  )) ENABLE; 

-- Creacion tabla A_PACIENTE_URGENCIA y especificacion de sus restricciones
CREATE TABLE A_PACIENTE_URGENCIAS(
  ID_PACIENTE       NUMBER, 
  ID_URGENCIA       NUMBER,
  CONSTRAINT A_PACIENTE_URGENCIAS_PK PRIMARY KEY (ID_PACIENTE, ID_URGENCIA)
);

ALTER TABLE A_PACIENTE_URGENCIAS
  ADD CONSTRAINT FK_ID_URGENCIAS FOREIGN KEY (ID_URGENCIA)
  REFERENCES A_URGENCIA(ID)
  ENABLE; 
  
ALTER TABLE  A_PACIENTE_URGENCIAS
  ADD CONSTRAINT FK_ID_PACIENTE_URG FOREIGN KEY(ID_PACIENTE)
  REFERENCES A_PACIENTE(ID)
  ENABLE;
  
-- Creacion tabla A_PACIENTE_RESERVAS y especificacion de sus restricciones
CREATE TABLE A_PACIENTE_RESERVAS(
  ID_PACIENTE       NUMBER, 
  ID_CONSULTA       NUMBER,
  CONSTRAINT A_PACIENTE_RESERVAS_PK PRIMARY KEY (ID_PACIENTE,  ID_CONSULTA)
);

ALTER TABLE A_PACIENTE_RESERVAS
  ADD CONSTRAINT FK_ID_CONSULTA FOREIGN KEY (ID_CONSULTA)
  REFERENCES A_CONSULTA(ID)
  ENABLE;
  
ALTER TABLE A_PACIENTE_RESERVAS
  ADD CONSTRAINT FK_ID_PACIENTE_RES FOREIGN KEY(ID_PACIENTE)
  REFERENCES A_PACIENTE(ID)
  ENABLE;
  
-- Creacion tabla A_GERENTE y especificacion de sus restricciones
CREATE TABLE A_GERENTE(
  ID        NUMBER,
  NOMBRE    VARCHAR(255) NOT NULL,
  CORREO    VARCHAR(255) NOT NULL UNIQUE,
  CONSTRAINT A_GERENTE_PK PRIMARY KEY (ID)
);

-- Creacion tabla A_IPS y especificacion de sus restricciones
CREATE TABLE A_IPS(
  ID            NUMBER, 
  NOMBRE        VARCHAR(255),
  CAPACIDAD     NUMBER,
  LOCALIZACION  VARCHAR(255) NOT NULL,
  CONSTRAINT A_IPS_PK PRIMARY KEY (ID)
);

ALTER TABLE A_IPS
  ADD CONSTRAINT C_CAPACIDAD
  CHECK (CAPACIDAD > 0)
  ENABLE; 
  
-- Creacion tabla A_IPS_SECRETARIAS
CREATE TABLE A_IPS_SECRETARIAS(
  ID_IPS    NUMBER,
  ID_SECRETARIA   NUMBER NOT NULL, 
  CONSTRAINT A_IPS_SECRETARIAS_PK PRIMARY KEY (ID_IPS, ID_SECRETARIA)
);

ALTER TABLE A_IPS_SECRETARIAS
  ADD CONSTRAINT FK_ID_SECRETARIA FOREIGN KEY(ID_SECRETARIA)
  REFERENCES A_SECRETARIA(ID)
  ENABLE;

ALTER TABLE A_IPS_SECRETARIAS
  ADD CONSTRAINT FK_ID_IPS_SEC FOREIGN KEY(ID_IPS)
  REFERENCES A_IPS(ID)
  ENABLE;
  
  
-- Creacion tabla A_IPS_SERVICIOS
CREATE TABLE A_IPS_SERVICIOS(
  ID_IPS          NUMBER, 
  ID_SERVICIO     NUMBER NOT NULL,
  CONSTRAINT A_IPS_SERVICIOS_PK PRIMARY KEY(ID_IPS, ID_SERVICIO) 
);

ALTER TABLE A_IPS_SERVICIOS
  ADD CONSTRAINT FK_ID_SERVICIO FOREIGN KEY (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
  ENABLE;
  
ALTER TABLE A_IPS_SERVICIOS
  ADD CONSTRAINT FK_ID_IPS_SERV FOREIGN KEY(ID_IPS)
  REFERENCES A_IPS(ID)
  ENABLE;
  
-- Creacion tabla A_IPS_MEDICOS
CREATE TABLE A_IPS_MEDICOS(
  ID_IPS          NUMBER, 
  ID_MEDICO     NUMBER NOT NULL,
  CONSTRAINT A_IPS_MEDICOS_PK PRIMARY KEY(ID_IPS, ID_MEDICO) 
);

ALTER TABLE A_IPS_MEDICOS
  ADD CONSTRAINT FK_ID_MEDICO FOREIGN KEY (ID_MEDICO)
  REFERENCES A_MEDICO(ID)
  ENABLE;
  
ALTER TABLE A_IPS_MEDICOS
  ADD CONSTRAINT FK_ID_IPS_MED FOREIGN KEY(ID_IPS)
  REFERENCES A_IPS(ID)
  ENABLE;


-- Creacion tabla A_IPS_HORARIOS
CREATE TABLE A_IPS_HORARIOS(
  ID_IPS          NUMBER, 
  ID_HORARIO      NUMBER NOT NULL,
  CONSTRAINT A_IPS_HORARIOS_PK PRIMARY KEY(ID_IPS, ID_HORARIO)  
);

ALTER TABLE A_IPS_HORARIOS
  ADD CONSTRAINT FK_ID_HORARIO FOREIGN KEY(ID_HORARIO)
  REFERENCES A_HORARIO(ID)
  ENABLE; 
  
ALTER TABLE A_IPS_HORARIOS
  ADD CONSTRAINT FK_ID_IPS_HOR FOREIGN KEY(ID_IPS)
  REFERENCES A_IPS(ID)
  ENABLE;
  
  
-- Creacion table A_SECRETARIA
CREATE TABLE A_SECRETARIA(
  ID          NUMBER,
  NOMBRE      VARCHAR(255) NOT NULL, 
  CORREO      VARCHAR(255) NOT NULL UNIQUE, 
  CONSTRAINT A_SECRETARIA_PK PRIMARY KEY (ID)
);

-- Creacion tabla A_MEDICO
CREATE TABLE A_MEDICO(
  ID            NUMBER, 
  NOMBRE        VARCHAR(255) NOT NULL, 
  CORREO        VARCHAR(255) NOT NULL UNIQUE, 
  ESPECIALIDAD  VARCHAR(255),
  CONSTRAINT A_MEDICO_PK PRIMARY KEY (ID)
);

-- Creacion tabla A_MEDICO_CONSULTA
CREATE TABLE A_MEDICO_CONSULTA(
  ID_MEDICO     NUMBER, 
  ID_CONSULTA   NUMBER NOT NULL,
  CONSTRAINT A_MEDICO_CONSULTA_PK PRIMARY KEY (ID_MEDICO, ID_CONSULTA)
);

ALTER TABLE A_MEDICO_CONSULTA
  ADD CONSTRAINT FK_ID_CONSULTA_MED FOREIGN KEY(ID_CONSULTA)
  REFERENCES A_CONSULTA(ID)
  ENABLE;

ALTER TABLE A_MEDICO_CONSULTA
  ADD CONSTRAINT FK_ID_MEDICO_CONS FOREIGN KEY (ID_MEDICO)
  REFERENCES A_MEDICO(ID)
  ENABLE;
  
  
-- Creacion tabla A_MEDICO_PACIENTE
CREATE TABLE A_MEDICO_PACIENTES(
  ID_MEDICO       NUMBER,
  ID_PACIENTE     NUMBER,
  CONSTRAINT A_MEDICO_PACIENTES_PK PRIMARY KEY (ID_MEDICO, ID_PACIENTE)
);

ALTER TABLE A_MEDICO_PACIENTES
  ADD CONSTRAINT FK_ID_PACIENTE_MED FOREIGN KEY (ID_PACIENTE)
  REFERENCES A_PACIENTE(ID)
  ENABLE;
  
ALTER TABLE A_MEDICO_PACIENTES
  ADD CONSTRAINT FK_ID_MEDICO_PACI FOREIGN KEY (ID_MEDICO)
  REFERENCES A_MEDICO(ID)
  ENABLE;
  
  
-- Creacion tabla A_MEDICO_URGENCIAS
CREATE TABLE A_MEDICO_URGENCIAS(
  ID_MEDICO     NUMBER,
  ID_URGENCIA   NUMBER, 
  CONSTRAINT A_MEDICO_URGENCIAS_PK PRIMARY KEY (ID_MEDICO)
);

ALTER TABLE A_MEDICO_URGENCIAS
  ADD CONSTRAINT FK_ID_URGENCIA FOREIGN KEY (ID_URGENCIA)
  REFERENCES A_URGENCIA(ID)
  ENABLE;
  
ALTER TABLE A_MEDICO_URGENCIAS
  ADD CONSTRAINT FK_ID_MEDICO_URG FOREIGN KEY (ID_MEDICO)
  REFERENCES A_MEDICO(ID)
  ENABLE;
  
  
-- Creacion tabla MEDICO_REGISTRO
CREATE TABLE A_MEDICO_REGISTRO(
  ID_MEDICO     NUMBER, 
  NUM_REGISTRO  NUMBER NOT NULL UNIQUE, 
  CONSTRAINT A_MEDICO_REGISTRO_PK PRIMARY KEY (ID_MEDICO)
);

ALTER TABLE A_MEDICO_REGISTRO
  ADD CONSTRAINT C_REGISTRO 
  CHECK (NUM_REGISTRO > 0)
  ENABLE; 
  
ALTER TABLE A_MEDICO_REGISTRO
  ADD CONSTRAINT FK_ID_MEDICO_REG FOREIGN KEY (ID_MEDICO)
  REFERENCES A_MEDICO(ID)
  ENABLE;
  
  
-- Creacion tabla A_URGENCIA 
CREATE TABLE A_URGENCIA(
  ID        NUMBER, 
  TRIAGE    NUMBER, 
  ID_ORDEN  NUMBER UNIQUE, 
  CONSTRAINT A_URGENCIA_PK PRIMARY KEY (ID)
);

ALTER TABLE A_URGENCIA
  ADD CONSTRAINT C_TRIAGE 
  CHECK (TRIAGE > 0)
  ENABLE;
  
ALTER TABLE A_URGENCIA
  ADD CONSTRAINT C_TRIAGE 
  CHECK (TRIAGE < 11)
  ENABLE;
  
ALTER TABLE A_URGENCIA
  ADD CONSTRAINT FK_ID_ORDEN FOREIGN KEY (ID_ORDEN)
  REFERENCES A_ORDEN(ID)
  ENABLE;


  
-- Crea la tabla de consulta y sus restricciones respectivas
CREATE TABLE A_CONSULTA 
(
  ID INT
, CUMPLIDA VARCHAR2(1) NOT NULL
, ID_ORDEN INT UNIQUE
, ID_ORDEN_PREVIA INT UNIQUE
, CONSTRAINT A_CONSULTA_PK PRIMARY KEY ( ID )
);

ALTER TABLE A_CONSULTA
   ADD CONSTRAINT FK_C_ORDEN FOREIGN KEY (ID_ORDEN)
  REFERENCES A_ORDEN(ID)
ENABLE;

ALTER TABLE A_CONSULTA
   ADD CONSTRAINT FK_C_ORDEN_PREVIA FOREIGN KEY (ID_ORDEN_PREVIA)
  REFERENCES A_ORDEN(ID)
ENABLE;

ALTER TABLE A_CONSULTA
  ADD CONSTRAINT CK_C_CUMPLIDA
    CHECK (CUMPLIDA IN ('Y', 'N'))
ENABLE;

-- Crea la tabla de Orden y sus restricciones respectivas
CREATE TABLE A_ORDEN 
(
  ID INT 
, DESCRIPCION VARCHAR(100)
, CONSTRAINT A_ORDEN_PK PRIMARY KEY ( ID )  
);

ALTER TABLE A_ORDEN
  ADD CONSTRAINT FK_O_URGENCIA FOREIGN KEY (ID_URGENCIA)
  REFERENCES A_URGENCIA(ID)
ENABLE;

CREATE TABLE A_ORDEN_SERVICIOS 
(
  ID_ORDEN INT 
, ID_SERVICIO INT
, CONSTRAINT A_ORDEN_SERVICIOS_PK PRIMARY KEY ( ID_ORDEN, ID_SERVICIO )    
);

ALTER TABLE A_ORDEN_SERVICIOS
   ADD CONSTRAINT FK_OS_ORDEN FOREIGN KEY (ID_ORDEN)
  REFERENCES A_ORDEN(ID)
ENABLE;

ALTER TABLE A_ORDEN_SERVICIOS
  ADD CONSTRAINT FK_OS_SERVICIOS FOREIGN KEY  (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

CREATE TABLE A_ORDEN_MEDICAMENTO
(
  ID_ORDEN INT 
, ID_MEDICAMENTO INT 
, CONSTRAINT A_ORDEN_MEDICAMENTO_PK PRIMARY KEY ( ID_ORDEN, ID_MEDICAMENTO )  
);

ALTER TABLE A_ORDEN_MEDICAMENTO
   ADD CONSTRAINT FK_OM_ORDEN FOREIGN KEY (ID_ORDEN)
  REFERENCES A_ORDEN(ID)
ENABLE;

ALTER TABLE A_ORDEN_MEDICAMENTO
   ADD CONSTRAINT FK_OM_MEDICAMENTO FOREIGN KEY (ID_MEDICAMENTO)
  REFERENCES A_MEDICAMENTO(ID)
ENABLE;

CREATE TABLE A_SERVICIO 
(
  ID INT,
  CAPACIDAD INT, 
  NOMBRE VARCHAR(50),
  CONSTRAINT A_SERVICIO_PK PRIMARY KEY ( ID )  
);

ALTER TABLE A_SERVICIO
  ADD CONSTRAINT CK_S_CAPACIDAD
    CHECK (CAPACIDAD > 0)
ENABLE;

ALTER TABLE A_SERVICIO
  ADD CONSTRAINT P_TIPO_SERV CHECK(NOMBRE IN(
    'PROCEDIMIENTO MEDICO', 
    'HOSPITALIZACION',
    'EXAMEN DIAGNOSTICO', 
    'TERAPIA'
  )) ENABLE; 
 

CREATE TABLE A_SERVICIO_HORARIO 
(
  ID_SERVICIO INT 
, ID_HORARIO INT NOT NULL 
, CONSTRAINT A_SERVICIO_HORARIO_PK PRIMARY KEY ( ID_SERVICIO, ID_HORARIO )  
);

ALTER TABLE A_SERVICIO_HORARIO 
  ADD CONSTRAINT FK_SH_SERVICIO FOREIGN KEY (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

ALTER TABLE A_SERVICIO_HORARIO 
  ADD CONSTRAINT FK_SH_HORARIO FOREIGN KEY (ID_HORARIO)
  REFERENCES A_HORARIO(ID)
ENABLE;

CREATE TABLE A_HORARIO 
(
  ID INT 
, H_INICIO TIMESTAMP 
, H_FIN TIMESTAMP
, CONSTRAINT A_HORARIO_PK PRIMARY KEY ( ID )  
);

ALTER TABLE A_HORARIO
  ADD CONSTRAINT CK_H_HORAS
    CHECK (H_INICIO < H_FIN)
ENABLE;

CREATE TABLE A_TERAPIA 
(
  ID_SERVICIO INT 
, NUM_SESIONES INT 
, TIPO VARCHAR2(30)
, CONSTRAINT A_TERAPIA PRIMARY KEY ( ID_SERVICIO )  
);

ALTER TABLE A_TERAPIA 
  ADD CONSTRAINT CK_T_SESIONES
    CHECK (NUM_SESIONES > 0)
ENABLE;

ALTER TABLE A_TERAPIA 
  ADD CONSTRAINT FK_T_SERVICIO FOREIGN KEY (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

CREATE TABLE A_EXAMEN_DIAGNOSTICO 
(
  ID_SERVICIO INT 
, RESULTADOS VARCHAR2(255) NOT NULL 
, CONSTRAINT A_EXAMEN_DIAGNOSTICO_PK PRIMARY KEY (ID_SERVICIO)
);

ALTER TABLE A_EXAMEN_DIAGNOSTICO
   ADD CONSTRAINT FK_ED_SERVICIO FOREIGN KEY(ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

CREATE TABLE A_HOSPITALIZACION 
(
  ID_SERVICIO INT 
, ID_VISITAS INT
, CONSTRAINT A_HOSPITALIZACION_PK PRIMARY KEY (ID_SERVICIO)
);

ALTER TABLE A_HOSPITALIZACION
   ADD CONSTRAINT FK_H_SERVICIO FOREIGN KEY (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

ALTER TABLE A_HOSPITALIZACION
   ADD CONSTRAINT FK_H_CONSULTA FOREIGN KEY (ID_VISITAS)
  REFERENCES A_CONSULTA(ID)
ENABLE;

CREATE TABLE A_PROCEDIMIENTOS
(
  ID_SERVICIO INT 
, TIPO VARCHAR2(50) 
, CONSTRAINT A_PROCEDIMIENTOS_PK PRIMARY KEY (ID_SERVICIO)
);

ALTER TABLE A_PROCEDIMIENTOS
  ADD CONSTRAINT FK_P_SERVICIO FOREIGN KEY (ID_SERVICIO)
  REFERENCES A_SERVICIO(ID)
ENABLE;

CREATE TABLE A_MEDICAMENTO
(
  ID INT 
, NOMBRE VARCHAR2(50) NOT NULL UNIQUE
, CONSTRAINT A_MEDICAMENTO_PK PRIMARY KEY (ID)
);

CREATE TABLE A_ROL(
  ID        NUMBER, 
  NOMBRE    VARCHAR(50) NOT NULL UNIQUE, 
  CONSTRAINT A_ROL_PK PRIMARY KEY (ID)
);

ALTER TABLE A_ROL
  ADD CONSTRAINT P_ROL CHECK(NOMBRE IN(
    'ADMINISTRADOR', 
    'PACIENTE',
    'MEDICO', 
    'GERENTE',
    'SECRETARIA'
  )) ENABLE; 

COMMIT; 