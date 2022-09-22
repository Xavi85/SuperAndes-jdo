package uniandes.isis2304.SuperAndes.persistencia;

class AAASql {
/*
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLBebedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarBebedor (PersistenceManager pm, long idBebedor, String nombre, String ciudad, String presupuesto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBebedor () + "(id, nombre, ciudad, presupuesto) values (?, ?, ?, ?)");
        q.setParameters(idBebedor, nombre, ciudad, presupuesto);
        return (long) q.executeUnique();
	}

	public long eliminarBebedorPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBebedor () + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public long eliminarBebedorPorId (PersistenceManager pm, long idBebedor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBebedor () + " WHERE id = ?");
        q.setParameters(idBebedor);
        return (long) q.executeUnique();            
	}

	public Bebedor darBebedorPorId (PersistenceManager pm, long idBebedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBebedor () + " WHERE id = ?");
		q.setResultClass(Bebedor.class);
		q.setParameters(idBebedor);
		return (Bebedor) q.executeUnique();
	}

	public List<Bebedor> darBebedoresPorNombre (PersistenceManager pm, String nombreBebedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBebedor () + " WHERE nombre = ?");
		q.setResultClass(Bebedor.class);
		q.setParameters(nombreBebedor);
		return (List<Bebedor>) q.executeList();
	}

	public List<Bebedor> darBebedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBebedor ());
		q.setResultClass(Bebedor.class);
		return (List<Bebedor>) q.executeList();
	}

	public List<Object []> darVisitasRealizadas (PersistenceManager pm, long idBebedor)
	{
        String sql = "SELECT bar.id, bar.nombre, bar.ciudad, bar.presupuesto, bar.cantsedes, vis.fechavisita, vis.horario";
        sql += " FROM ";
        sql += pp.darTablaBebedor () + " bdor, ";
        sql += pp.darTablaVisitan () + " vis, ";
        sql += pp.darTablaBar () + " bar ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = vis.idbebedor";
       	sql += " AND vis.idbar = bar.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idBebedor);
		return q.executeList();
	}

	public List<Object []> darBebidasQueLeGustan (PersistenceManager pm, long idBebedor)
	{
        String sql = "SELECT beb.id, beb.nombre, beb.idtipobebida, beb.gradoalcohol, tb.nombre";
        sql += " FROM ";
        sql += pp.darTablaBebedor () + " bdor, ";
        sql += pp.darTablaGustan () + " g, ";
        sql += pp.darTablaBebida () + " beb, ";
        sql += pp.darTablaTipoBebida () + " tb ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = g.idbebedor";
       	sql += " AND g.idBebida = beb.id";
       	sql += " AND beb.idtipobebida = tb.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idBebedor);
		return q.executeList();
	}

	public List<Object> darBebedoresYNumVisitasRealizadas (PersistenceManager pm)
	{
	    String sql = "SELECT id, nombre, ciudad, presupuesto, count (idbebedor) as numVisitas";
	    sql += " FROM " + pp.darTablaBebedor ();
	    sql += " LEFT OUTER JOIN " + pp.darTablaVisitan () + " ON id = idbebedor";
	    sql	+= " GROUP BY id, nombre, ciudad, presupuesto";
	    sql	+= " ORDER BY numVisitas";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public long darCantidadBebedoresCiudadVisitanBares (PersistenceManager pm, String ciudad)
	{
        String sql1 = "SELECT UNIQUE ID";
        sql1 += " FROM " + pp.darTablaBebedor ();
        sql1 += " INNER JOIN " + pp.darTablaVisitan () + " ON id = idbebedor";
       	sql1	+= " WHERE ciudad = ?";
       	
       	String sql = "SELECT count (*) FROM (" + sql1 + ")";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(ciudad);
		return ((BigDecimal) q.executeUnique()).longValue ();
	}

	public long cambiarCiudadBebedor (PersistenceManager pm, long idBebedor, String ciudad) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaBebedor () + " SET ciudad = ? WHERE id = ?");
	     q.setParameters(ciudad, idBebedor);
	     return (long) q.executeUnique();            
	}

	public long [] eliminarBebedorYVisitas_v1 (PersistenceManager pm, long idBebedor) 
	{
      Query q1 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan () + " WHERE idBebedor = ?");
      q1.setParameters(idBebedor);
      Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBebedor () + " WHERE id = ?");
      q2.setParameters(idBebedor);
      
      long visitasEliminadas = (long) q1.executeUnique ();
      long bebedoresEliminads = (long) q2.executeUnique ();
      return new long[] {bebedoresEliminads, visitasEliminadas};
	}

	public List<Object> darBebedoresYCuantosBaresVisitan (PersistenceManager pm)
	{
		// Selecciona las parejas [idBebedor, idBar] únicas de la tabla VISITAN
		String sql0 = "SELECT DISTINCT idbebedor, idBar";
		sql0 += " FROM " + pp.darTablaVisitan ();
		
		// Agrupa las parejas anteriores por idBebedor y cuenta cuántos bares visita cada bebedor
		String sql1 = "SELECT idbebedor, count (*) as numbares";
		sql1 += " FROM " + "(" + sql0 + ")";
		sql1 += " GROUP BY idBebedor";
		
		// Hace join de BEBEDORES con el resultado anterior para asociar la información del bebedor
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numbares, 0)";
        sql += " FROM " + pp.darTablaBebedor () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idBebedor";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public List<Object> darBebedoresYNumVisitasRealizadas_v2 (PersistenceManager pm)
	{		
		String sql1 = "SELECT idbebedor, count (*) as numVisitas";
		sql1 += " FROM " + pp.darTablaVisitan ();
		sql1 += " GROUP BY idBebedor";
		
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numVisitas, 0)";
        sql += " FROM " + pp.darTablaBebedor () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idBebedor";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
*/
}