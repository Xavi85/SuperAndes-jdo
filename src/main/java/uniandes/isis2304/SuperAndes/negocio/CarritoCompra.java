package uniandes.isis2304.SuperAndes.negocio;

public class CarritoCompra implements VOCarritoCompra {
	
	private long id;
	private long id_Cliente;
	private long id_Sucursal;
	private String fCarrito;
	private String estado;
	
	public CarritoCompra() {

		this.id = 0;
		this.id_Cliente = 0;
		this.id_Sucursal = 0;
		this.fCarrito = "";
		this.estado = "";
	}

	public CarritoCompra(long id, long id_Cliente, long id_Sucursal, String fCarrito, String estado) {
		super();
		this.id = id;
		this.id_Cliente = id_Cliente;
		this.id_Sucursal = id_Sucursal;
		this.fCarrito = fCarrito;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	
	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	public String getfCarrito() {
		return fCarrito;
	}

	public void setfCarrito(String fCarrito) {
		this.fCarrito = fCarrito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarritoCompra [id=");
		builder.append(id);
		builder.append(", id_Cliente=");
		builder.append(id_Cliente);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append(", fCarrito=");
		builder.append(fCarrito);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}
}
