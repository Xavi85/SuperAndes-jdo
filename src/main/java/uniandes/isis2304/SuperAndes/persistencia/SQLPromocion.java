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
	
	public List<Object[]> darPromocionesReq1 (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, 
			"SELECT * FROM ( "
				+ " SELECT " + pp.darTablaPromocion() + ".*, cantidad_promociones FROM ("
					+ " SELECT " + pp.darTablaPromocionProducto() + ".id_promocion, count(*) cantidad_promociones FROM VENTA"
					+ " INNER JOIN " + pp.darTablaCarritoCompra() + " ON " + pp.darTablaVenta() + ".id = " + pp.darTablaCarritoCompra() + ".id_venta"
					+ " INNER JOIN " + pp.darTablaPromocionProducto() + " ON " + pp.darTablaCarritoCompra() + ".id_producto = " + pp.darTablaPromocionProducto() + ".id_producto"
					+ " GROUP BY " + pp.darTablaPromocionProducto() + ".id_promocion) promociones"
				+ " INNER JOIN " + pp.darTablaPromocion() + " ON promociones.id_promocion = " + pp.darTablaPromocion() + ".id"
				+ " ORDER BY cantidad_promociones DESC) "
			+ " WHERE ROWNUM <= 10"  
			);
		//q.setResultClass(Promocion.class);
		return q.executeList();
	}	
}