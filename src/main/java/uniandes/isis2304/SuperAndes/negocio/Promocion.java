package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;

public class Promocion implements VOPromocion {

	private long id;
	private String nombre;
	Date fInicio;
	Date fFin;
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
		this.fInicio = new Date();
		this.fFin = new Date();
		this.descripcion = "";
		this.tipoPromocion = "";
		this.lleve = 0;
		this.pague = 0;
		this.descuento = 0;
		this.pVenta = 0;
		this.id_Sucursal = 0;
	}
	
	public Promocion(long id, String nombre, Date fInicio, Date fFin, String descripcion, String tipoPromocion,
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

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFin() {
		return fFin;
	}

	public void setfFin(Date fFin) {
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

	public void setLleve(int lleve) {
		this.lleve = lleve;
	}

	public int getPague() {
		return pague;
	}

	public void setPague(int pague) {
		this.pague = pague;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public int getpVenta() {
		return pVenta;
	}

	public void setpVenta(int pVenta) {
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
