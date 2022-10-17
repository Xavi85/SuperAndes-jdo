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
-- Creacion tabla VENTA
CREATE TABLE VENTA(
    id NUMBER,
    fVenta DATE NOT NULL,
    pTotal NUMBER NOT NULL,
    id_Sucursal NUMBER NOT NULL,
    id_Cajero NUMBER NOT NULL,
	CONSTRAINT PK_id_venta PRIMARY KEY (id),
    CONSTRAINT FK_venta_id_Sucursal FOREIGN KEY(id_Sucursal) REFERENCES SUCURSAL(id),
    CONSTRAINT FK_venta_id_Cajero FOREIGN KEY(id_Cajero) REFERENCES USUARIO(id)
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
-- Creacion tabla VENTA_PRODUCTO
CREATE TABLE VENTA_PRODUCTO(
    id_Venta NUMBER,
    id_Producto NUMBER,
    pVentaH NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
	CONSTRAINT PK_id_ventaProducto PRIMARY KEY (id_Venta, id_Producto),
    CONSTRAINT FK_ventaProducto_id_Venta FOREIGN KEY(id_Venta) REFERENCES VENTA(id),
    CONSTRAINT FK_ventaProducto_id_Producto FOREIGN KEY(id_Producto) REFERENCES PRODUCTO(idLote)
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
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (1, 'Exito Country', 'Colombia', 'Bogotá', 'Carrera 7c #127A-46', 1);
INSERT INTO SUCURSAL(id, nombre, pais, ciudad, direccion, id_Supermercado) VALUES (2, 'Exito Unicentro', 'Colombia', 'Bogotá', 'Calle 127 #14-46', 1);
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
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (1, '1195954', 'Cedula Extranjeria', 'Javier Serrano', 'serranor@gmail.com', 'Colombia', 'Bogotá', 'Carrera 7c #127A-46', null, 6, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (2, '63743682', 'Cedula Ciudadania', 'Alvaro Serrano', 'aserrano@grupoexito', 'Colombia', 'Bogotá', 'Carrera 8 #132-57', null, 3, null);
INSERT INTO USUARIO(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal) VALUES (3, '1189865', 'Cedula Extranjeria', 'Ana Varela', 'avarela@grupoexito', 'Colombia', 'Bogotá', 'Carrera 8 #132-57', null, 3, 2);
COMMIT;

-- Creacion tuplas PRODUCTO
INSERT INTO PRODUCTO(idLote, codigoBarra, nombre, marca, pVenta, presentacion, pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero, fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto)
VALUES (1, 1010101010, 'Galletas 1', 'Arcor', 1000, 'pres', 1000, 100, 'gramos', 100, 45, 'false', null, 10000, 5000, 5000, 10000, 3);
COMMIT;