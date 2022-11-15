package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.CarritoCompraProducto;

class SQLCarritoCompraProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLCarritoCompraProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarCarritoCompraProducto (PersistenceManager pm, long id_CarritoCompra, long id_Producto, int pVentaH, int cantidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoCompraProducto () + "(id_CarritoCompra, id_Producto, pVentaH, cantidad) values (?, ?, ?, ?)");
        q.setParameters(id_CarritoCompra, id_Producto, pVentaH, cantidad);
        return (long) q.executeUnique();
	}

	public List<CarritoCompraProducto> darCarritosComprasProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompraProducto ());
		q.setResultClass(CarritoCompraProducto.class);
		return (List<CarritoCompraProducto>) q.executeList();
	}
	
	public long eliminarCarritoCompraProductoPorIds (PersistenceManager pm, long id_CarritoCompra, long id_Producto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompraProducto () + " WHERE id_CarritoCompra = ? AND id_Producto = ?");
        q.setParameters(id_CarritoCompra, id_Producto);
        return (long) q.executeUnique();
	}
	
	public List<Object> darProdPorIdCarrito (PersistenceManager pm, long id_CarritoCompra)
	{
		Query q = pm.newQuery(SQL, "SELECT id_Producto FROM " + pp.darTablaCarritoCompraProducto () + " WHERE id_CarritoCompra = ?");
        q.setParameters(id_CarritoCompra);
		return q.executeList();
	}
	
	public List<Object> darCantPorIdCarrito (PersistenceManager pm, long id_CarritoCompra)
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM " + pp.darTablaCarritoCompraProducto () + " WHERE id_CarritoCompra = ?");
        q.setParameters(id_CarritoCompra);
		return q.executeList();
	}
}