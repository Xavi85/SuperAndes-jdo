package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.TipoProducto;

class SQLTipoProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLTipoProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarTipoProducto (PersistenceManager pm, long id, String nombre, String tipoAlmacen, String categoria, String subCategoria) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoProducto () + "(id, nombre, tipoAlmacen, categoria, subCategoria) values (?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, tipoAlmacen, categoria, subCategoria);
        return (long) q.executeUnique();
	}

	public long eliminarTipoProductoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoProducto () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public TipoProducto darTipoProductoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto () + " WHERE id = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(id);
		return (TipoProducto) q.executeUnique();
	}
	
	public TipoProducto darIdPorTipoProducto (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto () + " WHERE nombre = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(nombre);
		return (TipoProducto) q.executeUnique();
	}

	public List<TipoProducto> darTiposProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto ());
		q.setResultClass(TipoProducto.class);
		return (List<TipoProducto>) q.executeList();
	}
	
	public List<Object> darNombreTiposProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaTipoProducto ());
		return q.executeList();
	}
}