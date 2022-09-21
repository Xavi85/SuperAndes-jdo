package uniandes.isis2304.SuperAndes.negocio;

public interface VOTipoProducto {

	public long getId();

	public String getNombre();

	public String getTipoAlmacen();

	public String getCategoria();

	public String getSubCategoria();

	@Override
	public String toString();
}