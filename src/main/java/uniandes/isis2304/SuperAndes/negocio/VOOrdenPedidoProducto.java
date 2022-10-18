package uniandes.isis2304.SuperAndes.negocio;

public interface VOOrdenPedidoProducto {

	public long getId_OrdenPedido();

	public long getId_Producto();

	public long getCantCompra();

	public long getpCompra();

	@Override
	public String toString();
}
