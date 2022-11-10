package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.CarritoCompra;

class SQLCarritoCompra {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLCarritoCompra (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarCarritoCompra (PersistenceManager pm, long id_Venta, long id_Producto, int pVentaH, int cantidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoCompra () + "(id_Venta, id_Producto, pVentaH, cantidad) values (?, ?, ?, ?)");
        q.setParameters(id_Venta, id_Producto, pVentaH, cantidad);
        return (long) q.executeUnique();
	}

	public long eliminarCarritoCompraPorIdVenta (PersistenceManager pm, long id_Venta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompra () + " WHERE id_Venta = ?");
        q.setParameters(id_Venta);
        return (long) q.executeUnique();
	}

	public CarritoCompra darCarritoCompraPorIdVenta (PersistenceManager pm, long id_Venta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompra () + " WHERE id_Venta = ?");
		q.setResultClass(CarritoCompra.class);
		q.setParameters(id_Venta);
		return (CarritoCompra) q.executeUnique();
	}

	public List<CarritoCompra> darCarritosCompras (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompra ());
		q.setResultClass(CarritoCompra.class);
		return (List<CarritoCompra>) q.executeList();
	}
}