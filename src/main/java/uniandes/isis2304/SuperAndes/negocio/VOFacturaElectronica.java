package uniandes.isis2304.SuperAndes.negocio;

public interface VOFacturaElectronica {

	public long getId();

	public long getNumFactura();

	public long getId_Sucursal();

	public long getId_Cliente();
	
	public long getId_Venta();

	@Override
	public String toString();
}
