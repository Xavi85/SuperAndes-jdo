package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.VentaProducto;

class SQLVentaProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLVentaProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarVentaProducto (PersistenceManager pm, long id_Venta, long id_Producto, int pVentaH, int cantidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVentaProducto () + "(id_Venta, id_Producto, pVentaH, cantidad) values (?, ?, ?, ?)");
        q.setParameters(id_Venta, id_Producto, pVentaH, cantidad);
        return (long) q.executeUnique();
	}

	public long eliminarVentaProductoPorIdVenta (PersistenceManager pm, long id_Venta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVentaProducto () + " WHERE id_Venta = ?");
        q.setParameters(id_Venta);
        return (long) q.executeUnique();
	}

	public VentaProducto darVentaProductoPorIdVenta (PersistenceManager pm, long id_Venta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVentaProducto () + " WHERE id_Venta = ?");
		q.setResultClass(VentaProducto.class);
		q.setParameters(id_Venta);
		return (VentaProducto) q.executeUnique();
	}

	public List<VentaProducto> darVentasProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVentaProducto ());
		q.setResultClass(VentaProducto.class);
		return (List<VentaProducto>) q.executeList();
	}
}