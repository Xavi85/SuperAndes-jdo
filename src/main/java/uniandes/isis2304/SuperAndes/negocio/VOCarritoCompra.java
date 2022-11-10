package uniandes.isis2304.SuperAndes.negocio;

public interface VOCarritoCompra {

	public long getId_Venta();

	public long getId_Producto();

	public int getpVentaH();

	public int getCantidad();

	@Override
	public String toString();
}
