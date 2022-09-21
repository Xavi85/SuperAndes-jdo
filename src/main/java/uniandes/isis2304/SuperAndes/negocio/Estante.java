package uniandes.isis2304.SuperAndes.negocio;

public class Estante implements VOEstante {

	private long id;
	private int volMax;
	private int pesoMax;
	private String tipoAlmacen;
	private int nAbastecimiento;
	private long id_Sucursal;
	private long id_TipoProducto;
	
	public Estante() {

		this.id = 0;
		this.volMax = 0;
		this.pesoMax = 0;
		this.tipoAlmacen = "";
		this.nAbastecimiento = 0;
		this.id_Sucursal = 0;
		this.id_TipoProducto = 0;
	}
	
	public Estante(long id, int volMax, int pesoMax, String tipoAlmacen, int nAbastecimiento, long id_Sucursal, long id_TipoProducto) {

		this.id = id;
		this.volMax = volMax;
		this.pesoMax = pesoMax;
		this.tipoAlmacen = tipoAlmacen;
		this.nAbastecimiento = nAbastecimiento;;
		this.id_Sucursal = id_Sucursal;
		this.id_TipoProducto = id_TipoProducto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVolMax() {
		return volMax;
	}

	public void setVolMax(int volMax) {
		this.volMax = volMax;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}

	public String getTipoAlmacen() {
		return tipoAlmacen;
	}

	public void setTipoAlmacen(String tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}

	public int getnAbastecimiento() {
		return nAbastecimiento;
	}

	public void setnAbastecimiento(int nAbastecimiento) {
		this.nAbastecimiento = nAbastecimiento;
	}

	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	public long getId_TipoProducto() {
		return id_TipoProducto;
	}

	public void setId_TipoProducto(long id_TipoProducto) {
		this.id_TipoProducto = id_TipoProducto;
	}

	@Override
	public String toString() {
		return "Estante [id=" + id + ", volMax=" + volMax + ", pesoMax=" + pesoMax + ", tipoAlmacen=" + tipoAlmacen
				+ ", nAbastecimiento=" + nAbastecimiento + ", id_Sucursal=" + id_Sucursal + ", id_TipoProducto="
				+ id_TipoProducto + "]";
	}
}