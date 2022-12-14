package uniandes.isis2304.SuperAndes.negocio;

public class Promocion implements VOPromocion {

	private long id;
	private String nombre;
	private String fInicio;
	private String fFin;
	private String descripcion;
	private String tipoPromocion;
	private int lleve;
	private int pague;
	private float descuento;
	private int pVenta;
	private long id_Sucursal;
	
	public Promocion() {

		this.id = 0;
		this.nombre = "";
		this.fInicio = "";
		this.fFin = "";
		this.descripcion = "";
		this.tipoPromocion = "";
		this.lleve = 0;
		this.pague = 0;
		this.descuento = (float) 0;
		this.pVenta = 0;
		this.id_Sucursal = 0;
	}
	
	public Promocion(long id, String nombre, String fInicio, String fFin, String descripcion, String tipoPromocion,
			int lleve, int pague, float descuento, int pVenta, long id_Sucursal) {

		this.id = id;
		this.nombre = nombre;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.descripcion = descripcion;
		this.tipoPromocion = tipoPromocion;
		this.lleve = lleve;
		this.pague = pague;
		this.descuento = descuento;
		this.pVenta = pVenta;
		this.id_Sucursal = id_Sucursal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getfInicio() {
		return fInicio;
	}

	public void setfInicio(String fInicio) {
		this.fInicio = fInicio;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(String tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public int getLleve() {
		return lleve;
	}

	public void setLleve(Integer lleve) {
		this.lleve = lleve;
	}

	public int getPague() {
		return pague;
	}

	public void setPague(Integer pague) {
		this.pague = pague;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public int getpVenta() {
		return pVenta;
	}

	public void setpVenta(Integer pVenta) {
		this.pVenta = pVenta;
	}

	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Promocion [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", fInicio=");
		builder.append(fInicio);
		builder.append(", fFin=");
		builder.append(fFin);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", tipoPromocion=");
		builder.append(tipoPromocion);
		builder.append(", lleve=");
		builder.append(lleve);
		builder.append(", pague=");
		builder.append(pague);
		builder.append(", descuento=");
		builder.append(descuento);
		builder.append(", pVenta=");
		builder.append(pVenta);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append("]");
		return builder.toString();
	}	
}
