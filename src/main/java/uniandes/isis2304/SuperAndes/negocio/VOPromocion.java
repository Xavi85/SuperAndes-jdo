package uniandes.isis2304.SuperAndes.negocio;

public interface VOPromocion {

	public long getId() ;

	public String getNombre();

	public String getfInicio();

	public String getfFin();

	public String getDescripcion();

	public String getTipoPromocion();

	public int getLleve();

	public int getPague();

	public float getDescuento();

	public int getpVenta();

	public long getId_Sucursal();

	@Override
	public String toString();
}