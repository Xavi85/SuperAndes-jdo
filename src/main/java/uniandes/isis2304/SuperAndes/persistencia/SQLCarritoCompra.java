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

	public long adicionarCarritoCompra (PersistenceManager pm, long id, long id_Cliente, long id_Sucursal, String fCarrito, String estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoCompra () + "(id, id_Cliente, id_Sucursal, fCarrito, estado) values (?, ?, ?, TO_DATE(?, 'dd/MM/yyyy'), ?)");
        q.setParameters(id, id_Cliente, id_Sucursal, fCarrito, estado);
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
		return q.executeList();
	}
	
	public List<Object> darCarritosComprasPorSucursalYEstado (PersistenceManager pm, long id_Sucursal, String estado) 
	{
		Query q = pm.newQuery(SQL, "SELECT id FROM " + pp.darTablaCarritoCompra () + " WHERE id_Sucursal = ? AND estado = ?");
	    q.setParameters(id_Sucursal, estado);
		return q.executeList();
	}
	
	public long actualizarEstadoCarrito (PersistenceManager pm, long id, String estado) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarritoCompra () + " SET estado = ? WHERE id = ?");
	     q.setParameters(estado, id);
	     return (long) q.executeUnique();            
	}
}