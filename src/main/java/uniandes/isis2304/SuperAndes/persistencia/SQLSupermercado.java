package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Supermercado;

class SQLSupermercado {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLSupermercado (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarSupermercado (PersistenceManager pm, long id, long nit, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSupermercado () + "(id, nit, nombre) values (?, ?, ?)");
        q.setParameters(id, nit, nombre);
        return (long) q.executeUnique();
	}

	public long eliminarSupermercadoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercado () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Supermercado darSupermercadoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSupermercado () + " WHERE id = ?");
		q.setResultClass(Supermercado.class);
		q.setParameters(id);
		return (Supermercado) q.executeUnique();
	}

	public List<Supermercado> darSupermercados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSupermercado ());
		q.setResultClass(Supermercado.class);
		return (List<Supermercado>) q.executeList();
	}
}