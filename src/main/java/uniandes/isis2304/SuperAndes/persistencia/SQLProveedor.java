package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Proveedor;

class SQLProveedor {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProveedor (PersistenceManager pm, long id, long nit, String nombre, int calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor () + "(id, nit, nombre, calificacion) values (?, ?, ?, ?)");
        q.setParameters(id, nit, nombre, calificacion);
        return (long) q.executeUnique();
	}

	public long eliminarProveedorPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Proveedor darProveedorPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE id = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(id);
		return (Proveedor) q.executeUnique();
	}

	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor ());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}
}