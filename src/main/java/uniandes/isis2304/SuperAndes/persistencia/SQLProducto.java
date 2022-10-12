package uniandes.isis2304.SuperAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Producto;

class SQLProducto {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProducto (PersistenceManager pm, long idLote, String codigoBarra, String nombre, String marca, int pVenta, String presentacion,
			int pUnidadMedida, int cantPPT, String unidadMedida, int espEmpPeso, int espEmpVol, boolean esPerecedero,
			Date fVencimiento, int nReorden, int stockBodega, int stockProducto, int stockTotal, long id_TipoProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + "(idLote, codigoBarra, nombre, marca, pVenta, presentacion,\r\n"
        		+ "pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,\r\n"
        		+ "fVencimiento, nReorden, stockBodega, stockProducto, stockTotal, id_TipoProducto) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idLote, codigoBarra, nombre, marca, pVenta, presentacion,
        		pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,
        		fVencimiento, nReorden, stockBodega, stockProducto, stockTotal, id_TipoProducto);
        return (long) q.executeUnique();
	}

	public long eliminarProductoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Producto darProductoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
		q.setResultClass(Producto.class);
		q.setParameters(id);
		return (Producto) q.executeUnique();
	}

	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto ());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
}