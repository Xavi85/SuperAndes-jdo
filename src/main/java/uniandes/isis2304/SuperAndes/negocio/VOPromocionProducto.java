package uniandes.isis2304.SuperAndes.negocio;

public interface VOPromocionProducto {

	public long getId_Promocion();

	public long getId_Producto();
	
	public int getstockInicial();

	public int getstockActual();

	@Override
	public String toString();
}
