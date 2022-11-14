package uniandes.isis2304.SuperAndes.negocio;

public interface VOCarritoCompra {

	public long getId();

	public long getId_Cliente();
	
	public long getId_Sucursal();

	public String getfCarrito();

	public String getEstado();

	@Override
	public String toString();
}
