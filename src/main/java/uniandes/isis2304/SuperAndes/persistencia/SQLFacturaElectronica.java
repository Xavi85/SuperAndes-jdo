package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.FacturaElectronica;

class SQLFacturaElectronica {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLFacturaElectronica (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarFacturaElectronica (PersistenceManager pm, long id, long numFactura, long id_Sucursal, long id_Cliente, long id_Venta) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFacturaElectronica () + "(id, numFactura, id_Sucursal, id_Cliente, id_Venta) values (?, ?, ?, ?, ?)");
        q.setParameters(id, numFactura, id_Sucursal, id_Cliente, id_Venta);
        return (long) q.executeUnique();
	}

	public long eliminarFacturaElectronicaPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturaElectronica () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public FacturaElectronica darFacturaElectronicaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturaElectronica () + " WHERE id = ?");
		q.setResultClass(FacturaElectronica.class);
		q.setParameters(id);
		return (FacturaElectronica) q.executeUnique();
	}

	public List<FacturaElectronica> darFacturasElectronicas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturaElectronica ());
		q.setResultClass(FacturaElectronica.class);
		return (List<FacturaElectronica>) q.executeList();
	}
}