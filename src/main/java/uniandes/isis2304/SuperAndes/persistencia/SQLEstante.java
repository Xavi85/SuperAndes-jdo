package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Estante;

class SQLEstante {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarEstante (PersistenceManager pm, long id, int volMax, int pesoMax, String tipoAlmacen, int nAbastecimiento, long id_Sucursal, long id_TipoProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante () + "(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, volMax, pesoMax, tipoAlmacen, nAbastecimiento, id_Sucursal, id_TipoProducto);
        return (long) q.executeUnique();
	}

	public long eliminarEstantePorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Estante darEstantePorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante () + " WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(id);
		return (Estante) q.executeUnique();
	}

	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	
	public List<Object[]> darEstantesReq3 (PersistenceManager pm, long id_sucursal)
	{
		Query q = pm.newQuery(SQL, "select estante.id, estante.pesomax, estante.volmax, estante.tipoalmacen, 100*stocks.volestante/estante.volmax porcentaje_vol, 100*stocks.pesoestante/estante.pesomax porcentaje_peso "
				+ "from estante "
				+ "inner join ( "
				+ "select id_tipoproducto, sum(stockestante * espemppeso) pesoestante, sum(stockestante * espempvol) volestante "
				+ "from producto "
				+ "group by id_tipoproducto) stocks "
				+ "on estante.id_tipoproducto = stocks.id_tipoproducto "
				+ "where estante.id_sucursal = ?");
		q.setParameters(id_sucursal);
		return q.executeList();
	}
}