package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;

public class Venta implements VOVenta {
	
	private long id;
	Date fVenta;
	private int pTotal;
	private long id_Sucursal;
	private long id_Cajero;
	
	public Venta() {

		this.id = 0;
		this.fVenta = new Date() ;
		this.pTotal = 0;
		this.id_Sucursal = 0;
		this.id_Cajero = 0;
	}
	
	public Venta(long id, Date fVenta, int pTotal, long id_Sucursal, long id_Cajero) {

		this.id = id;
		this.fVenta = fVenta;
		this.pTotal = pTotal;
		this.id_Sucursal = id_Sucursal;
		this.id_Cajero = id_Cajero;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getfVenta() {
		return fVenta;
	}

	public void setfVenta(Date fVenta) {
		this.fVenta = fVenta;
	}

	public int getpTotal() {
		return pTotal;
	}

	public void setpTotal(int pTotal) {
		this.pTotal = pTotal;
	}

	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	public long getId_Cajero() {
		return id_Cajero;
	}

	public void setId_Cajero(long id_Cajero) {
		this.id_Cajero = id_Cajero;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Venta [id=");
		builder.append(id);
		builder.append(", fVenta=");
		builder.append(fVenta);
		builder.append(", pTotal=");
		builder.append(pTotal);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append(", id_Cajero=");
		builder.append(id_Cajero);
		builder.append("]");
		return builder.toString();
	}
}
