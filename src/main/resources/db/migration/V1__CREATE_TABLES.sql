CREATE TABLE gym (
	id_gimnasio INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nombre varchar(50),
	ciudad VARCHAR(50),
	direccion VARCHAR(50),
	codigo_postal INT,
	provincia VARCHAR(50),
	pais VARCHAR(50)
);

/*TABLA CLIENTES*/
CREATE TABLE cliente (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	id_gimnasio INT REFERENCES gym(id_gimnasio),
	dni VARCHAR(9) UNIQUE,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	fecha_nacimiento VARCHAR(10) NOT NULL,
	fecha_inscripcion VARCHAR(10) NOT NULL,
	email VARCHAR UNIQUE,
	calle VARCHAR,
	codigo_postal VARCHAR,
	ciudad VARCHAR,
	provincia VARCHAR
);

CREATE TABLE usuario (
	id_usuario INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	username VARCHAR NOT NULL UNIQUE,
	password VARCHAR NOT NULL,
	avatar varchar,
	fecha_creacion VARCHAR,
	ultima_mod_password VARCHAR,
	verificado BOOLEAN NOT NULL,
	rol VARCHAR
);

CREATE TABLE metodoPago (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_cliente INT references cliente(id),
    codigoConfirmacion INT,
    numeroTarjeta INT,
    IBAN VARCHAR,
    fecha_caducidad DATE,
    nombreTitular VARCHAR

);
CREATE TABLE factura(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    iva INT NOT NULL,
    importeUnitario INT NOT NULL

);
CREATE TABLE telefono(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_cliente INT REFERENCES cliente(id),
    numero varchar NOT NULL
);
CREATE TABLE  rutinaActiva(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR NOT NULL,
    descripcion VARCHAR NOT NULL,
    progreso INT NOT NULL

);
CREATE TABLE ejercicio(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_rutina INT REFERENCES rutinaActiva(id),
    nombre VARCHAR NOT NULL,
    dificultad INT NOT NULL
);

CREATE TABLE registrohorario (
	id_registrohorario INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	id_usuario INT REFERENCES usuario(id_usuario),
	horaEntrada VARCHAR,
	horaSalida VARCHAR,
	fecha VARCHAR
);

 
CREATE TABLE usuario_rol(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	usuario_id_usuario int references usuario(id_usuario),
	rol VARCHAR NOT NULL
);

CREATE TABLE verification_token(
    id_token INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    token varchar,
     expiryDate DATE

);

