-- Insertar 2 IPS
insert into A_IPS (ID, LOCALIZACION, CAPACIDAD) values (1, '4639 Corry Court', 42);
insert into A_IPS (ID, LOCALIZACION, CAPACIDAD) values (2, '9921 Oak Street', 92);

-- Insertar 2 Secretarias
insert into A_SECRETARIA (ID, NOMBRE, CORREO) values (1, 'Mónica Sánchez', 'm.sanchez@hotmail.com');
insert into A_SECRETARIA (ID, NOMBRE, CORREO) values (2, 'Rafael Guerreiro', 'rafguerro@gmail.com');

-- Insertar secretarias en A_IPS_SECRETARIAS
insert into A_IPS_SECRETARIAS (ID_IPS, ID_SECRETARIA) values (1, 1);
insert into A_IPS_SECRETARIAS (ID_IPS, ID_SECRETARIA) values (2, 2);

-- Insertar servicios de salud
-- TERAPIA
insert into A_SERVICIO (ID, CAPACIDAD, NOMBRE) values (1, 47, 'TERAPIA');
insert into A_TERAPIA (ID_SERVICIO, NUM_SESIONES, TIPO) values (1, 3, 'Física');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (1, 'Norbie Douberday', 'ndouberday0@mashable.com', 'RWD Info Pak');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (2, 'Rab Alwell', 'ralwell1@pagesperso-orange.fr', 'Cisco IOS');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (3, 'Edna De Pero', 'ede2@tiny.cc', 'Psychological Testing');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (4, 'Luz Locker', 'llocker3@seattletimes.com', 'DNSSEC');
insert into A_HORARIO (ID, H_INICIO, H_FIN) values (1, '20-11-24', '20-11-25');
insert into A_SERVICIO_HORARIO(ID_SERVICIO, ID_HORARIO) values (1, 1);

-- EXAMEN DIAGNÓSTICO
insert into A_SERVICIO (ID, CAPACIDAD, NOMBRE) values (2, 22, 'EXAMEN DIAGNOSTICO');
insert into A_EXAMEN_DIAGNOSTICO (ID_SERVICIO, RESULTADOS) values (2, 'El paciente está en mala condición');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (5, 'Maxie Immings', 'mimmings4@timesonline.co.uk', 'OTA');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (6, 'Celinka L''Episcopi', 'clepiscopi5@usda.gov', 'EBSD');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (7, 'Devland Turpie', 'dturpie6@aboutads.info', 'NMR');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (8, 'Jazmin Auchterlony', 'jauchterlony7@quantcast.com', 'Dbx');
insert into A_HORARIO (ID, H_INICIO, H_FIN) values (2, '21-11-24', '22-11-24');
insert into A_SERVICIO_HORARIO(ID_SERVICIO, ID_HORARIO) values (2, 2);

-- HOSPITALIZACIÓN
insert into A_SERVICIO (ID, CAPACIDAD, NOMBRE) values (3, 10, 'HOSPITALIZACION');
insert into A_ORDEN values (1, 'Se solicitan terapias');
insert into A_CONSULTA values (1, 'N', 1, null);
insert into A_HOSPITALIZACION (ID_SERVICIO, ID_VISITAS) values (3, 1);
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (9, 'Harry Lie', 'hlie8@intel.com', 'Zoo');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (10, 'Cynde Knowles', 'cknowles9@hexun.com', 'Slide Preparation');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (11, 'Eulalie Sendall', 'esendalla@mapy.cz', 'OTS');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (12, 'Elsinore Kelf', 'ekelfb@ovh.net', 'Warranty');
insert into A_HORARIO (ID, H_INICIO, H_FIN) values (3, '10/10/11', '11/10/11');
insert into A_SERVICIO_HORARIO(ID_SERVICIO, ID_HORARIO) values (3, 3);

-- PROCEDIMIENTO
insert into A_SERVICIO (ID, CAPACIDAD, NOMBRE) values (4, 5, 'PROCEDIMIENTO MEDICO');
insert into A_PROCEDIMIENTOS (ID_SERVICIO, TIPO) values (4, 'Cirugía');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (13, 'Renado Conaghy', 'rconaghyc@posterous.com', 'Ozone');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (14, 'Siana Merriott', 'smerriottd@netvibes.com', 'JMS');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (15, 'Valdemar Heaseman', 'vheasemane@ovh.net', 'PVS');
insert into A_MEDICO (ID, NOMBRE, CORREO, ESPECIALIDAD) values (16, 'Odette Cornelius', 'ocorneliusf@princeton.edu', 'Zachman');
insert into A_HORARIO (ID, H_INICIO, H_FIN) values (4, '12:57 AM', '11:16 PM');
insert into A_SERVICIO_HORARIO(ID_SERVICIO, ID_HORARIO) values (4, 4);

-- Insertar pacientes
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (1, 'Ulla de Villier', 'ude0@yandex.ru', '08-03-01', 'Bueno', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (2, 'Micky Oxbe', 'moxbe1@slate.com', '19-01-09', 'Medio', 'TARJETA DE IDENTIDAD');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (3, 'Baldwin Dring', 'bdring2@quantcast.com', '20-05-07', 'Grave', 'TARJETA DE IDENTIDAD');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (4, 'Ellette Killimister', 'ekillimister3@flavors.me', '19-04-22', 'Bueno', 'PASAPORTE');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (5, 'Kitti Worge', 'kworge4@istockphoto.com', '19-01-26', 'Bueno', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (6, 'Marylinda Kencott', 'mkencott5@archive.org', '19-04-27', 'Bueno', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (7, 'Herman Cozzi', 'hcozzi6@youtu.be', '19-09-24', 'Medio', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (8, 'Sheeree Daugherty', 'sdaugherty7@typepad.com', '04-01-07', 'Medio', 'TARJETA DE IDENTIDAD');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (9, 'Annecorinne Choldcroft', 'acholdcroft8@goo.gl', '19-05-16', 'Grave', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (10, 'Shaylah Clamo', 'sclamo9@parallels.com', '19-12-03', 'Malo', 'PASAPORTE');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (11, 'Dwain Everwin', 'deverwina@icq.com', '19-12-19', 'Bueno', 'TARJETA DE IDENTIDAD');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (12, 'Leona McVane', 'lmcvaneb@ucoz.ru', '19-03-02', 'Bueno', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (13, 'Farleigh Tschursch', 'ftschurschc@google.ru', '19-03-31', 'Bueno', 'PASAPORTE');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (14, 'Mart Toopin', 'mtoopind@stumbleupon.com', '10-10-13', 'Medio', 'CEDULA');
insert into A_PACIENTE (ID, NOMBRE, CORREO, F_NACIMIENTO, ESTADO, TIPO_DOC) values (15, 'Carly Melody', 'cmelodye@bing.com', '20-04-23', 'Bueno', 'TARJETA DE IDENTIDAD');

--Insertar servicios IPS
insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (1,1);
insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (1,2);
insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (1,3);

insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (2,1);
insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (2,2);
insert into A_IPS_SERVICIOS (ID_IPS, ID_SERVICIO) values (2,3);

COMMIT;