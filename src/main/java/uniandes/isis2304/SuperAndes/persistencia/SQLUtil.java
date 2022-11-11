/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: SuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.SuperAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
        Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega());
        Query qCarritoCompra = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompra());
        Query qCarritoCompraProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompraProducto());
        Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante());
        Query qFacturaElectronica = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturaElectronica());
        Query qOrdenPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido());
        Query qOrdenPedidoProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedidoProducto());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());
        Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion());
        Query qPromocionProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionProducto());
        Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor());
        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal());
        Query qSupermercado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercado());
        Query qTipoProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoProducto());
        Query qTipoUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoUsuario());
        Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());
        Query qVenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta());
        
        long bodegaEliminados = (long) qBodega.executeUnique ();
        long carritoCompraEliminados = (long) qCarritoCompra.executeUnique ();
        long carritoCompraProductoEliminados = (long) qCarritoCompraProducto.executeUnique ();
        long estanteEliminados = (long) qEstante.executeUnique ();
        long facturaElectronicaEliminadas = (long) qFacturaElectronica.executeUnique ();
        long ordenPedidoEliminadas = (long) qOrdenPedido.executeUnique ();
        long ordenPedidoProductoEliminados = (long) qOrdenPedidoProducto.executeUnique ();
        long productoEliminados = (long) qProducto.executeUnique ();
        long promocionEliminados = (long) qPromocion.executeUnique ();
        long promocionProductoEliminados = (long) qPromocionProducto.executeUnique ();
        long proveedorEliminados = (long) qProveedor.executeUnique ();
        long sucursalEliminados = (long) qSucursal.executeUnique ();
        long supermercadoEliminados = (long) qSupermercado.executeUnique ();
        long tipoProductoEliminados = (long) qTipoProducto.executeUnique ();
        long tipoUsuarioEliminados = (long) qTipoUsuario.executeUnique ();
        long usuarioEliminados = (long) qUsuario.executeUnique ();
        long ventaEliminados = (long) qVenta.executeUnique ();
        return new long[] {bodegaEliminados, carritoCompraEliminados, carritoCompraProductoEliminados, estanteEliminados, facturaElectronicaEliminadas, 
        		ordenPedidoEliminadas,ordenPedidoProductoEliminados, productoEliminados, promocionEliminados, promocionProductoEliminados, 
        		proveedorEliminados, sucursalEliminados, supermercadoEliminados, tipoProductoEliminados, tipoUsuarioEliminados, 
        		usuarioEliminados, ventaEliminados};
	}

}
