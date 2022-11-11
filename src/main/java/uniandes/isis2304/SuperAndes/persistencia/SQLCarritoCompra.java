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

	public long adicionarCarritoCompra (PersistenceManager pm, long id, long id_Cliente, String fCarrito, String estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoCompra () + "(id, id_Cliente, fCarrito, estado) values (?, ?, ?, ?)");
        q.setParameters(id, id_Cliente, fCarrito, estado);
        return (long) q.executeUnique();
	}

	public long eliminarCarritoCompraPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompra () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public CarritoCompra darCarritoCompraPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompra () + " WHERE id = ?");
		q.setResultClass(CarritoCompra.class);
		q.setParameters(id);
		return (CarritoCompra) q.executeUnique();
	}

	public List<CarritoCompra> darCarritosCompras (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompra ());
		q.setResultClass(CarritoCompra.class);
		return (List<CarritoCompra>) q.executeList();
	}
}