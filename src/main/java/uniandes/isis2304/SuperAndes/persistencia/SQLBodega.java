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
	
	public List<Object[]> darBodegasReq3 (PersistenceManager pm, long id_sucursal)
	{
		Query q = pm.newQuery(SQL, "select bodega.id, bodega.pesomax, bodega.volmax, bodega.tipoalmacen, 100*stocks.volbodega/bodega.volmax porcentaje_vol, 100*stocks.pesobodega/bodega.pesomax porcentaje_peso "
				+ "from bodega "
				+ "inner join ( "
				+ "select id_tipoproducto, sum(stockbodega * espemppeso) pesobodega, sum(stockbodega * espempvol) volbodega "
				+ "from producto "
				+ "group by id_tipoproducto) stocks "
				+ "on bodega.id_tipoproducto = stocks.id_tipoproducto "
				+ "where bodega.id_sucursal = ? ");
		q.setParameters(id_sucursal);
		return q.executeList();
	}
}