package uniandes.isis2304.SuperAndes.negocio;

public interface VOVenta {

	public long getId();

	public String getfVenta();

	public int getpTotal();

	public long getId_Sucursal();

	public long getId_Cajero();
	
	public long getId_Cliente();
	
	public long getId_CarritoCompra();

	@Override
	public String toString();
}
