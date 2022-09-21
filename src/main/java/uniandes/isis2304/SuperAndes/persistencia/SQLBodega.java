package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Bodega;

class SQLBodega {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarBodega (PersistenceManager pm, long id, int volMax, int pesoMax, String tipoAlmacen, long id_Sucursal, long id_TipoProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega () + "(id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
        return (long) q.executeUnique();
	}

	public long eliminarBodegaPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Bodega darBodegaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega () + " WHERE id = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(id);
		return (Bodega) q.executeUnique();
	}

	public List<Bodega> darBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega ());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
}