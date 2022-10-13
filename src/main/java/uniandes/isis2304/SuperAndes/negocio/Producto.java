package uniandes.isis2304.SuperAndes.negocio;

public class Producto implements VOProducto {

	private long idLote;
	private String codigoBarra;
	private String nombre;
	private String marca;
	private int pVenta;
	private String presentacion;
	private int pUnidadMedida;
	private int cantPPT;
	private String unidadMedida;
	private int espEmpPeso;
	private int espEmpVol;
	private String esPerecedero;
	private String fVencimiento;
	private int nReorden;
	private int stockBodega;
	private int stockEstante;
	private int stockTotal;
	private long id_TipoProducto;
	
	public Producto() {

		this.idLote = 0;
		this.codigoBarra = "";
		this.nombre = "";
		this.marca = "";
		this.pVenta = 0;
		this.presentacion = "";
		this.pUnidadMedida = 0;
		this.cantPPT = 0;
		this.unidadMedida = "";
		this.espEmpPeso = 0;
		this.espEmpVol = 0;
		this.esPerecedero = "";
		this.fVencimiento = "";
		this.nReorden = 0;
		this.stockBodega = 0;
		this.stockEstante = 0;
		this.stockTotal = 0;
		this.id_TipoProducto = 0;
	}
	
	public Producto(long idLote, String codigoBarra, String nombre, String marca, int pVenta, String presentacion,
			int pUnidadMedida, int cantPPT, String unidadMedida, int espEmpPeso, int espEmpVol, String esPerecedero,
			String fVencimiento, int nReorden, int stockBodega, int stockEstante, int stockTotal, long id_TipoProducto) {

		this.idLote = idLote;
		this.codigoBarra = codigoBarra;
		this.nombre = nombre;
		this.marca = marca;
		this.pVenta = pVenta;
		this.presentacion = presentacion;
		this.pUnidadMedida = pUnidadMedida;
		this.cantPPT = cantPPT;
		this.unidadMedida = unidadMedida;
		this.espEmpPeso = espEmpPeso;
		this.espEmpVol = espEmpVol;
		this.esPerecedero = esPerecedero;
		this.fVencimiento = fVencimiento;
		this.nReorden = nReorden;
		this.stockBodega = stockBodega;
		this.stockEstante = stockEstante;
		this.stockTotal = stockTotal;
		this.id_TipoProducto = id_TipoProducto;
	}

	public long getIdLote() {
		return idLote;
	}

	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getpVenta() {
		return pVenta;
	}

	public void setpVenta(int pVenta) {
		this.pVenta = pVenta;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public int getpUnidadMedida() {
		return pUnidadMedida;
	}

	public void setpUnidadMedida(int pUnidadMedida) {
		this.pUnidadMedida = pUnidadMedida;
	}

	public int getCantPPT() {
		return cantPPT;
	}

	public void setCantPPT(int cantPPT) {
		this.cantPPT = cantPPT;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public int getEspEmpPeso() {
		return espEmpPeso;
	}

	public void setEspEmpPeso(int espEmpPeso) {
		this.espEmpPeso = espEmpPeso;
	}

	public int getEspEmpVol() {
		return espEmpVol;
	}

	public void setEspEmpVol(int espEmpVol) {
		this.espEmpVol = espEmpVol;
	}

	public String getEsPerecedero() {
		return esPerecedero;
	}

	public void setEsPerecedero(String esPerecedero) {
		this.esPerecedero = esPerecedero;
	}

	public String getfVencimiento() {
		return fVencimiento;
	}

	public void setfVencimiento(String fVencimiento) {
		this.fVencimiento = fVencimiento;
	}

	public int getnReorden() {
		return nReorden;
	}

	public void setnReorden(int nReorden) {
		this.nReorden = nReorden;
	}

	public int getStockBodega() {
		return stockBodega;
	}

	public void setStockBodega(int stockBodega) {
		this.stockBodega = stockBodega;
	}

	public int getStockEstante() {
		return stockEstante;
	}

	public void setStockEstante(int stockEstante) {
		this.stockEstante = stockEstante;
	}

	public int getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	public long getId_TipoProducto() {
		return id_TipoProducto;
	}

	public void setId_TipoProducto(long id_TipoProducto) {
		this.id_TipoProducto = id_TipoProducto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [idLote=");
		builder.append(idLote);
		builder.append(", codigoBarra=");
		builder.append(codigoBarra);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", marca=");
		builder.append(marca);
		builder.append(", pVenta=");
		builder.append(pVenta);
		builder.append(", presentacion=");
		builder.append(presentacion);
		builder.append(", pUnidadMedida=");
		builder.append(pUnidadMedida);
		builder.append(", cantPPT=");
		builder.append(cantPPT);
		builder.append(", unidadMedida=");
		builder.append(unidadMedida);
		builder.append(", espEmpPeso=");
		builder.append(espEmpPeso);
		builder.append(", espEmpVol=");
		builder.append(espEmpVol);
		builder.append(", esPerecedero=");
		builder.append(esPerecedero);
		builder.append(", fVencimiento=");
		builder.append(fVencimiento);
		builder.append(", nReorden=");
		builder.append(nReorden);
		builder.append(", stockBodega=");
		builder.append(stockBodega);
		builder.append(", stockEstante=");
		builder.append(stockEstante);
		builder.append(", stockTotal=");
		builder.append(stockTotal);
		builder.append(", id_TipoProducto=");
		builder.append(id_TipoProducto);
		builder.append("]");
		return builder.toString();
	}
}