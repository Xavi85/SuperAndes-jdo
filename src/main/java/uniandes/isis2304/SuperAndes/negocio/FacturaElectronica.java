package uniandes.isis2304.SuperAndes.negocio;

public class FacturaElectronica implements VOFacturaElectronica {

	private long id;
	private long numFactura;
	private long id_Sucursal;
	private long id_Cliente;
	private long id_Venta;
	
	public FacturaElectronica() {

		this.id = 0;
		this.numFactura = 0;
		this.id_Sucursal = 0;
		this.id_Cliente = 0;
		this.id_Venta = 0;
	}
	
	public FacturaElectronica(long id, long numFactura, long id_Sucursal, long id_Cliente, long id_Venta) {

		this.id = id;
		this.numFactura = numFactura;
		this.id_Sucursal = id_Sucursal;
		this.id_Cliente = id_Cliente;
		this.id_Venta = id_Venta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(long numFactura) {
		this.numFactura = numFactura;
	}

	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	public long getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public long getId_Venta() {
		return id_Venta;
	}

	public void setId_Venta(long id_Venta) {
		this.id_Venta = id_Venta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacturaElectronica [id=");
		builder.append(id);
		builder.append(", numFactura=");
		builder.append(numFactura);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append(", id_Cliente=");
		builder.append(id_Cliente);
		builder.append(", id_Venta=");
		builder.append(id_Venta);
		builder.append("]");
		return builder.toString();
	}
}
