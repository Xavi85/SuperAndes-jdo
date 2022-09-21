package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;

public interface VOVenta {

	public long getId();

	public Date getfVenta();

	public int getpTotal();

	public long getId_Sucursal();

	public long getId_Cajero();

	@Override
	public String toString();
}
