CREATE SEQUENCE EPSAndes_sequence; 


--MODIFICADO
CREATE TABLE EPS(
  ID        NUMBER,
  NOMBRE    VARCHAR(255) NOT NULL,
  CONSTRAINT EPS_PK PRIMARY KEY (ID)
  );  

--NUEVO
CREATE TABLE CAMPANA(
  ID NUMBER,
  CANTIDAD_INSCRITOS NUMBER NOT NULL,
  FECHA_INICIO DATE NOT NULL,
  FECHA_FIN DATE NOT NULL,
  ID_EPS NUMBER NOT NULL, 
  CONSTRAINT CAMPANA_PK PRIMARY KEY (ID), 
  CONSTRAINT FK_C_EPS FOREIGN KEY (ID_EPS) REFERENCES EPS(ID)
);



CREATE TABLE IPS(
  ID        NUMBER,
  ID_EPS NUMBER NOT NULL,
  NOMBRE    VARCHAR(255) NOT NULL,
  CAPACIDAD NUMBER NOT NULL,
  LOCALIZACION VARCHAR(255),
  CONSTRAINT IPS_PK PRIMARY KEY (ID),
  CONSTRAINT FK_I_EPS FOREIGN KEY (ID_EPS) REFERENCES EPS(ID),
  CONSTRAINT CHK_IPS CHECK(CAPACIDAD > 0)
);

--MODIFICADO
CREATE TABLE HORARIO(
  ID        NUMBER,
  --SE BORRAN ID_IPS Y ID_CAMPANA
  HORA_INICIO TIMESTAMP NOT NULL, 
  HORA_FIN TIMESTAMP NOT NULL,
  DIAS VARCHAR(50) NOT NULL, --SE AGREGA DIAS
  CONSTRAINT HORARIO_PK PRIMARY KEY (ID),
  CONSTRAINT CHK_HORARIO CHECK (HORA_INICIO < HORA_FIN)
);

--MODIFICADA
CREATE TABLE ORDEN (
  ID NUMBER,
  DESCRIPCION VARCHAR(255),
  FECHA DATE NOT NULL, --SE AGREGA 
  CONSTRAINT ORDEN_PK PRIMARY KEY (ID)
);

--MODIFICADO
CREATE TABLE SERVICIO(
  ID NUMBER,
  --BORRADO ID ORDEN, ID HORARIO, CAPACIDAD, RESERVADO, INHABILITADO, INICIO_INHABILITACION, FIN_INHABILITACION
  NOMBRE VARCHAR(255),
  CONSTRAINT SERVICIO_PK PRIMARY KEY (ID)
  --BORRADO CONSTRAINT ID_ORDEN, HORARIO
  --BORRADO CONSTRAINT CHECK RESERVADO, INHABILITADO
);

--NUEVA TABLA
CREATE TABLE ORDENES_SERVICIOS(
  ID_ORDEN NUMBER,
  ID_SERVICIO NUMBER,
  CONSTRAINT ORDENES_SERVICIOS_PK PRIMARY KEY (ID_ORDEN, ID_SERVICIO),
  CONSTRAINT FK_OS_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT FK_OS_SERVICIO FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIO(ID),
  CONSTRAINT FK_OS_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID)--
);

CREATE TABLE MEDICAMENTO (
  ID NUMBER,
  NOMBRE VARCHAR(255),
  CONSTRAINT MEDICAMENTO_PK PRIMARY KEY (ID)
);

--MODIFICADO
CREATE TABLE USUARIO(
  ID        NUMBER,
  ID_EPS NUMBER NOT NULL, 
  ID_IPS_RECEPCIONISTA  NUMBER UNIQUE,
  ID_CAMPANA_USUARIO NUMBER UNIQUE, --MODIFICADO
  FECHA_NACIMIENTO DATE,
  NOMBRE    VARCHAR(30),
  CORREO VARCHAR(255),
  TIPO_DOCUMENTO VARCHAR(255),
  TIPO_USUARIO VARCHAR(255),
  CONSTRAINT USUARIO_PK PRIMARY KEY (ID),
  CONSTRAINT FK_U_EPS FOREIGN KEY (ID_EPS) REFERENCES EPS(ID), 
  CONSTRAINT FK_U_IPS FOREIGN KEY (ID_IPS_RECEPCIONISTA) REFERENCES IPS(ID),
  CONSTRAINT FK_U_CAMPANA FOREIGN KEY (ID_CAMPANA_USUARIO) REFERENCES CAMPANA(ID),
  CONSTRAINT CHK_TIPO_DOCUMENTO CHECK (TIPO_DOCUMENTO IN ('CEDULA', 'PASAPORTE', 'TARJETA DE IDENTIDAD')),
  CONSTRAINT CHK_TIPO_USUARIO CHECK(TIPO_USUARIO IN ('ADMINISTRADOR', 'RECEPCIONISTA', 'GERENTE', 'MEDICO', 'PACIENTE', 'ORGANIZADOR CAMPANA'))--
);

--MODIFICADO
CREATE TABLE MEDICO(
  ID_USUARIO NUMBER,
  NUMREG NUMBER NOT NULL UNIQUE,
  --BORRADO ESPECIALIDAD
  CONSTRAINT MEDICO_PK PRIMARY KEY (ID_USUARIO),
  CONSTRAINT FK_M_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

--MODIFICADO
CREATE TABLE IPS_MEDICOS(
  ID_IPS NUMBER,
  ID_MEDICO NUMBER,
  CONSTRAINT IM_M PRIMARY KEY (ID_IPS, ID_MEDICO),
  CONSTRAINT FK_IM_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID),
  CONSTRAINT FK_IM_MEDICO FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO)
);

--MODIFICADO
CREATE TABLE IPS_SERVICIOS(
  ID_IPS NUMBER,
  ID_SERVICIO NUMBER,
  CAPACIDAD NUMBER, --NUEVA
  INHABILITADO VARCHAR(1), 
  RESERVADO VARCHAR(1), 
  INICIO_INHABILITACION DATE, 
  FIN_INHABILITACION DATE,
  CONSTRAINT IS_PK PRIMARY KEY (ID_IPS, ID_SERVICIO),
  CONSTRAINT FK_IS_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID),
  CONSTRAINT FK_IS_SERVICIO FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIO(ID),
  CONSTRAINT CHK_IPS_SER_CAP CHECK (CAPACIDAD > 0),
  CONSTRAINT CHK_IPS_SER_INH_RES CHECK (RESERVADO IN('S', 'N') AND INHABILITADO IN('S', 'N'))
);


--MODIFICADO
CREATE TABLE ORDENES_MEDICAMENTOS (
  ID_ORDEN NUMBER,
  ID_MEDICAMENTO NUMBER,
  CONSTRAINT ORDMED_PK PRIMARY KEY (ID_ORDEN, ID_MEDICAMENTO),
  CONSTRAINT FK_OM_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT FK_OM_MEDICAMENTO FOREIGN KEY (ID_MEDICAMENTO) REFERENCES MEDICAMENTO(ID)
);

--MODIFICADO
CREATE TABLE PACIENTE(
  ID_USUARIO NUMBER,
  ID_EPS NUMBER NOT NULL,
  ID_CAMPANA,
  ESTADO_SALUD VARCHAR(255),
  CONSTRAINT PACIENTE_PK PRIMARY KEY (ID_USUARIO),
  CONSTRAINT FK_P_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),
  CONSTRAINT FK_P_CAMPANA FOREIGN KEY (ID_CAMPANA) REFERENCES CAMPANA(ID),--
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

--MODIFICADO
CREATE TABLE CONSULTA(
  ID NUMBER,
  ID_PACIENTE NUMBER NOT NULL,
  ID_MEDICO NUMBER NOT NULL,
  ID_ORDEN NUMBER UNIQUE,
  CUMPLIDA VARCHAR(1),
  CAPACIDAD NUMBER NOT NULL,
  FECHA DATE NOT NULL,--
  CONSTRAINT CONSULTA_PK PRIMARY KEY (ID),
  CONSTRAINT FK_C_PACIENTE FOREIGN KEY (ID_PACIENTE) REFERENCES PACIENTE(ID_USUARIO),
  CONSTRAINT FK_C_MEDICO FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO),
  CONSTRAINT FK_C_ORDEN FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN(ID),
  CONSTRAINT CHK_CONSULTA CHECK(CUMPLIDA IN ('S', 'N'))
);

--NUEVA TABLA
CREATE TABLE MEDICOS_PACIENTES(
  ID_MEDICO NUMBER NOT NULL,
  ID_PACIENTE NUMBER NOT NULL,
  CONSTRAINT MED_PAC_PK PRIMARY KEY (ID_MEDICO, ID_PACIENTE),
  CONSTRAINT FK_MP_MED FOREIGN KEY (ID_MEDICO) REFERENCES MEDICO(ID_USUARIO),
  CONSTRAINT FK_MP_PAC FOREIGN KEY (ID_PACIENTE) REFERENCES PACIENTE(ID_USUARIO)
);

--NUEVA TABLA
CREATE TABLE IPS_HORARIO(
  ID_IPS NUMBER, 
  ID_HORARIO NUMBER, 
  CONSTRAINT IH_PK PRIMARY KEY (ID_IPS, ID_HORARIO),
  CONSTRAINT FK_IH_IPS FOREIGN KEY (ID_IPS) REFERENCES IPS(ID), 
  CONSTRAINT FK_IH_HOR FOREIGN KEY (ID_HORARIO) REFERENCES HORARIO(ID)
);

--NUEVA TABLA
CREATE TABLE SERVICIO_HORARIO(
ID_SERVICIO NUMBER, 
ID_HORARIO NUMBER, 
CONSTRAINT SH_PK PRIMARY KEY (ID_SERVICIO, ID_HORARIO), 
CONSTRAINT FK_SH_SERV FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIO(ID), 
CONSTRAINT FK_SH_HOR FOREIGN KEY (ID_HORARIO) REFERENCES HORARIO(ID)
);

--NUEVA TABLA
CREATE TABLE CAMPANA_HORARIO(
  ID_CAMPANA NUMBER,
  ID_HORARIO NUMBER, 
  CONSTRAINT CH_PK PRIMARY KEY (ID_CAMPANA, ID_HORARIO),
  CONSTRAINT FK_CH_CAMP FOREIGN KEY (ID_CAMPANA) REFERENCES CAMPANA(ID), 
  CONSTRAINT FK_CH_HOR FOREIGN KEY (ID_HORARIO) REFERENCES HORARIO(ID)
); 

COMMIT; 

