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
}