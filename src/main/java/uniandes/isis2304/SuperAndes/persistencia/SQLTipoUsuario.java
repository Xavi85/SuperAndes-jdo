package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.TipoUsuario;

class SQLTipoUsuario {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLTipoUsuario (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarTipoUsuario (PersistenceManager pm, long id, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoUsuario () + "(id, tipo) values (?, ?)");
        q.setParameters(id, tipo);
        return (long) q.executeUnique();
	}

	public long eliminarTipoUsuarioPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoUsuario () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public TipoUsuario darTipoUsuarioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoUsuario () + " WHERE id = ?");
		q.setResultClass(TipoUsuario.class);
		q.setParameters(id);
		return (TipoUsuario) q.executeUnique();
	}

	public List<TipoUsuario> darTiposUsuario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoUsuario ());
		q.setResultClass(TipoUsuario.class);
		return (List<TipoUsuario>) q.executeList();
	}
}