package uniandes.isis2304.SuperAndes.negocio;

public class Venta implements VOVenta {
	
	private long id;
	private String fVenta;
	private int pTotal;
	private long id_Sucursal;
	private long id_Cajero;
	private long id_Cliente;
	private long id_CarritoCompra;
	
	public Venta() {

		this.id = 0;
		this.fVenta = "";
		this.pTotal = 0;
		this.id_Sucursal = 0;
		this.id_Cajero = 0;
	}

	public Venta(long id, String fVenta, int pTotal, long id_Sucursal, long id_Cajero, long id_Cliente,
			long id_CarritoCompra) {
		super();
		this.id = id;
		this.fVenta = fVenta;
		this.pTotal = pTotal;
		this.id_Sucursal = id_Sucursal;
		this.id_Cajero = id_Cajero;
		this.id_Cliente = id_Cliente;
		this.id_CarritoCompra = id_CarritoCompra;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfVenta() {
		return fVenta;
	}

	public void setfVenta(String fVenta) {
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

	public long getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public long getId_CarritoCompra() {
		return id_CarritoCompra;
	}

	public void setId_CarritoCompra(long id_CarritoCompra) {
		this.id_CarritoCompra = id_CarritoCompra;
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
		builder.append(", id_Cliente=");
		builder.append(id_Cliente);
		builder.append(", id_CarritoCompra=");
		builder.append(id_CarritoCompra);
		builder.append("]");
		return builder.toString();
	}
}
