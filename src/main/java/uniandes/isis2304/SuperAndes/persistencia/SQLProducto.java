package uniandes.isis2304.SuperAndes.persistencia;

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
			int pUnidadMedida, int cantPPT, String unidadMedida, int espEmpPeso, int espEmpVol, String esPerecedero,
			String fVencimiento, int nReorden, int stockBodega, int stockEstante, int stockTotal, long id_TipoProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + "(idLote, codigoBarra, nombre, marca, pVenta, presentacion,\r\n"
        		+ "pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,\r\n"
        		+ "fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'dd/MM/yyyy'), ?, ?, ?, ?, ?)");
        q.setParameters(idLote, codigoBarra, nombre, marca, pVenta, presentacion,
        		pUnidadMedida, cantPPT, unidadMedida, espEmpPeso, espEmpVol, esPerecedero,
        		fVencimiento, nReorden, stockBodega, stockEstante, stockTotal, id_TipoProducto);
        return (long) q.executeUnique();
	}

	public long actualizarStockBodega (PersistenceManager pm, int stock, long idLote) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProducto () + " SET stockBodega = stockBodega + ?, stockTotal = stockTotal + ? WHERE idLote = ?");
	     q.setParameters(stock, stock, idLote);
	     return (long) q.executeUnique();            
	}
	
	public long actualizarStockEstante (PersistenceManager pm, int stock, long idLote) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProducto () + " SET stockEstante = stockEstante + ? WHERE idLote = ?");
	     q.setParameters(stock, idLote);
	     return (long) q.executeUnique();            
	}
	
	public long actualizarStockTotal (PersistenceManager pm, int stock, long idLote) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProducto () + " SET stockTotal = stockTotal + ? WHERE idLote = ?");
	     q.setParameters(stock, idLote);
	     return (long) q.executeUnique();            
	}

	public long eliminarProductoPorId (PersistenceManager pm, long idLote)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
        q.setParameters(idLote);
        return (long) q.executeUnique();
	}

	public Producto darProductoPorId (PersistenceManager pm, long idLote) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
		q.setResultClass(Producto.class);
		q.setParameters(idLote);
		return (Producto) q.executeUnique();
	}
	
	public List<Producto> darProductoPorTipoProducto (PersistenceManager pm, long id_TipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE id_TipoProducto = ?");
		q.setResultClass(Producto.class);
		q.setParameters(id_TipoProducto);
		return q.executeList();
	}
	
	public List<Object> darProductoPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT idLote FROM " + pp.darTablaProducto () + " WHERE nombre = ?");
		q.setParameters(nombre);
		return q.executeList();
	}
	
	public List<Object> darPrecioPorId (PersistenceManager pm, long idLote) 
	{
		Query q = pm.newQuery(SQL, "SELECT pVenta FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
		q.setParameters(idLote);
		return q.executeList();
	}
	
	public List<Object> darNombrePorId (PersistenceManager pm, long idLote) 
	{
		Query q = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaProducto () + " WHERE idLote = ?");
		q.setParameters(idLote);
		return q.executeList();
	}

	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto ());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	
	public List<String> darNombreProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaProducto ());
		return (List<String>) q.executeList();
	}
	
	public List<Long> darIdProductoPorTipoProducto (PersistenceManager pm, long id_TipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT idLote FROM " + pp.darTablaProducto () + " WHERE id_TipoProducto = ?");
		q.setParameters(id_TipoProducto);
		return q.executeList();
	}
	
	public List<Integer> darSBProductoPorTipoProducto (PersistenceManager pm, long id_TipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT stockBodega FROM " + pp.darTablaProducto () + " WHERE id_TipoProducto = ?");
		q.setParameters(id_TipoProducto);
		return q.executeList();
	}
	
	public List<Integer> darSEProductoPorTipoProducto (PersistenceManager pm, long id_TipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT stockEstante FROM " + pp.darTablaProducto () + " WHERE id_TipoProducto = ?");
		q.setParameters(id_TipoProducto);
		return q.executeList();
	}
	
	public List<Object> darIdProductos (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT idLote FROM " + pp.darTablaProducto ());
		return q.executeList();
	}
	
	
}