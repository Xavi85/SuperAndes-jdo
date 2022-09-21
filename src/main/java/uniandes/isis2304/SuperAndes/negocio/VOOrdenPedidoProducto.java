package uniandes.isis2304.SuperAndes.negocio;

public interface VOOrdenPedidoProducto {

	public long getId_OrdenPedido();

	public long getId_Producto();

	public int getCantCompra();

	public int getpCompra();

	@Override
	public String toString();
}
