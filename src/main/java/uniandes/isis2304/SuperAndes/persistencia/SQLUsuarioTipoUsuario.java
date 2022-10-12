package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.UsuarioTipoUsuario;

class SQLUsuarioTipoUsuario {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLUsuarioTipoUsuario (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarUsuarioTipoUsuario (PersistenceManager pm, long id_Usuario, long id_TipoUsuario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarioTipoUsuario () + "(id_Usuario, id_TipoUsuario) values (?, ?)");
        q.setParameters(id_Usuario, id_TipoUsuario);
        return (long) q.executeUnique();
	}

	public long eliminarUsuarioTipoUsuarioPorIdUsuarioYTipoUsuario (PersistenceManager pm, long id_Usuario, long id_TipoUsuario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioTipoUsuario () + " WHERE id_Usuario = ? AND id_TipoUsuario = ?");
        q.setParameters(id_Usuario, id_TipoUsuario);
        return (long) q.executeUnique();
	}

	public UsuarioTipoUsuario darUsuarioTipoUsuarioPorIdUsuario (PersistenceManager pm, long id_Usuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioTipoUsuario () + " WHERE id = ?");
		q.setResultClass(UsuarioTipoUsuario.class);
		q.setParameters(id_Usuario);
		return (UsuarioTipoUsuario) q.executeUnique();
	}

	public List<UsuarioTipoUsuario> darUsuariosTiposUsuario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioTipoUsuario ());
		q.setResultClass(UsuarioTipoUsuario.class);
		return (List<UsuarioTipoUsuario>) q.executeList();
	}
}