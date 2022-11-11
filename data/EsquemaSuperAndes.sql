CREATE SEQUENCE SuperAndes_sequence;

-- * Creacion tabla SUPERMERCADO.
CREATE TABLE SUPERMERCADO(
    id NUMBER,
    nit NUMBER NOT NULL UNIQUE,
    nombre VARCHAR2(255 BYTE) NOT NULL,
	CONSTRAINT PK_id_supermercado PRIMARY KEY (id)
);
--------------------------------------------------------------------------------
-- * Creacion tabla SUCURSAL.
CREATE TABLE SUCURSAL(
    id NUMBER,
    nombre VARCHAR2(255 BYTE) NOT NULL UNIQUE,
    pais VARCHAR2(255 BYTE) NOT NULL,
    ciudad VARCHAR2(255 BYTE) NOT NULL,
    direccion VARCHAR2(255 BYTE) NOT NULL,
    id_Supermercado NUMBER NOT NULL,
	CONSTRAINT PK_id_sucursal PRIMARY KEY (id),
    CONSTRAINT FK_sucursal_id_Supermercado FOREIGN KEY(id_Supermercado) REFERENCES SUPERMERCADO(id)
);
--------------------------------------------------------------------------------
-- * Creacion tabla TIPO_PRODUCTO.
CREATE TABLE TIPO_PRODUCTO(
    id NUMBER,
    nombre VARCHAR2(255 BYTE) NOT NULL UNIQUE,
    tipoAlmacen VARCHAR2(255 BYTE) NOT NULL,
    categoria VARCHAR2(255 BYTE) NOT NULL,
    subCategoria VARCHAR2(255 BYTE) NOT NULL,
	CONSTRAINT PK_id_tipoProducto PRIMARY KEY (id),
    CONSTRAINT CHECK_tipoProducto_tipoAlmacen CHECK(tipoAlmacen IN ('Refrigerado', 'Congelado', 'Normal'))
);
--------------------------------------------------------------------------------
-- Creacion tabla BODEGA
CREATE TABLE BODEGA(
    id NUMBER,
    volMax NUMBER NOT NULL,
    pesoMax NUMBER NOT NULL,
    tipoAlmacen VARCHAR2(255 BYTE) NOT NULL,
    id_Sucursal NUMBER NOT NULL,
    id_TipoProducto NUMBER NOT NULL,
	CONSTRAINT PK_id_bodega PRIMARY KEY (id),
    CONSTRAINT FK_bodega_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT FK_bodega_id_TipoProducto FOREIGN KEY(id_TipoProducto) REFERENCES TIPO_PRODUCTO(id),
    CONSTRAINT CHECK_bodega_tipoAlmacen CHECK(tipoAlmacen IN ('Refrigerado', 'Congelado', 'Normal'))
);
--------------------------------------------------------------------------------
-- Creacion tabla ESTANTE
CREATE TABLE ESTANTE(
    id NUMBER,
    volMax NUMBER NOT NULL,
    pesoMax NUMBER NOT NULL,
    tipoAlmacen VARCHAR2(255 BYTE) NOT NULL,
    nAbastecimiento NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
    id_TipoProducto NUMBER NOT NULL,
	CONSTRAINT PK_id_estante PRIMARY KEY (id),
    CONSTRAINT FK_estante_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT FK_estante_id_TipoProducto FOREIGN KEY(id_TipoProducto) REFERENCES TIPO_PRODUCTO(id),
    CONSTRAINT CHECK_estante_tipoAlmacen CHECK(tipoAlmacen IN ('Refrigerado', 'Congelado', 'Normal'))
);
--------------------------------------------------------------------------------
-- Creacion tabla PROVEEDOR
CREATE TABLE PROVEEDOR(
    id NUMBER,
    nit NUMBER NOT NULL UNIQUE,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    calificacion NUMBER NOT NULL,
	CONSTRAINT PK_id_proveedor PRIMARY KEY (id)
);
--------------------------------------------------------------------------------
-- Creacion tabla PRODUCTO
CREATE TABLE PRODUCTO(
    idLote NUMBER,
    codigoBarra VARCHAR2(255 BYTE) NOT NULL UNIQUE,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    marca VARCHAR2(255 BYTE) NOT NULL,
    pVenta NUMBER NOT NULL,
    presentacion VARCHAR2(255 BYTE) NOT NULL,
    pUnidadMedida NUMBER NOT NULL,
    cantPPT NUMBER NOT NULL,
    unidadMedida VARCHAR2(255 BYTE) NOT NULL,
    espEmpPeso NUMBER NOT NULL,
    espEmpVol NUMBER NOT NULL,
    esPerecedero VARCHAR2(255 BYTE) NOT NULL,
    fVencimiento DATE,
    nReorden NUMBER NOT NULL,
    stockBodega NUMBER NOT NULL,
    stockEstante NUMBER NOT NULL,
    stockTotal NUMBER NOT NULL,
    id_TipoProducto NUMBER NOT NULL,
	CONSTRAINT PK_id_producto PRIMARY KEY (idLote),
    CONSTRAINT FK_producto_id_TipoProducto FOREIGN KEY(id_TipoProducto) REFERENCES TIPO_PRODUCTO(id),
    CONSTRAINT CHECK_producto_esPerecedero CHECK(esPerecedero IN ('true', 'false'))
);
--------------------------------------------------------------------------------
-- Creacion tabla ORDEN_PEDIDO
CREATE TABLE ORDEN_PEDIDO(
    id NUMBER,
    fCompra DATE NOT NULL,
    vTotal NUMBER NOT NULL,
    id_Proveedor NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
	CONSTRAINT PK_id_ordenPedido PRIMARY KEY (id),
    CONSTRAINT FK_ordenPedido_id_Proveedor FOREIGN KEY(id_Proveedor) REFERENCES PROVEEDOR(id),
    CONSTRAINT FK_ordenPedido_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id)
);
--------------------------------------------------------------------------------
-- Creacion tabla ORDEN_PEDIDO_PRODUCTO
CREATE TABLE ORDEN_PEDIDO_PRODUCTO(
    id_OrdenPedido NUMBER,
    id_Producto NUMBER,
    cantCompra NUMBER NOT NULL,
    pCompra NUMBER NOT NULL,
	CONSTRAINT PK_id_ordenPedidoProducto PRIMARY KEY (id_OrdenPedido, id_Producto),
    CONSTRAINT FK_ordenPedidoProducto_id_OrdenPedido FOREIGN KEY(id_OrdenPedido) REFERENCES ORDEN_PEDIDO(id),
    CONSTRAINT FK_ordenPedidoProducto_id_Producto FOREIGN KEY(id_Producto) REFERENCES PRODUCTO(idLote)
);
--------------------------------------------------------------------------------
-- Creacion tabla TIPO_USUARIO
CREATE TABLE TIPO_USUARIO(
    id NUMBER,
    tipo VARCHAR2(255 BYTE) NOT NULL UNIQUE,
    CONSTRAINT PK_id_tipoUsuario PRIMARY KEY (id),
    CONSTRAINT CHECK_tipoUsuario CHECK(tipo IN ('Administrador', 'Gerente General', 'Gerente Sucursal', 'Operador', 'Cajero', 'Cliente'))
);
--------------------------------------------------------------------------------
-- Creacion tabla USUARIO
CREATE TABLE USUARIO(
    id NUMBER,
    nDocumento NUMBER NOT NULL,
    tipoDocumento VARCHAR2(255 BYTE) NOT NULL,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    correo VARCHAR2(255 BYTE) NOT NULL UNIQUE,
    pais VARCHAR2(255 BYTE) NOT NULL,
    ciudad VARCHAR2(255 BYTE) NOT NULL,
    direccion VARCHAR2(255 BYTE) NOT NULL,
    puntos NUMBER,
    id_TipoUsuario NUMBER NOT NULL,
    id_Sucursal NUMBER,
	CONSTRAINT PK_id_usuario PRIMARY KEY (id),
    CONSTRAINT FK_usuario_id_TipoUsuario FOREIGN KEY(id_TipoUsuario) REFERENCES TIPO_USUARIO(id),
    CONSTRAINT FK_usuario_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT CHECK_usuario_tipoDocumento CHECK(tipoDocumento IN ('Cedula Ciudadania', 'Cedula Extranjeria', 'Tarjeta de Identificacion', 'Pasaporte', 'NIT'))
);
--------------------------------------------------------------------------------
-- Creacion tabla CARRITO_COMPRA
CREATE TABLE CARRITO_COMPRA(
    id NUMBER,
    id_Cliente NUMBER NOT NULL,
    fCarrito DATE NOT NULL,
    estado  VARCHAR2(255 BYTE) NOT NULL,
	CONSTRAINT PK_id_carritoCompra PRIMARY KEY (id),
    CONSTRAINT FK_carritoCompra_id_Cliente FOREIGN KEY(id_Cliente) REFERENCES USUARIO(id),
    CONSTRAINT CHECK_carritoCompra_estado CHECK(estado IN ('EnProceso', 'Abandonado', 'Ejecutado'))
);
--------------------------------------------------------------------------------
-- Creacion tabla VENTA
CREATE TABLE VENTA(
    id NUMBER,
    fVenta DATE NOT NULL,
    pTotal NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
    id_Cajero NUMBER NOT NULL,
    id_Cliente NUMBER NOT NULL,
    id_CarritoCompra NUMBER NOT NULL,
	CONSTRAINT PK_id_venta PRIMARY KEY (id),
    CONSTRAINT FK_venta_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT FK_venta_id_Cajero FOREIGN KEY(id_Cajero) REFERENCES USUARIO(id),
    CONSTRAINT FK_venta_id_Cliente FOREIGN KEY(id_Cliente) REFERENCES USUARIO(id),
    CONSTRAINT FK_venta_id_CarritoCompra FOREIGN KEY(id_CarritoCompra) REFERENCES CARRITO_COMPRA(id)
);
--------------------------------------------------------------------------------
-- Creacion tabla FACTURA_ELECTRONICA
CREATE TABLE FACTURA_ELECTRONICA(
    id NUMBER,
    numFactura NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
    id_Cliente NUMBER NOT NULL,
    id_Venta NUMBER NOT NULL,
	CONSTRAINT PK_id_facturaElectronica PRIMARY KEY (id),
    CONSTRAINT FK_facturaElectronica_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT FK_facturaElectronica_id_Cliente FOREIGN KEY(id_Cliente) REFERENCES USUARIO(id),
    CONSTRAINT FK_facturaElectronica_id_Venta FOREIGN KEY(id_Venta) REFERENCES VENTA(id)
);
--------------------------------------------------------------------------------
-- Creacion tabla CARRITO_COMPRA_PRODUCTO
CREATE TABLE CARRITO_COMPRA_PRODUCTO(
    id_CarritoCompra NUMBER,
    id_Producto NUMBER,
    pVentaH NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
	CONSTRAINT PK_id_carritoCompra_Producto PRIMARY KEY (id_CarritoCompra, id_Producto),
    CONSTRAINT FK_carritoCompra_Producto_id_CarritoCompra FOREIGN KEY(id_CarritoCompra) REFERENCES CARRITO_COMPRA(id),
    CONSTRAINT FK_carritoCompra_Producto_id_Producto FOREIGN KEY(id_Producto) REFERENCES PRODUCTO(idLote)
);
--------------------------------------------------------------------------------
-- Creacion tabla PROMOCION
CREATE TABLE PROMOCION(
    id NUMBER,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    fInicio DATE NOT NULL,
    fFin DATE NOT NULL,
    descripcion VARCHAR2(255 BYTE) NOT NULL,
    tipoPromocion VARCHAR2(255 BYTE) NOT NULL,
    lleve NUMBER NOT NULL,
    pague NUMBER NOT NULL,
    descuento NUMBER NOT NULL,
    pVenta NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
	CONSTRAINT PK_id_promocion PRIMARY KEY (id),
    CONSTRAINT FK_promocion_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT CHECK_promocion_tipoPromocion CHECK(tipoPromocion IN ('PagueNlleveM', 'PagueXlleveY', 'Pague1Segundo', 'DescuentoPorcentaje', 'PaqueteProducutos'))
);
--------------------------------------------------------------------------------
-- Creacion tabla PROMOCION_PRODUCTO
CREATE TABLE PROMOCION_PRODUCTO(
    id_Promocion NUMBER,
    id_Producto NUMBER,
    stockInicial NUMBER NOT NULL,
    stockActual NUMBER NOT NULL,
	CONSTRAINT PK_id_promocionProducto PRIMARY KEY (id_Promocion, id_Producto),
    CONSTRAINT FK_promocionProducto_id_Promocion FOREIGN KEY(id_Promocion) REFERENCES PROMOCION(id),
    CONSTRAINT FK_promocionProducto_id_Producto FOREIGN KEY(id_Producto) REFERENCES PRODUCTO(idLote)
);
------------------------------------------------------------------------------
COMMIT;

--------------------------------------------------------------------------------
--------------------------- NIVEL DE AISLAMEINTO -------------------------------
--------------------------------------------------------------------------------


--SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

--------------------------------------------------------------------------------
------------------------ CARGA DE DATOS DE PRUEBAS -----------------------------
--------------------------------------------------------------------------------
-- Creacion tuplas PROVEEDOR
INSERT INTO PROVEEDOR(id, nit, nombre, calificacion) VALUES (1, 11959541, 'Javier S1.', 5);
INSERT INTO PROVEEDOR(id, nit, nombre, calificacion) VALUES (2, 11959542, 'Javier S2.', 6);
INSERT INTO PROVEEDOR(id, nit, nombre, calificacion) VALUES (3, 11959543, 'Javier S3.', 7);
COMMIT;

-- Creacion tuplas TIPO_PRODUCTO
INSERT INTO TIPO_PRODUCTO(id, nombre, tipoAlmacen, categoria, subCategoria) VALUES (1, 'Galletas', 'Refrigerado', 'Abarrotes', 'Golosinas');
INSERT INTO TIPO_PRODUCTO(id, nombre, tipoAlmacen, categoria, subCategoria) VALUES (2, 'Legumbres', 'Normal', 'Abarrotes', 'Granos');
INSERT INTO TIPO_PRODUCTO(id, nombre, tipoAlmacen, categoria, subCategoria) VALUES (3, 'Cloro', 'Congelado', 'Utlies de Aseo', 'Desinfectantes');
COMMIT;

-- Creacion tuplas SUPERMERCADO
INSERT INTO SUPERMERCADO(id, nit, nombre) VALUES (1, 778889991, 'Exito');
COMMIT;

-- Creacion tuplas SUCURSAL
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (1, 'Exito Country', 'Colombia', 'Bogot?', 'Carrera 7c #127A-46', 1);
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (2, 'Exito Unicentro', 'Colombia', 'Bogot?', 'Calle 127 #14-46', 1);
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (3, 'Exito La Colina', 'Colombia', 'Bogot?', 'Avenida Boyac?, Carrera 72 No. 146B', 1);
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (4, 'Exito Calle 80', 'Colombia', 'Bogot?', 'Carrera 59A No. 79 - 30', 1);
COMMIT;

-- Creacion tuplas TIPO_USUARIO
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (1, 'Administrador');
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (2, 'Gerente General');
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (3, 'Gerente Sucursal');
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (4, 'Operador');
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (5, 'Cajero');
INSERT INTO TIPO_USUARIO(id, tipo) VALUES (6, 'Cliente');
COMMIT;

-- Creacion tuplas USUARIO
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (1, '101010', 'Cedula Extranjeria', 'Javier Serrano', 'serranor@gmail.com', 'Colombia', 'Bogotá', 'Carrera 7c #127A-46', null, 1, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (2, '202020', 'Cedula Ciudadania', 'Alvaro Serrano', 'aserrano@grupoexito', 'Colombia', 'Bogotá', 'Carrera 8 #132-57', null, 2, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (3, '303030', 'Cedula Extranjeria', 'Ana Varela', 'avarela@grupoexito', 'Colombia', 'Bogotá', 'Carrera 8 #132-57', null, 3, 2);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (4, '404040', 'Cedula Ciudadania', 'Lindsay Pinto', 'lpinto@gmail.com', 'Colombia', 'Bogotá', 'Carrera 68F #66-10', null, 4, 2);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (5, '505050', 'Cedula Ciudadania', 'Andres Arango', 'aarango@gmail.com', 'Colombia', 'Bogotá', 'Britalia', null, 5, 1);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (6, '616161', 'Cedula Ciudadania', 'Victor Pinto', 'vpinto@gmail.com', 'Colombia', 'Bogotá', 'transversal 74 # 63a-26', null, 6, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (7, '626262', 'Cedula Ciudadania', 'Jose Morales', 'moralesj@hotmail.com', 'Colombia', 'Bogotá', 'carrera 58 sur # 15-73', null, 6, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (8, '636363', 'Cedula Ciudadania', 'Edna Marulanda', 'Emarulandaj@hotmail.com', 'Colombia', 'Bogotá', 'carrera 58 sur # 15-74', null, 6, null);
COMMIT;

-- Creacion tuplas PRODUCTO
INSERT INTO PRODUCTO(idLote, codigoBarra, nombre, marca, pVenta, presentacion, pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero, fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto) VALUES (1, '3c34eb12', 'Galletas 1', 'Arcor', 1000, 'Paquete', 10, 100, 'gramos', 100, 45, 'true', DATE '2024-12-31', 10000, 5000, 5000, 55000, 1);
INSERT INTO PRODUCTO(idLote, codigoBarra, nombre, marca, pVenta, presentacion, pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero, fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto) VALUES (2, '3c34eb13', 'Galletas el mordisco', 'Noel', 1500, 'Paquetex2', 15, 100, 'gramos', 150, 60, 'true', DATE '2025-03-03', 3000, 5000, 5000, 10000, 1);
INSERT INTO PRODUCTO(idLote, codigoBarra, nombre, marca, pVenta, presentacion, pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero, fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto) VALUES (3, '79450fa0', 'Frijol bola roja', 'Diana', 8500, 'Bolsa',17, 500, 'gramos', 500, 60, 'false', null, 2000, 5000, 1000, 6000, 2);
INSERT INTO PRODUCTO(idLote, codigoBarra, nombre, marca, pVenta, presentacion, pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero, fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto) VALUES (4, 'f07aa3c7', 'Desinfectante con cloro', 'Blancox', 5400, 'Botella',18, 300, 'mililitros', 300, 80, 'false', null, 1500, 55000, 100, 55100, 3);
COMMIT;

-- Creacion tuplas BODEGA
INSERT INTO BODEGA(id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto) VALUES (1, 500000, 30000, 'Normal', 1, 1);
INSERT INTO BODEGA(id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto) VALUES (2, 400000, 20000, 'Refrigerado', 1, 2);
INSERT INTO BODEGA(id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto) VALUES (3, 300000, 10000, 'Congelado', 1, 3);
COMMIT;

-- Creacion tuplas ESTANTE
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (1, 5000, 300, 'Normal', 2000, 1, 1);
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (2, 4000, 200, 'Normal', 1500, 1, 1);
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (3, 6000, 350, 'Normal', 2400, 1, 2);
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (4, 1000, 300, 'Normal', 600, 1, 2);
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (5, 8000, 900, 'Normal', 3000, 1, 3);
INSERT INTO ESTANTE(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) VALUES (6, 5500, 300, 'Normal', 2200, 1, 3);
COMMIT;

-- Creacion tuplas CARRITO_COMPRA
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (1, 6, DATE '2021-09-15', 'EnProceso');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (2, 7, DATE '2022-03-15', 'Abandonado');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (3, 8, DATE '2022-04-15', 'Ejecutado');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (4, 6, DATE '2021-04-28', 'EnProceso');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (5, 7, DATE '2021-05-21', 'Abandonado');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (6, 8, DATE '2021-02-14', 'Ejecutado');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (7, 6, DATE '2020-11-28', 'EnProceso');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (8, 7, DATE '2020-11-04', 'Abandonado');
INSERT INTO CARRITO_COMPRA(id, id_Cliente, fCarrito, estado) VALUES (9, 8, DATE '2020-04-25', 'Ejecutado');
COMMIT;

-- Creacion tuplas VENTA
INSERT INTO VENTA(id, fVenta, pTotal, id_Sucursal, id_Cajero, id_Cliente, id_CarritoCompra) VALUES (1, DATE '2021-09-15', 15600, 1, 5, 6, 3);
INSERT INTO VENTA(id, fVenta, pTotal, id_Sucursal, id_Cajero, id_Cliente, id_CarritoCompra) VALUES (2, DATE '2022-03-15', 8500, 1, 5, 7, 6);
INSERT INTO VENTA(id, fVenta, pTotal, id_Sucursal, id_Cajero, id_Cliente, id_CarritoCompra) VALUES (3, DATE '2022-04-15', 1100, 1, 5, 8, 9);
COMMIT;

-- Creacion tuplas CARRITO_COMPRA_PRODUCTO
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (3, 4, 20000, 4);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (3, 3, 68000, 9);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (3, 2, 33000, 8);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (7, 1, 71150, 6);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (9, 3, 54000, 9);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (9, 4, 50000, 6);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (6, 2, 61337, 7);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (6, 3, 12000, 2);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (8, 3, 33000, 9);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (5, 1, 35000, 3);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (4, 4, 44659, 10);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (4, 2, 22521, 10);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (2, 4, 29000, 10);
INSERT INTO CARRITO_COMPRA_PRODUCTO (id_CarritoCompra, id_producto, pVentaH, cantidad) VALUES (1, 3, 59890, 10);
COMMIT;

-- Creacion tuplas FACTURA_ELECTRONICA
INSERT INTO FACTURA_ELECTRONICA(id, numFactura, id_Sucursal, id_Cliente, id_Venta) VALUES (1, 3853, 1, 1, 1);
INSERT INTO FACTURA_ELECTRONICA(id, numFactura, id_Sucursal, id_Cliente, id_Venta) VALUES (2, 3854, 1, 6, 2);
INSERT INTO FACTURA_ELECTRONICA(id, numFactura, id_Sucursal, id_Cliente, id_Venta) VALUES (3, 3855, 2, 7, 3);
COMMIT;

-- Creacion tuplas ORDEN_PEDIDO
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (1, DATE '2021-01-26', 52345630, 1, 1);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (2, DATE '2021-03-23', 456700152, 2, 2);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (3, DATE '2021-06-12', 13400000, 3, 3);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (4, DATE '2021-09-13', 45356200, 1, 4);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (5, DATE '2021-12-27', 26456400, 2, 1);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (6, DATE '2022-03-15', 23567900, 3, 2);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (7, DATE '2022-06-10', 2353456, 1, 3);
INSERT INTO ORDEN_PEDIDO (id, fCompra, vTotal, id_Proveedor, id_Sucursal) VALUES (8, DATE '2022-09-02', 45356900, 2, 4);
COMMIT;

-- Creacion tuplas ORDEN_PEDIDO_PRODUCTO
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (1, 1, 10000,10000000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (2, 2, 15000,22500000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (3, 3, 5000,42500000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (4, 4, 1000,5400000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (5, 1, 1000,10000000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (6, 2, 1200,1800000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (7, 1, 2000,2000000);
INSERT INTO ORDEN_PEDIDO_PRODUCTO(id_OrdenPedido, id_Producto, cantCompra, pCompra) VALUES (8, 4, 5400,29160000);
COMMIT;

-- Creacion tuplas PROMOCION
INSERT INTO PROMOCION(id, nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal) VALUES (1, 'Galletas 3X2', DATE '2022-01-01', DATE '2022-12-31', 'Pague 2 paquetes de galletas y lleve 3', 'PagueNlleveM', 3, 2, 0, 0, 1);
INSERT INTO PROMOCION(id, nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal) VALUES (2, 'Galletas 50%', DATE '2021-01-01', DATE '2021-12-31', 'Galletas con el 50% de descuento', 'DescuentoPorcentaje', 1, 1, 50, 0, 2);
INSERT INTO PROMOCION(id, nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal) VALUES (3, 'Blancox lleve 3 pague 2', DATE '2022-08-01', DATE '2022-10-16', 'Desinfectante blancox pague 2 y lleve 3', 'PagueNlleveM', 3, 2, 10800, 5400, 3);
INSERT INTO PROMOCION(id, nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal) VALUES (4, 'Frijol 30%', DATE '2022-08-01', DATE '2022-08-16', 'Frijol bola roja con 30% de descuento', 'DescuentoPorcentaje', 1, 1, 30, 0, 4);
COMMIT;

-- Creacion tuplas PROMOCION_PRODUCTO
INSERT INTO PROMOCION_PRODUCTO(id_Promocion, id_Producto, stockinicial, stockactual) VALUES (1,1, 0, 0);
INSERT INTO PROMOCION_PRODUCTO(id_Promocion, id_Producto, stockinicial, stockactual) VALUES (2,2, 0, 0);
INSERT INTO PROMOCION_PRODUCTO(id_Promocion, id_Producto, stockinicial, stockactual) VALUES (3,4, 0, 0);
INSERT INTO PROMOCION_PRODUCTO(id_Promocion, id_Producto, stockinicial, stockactual) VALUES (4,3, 0, 0);
COMMIT;