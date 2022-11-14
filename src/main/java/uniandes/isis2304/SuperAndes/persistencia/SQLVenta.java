package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Venta;

class SQLVenta {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLVenta (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarVenta (PersistenceManager pm, long id, String fVenta, int pTotal, long id_Sucursal, long id_Cajero, long id_Cliente, long id_CarritoCompra) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVenta () + "(id, fVenta, pTotal, id_Sucursal, id_Cajero, id_Cliente, id_CarritoCompra) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fVenta, pTotal, id_Sucursal, id_Cajero, id_Cliente, id_CarritoCompra);
        return (long) q.executeUnique();
	}

	public long eliminarVentaPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Venta darVentaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta () + " WHERE id = ?");
		q.setResultClass(Venta.class);
		q.setParameters(id);
		return (Venta) q.executeUnique();
	}

	public List<Venta> darVentas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta ());
		q.setResultClass(Venta.class);
		return (List<Venta>) q.executeList();
	}
}