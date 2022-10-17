package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Promocion;

class SQLPromocion {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarPromocion (PersistenceManager pm, long id, String nombre, String fInicio, String fFin, String descripcion, String tipoPromocion,
			int lleve, int pague, float descuento, int pVenta, long id_Sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocion () + "(id, nombre, fInicio, fFin, descripcion, tipoPromocion,\r\n"
        		+ "lleve, pague, descuento, pVenta, id_Sucursal) values (?, ?, TO_DATE(?, 'dd/MM/yyyy'), TO_DATE(?, 'dd/MM/yyyy'), ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, fInicio, fFin, descripcion, tipoPromocion, lleve, pague, descuento, pVenta, id_Sucursal);
        return (long) q.executeUnique();
	}

	public long eliminarPromocionPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Promocion darPromocionPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion () + " WHERE id = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(id);
		return (Promocion) q.executeUnique();
	}

	public List<Promocion> darPromociones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion ());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
}