package uniandes.isis2304.SuperAndes.negocio;

public class OrdenPedidoProducto implements VOOrdenPedidoProducto {

	private long id_OrdenPedido;
	private long id_Producto;
	private int cantCompra;
	private int pCompra;
	
	public OrdenPedidoProducto() {

		this.id_OrdenPedido = 0;
		this.id_Producto = 0;
		this.cantCompra = 0;
		this.pCompra = 0;
	}
	
	public OrdenPedidoProducto(long id_OrdenPedido, long id_Producto, int cantCompra, int pCompra) {

		this.id_OrdenPedido = id_OrdenPedido;
		this.id_Producto = id_Producto;
		this.cantCompra = cantCompra;
		this.pCompra = pCompra;
	}

	public long getId_OrdenPedido() {
		return id_OrdenPedido;
	}

	public void setId_OrdenPedido(long id_OrdenPedido) {
		this.id_OrdenPedido = id_OrdenPedido;
	}

	public long getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(long id_Producto) {
		this.id_Producto = id_Producto;
	}

	public int getCantCompra() {
		return cantCompra;
	}

	public void setCantCompra(int cantCompra) {
		this.cantCompra = cantCompra;
	}

	public int getpCompra() {
		return pCompra;
	}

	public void setpCompra(int pCompra) {
		this.pCompra = pCompra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdenPedidoProducto [id_OrdenPedido=");
		builder.append(id_OrdenPedido);
		builder.append(", id_Producto=");
		builder.append(id_Producto);
		builder.append(", cantCompra=");
		builder.append(cantCompra);
		builder.append(", pCompra=");
		builder.append(pCompra);
		builder.append("]");
		return builder.toString();
	}	
}
