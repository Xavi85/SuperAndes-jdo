package uniandes.isis2304.SuperAndes.negocio;

public interface VOSucursal {

	public long getId();

	public String getNombre();

	public String getPais();

	public String getCiudad();

	public String getDireccion();

	public long getId_Supermercado();

	@Override
	public String toString();
}
