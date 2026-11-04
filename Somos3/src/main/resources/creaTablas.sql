-- CREACION DE USUARIOS

DROP USER IF EXISTS 'usuario_somos3'@'localhost';
CREATE USER 'usuario_somos3'@'localhost'
IDENTIFIED BY 'usuario_contra3';

DROP USER IF EXISTS 'admin_somos3'@'localhost';
CREATE USER 'admin_somos3'@'localhost'
IDENTIFIED BY 'admin_contra3';

-- CREACION DE LA BD

DROP DATABASE IF EXISTS somos3_bd;
CREATE DATABASE somos3_bd;
GRANT ALL PRIVILEGES ON somos3_bd.*
TO 'admin_somos3'@'localhost';
FLUSH PRIVILEGES;
USE somos3_bd;

-- TABLA USUARIO
CREATE TABLE USUARIO(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(25) NOT NULL DEFAULT 'CLIENTE',
    estado BOOLEAN DEFAULT TRUE
);

-- TABLA CATEGORIA
CREATE TABLE CATEGORIA(
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);

-- TABLA PRODUCTO
CREATE TABLE PRODUCTO(
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    precio DECIMAL(10,2) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
);

-- TABLA PEDIDO
CREATE TABLE PEDIDO(
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    estado VARCHAR(25) DEFAULT 'Pendiente',
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

-- TABLA TALLA
CREATE TABLE TALLA(
    id_talla INT AUTO_INCREMENT PRIMARY KEY,
    nombre_talla VARCHAR(10) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);

-- TABLA COLOR
CREATE TABLE COLOR(
    id_color INT AUTO_INCREMENT PRIMARY KEY,
    nombre_color VARCHAR(50) NOT NULL,
    codigo_hex CHAR(7),
    estado BOOLEAN DEFAULT TRUE
);

-- TABLA INVENTARIO
CREATE TABLE INVENTARIO(
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    id_talla INT NOT NULL,
    id_color INT NOT NULL,
    cantidad_disponible INT NOT NULL DEFAULT 0,
    stock_minimo INT DEFAULT 0,
    FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto),
    FOREIGN KEY (id_talla) REFERENCES TALLA(id_talla),
    FOREIGN KEY (id_color) REFERENCES COLOR(id_color),
    UNIQUE (id_producto, id_talla, id_color)
);

-- TABLA IMAGENPRODUCTO
CREATE TABLE IMAGENPRODUCTO(
    id_imagen INT AUTO_INCREMENT PRIMARY KEY,
    ruta_imagen VARCHAR(255) NOT NULL,
    imagen_principal BOOLEAN DEFAULT FALSE,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto)
);

-- TABLA DIRECCION
CREATE TABLE DIRECCION(
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    provincia VARCHAR(50) NOT NULL,
    canton VARCHAR(50) NOT NULL,
    direccion_exacta VARCHAR(255) NOT NULL,
    codigo_postal VARCHAR(15),
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

-- TABLA PAGO
CREATE TABLE PAGO(
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    metodo_pago VARCHAR(50) NOT NULL,
    fecha_pago DATETIME,
    monto DECIMAL(10,2) NOT NULL,
    estado_pago VARCHAR(25) DEFAULT 'Pendiente',
    referencia_transaccion VARCHAR(100),
    id_pedido INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido)
);

-- TABLA ENVIO
CREATE TABLE ENVIO(
    id_envio INT AUTO_INCREMENT PRIMARY KEY,
    fecha_envio DATETIME,
    fecha_entrega DATETIME,
    empresa_transporte VARCHAR(100),
    numero_seguimiento VARCHAR(100),
    estado_envio VARCHAR(25) DEFAULT 'Pendiente',
    id_pedido INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
    id_direccion INT NOT NULL,
    FOREIGN KEY (id_direccion) REFERENCES DIRECCION(id_direccion)
);

-- TABLA CARRITO
CREATE TABLE CARRITO(
    id_carrito INT AUTO_INCREMENT PRIMARY KEY,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(25) DEFAULT 'Activo',
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

-- TABLA CARRITODETALLE
CREATE TABLE CARRITODETALLE(
    id_detalle_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_carrito INT NOT NULL,
    id_inventario INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_carrito) REFERENCES CARRITO(id_carrito),
    FOREIGN KEY (id_inventario) REFERENCES INVENTARIO(id_inventario),
    UNIQUE (id_carrito, id_inventario)
);

-- TABLA PEDIDODETALLE
CREATE TABLE PEDIDODETALLE(
    id_detalle_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_inventario INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
    FOREIGN KEY (id_inventario) REFERENCES INVENTARIO(id_inventario)
);