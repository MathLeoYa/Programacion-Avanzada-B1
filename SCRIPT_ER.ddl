-- Generado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   en:        2024-05-13 23:20:04 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE bodeguero (
    idbodeguero     INTEGER NOT NULL,
    local           VARCHAR2(50),
    empleado_idempl INTEGER NOT NULL
);

ALTER TABLE bodeguero ADD CONSTRAINT bodeguero_pk PRIMARY KEY ( idbodeguero );

CREATE TABLE cliente (
    idcli             INTEGER NOT NULL,
    celular           VARCHAR2(15) NOT NULL,
    persona_idpersona INTEGER NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( idcli );

CREATE TABLE cliente_direccion (
    cliente_idcli         INTEGER NOT NULL,
    direccion_iddireccion INTEGER NOT NULL
);

CREATE TABLE direccion (
    iddireccion INTEGER NOT NULL,
    calle1      VARCHAR2(50) NOT NULL,
    calle2      VARCHAR2(50) NOT NULL,
    codigo      VARCHAR2(10) NOT NULL,
    referencia  VARCHAR2(100) NOT NULL,
    actual      INTEGER NOT NULL
);

ALTER TABLE direccion ADD CONSTRAINT direccion_pk PRIMARY KEY ( iddireccion );

CREATE TABLE empleado (
    idempl            INTEGER NOT NULL,
    ciudad            VARCHAR2(50) NOT NULL,
    persona_idpersona INTEGER NOT NULL
);

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( idempl );

CREATE TABLE entrega (
    identrega       INTEGER NOT NULL,
    codigo          VARCHAR2(10) NOT NULL,
    fecha           DATE NOT NULL,
    observacion     VARCHAR2(250) NOT NULL,
    empleado_idempl INTEGER NOT NULL,
    cliente_idcli   INTEGER NOT NULL,
    paquete_idpaq   INTEGER NOT NULL
);

ALTER TABLE entrega ADD CONSTRAINT entrega_pk PRIMARY KEY ( identrega );

CREATE TABLE estado (
    tipo        INTEGER NOT NULL,
    estado      VARCHAR2(20) NOT NULL,
    fecha       DATE NOT NULL,
    observacion VARCHAR2(250) NOT NULL
);

ALTER TABLE estado ADD CONSTRAINT estado_pk PRIMARY KEY ( tipo );

CREATE TABLE paquete (
    idpaq       INTEGER NOT NULL,
    codigo      VARCHAR2(10) NOT NULL,
    descripcion VARCHAR2(100) NOT NULL,
    peso        NUMBER NOT NULL,
    largo       NUMBER NOT NULL,
    ancho       NUMBER NOT NULL,
    estado_tipo INTEGER NOT NULL
);

ALTER TABLE paquete ADD CONSTRAINT paquete_pk PRIMARY KEY ( idpaq );

CREATE TABLE persona (
    cedula   INTEGER NOT NULL,
    nombre   VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    email    VARCHAR2(100) NOT NULL
);

ALTER TABLE persona ADD CONSTRAINT persona_pk PRIMARY KEY ( cedula );

CREATE TABLE repartidor (
    idrepar         INTEGER NOT NULL,
    zona            INTEGER NOT NULL,
    empleado_idempl INTEGER NOT NULL
);

ALTER TABLE repartidor ADD CONSTRAINT repartidor_pk PRIMARY KEY ( idrepar );

ALTER TABLE bodeguero
    ADD CONSTRAINT bodeguero_empleado_fk FOREIGN KEY ( empleado_idempl )
        REFERENCES empleado ( idempl );

ALTER TABLE cliente_direccion
    ADD CONSTRAINT cliente_direccion_cliente_fk FOREIGN KEY ( cliente_idcli )
        REFERENCES cliente ( idcli );

ALTER TABLE cliente_direccion
    ADD CONSTRAINT cliente_direccion_direccion_fk FOREIGN KEY ( direccion_iddireccion )
        REFERENCES direccion ( iddireccion );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_persona_fk FOREIGN KEY ( persona_idpersona )
        REFERENCES persona ( cedula );

ALTER TABLE empleado
    ADD CONSTRAINT empleado_persona_fk FOREIGN KEY ( persona_idpersona )
        REFERENCES persona ( cedula );

ALTER TABLE entrega
    ADD CONSTRAINT entrega_cliente_fk FOREIGN KEY ( cliente_idcli )
        REFERENCES cliente ( idcli );

ALTER TABLE entrega
    ADD CONSTRAINT entrega_empleado_fk FOREIGN KEY ( empleado_idempl )
        REFERENCES empleado ( idempl );

ALTER TABLE entrega
    ADD CONSTRAINT entrega_paquete_fk FOREIGN KEY ( paquete_idpaq )
        REFERENCES paquete ( idpaq );

ALTER TABLE paquete
    ADD CONSTRAINT paquete_estado_fk FOREIGN KEY ( estado_tipo )
        REFERENCES estado ( tipo );

ALTER TABLE repartidor
    ADD CONSTRAINT repartidor_empleado_fk FOREIGN KEY ( empleado_idempl )
        REFERENCES empleado ( idempl );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             19
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
