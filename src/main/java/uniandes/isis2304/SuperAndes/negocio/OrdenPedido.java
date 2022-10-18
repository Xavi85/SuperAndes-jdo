package uniandes.isis2304.SuperAndes.negocio;

public class OrdenPedido implements VOOrdenPedido {

	private long id;
	String fCompra;
	private long vTotal;
	String estado;
	private long id_Proveedor;
	private long id_Sucursal;
	
	public OrdenPedido() {

		this.id = 0;
		this.fCompra = "";
		this.vTotal = 0;
		this.estado = "";
		this.id_Proveedor = 0;
		this.id_Sucursal = 0;
	}

	public OrdenPedido(long id, String fCompra, long vTotal, String estado, long id_Proveedor, long id_Sucursal) {
		
		this.id = id;
		this.fCompra = fCompra;
		this.vTotal = vTotal;
		this.estado = estado;
		this.id_Proveedor = id_Proveedor;
		this.id_Sucursal = id_Sucursal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfCompra() {
		return fCompra;
	}

	public void setfCompra(String fCompra) {
		this.fCompra = fCompra;
	}

	public long getvTotal() {
		return vTotal;
	}

	public void setvTotal(long vTotal) {
		this.vTotal = vTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		builder.append("OrdenPedido [id=");
		builder.append(id);
		builder.append(", fCompra=");
		builder.append(fCompra);
		builder.append(", vTotal=");
		builder.append(vTotal);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", id_Proveedor=");
		builder.append(id_Proveedor);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append("]");
		return builder.toString();
	}
}
