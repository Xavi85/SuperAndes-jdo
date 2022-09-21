package uniandes.isis2304.SuperAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.OrdenPedido;

class SQLOrdenPedido {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLOrdenPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarOrdenPedido (PersistenceManager pm, long id, Date fCompra, int vTotal, long id_Proveedor, long id_Sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrdenPedido () + "(id, fCompra, vTotal, id_Proveedor, id_Sucursal) values (?, ?, ?, ?, ?)");
        q.setParameters(id, fCompra, vTotal, id_Proveedor, id_Sucursal);
        return (long) q.executeUnique();
	}

	public long eliminarOrdenPedidoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public OrdenPedido darOrdenPedidoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido () + " WHERE id = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(id);
		return (OrdenPedido) q.executeUnique();
	}

	public List<OrdenPedido> darOrdenesPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido ());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}
}