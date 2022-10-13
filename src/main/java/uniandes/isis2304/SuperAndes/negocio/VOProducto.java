package uniandes.isis2304.SuperAndes.negocio;

public interface VOProducto {

	public long getIdLote();

	public String getCodigoBarra();

	public String getNombre();

	public String getMarca();

	public int getpVenta();

	public String getPresentacion();

	public int getpUnidadMedida();

	public int getCantPPT();

	public String getUnidadMedida();

	public int getEspEmpPeso();

	public int getEspEmpVol();

	public String getEsPerecedero();

	public String getfVencimiento();

	public int getnReorden();

	public int getStockBodega();

	public int getStockEstante();

	public int getStockTotal();

	public long getId_TipoProducto();

	@Override
	public String toString();
}