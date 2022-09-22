package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.SuperAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
	
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	private PersistenciaSuperAndes pp;
	
	public SuperAndes () {
		
		pp = PersistenciaSuperAndes.getInstance ();
	}
	
	public SuperAndes (JsonObject tableConfig) {
		
		pp = PersistenciaSuperAndes.getInstance (tableConfig);
	}
	
	public void cerrarUnidadPersistencia ()	{
		pp.cerrarUnidadPersistencia ();
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las Bodegas
	 *****************************************************************/

	public Bodega adicionarBodega (int volMax, int pesoMax, String tipoAlmacen, long id_Sucursal, long id_TipoProducto) {
		
		log.info ("Adicionando Bodega " + volMax + ", " + pesoMax + ", " + tipoAlmacen + ", " + id_Sucursal + ", " + id_TipoProducto);
		Bodega Bodega = pp.adicionarBodega (volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
        log.info ("Adicionando Bodega: " + Bodega);
        return Bodega;
	}

	public long eliminarBodegaPorId (long id) {
		
        log.info ("Eliminando Bodega por id: " + id);
        long resp = pp.eliminarBodegaPorId (id);
        log.info ("Eliminando Bodega por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Bodega darBodegaPorId (long id)
	{
        log.info ("Dar información de una bodega por id: " + id);
        Bodega bodega = pp.darBodegaPorId (id);
        log.info ("Buscando Bodega por Id: " + bodega != null ? bodega : "NO EXISTE");
        return bodega;
	}

	public List<Bodega> darBodegas () {
		
        log.info ("Consultando Bodegas");
        List<Bodega> Bodegas = pp.darBodegas ();	
        log.info ("Consultando Bodegas: " + Bodegas.size() + " Bodegas existentes");
        return Bodegas;
	}

	public List<VOBodega> darVOBodegas () {
		
		log.info ("Generando los VO de las Bodegas");       
        List<VOBodega> voBodegas = new LinkedList<VOBodega> ();
        for (Bodega beb : pp.darBodegas ())
        {
        	voBodegas.add (beb);
        }
        log.info ("Generando los VO de las Bodegas: " + voBodegas.size() + " existentes");
        return voBodegas;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Estantes
	 *****************************************************************/

	public Estante adicionarEstante (int volMax, int pesoMax, String tipoAlmacen, int nAbastecimiento, long id_Sucursal, long id_TipoProducto) {
		
		log.info ("Adicionando Estante " + volMax + ", " + pesoMax + ", " + tipoAlmacen + ", "  + nAbastecimiento + ", " + id_Sucursal + ", " + id_TipoProducto);
		Estante estante = pp.adicionarEstante (volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto);
        log.info ("Adicionando Estante: " + estante);
        return estante;
	}

	public long eliminarEstantePorId (long id) {
		
        log.info ("Eliminando Estante por id: " + id);
        long resp = pp.eliminarEstantePorId (id);
        log.info ("Eliminando Estante por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Estante darEstantePorId (long id)
	{
        log.info ("Dar información de un Estante por id: " + id);
        Estante estante = pp.darEstantePorId (id);
        log.info ("Buscando Estante por Id: " + estante != null ? estante : "NO EXISTE");
        return estante;
	}

	public List<Estante> darEstantes () {
		
        log.info ("Consultando Estantes");
        List<Estante> estante = pp.darEstantes ();	
        log.info ("Consultando Estantes: " + estante.size() + " Estantes existentes");
        return estante;
	}

	public List<VOEstante> darVOEstantes () {
		
		log.info ("Generando los VO de los Estantes");       
        List<VOEstante> voEstantes = new LinkedList<VOEstante> ();
        for (Estante beb : pp.darEstantes ())
        {
        	voEstantes.add (beb);
        }
        log.info ("Generando los VO de los Estantes: " + voEstantes.size() + " existentes");
        return voEstantes;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Facturas Electronicas
	 *****************************************************************/

	public FacturaElectronica adicionarFacturaElectronica (long numFactura, long id_Sucursal, long id_Cliente, long id_Venta) {
		
		log.info ("Adicionando Facturas Electronica " + numFactura);
		FacturaElectronica facturasElectronica = pp.adicionarFacturaElectronica (numFactura, id_Sucursal, id_Cliente, id_Venta);
        log.info ("Adicionando FacturasElectronica: " + facturasElectronica);
        return facturasElectronica;
	}

	public long eliminarFacturaElectronicaPorId (long id) {
		
        log.info ("Eliminando Factura Electronica por id: " + id);
        long resp = pp.eliminarFacturaElectronicaPorId (id);
        log.info ("Eliminando Facturas Electronica por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public FacturaElectronica darFacturasElectronicaPorId (long id)
	{
        log.info ("Dar información de una Facturas Electronica por id: " + id);
        FacturaElectronica facturasElectronica = pp.darFacturaElectronicaPorId (id);
        log.info ("Buscando Facturas Electronic por Id: " + facturasElectronica != null ? facturasElectronica : "NO EXISTE");
        return facturasElectronica;
	}

	public List<FacturaElectronica> darFacturasElectronicas () {
		
        log.info ("Consultando Facturas Electronicas");
        List<FacturaElectronica> FacturasElectronicas = pp.darFacturasElectronicas ();	
        log.info ("Consultando Facturas Electronicas: " + FacturasElectronicas.size() + " Facturas Electronicas existentes");
        return FacturasElectronicas;
	}

	public List<VOFacturaElectronica> darVOFacturasElectronicas () {
		
		log.info ("Generando los VO de las Facturas Electronicas");       
        List<VOFacturaElectronica> voFacturasElectronicas = new LinkedList<VOFacturaElectronica> ();
        for (FacturaElectronica beb : pp.darFacturasElectronicas ())
        {
        	voFacturasElectronicas.add (beb);
        }
        log.info ("Generando los VO de las Facturas Electronicas: " + voFacturasElectronicas.size() + " existentes");
        return voFacturasElectronicas;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Ordenes de Pedidos
	 *****************************************************************/

	public OrdenPedido adicionarOrdenPedido (Date fCompra, int vTotal, long id_Proveedor, long id_Sucursal) {
		
		log.info ("Adicionando Orden de Pedido " + fCompra + ", " + vTotal + ", " + id_Proveedor + ", "  + id_Sucursal);
		OrdenPedido ordenPedido = pp.adicionarOrdenPedido (fCompra, vTotal, id_Proveedor, id_Sucursal);
        log.info ("Adicionando Orden de Pedido: " + ordenPedido);
        return ordenPedido;
	}

	public long eliminarOrdenPedidoPorId (long id) {
		
        log.info ("Eliminando Orden de Pedido por id: " + id);
        long resp = pp.eliminarOrdenPedidoPorId (id);
        log.info ("Eliminando Orden de Pedido por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public OrdenPedido darOrdenPedidoPorId (long id)
	{
        log.info ("Dar información de una Orden de Pedido por id: " + id);
        OrdenPedido ordenPedido = pp.darOrdenPedidoPorId (id);
        log.info ("Buscando Orden de Pedido por Id: " + ordenPedido != null ? ordenPedido : "NO EXISTE");
        return ordenPedido;
	}

	public List<OrdenPedido> darOrdenesPedidos () {
		
        log.info ("Consultando Ordenes de Pedidos");
        List<OrdenPedido> ordenPedido = pp.darOrdenesPedidos ();	
        log.info ("Consultando Ordenes de Pedidos: " + ordenPedido.size() + " Ordenes de Pedidos existentes");
        return ordenPedido;
	}

	public List<VOOrdenPedido> darVOOrdenesPedidos () {
		
		log.info ("Generando los VO de las Ordenes de Pedidos");       
        List<VOOrdenPedido> voOrdenesPedidos = new LinkedList<VOOrdenPedido> ();
        for (OrdenPedido beb : pp.darOrdenesPedidos ())
        {
        	voOrdenesPedidos.add (beb);
        }
        log.info ("Generando los VO de las Ordenes de Pedidos: " + voOrdenesPedidos.size() + " existentes");
        return voOrdenesPedidos;
	}

	
	/* *******************************************************************
	 * 			Métodos para manejar las Ordenes de Pedidos de Productos
	 ********************************************************************/

	public OrdenPedidoProducto adicionarOrdenPedidoProducto (long id_OrdenPedido, long id_Producto, int cantCompra, int pCompra) {
		
		log.info ("Adicionando Orden de Pedido de Producto " + id_OrdenPedido + ", " + id_Producto + ", " + cantCompra + ", "  + pCompra);
		OrdenPedidoProducto ordenPedidoProducto = pp.adicionarOrdenPedidoProducto (id_OrdenPedido, id_Producto, cantCompra, pCompra);
        log.info ("Adicionando Orden de Pedido de Producto: " + ordenPedidoProducto);
        return ordenPedidoProducto;
	}

	public long eliminarOrdenPedidoProductoPorIdOrdenPedido (long id_OrdenPedido) {
		
        log.info ("Eliminando Orden de Pedido de Producto por id de Orden de Pedido: " + id_OrdenPedido);
        long resp = pp.eliminarOrdenPedidoProductoPorIdOrdenPedido (id_OrdenPedido);
        log.info ("Eliminando Orden de Pedido de Producto por id de Orden de Pedido: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public OrdenPedidoProducto darOrdenPedidoProductoPorIdOrdenPedido (long id_OrdenPedido)
	{
        log.info ("Dar información de una Orden de Pedido de Producto por id de Orden de Pedido: " + id_OrdenPedido);
        OrdenPedidoProducto ordenPedidoProducto = pp.darOrdenPedidoProductoPorIdOrdenPedido (id_OrdenPedido);
        log.info ("Buscando Orden de Pedido de Producto por id de Orden de Pedido: " + ordenPedidoProducto != null ? ordenPedidoProducto : "NO EXISTE");
        return ordenPedidoProducto;
	}

	public List<OrdenPedidoProducto> darOrdenesPedidosProductos () {
		
        log.info ("Consultando Ordenes de Pedidos de Productos");
        List<OrdenPedidoProducto> ordenPedidoProducto = pp.darOrdenesPedidosProductos ();	
        log.info ("Consultando Ordenes de Pedidos de Productos: " + ordenPedidoProducto.size() + " Ordenes de Pedidos de Productos existentes");
        return ordenPedidoProducto;
	}

	public List<VOOrdenPedidoProducto> darVOOrdenesPedidosProductos () {
		
		log.info ("Generando los VO de las Ordenes de Pedidos de Productos");       
        List<VOOrdenPedidoProducto> voOrdenesPedidosProductos = new LinkedList<VOOrdenPedidoProducto> ();
        for (OrdenPedidoProducto beb : pp.darOrdenesPedidosProductos ())
        {
        	voOrdenesPedidosProductos.add (beb);
        }
        log.info ("Generando los VO de las Ordenes de Pedidos de Productos: " + voOrdenesPedidosProductos.size() + " existentes");
        return voOrdenesPedidosProductos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Productos
	 *****************************************************************/

	public Producto adicionarProducto (String codigoBarra, String nombre, String marca, int pVenta, String presentacion,
			int pUnidadMedida, int cantPPT, String unidadMedida, int espEmpPeso, int espEmpVol, boolean esPerecedero,
			Date fVencimiento, int nReorden, int stockBodega, int stockProducto, int stockTotal, long id_TipoProducto) {
		
		log.info ("Adicionando Producto " + nombre);
		Producto producto = pp.adicionarProducto (codigoBarra, nombre, marca, pVenta, presentacion,
        		pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,
        		fVencimiento, nReorden, stockBodega, stockProducto, stockTotal, id_TipoProducto);
        log.info ("Adicionando Producto: " + producto);
        return producto;
	}

	public long eliminarProductoPorId (long id) {
		
        log.info ("Eliminando Producto por id: " + id);
        long resp = pp.eliminarProductoPorId (id);
        log.info ("Eliminando Producto por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Producto darProductoPorId (long id)
	{
        log.info ("Dar información de un Producto por id: " + id);
        Producto producto = pp.darProductoPorId (id);
        log.info ("Buscando Producto por Id: " + producto != null ? producto : "NO EXISTE");
        return producto;
	}

	public List<Producto> darProductos () {
		
        log.info ("Consultando Productos");
        List<Producto> producto = pp.darProductos ();	
        log.info ("Consultando Productos: " + producto.size() + " Productos existentes");
        return producto;
	}

	public List<VOProducto> darVOProductos () {
		
		log.info ("Generando los VO de los Productos");       
        List<VOProducto> voProductos = new LinkedList<VOProducto> ();
        for (Producto beb : pp.darProductos ())
        {
        	voProductos.add (beb);
        }
        log.info ("Generando los VO de los Productos: " + voProductos.size() + " existentes");
        return voProductos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Promociones
	 *****************************************************************/

	public Promocion adicionarPromocion (String nombre, Date fInicio, Date fFin, String descripcion, String tipoPromocion,
			int lleve, int pague, float descuento, int pVenta, long id_Sucursal) {
		
		log.info ("Adicionando Promocion " + nombre);
		Promocion promocion = pp.adicionarPromocion (nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal);
        log.info ("Adicionando Promocion: " + promocion);
        return promocion;
	}

	public long eliminarPromocionPorId (long id) {
		
        log.info ("Eliminando Promocion por id: " + id);
        long resp = pp.eliminarPromocionPorId (id);
        log.info ("Eliminando Promocion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Promocion darPromocionPorId (long id)
	{
        log.info ("Dar información de una Promocion por id: " + id);
        Promocion promocion = pp.darPromocionPorId (id);
        log.info ("Buscando bebedor por Id: " + promocion != null ? promocion : "NO EXISTE");
        return promocion;
	}

	public List<Promocion> darPromociones () {
		
        log.info ("Consultando Promociones");
        List<Promocion> promocion = pp.darPromociones ();	
        log.info ("Consultando Promociones: " + promocion.size() + " Promociones existentes");
        return promocion;
	}

	public List<VOPromocion> darVOPromociones () {
		
		log.info ("Generando los VO de las Promociones");       
        List<VOPromocion> voPromociones = new LinkedList<VOPromocion> ();
        for (Promocion beb : pp.darPromociones ())
        {
        	voPromociones.add (beb);
        }
        log.info ("Generando los VO de las Promociones: " + voPromociones.size() + " existentes");
        return voPromociones;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Promociones de Productos
	 *****************************************************************/

	public PromocionProducto adicionarPromocionProducto (long id_Promocion, long id_Producto) {
		
		log.info ("Adicionando Promocion de Producto " + id_Promocion + ", " + id_Producto);
		PromocionProducto promocionProducto = pp.adicionarPromocionProducto (id_Promocion, id_Producto);
        log.info ("Adicionando Promocion de Producto: " + promocionProducto);
        return promocionProducto;
	}

	public long eliminarPromocionProductoPorIdPromocion (long id_Promocion) {
		
        log.info ("Eliminando Promocion de Producto por id Promocion: " + id_Promocion);
        long resp = pp.eliminarPromocionProductoPorIdPromocion (id_Promocion);
        log.info ("Eliminando Promocion de Producto por id Promocion: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public PromocionProducto darPromocionProductoPorIdPromocion (long id_Promocion)
	{
        log.info ("Dar información de una Promocion de Producto por id Promocion: " + id_Promocion);
        PromocionProducto promocionProducto = pp.darPromocionProductoPorIdPromocion (id_Promocion);
        log.info ("Buscando Promocion de Producto por Id Promocion: " + promocionProducto != null ? promocionProducto : "NO EXISTE");
        return promocionProducto;
	}

	public List<PromocionProducto> darPromocionesProductos () {
		
        log.info ("Consultando Promociones de Productos");
        List<PromocionProducto> promocionProducto = pp.darPromocionesProductos ();	
        log.info ("Consultando Promociones de Productos: " + promocionProducto.size() + " Promociones de Productos existentes");
        return promocionProducto;
	}

	public List<VOPromocionProducto> darVOPromocionesProductos () {
		
		log.info ("Generando los VO de las Promociones de Productos");       
        List<VOPromocionProducto> voPromocionesProductos = new LinkedList<VOPromocionProducto> ();
        for (PromocionProducto beb : pp.darPromocionesProductos ())
        {
        	voPromocionesProductos.add (beb);
        }
        log.info ("Generando los VO de las Promociones de Productos: " + voPromocionesProductos.size() + " existentes");
        return voPromocionesProductos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Proveedores
	 *****************************************************************/

	public Proveedor adicionarProveedor (long nit, String nombre, int calificacion) {
		
		log.info ("Adicionando Proveedor " + nombre);
		Proveedor proveedor = pp.adicionarProveedor (nit, nombre, calificacion);
        log.info ("Adicionando Proveedor: " + proveedor);
        return proveedor;
	}

	public long eliminarProveedorPorId (long id) {
		
        log.info ("Eliminando Proveedor por id: " + id);
        long resp = pp.eliminarProveedorPorId (id);
        log.info ("Eliminando Proveedor por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Proveedor darProveedorPorId (long id)
	{
        log.info ("Dar información de un Proveedor por id: " + id);
        Proveedor proveedor = pp.darProveedorPorId (id);
        log.info ("Buscando Proveedor por Id: " + proveedor != null ? proveedor : "NO EXISTE");
        return proveedor;
	}

	public List<Proveedor> darProveedores () {
		
        log.info ("Consultando Proveedores");
        List<Proveedor> proveedor = pp.darProveedores ();	
        log.info ("Consultando Proveedores: " + proveedor.size() + " Proveedores existentes");
        return proveedor;
	}

	public List<VOProveedor> darVOProveedores () {
		
		log.info ("Generando los VO de los Proveedores");       
        List<VOProveedor> voProveedores = new LinkedList<VOProveedor> ();
        for (Proveedor beb : pp.darProveedores ())
        {
        	voProveedores.add (beb);
        }
        log.info ("Generando los VO de los Proveedores: " + voProveedores.size() + " existentes");
        return voProveedores;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Sucursales
	 *****************************************************************/

	public Sucursal adicionarSucursal (String nombre, String pais, String ciudad, String direccion, long id_Supermercado) {
		
		log.info ("Adicionando Sucursal " + nombre);
		Sucursal sucursal = pp.adicionarSucursal (nombre, pais, ciudad, direccion, id_Supermercado);
        log.info ("Adicionando Sucursal: " + sucursal);
        return sucursal;
	}

	public long eliminarSucursalPorId (long id) {
		
        log.info ("Eliminando Sucursal por id: " + id);
        long resp = pp.eliminarSucursalPorId (id);
        log.info ("Eliminando Sucursal por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Sucursal darSucursalPorId (long id)
	{
        log.info ("Dar información de una Sucursal por id: " + id);
        Sucursal sucursal = pp.darSucursalPorId (id);
        log.info ("Buscando Sucursal por Id: " + sucursal != null ? sucursal : "NO EXISTE");
        return sucursal;
	}

	public List<Sucursal> darSucursales () {
		
        log.info ("Consultando Sucursales");
        List<Sucursal> sucursal = pp.darSucursales ();	
        log.info ("Consultando Sucursales: " + sucursal.size() + " Sucursales existentes");
        return sucursal;
	}

	public List<VOSucursal> darVOSucursales () {
		
		log.info ("Generando los VO de las Sucursales");       
        List<VOSucursal> voSucursales = new LinkedList<VOSucursal> ();
        for (Sucursal beb : pp.darSucursales ())
        {
        	voSucursales.add (beb);
        }
        log.info ("Generando los VO de las Sucursales: " + voSucursales.size() + " existentes");
        return voSucursales;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Supermercados
	 *****************************************************************/

	public Supermercado adicionarSupermercado (long nit, String nombre) {
		
		log.info ("Adicionando Supermercado " + nombre);
		Supermercado supermercado = pp.adicionarSupermercado (nit, nombre);
        log.info ("Adicionando Supermercado: " + supermercado);
        return supermercado;
	}

	public long eliminarSupermercadoPorId (long id) {
		
        log.info ("Eliminando Supermercado por id: " + id);
        long resp = pp.eliminarSupermercadoPorId (id);
        log.info ("Eliminando Supermercado por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Supermercado darSupermercadoPorId (long id)
	{
        log.info ("Dar información de un Supermercado por id: " + id);
        Supermercado supermercado = pp.darSupermercadoPorId (id);
        log.info ("Buscando Supermercado por Id: " + supermercado != null ? supermercado : "NO EXISTE");
        return supermercado;
	}

	public List<Supermercado> darSupermercados () {
		
        log.info ("Consultando Supermercados");
        List<Supermercado> supermercado = pp.darSupermercados ();	
        log.info ("Consultando Supermercados: " + supermercado.size() + " Supermercados existentes");
        return supermercado;
	}

	public List<VOSupermercado> darVOSupermercados () {
		
		log.info ("Generando los VO de los Supermercados");       
        List<VOSupermercado> voSupermercados = new LinkedList<VOSupermercado> ();
        for (Supermercado beb : pp.darSupermercados ())
        {
        	voSupermercados.add (beb);
        }
        log.info ("Generando los VO de los Supermercados: " + voSupermercados.size() + " existentes");
        return voSupermercados;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Tipos de Productos
	 *****************************************************************/

	public TipoProducto adicionarTipoProducto (String nombre, String tipoAlmacen, String categoria, String subCategoria) {
		
		log.info ("Adicionando Tipo de Producto " + nombre);
		TipoProducto tipoProducto = pp.adicionarTipoProducto (nombre, tipoAlmacen, categoria, subCategoria);
        log.info ("Adicionando Tipo de Producto: " + tipoProducto);
        return tipoProducto;
	}

	public long eliminarTipoProductoPorId (long id) {
		
        log.info ("Eliminando Tipo de Producto por id: " + id);
        long resp = pp.eliminarTipoProductoPorId (id);
        log.info ("Eliminando Tipo de Producto por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public TipoProducto darTipoProductoPorId (long id)
	{
        log.info ("Dar información de un Tipo de Producto por id: " + id);
        TipoProducto tipoProducto = pp.darTipoProductoPorId (id);
        log.info ("Buscando Tipo de Producto por Id: " + tipoProducto != null ? tipoProducto : "NO EXISTE");
        return tipoProducto;
	}

	public List<TipoProducto> darTiposProductos () {
		
        log.info ("Consultando Tipos de Productos");
        List<TipoProducto> tipoProducto = pp.darTiposProductos ();	
        log.info ("Consultando Tipos de Productos: " + tipoProducto.size() + " TiposProductos existentes");
        return tipoProducto;
	}

	public List<VOTipoProducto> darVOTiposProductos () {
		
		log.info ("Generando los VO de los Tipos de Productos");       
        List<VOTipoProducto> voTiposProductos = new LinkedList<VOTipoProducto> ();
        for (TipoProducto beb : pp.darTiposProductos ())
        {
        	voTiposProductos.add (beb);
        }
        log.info ("Generando los VO de los Tipos de Productos: " + voTiposProductos.size() + " existentes");
        return voTiposProductos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Usuarios
	 *****************************************************************/

	public Usuario adicionarUsuario (long nDocumento, String tipoDocumento, String tipoUsuario, String nombre, String correo,
			String pais, String ciudad, String direccion, int puntos, long id_Sucursal, long id_Supermercado) {
		
		log.info ("Adicionando Usuario " + nombre);
		Usuario usuario = pp.adicionarUsuario (nDocumento, tipoDocumento, tipoUsuario, nombre, correo, pais, ciudad, direccion, puntos, id_Sucursal, id_Supermercado);
        log.info ("Adicionando Usuario: " + usuario);
        return usuario;
	}

	public long eliminarUsuarioPorId (long id) {
		
        log.info ("Eliminando Usuario por id: " + id);
        long resp = pp.eliminarUsuarioPorId (id);
        log.info ("Eliminando Usuario por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Usuario darUsuarioPorId (long id)
	{
        log.info ("Dar información de un Usuario por id: " + id);
        Usuario usuario = pp.darUsuarioPorId (id);
        log.info ("Buscando Usuario por Id: " + usuario != null ? usuario : "NO EXISTE");
        return usuario;
	}

	public List<Usuario> darUsuarios () {
		
        log.info ("Consultando Usuarios");
        List<Usuario> usuario = pp.darUsuarios ();	
        log.info ("Consultando Usuarios: " + usuario.size() + " Usuarios existentes");
        return usuario;
	}

	public List<VOUsuario> darVOUsuarios () {
		
		log.info ("Generando los VO de los Usuarios");       
        List<VOUsuario> voUsuarios = new LinkedList<VOUsuario> ();
        for (Usuario beb : pp.darUsuarios ())
        {
        	voUsuarios.add (beb);
        }
        log.info ("Generando los VO de los Usuarios: " + voUsuarios.size() + " existentes");
        return voUsuarios;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Ventas
	 *****************************************************************/

	public Venta adicionarVenta (Date fVenta, int pTotal, long id_Sucursal, long id_Cajero) {
		
		log.info ("Adicionando Venta " + fVenta + ", " + pTotal + ", " + id_Sucursal + ", "  + id_Cajero);
		Venta venta = pp.adicionarVenta (fVenta, pTotal, id_Sucursal, id_Cajero);
        log.info ("Adicionando Venta: " + venta);
        return venta;
	}

	public long eliminarVentaPorId (long id) {
		
        log.info ("Eliminando Venta por id: " + id);
        long resp = pp.eliminarVentaPorId (id);
        log.info ("Eliminando Venta por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Venta darVentaPorId (long id)
	{
        log.info ("Dar información de una Venta por id: " + id);
        Venta venta = pp.darVentaPorId (id);
        log.info ("Buscando Venta por Id: " + venta != null ? venta : "NO EXISTE");
        return venta;
	}

	public List<Venta> darVentas () {
		
        log.info ("Consultando Ventas");
        List<Venta> venta = pp.darVentas ();	
        log.info ("Consultando Ventas: " + venta.size() + " Ventas existentes");
        return venta;
	}

	public List<VOVenta> darVOVentas () {
		
		log.info ("Generando los VO de las Ventas");       
        List<VOVenta> voVentas = new LinkedList<VOVenta> ();
        for (Venta beb : pp.darVentas ())
        {
        	voVentas.add (beb);
        }
        log.info ("Generando los VO de las Ventas: " + voVentas.size() + " existentes");
        return voVentas;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Ventas de Productos
	 *****************************************************************/

	public VentaProducto adicionarVentaProducto (long id_Venta, long id_Producto, int pVentaH, int cantidad) {
		
		log.info ("Adicionando Venta de Producto " + id_Venta + ", " + id_Producto + ", " + pVentaH + ", "  + cantidad);
		VentaProducto ventaProducto = pp.adicionarVentaProducto (id_Venta, id_Producto, pVentaH, cantidad);
        log.info ("Adicionando Venta de Producto: " + ventaProducto);
        return ventaProducto;
	}

	public long eliminarVentaProductoPorIdVenta (long id_Venta) {
		
        log.info ("Eliminando Venta de Producto por id: " + id_Venta);
        long resp = pp.eliminarVentaProductoPorIdVenta (id_Venta);
        log.info ("Eliminando Venta de Producto por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public VentaProducto darVentaProductoPorIdVenta (long id_Venta)
	{
        log.info ("Dar información de una Venta de Producto por id: " + id_Venta);
        VentaProducto ventaProducto = pp.darVentaProductoPorIdVenta (id_Venta);
        log.info ("Buscando VentaProducto por Id: " + ventaProducto != null ? ventaProducto : "NO EXISTE");
        return ventaProducto;
	}

	public List<VentaProducto> darVentasProductos () {
		
        log.info ("Consultando Ventas de Productos");
        List<VentaProducto> ventaProducto = pp.darVentasProductos ();	
        log.info ("Consultando Ventas de Productos: " + ventaProducto.size() + " Ventas de Productos existentes");
        return ventaProducto;
	}

	public List<VOVentaProducto> darVOVentasProductos () {
		
		log.info ("Generando los VO de las Ventas de Productos");       
        List<VOVentaProducto> voVentasProductos = new LinkedList<VOVentaProducto> ();
        for (VentaProducto beb : pp.darVentasProductos ())
        {
        	voVentasProductos.add (beb);
        }
        log.info ("Generando los VO de las Ventas de Productos: " + voVentasProductos.size() + " existentes");
        return voVentasProductos;
	}

	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	public long [] limpiarSuperAndes () {
		
        log.info ("Limpiando la BD de SuperAndes");
        long [] borrrados = pp.limpiarSuperAndes();	
        log.info ("Limpiando la BD de SuperAndes: Listo!");
        return borrrados;
	}
}
