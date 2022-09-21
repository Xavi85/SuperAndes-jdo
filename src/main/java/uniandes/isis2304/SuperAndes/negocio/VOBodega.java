package uniandes.isis2304.SuperAndes.negocio;

public interface VOBodega {

	public long getId();

	public int getVolMax();

	public int getPesoMax();

	public String getTipoAlmacen();

	public long getId_Sucursal();

	public long getId_TipoProducto();

	@Override
	public String toString();
}