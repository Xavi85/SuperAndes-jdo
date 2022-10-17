package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.PromocionProducto;

class SQLPromocionProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLPromocionProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarPromocionProducto (PersistenceManager pm, long id_Promocion, long id_Producto, int stockInicial, int stockActual) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionProducto () + "(id_Promocion, id_Producto, stockInicial, stockActual) values (?, ?, ?, ?)");
        q.setParameters(id_Promocion, id_Producto, stockInicial, stockActual);
        return (long) q.executeUnique();
	}

	public long eliminarPromocionProductoPorIdPromocion (PersistenceManager pm, long id_Promocion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionProducto () + " WHERE id_Promocion = ?");
        q.setParameters(id_Promocion);
        return (long) q.executeUnique();
	}

	public PromocionProducto darPromocionProductoPorIdPromocion (PersistenceManager pm, long id_Promocion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionProducto () + " WHERE id_Promocion = ?");
		q.setResultClass(PromocionProducto.class);
		q.setParameters(id_Promocion);
		return (PromocionProducto) q.executeUnique();
	}

	public List<PromocionProducto> darPromocionesProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionProducto ());
		q.setResultClass(PromocionProducto.class);
		return (List<PromocionProducto>) q.executeList();
	}
}