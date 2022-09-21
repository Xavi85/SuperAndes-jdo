package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;

public interface VOOrdenPedido {

	public long getId();

	public Date getfCompra();

	public int getvTotal();

	public long getId_Proveedor();

	public long getId_Sucursal();

	@Override
	public String toString();
}
