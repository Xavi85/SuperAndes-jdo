package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.OrdenPedidoProducto;

class SQLOrdenPedidoProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLOrdenPedidoProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarOrdenPedidoProducto (PersistenceManager pm, long id_OrdenPedido, long id_Producto, long cantCompra, long pCompra) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrdenPedidoProducto () + "(id_OrdenPedido, id_Producto, cantCompra, pCompra) values (?, ?, ?, ?)");
        q.setParameters(id_OrdenPedido, id_Producto, cantCompra, pCompra);
        return (long) q.executeUnique();
	}

	public long eliminarOrdenPedidoProductoPorIdOrdenPedido (PersistenceManager pm, long id_OrdenPedido)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedidoProducto () + " WHERE id_OrdenPedido = ?");
        q.setParameters(id_OrdenPedido);
        return (long) q.executeUnique();
	}

	public List<Object> darProductoPorIdOrdenPedido (PersistenceManager pm, long id_OrdenPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT id_Producto FROM " + pp.darTablaOrdenPedidoProducto () + " WHERE id_OrdenPedido = ?");
		q.setParameters(id_OrdenPedido);
		return q.executeList();
	}
	
	public List<Object> darCantCompraPorIdOrdenPedido (PersistenceManager pm, long id_OrdenPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT cantCompra FROM " + pp.darTablaOrdenPedidoProducto () + " WHERE id_OrdenPedido = ?");
		q.setParameters(id_OrdenPedido);
		return q.executeList();
	}

	public List<OrdenPedidoProducto> darOrdenesPedidosProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedidoProducto ());
		q.setResultClass(OrdenPedidoProducto.class);
		return (List<OrdenPedidoProducto>) q.executeList();
	}
}