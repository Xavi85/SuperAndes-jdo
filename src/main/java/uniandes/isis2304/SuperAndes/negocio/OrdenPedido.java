package uniandes.isis2304.SuperAndes.negocio;

import java.util.Date;

public class OrdenPedido implements VOOrdenPedido {

	private long id;
	Date fCompra;
	private int vTotal;
	private long id_Proveedor;
	private long id_Sucursal;
	
	public OrdenPedido() {

		this.id = 0;
		this.fCompra = new Date();
		this.vTotal = 0;
		this.id_Proveedor = 0;
		this.id_Sucursal = 0;
	}
	
	public OrdenPedido(long id, Date fCompra, int vTotal, long id_Proveedor, long id_Sucursal) {

		this.id = id;
		this.fCompra = fCompra;
		this.vTotal = vTotal;
		this.id_Proveedor = id_Proveedor;
		this.id_Sucursal = id_Sucursal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getfCompra() {
		return fCompra;
	}

	public void setfCompra(Date fCompra) {
		this.fCompra = fCompra;
	}

	public int getvTotal() {
		return vTotal;
	}

	public void setvTotal(int vTotal) {
		this.vTotal = vTotal;
	}

	public long getId_Proveedor() {
		return id_Proveedor;
	}

	public void setId_Proveedor(long id_Proveedor) {
		this.id_Proveedor = id_Proveedor;
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
		builder.append("OrdenPedido [numOP=");
		builder.append(id);
		builder.append(", fCompra=");
		builder.append(fCompra);
		builder.append(", vTotal=");
		builder.append(vTotal);
		builder.append(", id_Proveedor=");
		builder.append(id_Proveedor);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append("]");
		return builder.toString();
	}
}
