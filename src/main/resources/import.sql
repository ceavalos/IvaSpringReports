INSERT INTO empresas (nombre, nit, nrc, representante, direccion, telefono, correo, usuario)  VALUES ('FUDEM',      '0715-12141975-101', '123-56', 'PEPE TORO',           'SAN SALVADOR', '22708201', 'eltoro@gmail.com',  'CAVALOS');
INSERT INTO empresas (nombre, nit, nrc, representante, direccion, telefono, correo, usuario)  VALUES ('GALAXIA',    '0715-12141975',     '123-56', 'LA MUJER MARAVILLA', 'SAN SALVADOR', '22708201', 'galxia@gmail.com',   'CAVALOS');
INSERT INTO empresas (nombre, nit, nrc, representante, direccion, telefono, correo, usuario)  VALUES ('INNOVACION', '0715-12141975',     '123-56', 'EL GIGANTE',         'SAN SALVADOR', '22708201', 'cipotex<@gmail.com', 'CAVALOS');

INSERT INTO periodos(descripcion, fechainicio, fechafinal) values('ENERO/2020', '2020-01-01', '2020-01-31');
INSERT INTO periodos(descripcion, fechainicio, fechafinal) values('FEBRERO/2020', '2020-02-01', '2020-02-29');
INSERT INTO periodos(descripcion, fechainicio, fechafinal) values('MARZO/2020', '2020-03-01', '2020-03-31');
INSERT INTO periodos(descripcion, fechainicio, fechafinal) values('ABRIL/2020', '2020-04-01', '2020-04-30');
INSERT INTO periodos(descripcion, fechainicio, fechafinal) values('MAYO/2020', '2020-05-01', '2020-05-31');


INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Enero', 1,1);
INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Enero', 2,1);
INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Febrero', 1,2);
INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Febrero', 2,2);
INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Mayo', 1,5);
INSERT INTO declaraciones(descripcion, empresa_id, periodo_id) values('Declaracion Mayo', 2,5);

 
INSERT INTO clientes (apellido, nombre, dui, email, nit, nrc) values ('Avalos', 'Carlos', '00867376-8', 'cipotex@hotmail.com', '0745-12041975-101-7' , '1234-56' );
INSERT INTO clientes (apellido, nombre, dui, email, nit, nrc) values ('Manzano', 'Fernando', '867376-9', 'lacosa@hotmail.com', '0765-12041975-101-7', '23432-44'  );
INSERT INTO clientes (apellido, nombre, dui, email, nit, nrc) values ('Segovia', 'Joaquin', '45867376-10', 'elotro@hotmail.com', '0315-12041975-101-7', '32467-99'  );

INSERT INTO compras (declaracion_id, fecha_Emision, numero_documento,  nrc, nit_dui, nombre_proveedor, comp_exc_interna, comp_exc_importa, comp_grv_interna, comp_grv_importa, fovial, iva_crefiscal, total_compras, compras_excluidas, percepcion) values (1,'2020-01-01' ,'73643', '12345-96','0654-123435-101-4','Inversiones Lemus S.A de C.V', 0 , 0, 10 ,0 ,0 ,0.13 ,10.13,0,0);
INSERT INTO compras (declaracion_id, fecha_Emision, numero_documento,  nrc, nit_dui, nombre_proveedor, comp_exc_interna, comp_exc_importa, comp_grv_interna, comp_grv_importa, fovial, iva_crefiscal, total_compras, compras_excluidas, percepcion) values (1,'2020-01-01' ,'73644', '12345-96','0654-123435-101-4','Inversiones Lemus S.A de C.V', 0 , 0, 10 ,0 ,0 ,0.13 ,10.13,0,0);
INSERT INTO compras (declaracion_id, fecha_Emision, numero_documento,  nrc, nit_dui, nombre_proveedor, comp_exc_interna, comp_exc_importa, comp_grv_interna, comp_grv_importa, fovial, iva_crefiscal, total_compras, compras_excluidas, percepcion) values (1,'2020-01-01' ,'73645', '12345-96','0654-123435-101-4','Inversiones Lemus S.A de C.V', 0 , 0, 10 ,0 ,0 ,0.13 ,10.13,0,0);
 
INSERT INTO ventas (FECHA_EMISION, IVA_RETENCION, NOMBRE, NRC, NUMERO_PRE_IMPRESO, V_INT_DEBITO_FISCAL, V_INT_EXCENTAS, V_INT_GRAVADAS, V_INT_NO_SUJETAS, TOTAL, DECLARACION_ID)  VALUES('2020-01-01', 10, 'CARLOS', '32423', '101010', 0, 0, 100, 0,  110, 1);
INSERT INTO ventas (FECHA_EMISION, IVA_RETENCION, NOMBRE, NRC, NUMERO_PRE_IMPRESO, V_INT_DEBITO_FISCAL, V_INT_EXCENTAS, V_INT_GRAVADAS, V_INT_NO_SUJETAS, TOTAL, DECLARACION_ID)  VALUES('2020-01-01', 10, 'CARLOS2', '32423', '3456', 0, 0, 100, 0, 110, 1);
INSERT INTO ventas (FECHA_EMISION, IVA_RETENCION, NOMBRE, NRC, NUMERO_PRE_IMPRESO, V_INT_DEBITO_FISCAL, V_INT_EXCENTAS, V_INT_GRAVADAS, V_INT_NO_SUJETAS, TOTAL, DECLARACION_ID)  VALUES('2020-01-01', 10, 'CARLOS3', '32423', '5662', 0, 0, 100, 0, 110, 1);
INSERT INTO ventas (FECHA_EMISION, IVA_RETENCION, NOMBRE, NRC, NUMERO_PRE_IMPRESO, V_INT_DEBITO_FISCAL, V_INT_EXCENTAS, V_INT_GRAVADAS, V_INT_NO_SUJETAS, TOTAL, DECLARACION_ID)  VALUES('2020-01-01', 10, 'CARLOS4', '32423', '124446', 0, 0, 100, 0, 110, 1);

INSERT INTO users(username, password, enabled)  VALUES ('admin', '$2a$10$s8wmTOcaMR.oBbBHdaLO4Ot8gbfaxobqVEwB8yzfkeTel5FNuj21C', 1);
INSERT INTO users(username, password, enabled)  VALUES ('cavalos', '$2a$10$SNTkRWGE/WNsk7OkIU/tv.6WcqKOYEft9zeiqA.ubwm/33EvniHiO', 1);

INSERT INTO authorities(user_id, authority)  VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities(user_id, authority)  VALUES (1, 'ROLE_USER');
INSERT INTO authorities(user_id, authority)  VALUES (2, 'ROLE_USER');

