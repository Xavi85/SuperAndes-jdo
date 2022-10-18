package uniandes.isis2304.SuperAndes.negocio;

public interface VOOrdenPedido {

	public long getId();

	public String getfCompra();

	public long getvTotal();
	
	public String getEstado();

	public long getId_Proveedor();

	public long getId_Sucursal();

	@Override
	public String toString();
}
