package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Sucursal;

class SQLSucursal {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarSucursal (PersistenceManager pm, long id, String nombre, String pais, String ciudad, String direccion, long id_Supermercado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal () + "(id, nombre, pais, ciudad, direccion, id_Supermercado) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, pais, ciudad, direccion, id_Supermercado);
        return (long) q.executeUnique();
	}

	public long eliminarSucursalPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Sucursal darSucursalPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		return (Sucursal) q.executeUnique();
	}

	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal ());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
}