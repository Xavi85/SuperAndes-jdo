package uniandes.isis2304.SuperAndes.negocio;

public interface VOCarritoCompraProducto {

	public long getId_CarritoCompra();

	public long getId_Producto();

	public int getpVentaH();

	public int getCantidad();

	@Override
	public String toString();
}
