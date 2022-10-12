package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Sucursal;
import uniandes.isis2304.SuperAndes.negocio.Usuario;

class SQLUsuario {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLUsuario (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarUsuario (PersistenceManager pm, long id, long nDocumento, String tipoDocumento, String nombre, String correo, String pais,
			String ciudad, String direccion, int puntos, long id_TipoUsuario, long id_Sucursal, long id_Supermercado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + "(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal, id_Supermercado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nDocumento, tipoDocumento, nombre, correo, pais, ciudad, direccion, puntos, id_TipoUsuario, id_Sucursal, id_Supermercado);
        return (long) q.executeUnique();
	}

	public long eliminarUsuarioPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Usuario darUsuarioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		return (Usuario) q.executeUnique();
	}
	
	public Usuario darIdPorUsuario (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario () + " WHERE nombre = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(nombre);
		return (Usuario) q.executeUnique();
	}

	public List<Usuario> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario ());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	public List<Object> darNombreUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaUsuario ());
		return q.executeList();
	}
}