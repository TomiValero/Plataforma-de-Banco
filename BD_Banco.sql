CREATE SCHEMA BancoBD;

USE BancoBD;

CREATE TABLE Provincias (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(25)
);

CREATE TABLE Localidades (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_provincia INT,
    descripcion VARCHAR(25),
    FOREIGN KEY (id_provincia) REFERENCES Provincias(id)
);

CREATE TABLE TiposDeUsuarios (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(25)
);

CREATE TABLE TiposDeCuentas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(25)
);

CREATE TABLE TiposDeMovimientos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(25)
);

CREATE TABLE Clientes (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dni VARCHAR(8),
    cuil VARCHAR(11),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    sexo VARCHAR(10),
    nacionalidad VARCHAR(20),
    fecha_nac DATETIME,
    direccion VARCHAR(100),
    id_localidad INT,
    id_provincia INT,
    correo VARCHAR(50),
    telefono VARCHAR(10),
    usuario VARCHAR(50),
    contraseña VARCHAR(50),
    baja BIT,
    id_tipo_usu INT,
    FOREIGN KEY (id_localidad) REFERENCES Localidades(id),
    FOREIGN KEY (id_provincia) REFERENCES Provincias(id),
    FOREIGN KEY (id_tipo_usu) REFERENCES TiposDeUsuarios(id)
);

CREATE TABLE Cuentas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_cliente INT,
    fecha_alta DATETIME,
    id_tipo_cuenta INT,
    num_cuenta INT,
    cbu INT,
    saldo DECIMAL,
    baja BIT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES TiposDeCuentas(id)
);

CREATE TABLE Prestamos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_cliente INT,
    id_cuenta INT,
    fecha_alta DATETIME,
    importe_pedido DECIMAL,
    cant_cuotas INT,
    importe_por_mes DECIMAL,
    estado VARCHAR(10),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id)
);

CREATE TABLE Cuotas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_prestamo INT,
    num_cuota INT,
    fecha_pago DATETIME,
    monto DECIMAL,
    pago BIT,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id)
);

CREATE TABLE Movimientos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_tipo_movimiento INT,
    monto DECIMAL,
    fecha DATETIME,
    id_cuenta INT,
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES TiposDeMovimientos(id)
);
-- CARGA DE DATOS

INSERT INTO Provincias (descripcion)
VALUES
('Buenos Aires'),
('Córdoba'),
('Santa Fe'),
('Mendoza'),
('Tucumán');

INSERT INTO Localidades (id_provincia, descripcion)
VALUES
(1, 'La Plata'),
(1, 'Mar del Plata'),
(2, 'Córdoba'),
(2, 'Villa Carlos Paz'),
(3, 'Rosario'),
(3, 'Santa Fe'),
(4, 'Mendoza'),
(4, 'San Rafael'),
(5, 'San Miguel de Tucumán'),
(5, 'Yerba Buena');

INSERT INTO TiposDeUsuarios (descripcion)
VALUES
('Cliente'), -- ID=1
('Admin'); -- ID=2

INSERT INTO TiposDeCuentas (descripcion)
VALUES
('Caja de ahorro'), -- ID=1
('Cuenta corriente'); -- ID=2

-- Insertar datos en TiposDeMovimientos
INSERT INTO TiposDeMovimientos (descripcion)
VALUES
('Alta de cuenta'),        -- Genera un movimiento positivo en la cuenta origen (ID=1)
('Alta de un préstamo'),   -- Genera un movimiento positivo en la cuenta origen (ID=2)
('Pago de préstamo'),      -- Genera un movimiento negativo en la cuenta origen (ID=3)
('Transferencia-Extracción'), -- Movimiento negativo en la cuenta origen (ID=4)
('Transferencia-Depósito');   -- Movimiento positivo en la cuenta destino (ID=5)


INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nac, direccion, id_localidad, id_provincia, correo, telefono, usuario, contraseña, baja, id_tipo_usu)
VALUES
('12345678', '20123456789', 'Juan', 'Pérez', 'Masculino', 'Argentino', '1985-01-15', 'Calle 123', 9, 5, 'juan.perez@mail.com', '3412345678', 'juan.perez', '1111', 0, 1),
('87654321', '20987654321', 'María', 'González', 'Femenino', 'Argentina', '1990-05-20', 'Av. Siempreviva 742', 10, 5, 'maria.gonzalez@mail.com', '3418765432', 'maria.gonzalez', '2222', 0, 2),
('11223344', '20112233445', 'Carlos', 'López', 'Masculino', 'Argentino', '1980-03-10', 'Calle Falsa 456', 1, 1, 'carlos.lopez@mail.com', '1122334455', 'carlos.lopez', '3333', 0, 1),
('44332211', '20443322110', 'Ana', 'Martínez', 'Femenino', 'Argentina', '1995-07-25', 'Ruta 3 KM 58', 2, 1, 'ana.martinez@mail.com', '1177889900', 'ana.martinez', '4444', 0, 2),
('56789012', '20567890123', 'Luis', 'Ramírez', 'Masculino', 'Argentino', '1975-12-05', 'Calle Mendoza 789', 5, 2, 'luis.ramirez@mail.com', '3434567890', 'luis.ramirez', '5555', 0, 1),
('21098765', '20210987654', 'Laura', 'Fernández', 'Femenino', 'Argentina', '1988-11-15', 'Av. Libertad 1234', 6, 2, 'laura.fernandez@mail.com', '3476543210', 'laura.fernandez', '6666', 0, 1),
('31415926', '20314159265', 'Pablo', 'Alvarez', 'Masculino', 'Argentino', '1982-09-30', 'Av. Córdoba 1000', 7, 3, 'pablo.alvarez@mail.com', '3412345679', 'pablo.alvarez', '7777', 0, 1),
('27182818', '20271828180', 'Sofía', 'Gómez', 'Femenino', 'Argentina', '1993-04-18', 'Calle Las Rosas 258', 8, 3, 'sofia.gomez@mail.com', '3416549873', 'sofia.gomez', '8888', 0, 1),
('31427182', '20314271822', 'Federico', 'Morales', 'Masculino', 'Argentino', '1970-08-01', 'Bv. Mitre 123', 4, 4, 'federico.morales@mail.com', '1165478392', 'federico.morales', '9999', 0, 1),
('10293847', '20102938475', 'Clara', 'Hernández', 'Femenino', 'Argentina', '1996-10-21', 'Ruta 9 KM 90', 5, 5, 'clara.hernandez@mail.com', '3432109876', 'clara.hernandez', '1010', 0, 1),
('19283746', '20192837465', 'Andrés', 'Torres', 'Masculino', 'Argentino', '1989-06-11', 'Calle 8 Norte 745', 3, 1, 'andres.torres@mail.com', '3421987654', 'andres.torres', '1111', 0, 1),
('91827364', '20918273645', 'Lucía', 'Reyes', 'Femenino', 'Argentina', '1991-02-08', 'Calle del Sol 900', 10, 5, 'lucia.reyes@mail.com', '3419098765', 'lucia.reyes', '1212', 0, 1);


INSERT INTO Cuentas (id_cliente, fecha_alta, id_tipo_cuenta, num_cuenta, cbu, saldo, baja)
VALUES
(1, '2024-01-05', 1, 1001, 10000001, 0, 0),
(1, '2024-02-15', 2, 1002, 10000002, 20000, 0),
(2, '2024-03-20', 1, 2001, 20000001, 0, 0),
(3, '2024-04-10', 2, 3001, 30000001, 0, 0),
(3, '2024-05-12', 1, 3002, 30000002, 20000, 0),
(3, '2024-06-18', 1, 3003, 30000003, 10000, 0),
(4, '2024-07-01', 1, 4001, 40000001, 0, 0),
(4, '2024-08-08', 2, 4002, 40000002, 20000, 0),
(5, '2024-09-14', 1, 5001, 50000001, 10000, 0),
(6, '2024-10-22', 2, 6001, 60000001, 10000, 0),
(7, '2024-11-03', 1, 7001, 70000001, 0, 0),
(7, '2024-12-10', 1, 7002, 70000002, 20000, 0),
(8, '2024-01-12', 1, 8001, 80000001, 15000, 0),
(9, '2024-02-24', 2, 9001, 90000001, 10000, 0),
(10, '2024-03-05', 1, 10001, 10000003, 0, 0),
(11, '2024-04-18', 1, 11001, 11000001, 0, 0),
(12, '2024-05-30', 2, 12001, 12000001, 5000, 0),
(12, '2024-06-15', 1, 12002, 12000002, 20000, 0),
(12, '2024-07-25', 2, 12003, 12000003, 5000, 0);

INSERT INTO Movimientos (id_tipo_movimiento, monto, fecha, id_cuenta)
VALUES
-- Alta de cuentas (ID=1), distribuidos en 2024
(1, 10000, '2024-01-05', 1),
(1, 10000, '2024-02-15', 2),
(1, 10000, '2024-03-20', 3),
(1, 10000, '2024-04-10', 4),
(1, 10000, '2024-05-12', 5),
(1, 10000, '2024-06-18', 6),
(1, 10000, '2024-07-01', 7),
(1, 10000, '2024-08-08', 8),
(1, 10000, '2024-09-14', 9),
(1, 10000, '2024-10-22', 10),
(1, 10000, '2024-11-03', 11),
(1, 10000, '2024-12-10', 12),
(1, 10000, '2024-01-12', 13),
(1, 10000, '2024-02-24', 14),
(1, 10000, '2024-03-05', 15),
(1, 10000, '2024-04-18', 16),
(1, 10000, '2024-05-30', 17),
(1, 10000, '2024-06-15', 18),
(1, 10000, '2024-07-25', 19),
-- Transferencias entre cuentas (Extracción y Depósito)
(4, -5000, '2024-02-01', 1), -- Extracción desde cuenta 1
(5, 5000, '2024-02-01', 2), -- Depósito en cuenta 2
(4, -5000, '2024-03-15', 3), -- Extracción desde cuenta 3
(5, 5000, '2024-03-15', 5), -- Depósito en cuenta 5
(4, -5000, '2024-06-20', 7), -- Extracción desde cuenta 7
(5, 5000, '2024-06-20', 8), -- Depósito en cuenta 8
(4, -5000, '2024-09-10', 10), -- Extracción desde cuenta 10
(5, 5000, '2024-09-10', 11), -- Depósito en cuenta 11
(4, -5000, '2024-12-15', 12), -- Extracción desde cuenta 12
(5, 5000, '2024-12-15', 13),
-- Nuevas transferencias para todos los clientes con múltiples cuentas
(4, -4000, '2024-01-20', 2), -- Extracción desde cuenta 2
(5, 4000, '2024-01-20', 1), -- Depósito en cuenta 1
(4, -7000, '2024-04-01', 5), -- Extracción desde cuenta 5
(5, 7000, '2024-04-01', 4), -- Depósito en cuenta 4
(4, -3000, '2024-06-25', 8), -- Extracción desde cuenta 8
(5, 3000, '2024-06-25', 7), -- Depósito en cuenta 7
(4, -2000, '2024-10-05', 11), -- Extracción desde cuenta 11
(5, 2000, '2024-10-05', 10), -- Depósito en cuenta 10
(4, -8000, '2024-12-20', 13), -- Extracción desde cuenta 13
(5, 8000, '2024-12-20', 12);




