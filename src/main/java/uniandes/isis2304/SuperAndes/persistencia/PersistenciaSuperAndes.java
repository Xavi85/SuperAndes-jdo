package uniandes.isis2304.SuperAndes.persistencia;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.SuperAndes.negocio.Bodega;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.PromocionProducto;
import uniandes.isis2304.SuperAndes.negocio.Proveedor;
import uniandes.isis2304.SuperAndes.negocio.Sucursal;
import uniandes.isis2304.SuperAndes.negocio.Supermercado;
import uniandes.isis2304.SuperAndes.negocio.TipoProducto;
import uniandes.isis2304.SuperAndes.negocio.TipoUsuario;
import uniandes.isis2304.SuperAndes.negocio.Usuario;
import uniandes.isis2304.SuperAndes.negocio.Venta;
import uniandes.isis2304.SuperAndes.negocio.CarritoCompra;
import uniandes.isis2304.SuperAndes.negocio.FacturaElectronica;
import uniandes.isis2304.SuperAndes.negocio.OrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.OrdenPedidoProducto;
import uniandes.isis2304.SuperAndes.negocio.Producto;
import uniandes.isis2304.SuperAndes.negocio.Promocion;

public class PersistenciaSuperAndes {
	
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	public final static String SQL = "javax.jdo.query.SQL";
	private static PersistenciaSuperAndes instance;
	private PersistenceManagerFactory pmf;
	private List <String> tablas;
	private SQLUtil sqlUtil;
	private SQLBodega sqlBodega;
	private SQLEstante sqlEstante;
	private SQLFacturaElectronica sqlFacturaElectronica;
	private SQLOrdenPedido sqlOrdenPedido;
	private SQLOrdenPedidoProducto sqlOrdenPedidoProducto;
	private SQLProducto sqlProducto;
	private SQLPromocion sqlPromocion;
	private SQLPromocionProducto sqlPromocionProducto;
	private SQLProveedor sqlProveedor;
	private SQLSucursal sqlSucursal;
	private SQLSupermercado sqlSupermercado;
	private SQLTipoProducto sqlTipoProducto;
	private SQLTipoUsuario sqlTipoUsuario;
	private SQLUsuario sqlUsuario;
	private SQLVenta sqlVenta;
	private SQLCarritoCompra sqlCarritoCompra;
	
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	private PersistenciaSuperAndes () {
		
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SuperAndes_sequence");
		tablas.add ("BODEGA");
		tablas.add ("ESTANTE");
		tablas.add ("FACTURA_ELECTRONICA");
		tablas.add ("ORDEN_PEDIDO");
		tablas.add ("ORDEN_PEDIDO_PRODUCTO");
		tablas.add ("PRODUCTO");
		tablas.add ("PROMOCION");
		tablas.add ("PROMOCION_PRODUCTO");
		tablas.add ("PROVEEDOR");
		tablas.add ("SUCURSAL");
		tablas.add ("SUPERMERCADO");
		tablas.add ("TIPO_PRODUCTO");
		tablas.add ("TIPO_USUARIO");
		tablas.add ("USUARIO");
		tablas.add ("VENTA");
		tablas.add ("VENTA_PRODUCTO");
	}

	private PersistenciaSuperAndes (JsonObject tableConfig) {
		
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	public static PersistenciaSuperAndes getInstance ()	{
		
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
	
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig) {
		
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	public void cerrarUnidadPersistencia ()	{
		
		pmf.close ();
		instance = null;
	}

	private List <String> leerNombresTablas (JsonObject tableConfig) {
		
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}

	private void crearClasesSQL () {
				
		sqlBodega = new SQLBodega(this);
		sqlEstante = new SQLEstante(this);
		sqlFacturaElectronica = new SQLFacturaElectronica(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlOrdenPedidoProducto = new SQLOrdenPedidoProducto(this);
		sqlProducto = new SQLProducto(this);
		sqlPromocion = new SQLPromocion(this);
		sqlPromocionProducto = new SQLPromocionProducto(this);
		sqlProveedor = new SQLProveedor(this);
		sqlSucursal = new SQLSucursal(this);
		sqlSupermercado = new SQLSupermercado(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlTipoUsuario = new SQLTipoUsuario(this);
		sqlUsuario = new SQLUsuario(this);
		sqlUtil = new SQLUtil(this);
		sqlVenta = new SQLVenta(this);
		sqlCarritoCompra = new SQLCarritoCompra(this);	
		
	}

	public String darSeqSuperAndes() { return tablas.get(0); }
	public String darTablaBodega() { return tablas.get(1); }
	public String darTablaEstante()	{ return tablas.get(2); }
	public String darTablaFacturaElectronica() { return tablas.get(3); }
	public String darTablaOrdenPedido()	{ return tablas.get(4);	}
	public String darTablaOrdenPedidoProducto()	{ return tablas.get(5);	}
	public String darTablaProducto() { return tablas.get(6); }
	public String darTablaPromocion() { return tablas.get(7); }
	public String darTablaPromocionProducto() { return tablas.get(8); }
	public String darTablaProveedor() { return tablas.get(9); }
	public String darTablaSucursal() { return tablas.get(10); }
	public String darTablaSupermercado() { return tablas.get(11); }
	public String darTablaTipoProducto() { return tablas.get(12); }
	public String darTablaTipoUsuario() { return tablas.get(13); }
	public String darTablaUsuario() { return tablas.get(14); }
	public String darTablaVenta() { return tablas.get(15); }
	public String darTablaCarritoCompra() { return tablas.get(16); }

	private long nextval () {
		
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }

	private String darDetalleException(Exception e) {
		
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Bodegas
	 *****************************************************************/
	
	public Bodega adicionarBodega (int volMax, int pesoMax, String tipoAlmacen, long id_Sucursal, long id_TipoProducto) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlBodega.adicionarBodega(pm, id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
            tx.commit();
            
            log.trace ("Inserción de Bodega: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Bodega (id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarBodegaPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlBodega.eliminarBodegaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Bodega darBodegaPorId (long id) {
		
		return sqlBodega.darBodegaPorId (pmf.getPersistenceManager(), id);
	}

	public List<Bodega> darBodegas () {
		
		return sqlBodega.darBodegas (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Estantes
	 *****************************************************************/

	public Estante adicionarEstante (int volMax, int pesoMax, String tipoAlmacen, int nAbastecimiento, long id_Sucursal, long id_TipoProducto) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pm, id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto);
            tx.commit();
            
            log.trace ("Inserción de Estante: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Estante (id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarEstantePorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlEstante.eliminarEstantePorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Estante darEstantePorId (long id) {
		
		return sqlEstante.darEstantePorId (pmf.getPersistenceManager(), id);
	}
	
	public List<Object> darIdEstantes () {
		
		return sqlEstante.darIdEstantes (pmf.getPersistenceManager());
	}
	
	public List<Object> darNAbastecimientoId (long id) {
		
		return sqlEstante.darNAbastecimientoId(pmf.getPersistenceManager(), id);
	}
	
	public List<Object> darTipoProductoId (long id) {
		
		return sqlEstante.darTipoProductoId(pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las FacturasElectronicas
	 *****************************************************************/

	public FacturaElectronica adicionarFacturaElectronica (long numFactura, long id_Sucursal, long id_Cliente, long id_Venta) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlFacturaElectronica.adicionarFacturaElectronica(pm, id, numFactura, id_Sucursal, id_Cliente, id_Venta);
            tx.commit();
            
            log.trace ("Inserción de FacturaElectronica: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new FacturaElectronica (id, numFactura, id_Sucursal, id_Cliente, id_Venta);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarFacturaElectronicaPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlFacturaElectronica.eliminarFacturaElectronicaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public FacturaElectronica darFacturaElectronicaPorId (long id) {
		
		return sqlFacturaElectronica.darFacturaElectronicaPorId (pmf.getPersistenceManager(), id);
	}

	public List<FacturaElectronica> darFacturasElectronicas () {
		
		return sqlFacturaElectronica.darFacturasElectronicas (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las OrdenesPedidos
	 *****************************************************************/

	public OrdenPedido adicionarOrdenPedido (String fCompra, long vTotal, String estado, long id_Proveedor, long id_Sucursal) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOrdenPedido.adicionarOrdenPedido(pm, id, fCompra, vTotal, estado, id_Proveedor, id_Sucursal);
            tx.commit();
            
            log.trace ("Inserción de OrdenPedido: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OrdenPedido (id, fCompra, vTotal, estado, id_Proveedor, id_Sucursal);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarOrdenPedidoPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlOrdenPedido.eliminarOrdenPedidoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cambiarEstado (String estado, long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.cambiarEstado(pm, estado, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public OrdenPedido darOrdenPedidoPorId (long id) {
		
		return sqlOrdenPedido.darOrdenPedidoPorId (pmf.getPersistenceManager(), id);
	}

	public List<OrdenPedido> darOrdenesPedidos () {
		
		return sqlOrdenPedido.darOrdenesPedidos (pmf.getPersistenceManager());
	}
	
	public List<Object> darOrdenesPedidosPorSucursalYEstado (long id_Sucursal, String estado) {
		
		return sqlOrdenPedido.darOrdenesPedidosPorSucursalYEstado (pmf.getPersistenceManager(), id_Sucursal, estado);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las OrdenesPedidosProductos
	 *****************************************************************/

	public OrdenPedidoProducto adicionarOrdenPedidoProducto (long id_OrdenPedido, long id_Producto, long cantCompra, long pCompra) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlOrdenPedidoProducto.adicionarOrdenPedidoProducto(pm, id_OrdenPedido, id_Producto, cantCompra, pCompra);
            tx.commit();
            
            log.trace ("Inserción de OrdenPedidoProducto: " + id_OrdenPedido + id_Producto + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OrdenPedidoProducto (id_OrdenPedido, id_Producto, cantCompra, pCompra);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarOrdenPedidoProductoPorIdOrdenPedido (long id_OrdenPedido) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlOrdenPedidoProducto.eliminarOrdenPedidoProductoPorIdOrdenPedido(pm, id_OrdenPedido);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Object> darProductoPorIdOrdenPedido (long id_OrdenPedido) {
		
		return sqlOrdenPedidoProducto.darProductoPorIdOrdenPedido (pmf.getPersistenceManager(), id_OrdenPedido);
	}
	
	public List<Object> darCantCompraPorIdOrdenPedido (long id_OrdenPedido) {
		
		return sqlOrdenPedidoProducto.darCantCompraPorIdOrdenPedido (pmf.getPersistenceManager(), id_OrdenPedido);
	}

	public List<OrdenPedidoProducto> darOrdenesPedidosProductos () {
		
		return sqlOrdenPedidoProducto.darOrdenesPedidosProductos (pmf.getPersistenceManager());
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Productos
	 *****************************************************************/

	public Producto adicionarProducto (String codigoBarra, String nombre, String marca, int pVenta, String presentacion,
			int pUnidadMedida, int cantPPT, String unidadMedida, int espEmpPeso, int espEmpVol, String esPerecedero,
			String fVencimiento, int nReorden, int stockBodega, int stockEstante, int stockTotal, long id_TipoProducto) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long idLote = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto(pm, idLote, codigoBarra, nombre, marca, pVenta, presentacion,
            		pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,
            		fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto);
            tx.commit();
            
            log.trace ("Inserción de Producto: " + idLote + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto (idLote, codigoBarra, nombre, marca, pVenta, presentacion,
            		pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,
            		fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarProductoPorId (long idLote) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorId(pm, idLote);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cambiarStocks (int stock, long idLote)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.cambiarStocks(pm, stock, idLote);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Producto darProductoPorId (long idLote) {
		
		return sqlProducto.darProductoPorId (pmf.getPersistenceManager(), idLote);
	}
	
	public List<Producto> darProductoPorTipoProducto (long id_TipoProducto) {
		
		return sqlProducto.darProductoPorTipoProducto (pmf.getPersistenceManager(), id_TipoProducto);
	}
	
	public List<Object> darProductoPorNombre (String nombre) {
		
		return sqlProducto.darProductoPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<Producto> darProductos () {
		
		return sqlProducto.darProductos (pmf.getPersistenceManager());
	}
	
	public List<String> darNombreProductos () {
		
		return sqlProducto.darNombreProductos (pmf.getPersistenceManager());
	}
	
	public List<Object> darIdProductos () {
		
		return sqlProducto.darIdProductos (pmf.getPersistenceManager());
	}
	
	public List<Long> darIdProductoPorTipoProducto (long id_TipoProducto) {
		
		return sqlProducto.darIdProductoPorTipoProducto (pmf.getPersistenceManager(), id_TipoProducto);
	}
	
	public List<Integer> darSBProductoPorTipoProducto (long id_TipoProducto) {
		
		return sqlProducto.darSBProductoPorTipoProducto (pmf.getPersistenceManager(), id_TipoProducto);
	}
	
	public List<Integer> darSEProductoPorTipoProducto (long id_TipoProducto) {
		
		return sqlProducto.darSEProductoPorTipoProducto (pmf.getPersistenceManager(), id_TipoProducto);
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las Promociones
	 *****************************************************************/

	public Promocion adicionarPromocion (String nombre, String fInicio, String fFin, String descripcion, String tipoPromocion,
			int lleve, int pague, float descuento, int pVenta, long id_Sucursal) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, id, nombre, fInicio, fFin, descripcion, tipoPromocion,
            		lleve, pague, descuento, pVenta, id_Sucursal);
            tx.commit();
            
            log.trace ("Inserción de Promocion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Promocion (id, nombre, fInicio, fFin, descripcion, tipoPromocion,
            		lleve, pague, descuento, pVenta, id_Sucursal);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarPromocionPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlPromocion.eliminarPromocionPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Promocion darPromocionPorId (long id) {
		
		return sqlPromocion.darPromocionPorId (pmf.getPersistenceManager(), id);
	}

	public List<Promocion> darPromociones () {
		
		return sqlPromocion.darPromociones(pmf.getPersistenceManager());
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar las PromocionesProductos
	 *****************************************************************/

	public PromocionProducto adicionarPromocionProducto (long id_Promocion, long id_Producto, int stockInicial, int stockActual) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlPromocionProducto.adicionarPromocionProducto(pm, id_Promocion, id_Producto, stockInicial, stockActual);
            tx.commit();
            
            log.trace ("Inserción de PromocionProducto: " + id_Promocion + ", " + id_Producto + ", " + stockInicial + ", " + stockActual + ", " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionProducto (id_Promocion, id_Producto, stockInicial, stockActual);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarPromocionProductoPorIdPromocion (long id_Promocion) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlPromocionProducto.eliminarPromocionProductoPorIdPromocion(pm, id_Promocion);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public PromocionProducto darPromocionProductoPorIdPromocion (long id_Promocion) {
		
		return sqlPromocionProducto.darPromocionProductoPorIdPromocion (pmf.getPersistenceManager(), id_Promocion);
	}

	public List<PromocionProducto> darPromocionesProductos () {
		
		return sqlPromocionProducto.darPromocionesProductos (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Proveedores
	 *****************************************************************/

	public Proveedor adicionarProveedor (long nit, String nombre, int calificacion) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, id, nit, nombre, calificacion);
            tx.commit();
            
            log.trace ("Inserción de Proveedor: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedor (id, nit, nombre, calificacion);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarProveedorPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlProveedor.eliminarProveedorPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Proveedor darProveedorPorId (long id) {
		
		return sqlProveedor.darProveedorPorId (pmf.getPersistenceManager(), id);
	}

	public List<Proveedor> darProveedores () {
		
		return sqlProveedor.darProveedores (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las Sucursales
	 *****************************************************************/

	public Sucursal adicionarSucursal (String nombre, String pais, String ciudad, String direccion, long id_Supermercado) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, id, nombre, pais, ciudad, direccion, id_Supermercado);
            tx.commit();
            
            log.trace ("Inserción de Sucursal: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursal (id, nombre, pais, ciudad, direccion, id_Supermercado);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarSucursalPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Sucursal darSucursalPorId (long id) {
		
		return sqlSucursal.darSucursalPorId (pmf.getPersistenceManager(), id);
	}

	public Sucursal darIdPorSucursal (String nombre) {
		
		return sqlSucursal.darIdPorSucursal (pmf.getPersistenceManager(), nombre);
	}
	
	public List<Sucursal> darSucursales () {
		
		return sqlSucursal.darSucursales (pmf.getPersistenceManager());
	}
	
	public List<Object> darNombreSucursales () {
		
		return sqlSucursal.darNombreSucursales (pmf.getPersistenceManager());
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los Supermercados
	 *****************************************************************/

	public Supermercado adicionarSupermercado (long nit, String nombre) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSupermercado.adicionarSupermercado(pm, id, nit, nombre);
            tx.commit();
            
            log.trace ("Inserción de Supermercado: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Supermercado (id, nit, nombre);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarSupermercadoPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlSupermercado.eliminarSupermercadoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Supermercado darSupermercadoPorId (long id) {
		
		return sqlSupermercado.darSupermercadoPorId (pmf.getPersistenceManager(), id);
	}

	public List<Supermercado> darSupermercados () {
		
		return sqlSupermercado.darSupermercados (pmf.getPersistenceManager());
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los TiposProductos
	 *****************************************************************/

	public TipoProducto adicionarTipoProducto (String nombre, String tipoAlmacen, String categoria, String subCategoria) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlTipoProducto.adicionarTipoProducto(pm, id, nombre, tipoAlmacen, categoria, subCategoria);
            tx.commit();
            
            log.trace ("Inserción de TipoProducto: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoProducto (id, nombre, tipoAlmacen, categoria, subCategoria);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarTipoProductoPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlTipoProducto.eliminarTipoProductoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public TipoProducto darTipoProductoPorId (long id) {
		
		return sqlTipoProducto.darTipoProductoPorId (pmf.getPersistenceManager(), id);
	}
	
	public TipoProducto darIdPorTipoProducto (String nombre) {
		
		return sqlTipoProducto.darIdPorTipoProducto (pmf.getPersistenceManager(), nombre);
	}

	public List<TipoProducto> darTiposProductos () {
		
		return sqlTipoProducto.darTiposProductos (pmf.getPersistenceManager());
	}

	public List<Object> darNombreTiposProductos () {
		
		return sqlTipoProducto.darNombreTiposProductos (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TiposUsuarios
	 *****************************************************************/

	public TipoUsuario adicionarTipoUsuario (String tipo) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlTipoUsuario.adicionarTipoUsuario(pm, id, tipo);
            tx.commit();
            
            log.trace ("Inserción de TipoUsuario: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoUsuario (id, tipo);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarTipoUsuarioPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlTipoUsuario.eliminarTipoUsuarioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public TipoUsuario darTipoUsuarioPorId (long id) {
		
		return sqlTipoUsuario.darTipoUsuarioPorId (pmf.getPersistenceManager(), id);
	}
	
	public TipoUsuario darIdPorTipoUsuario (String tipo) {
		
		return sqlTipoUsuario.darIdPorTipoUsuario (pmf.getPersistenceManager(), tipo);
	}

	public List<TipoUsuario> darTiposUsuario () {
		
		return sqlTipoUsuario.darTiposUsuario (pmf.getPersistenceManager());
	}
	
	public List<Object> darNombreTiposUsuario () {
		
		return sqlTipoUsuario.darNombreTiposUsuario (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Usuarios
	 *****************************************************************/

	public Usuario adicionarUsuario (long nDocumento, String tipoDocumento, String nombre, String correo, String pais, String ciudad, 
			String direccion, Integer puntos, long id_TipoUsuario, Long id_Sucursal) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal);
            tx.commit();
            
            log.trace ("Inserción de Usuario: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario (id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarUsuarioPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Usuario darUsuarioPorId (long id) {
		
		return sqlUsuario.darUsuarioPorId (pmf.getPersistenceManager(), id);
	}

	public Usuario darIdPorUsuario (String nombre) {
		
		return sqlUsuario.darIdPorUsuario (pmf.getPersistenceManager(), nombre);
	}
	
	public List<Usuario> darUsuarios () {
		
		return sqlUsuario.darUsuarios (pmf.getPersistenceManager());
	}
	
	public List<Object> darNombreUsuarios () {
		
		return sqlUsuario.darNombreUsuarios (pmf.getPersistenceManager());
	}
	
	public List<Object> darIdSucursalUsuarioConDocumentoIdTipoUsuario (long nDocumento, long id_TipoUsuario) {
		
		return sqlUsuario.darIdSucursalUsuarioConDocumentoIdTipoUsuario (pmf.getPersistenceManager(), nDocumento, id_TipoUsuario);
	}
	
	public List<Object> darNombreUsuarioConDocumentoIdTipoUsuario (long nDocumento, long id_TipoUsuario) {
		
		return sqlUsuario.darNombreUsuarioConDocumentoIdTipoUsuario (pmf.getPersistenceManager(), nDocumento, id_TipoUsuario);
	}
	
		
	/* ****************************************************************
	 * 			Métodos para manejar las Ventas
	 *****************************************************************/

	public Venta adicionarVenta (Date fVenta, int pTotal, long id_Sucursal, long id_Cajero) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlVenta.adicionarVenta(pm, id, fVenta, pTotal, id_Sucursal, id_Cajero);
            tx.commit();
            
            log.trace ("Inserción de Venta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Venta (id, fVenta, pTotal, id_Sucursal, id_Cajero);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarVentaPorId (long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlVenta.eliminarVentaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Venta darVentaPorId (long id) {
		
		return sqlVenta.darVentaPorId (pmf.getPersistenceManager(), id);
	}

	public List<Venta> darVentas () {
		
		return sqlVenta.darVentas (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las CarritoCompra
	 *****************************************************************/

	public CarritoCompra adicionarCarritoCompra (long id_Venta, long id_Producto, int pVentaH, int cantidad) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlCarritoCompra.adicionarCarritoCompra(pm, id_Venta, id_Producto, pVentaH, cantidad);
            tx.commit();
            
            log.trace ("Inserción de VentaProducto: " + id_Venta + id_Producto + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new CarritoCompra (id_Venta, id_Producto, pVentaH, cantidad);
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarCarritoCompraPorIdVenta (long id_Venta) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlCarritoCompra.eliminarCarritoCompraPorIdVenta(pm, id_Venta);
            tx.commit();
            return resp;
        }
        catch (Exception e) {
        	
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally {
        	
            if (tx.isActive()) {
            	
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public CarritoCompra darCarritoCompraPorIdVenta (long id_Venta) {
		
		return sqlCarritoCompra.darCarritoCompraPorIdVenta (pmf.getPersistenceManager(), id_Venta);
	}

	public List<CarritoCompra> darCarritosCompras () {
		
		return sqlCarritoCompra.darCarritosCompras (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las consultas
	 *****************************************************************/
	
	public List<Object[]> darSucursalesReq1 () {
		
		return sqlSucursal.darSucursalesReq1(pmf.getPersistenceManager());
	}
	
	public List<Object[]> darPromocionesReq1 () {
		return sqlPromocion.darPromocionesReq1(pmf.getPersistenceManager());
	}
	
	public List<Object[]> darBodegasReq3 (long id_sucursal) {
		return sqlBodega.darBodegasReq3(pmf.getPersistenceManager(), id_sucursal);
	}
	
	public List<Object[]> darEstantesReq3 (long id_sucursal) {
		return sqlEstante.darEstantesReq3(pmf.getPersistenceManager(), id_sucursal);
	}
	
	/* ****************************************************************
	 * 			  Fin métodos para manejadores
	 *****************************************************************/
	
	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}	
 }