CREATE SEQUENCE EPSAndes_sequence; 

CREATE TABLE EPS(
  ID        NUMBER,
  NOMBRE    VARCHAR(255) NOT NULL, 
  CONSTRAINT EPS_PK PRIMARY KEY (ID)
); 

CREATE TABLE IPS(
  ID        NUMBER,
  ID_EPS NUMBER NOT NULL UNIQUE,
  CAPACIDAD NUMBER NOT NULL,
  LOCALIZACION VARCHAR(255),
  CONSTRAINT IPS_PK PRIMARY KEY (ID),
  CONSTRAINT FK_I_EPS FOREIGN KEY (ID_EPS) REFERENCES EPS(ID),
  CONSTRAINT CHK_IPS CHECK(CAPACIDAD > 0)
);

CREATE TABLE HORARIO(
  ID        NUMBER,
  ID_IPS NUMBER NOT NULL UNIQUE,
  HORA_INICIO TIMESTAMP NOT NULL,
  HORA_FIN TIMESTAMP NOT NULL,
  CONSTRAINT HORARIO_PK PRIMARY KEY (ID),
  CONSTRAINT FK_H_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID),
  CONSTRAINT CHK_HORARIO CHECK (HORA_INICIO < HORA_FIN)
);

CREATE TABLE ORDEN (
  ID NUMBER,
  DESCRIPCION VARCHAR(255),
  CONSTRAINT ORDEN_PK PRIMARY KEY (ID)
);

CREATE TABLE SERVICIO(
  ID NUMBER,
  ID_ORDEN NUMBER NOT NULL UNIQUE,
  ID_HORARIO NUMBER NOT NULL UNIQUE,
  CAPACIDAD NUMBER,
  NOMBRE VARCHAR(255),
  CONSTRAINT SERVICIO_PK PRIMARY KEY (ID),
  CONSTRAINT FK_S_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT FK_S_HORARIO FOREIGN KEY (ID_HORARIO) REFERENCES HORARIO(ID),
  CONSTRAINT CHK_SERVICIO CHECK(CAPACIDAD > 0)
);

CREATE TABLE MEDICAMENTO (
  ID NUMBER,
  NOMBRE VARCHAR(255),
  CONSTRAINT MEDICAMENTO_PK PRIMARY KEY (ID)
);

CREATE TABLE USUARIO(
  ID        NUMBER,
  ID_EPS_GERENTE NUMBER UNIQUE,
  ID_EPS_ADMIN NUMBER UNIQUE,
  ID_IPS_RECEPCIONISTA  NUMBER UNIQUE,
  FECHA_NACIMIENTO TIMESTAMP,
  NOMBRE    VARCHAR(30),
  CORREO VARCHAR(255),
  TIPO_DOCUMENTO VARCHAR(255),
  TIPO_USUARIO VARCHAR(255),
  CONSTRAINT USUARIO_PK PRIMARY KEY (ID),
  CONSTRAINT FK_UG_EPS FOREIGN KEY (ID_EPS_GERENTE) REFERENCES EPS(ID),
  CONSTRAINT FK_UA_EPS FOREIGN KEY (ID_EPS_ADMIN) REFERENCES EPS(ID),
  CONSTRAINT FK_U_IPS FOREIGN KEY (ID_IPS_RECEPCIONISTA) REFERENCES IPS(ID),
  CONSTRAINT CHK_TIPO_DOCUMENTO CHECK (TIPO_DOCUMENTO IN ('CEDULA', 'PASAPORTE', 'TARJETA DE IDENTIDAD')),
  CONSTRAINT CHK_TIPO_USUARIO CHECK(TIPO_USUARIO IN ('ADMINISTRADOR', 'RECEPCIONISTA', 'GERENTE', 'MEDICO', 'PACIENTE'))
);

CREATE TABLE MEDICO(
  ID_USUARIO NUMBER,
  NUMREG NUMBER NOT NULL UNIQUE,
  ESPECIALIDAD VARCHAR(255),
  CONSTRAINT MEDICO_PK PRIMARY KEY (ID_USUARIO),
  CONSTRAINT FK_M_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE IPS_MEDICOS(
  ID_IPS NUMBER NOT NULL,
  ID_MEDICO NUMBER NOT NULL,
  CONSTRAINT IM_M PRIMARY KEY (ID_IPS, ID_MEDICO),
  CONSTRAINT FK_IM_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID),
  CONSTRAINT FK_IM_MEDICO FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO)
);

CREATE TABLE IPS_SERVICIOS(
  ID_IPS NUMBER NOT NULL,
  ID_SERVICIO NUMBER NOT NULL,
  CONSTRAINT IS_PK PRIMARY KEY (ID_IPS, ID_SERVICIO),
  CONSTRAINT FK_IS_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID),
  CONSTRAINT FK_IS_SERVICIO FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIO(ID)
);

CREATE TABLE ORDEN_MEDICAMENTO (
  ID_ORDEN NUMBER NOT NULL,
  ID_MEDICAMENTO NUMBER NOT NULL,
  CONSTRAINT ORDMED_PK PRIMARY KEY (ID_ORDEN, ID_MEDICAMENTO),
  CONSTRAINT FK_OM_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT FK_OM_MEDICAMENTO FOREIGN KEY (ID_MEDICAMENTO) REFERENCES MEDICAMENTO(ID)
);

CREATE TABLE PACIENTE(
  ID_USUARIO NUMBER,
  ID_EPS NUMBER NOT NULL UNIQUE,
  ESTADO_SALUD VARCHAR(255),
  CONSTRAINT PACIENTE_PK PRIMARY KEY (ID_USUARIO),
  CONSTRAINT FK_P_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),
  CONSTRAINT FK_P_EPS FOREIGN KEY (ID_EPS) REFERENCES EPS(ID)
);

CREATE TABLE URGENCIA(
  ID NUMBER,
  ID_PACIENTE NUMBER NOT NULL UNIQUE,
  ID_MEDICO NUMBER NOT NULL UNIQUE,
  ID_ORDEN NUMBER UNIQUE,
  TRIAGE NUMBER NOT NULL,
  CONSTRAINT URGENCIA_PK PRIMARY KEY (ID),
  CONSTRAINT FK_U_PACIENTE FOREIGN KEY (ID_PACIENTE) REFERENCES PACIENTE(ID_USUARIO),
  CONSTRAINT FK_U_MEDICO FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO),
  CONSTRAINT FK_U_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT CHK_URGENCIA CHECK(TRIAGE > 0 AND TRIAGE < 11)
);

CREATE TABLE CONSULTA(
  ID NUMBER,
  ID_PACIENTE NUMBER NOT NULL UNIQUE,
  ID_MEDICO NUMBER NOT NULL UNIQUE,
  ID_ORDEN NUMBER UNIQUE,
  CUMPLIDA VARCHAR(1),
  CONSTRAINT CONSULTA_PK PRIMARY KEY (ID),
  CONSTRAINT FK_C_PACIENTE FOREIGN KEY (ID_PACIENTE) REFERENCES PACIENTE(ID_USUARIO),
  CONSTRAINT FK_C_MEDICO FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO),
  CONSTRAINT FK_C_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT CHK_CONSULTA CHECK(CUMPLIDA IN ('S', 'N'))
);

CREATE TABLE MEDICOS_PACIENTES(
  ID_MEDICO NUMBER NOT NULL,
  ID_PACIENTE NUMBER NOT NULL,
  CONSTRAINT MED_PAC_PK PRIMARY KEY (ID_MEDICO, ID_PACIENTE),
  CONSTRAINT FK_MP_MED FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO),
  CONSTRAINT FK_MP_PAC FOREIGN KEY (ID_PACIENTE) REFERENCES PACIENTE(ID_USUARIO)
);

COMMIT; 
