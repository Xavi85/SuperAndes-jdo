package uniandes.isis2304.SuperAndes.negocio;

public class CarritoCompraProducto implements VOCarritoCompraProducto {
	
	private long id_CarritoCompra;
	private long id_Producto;
	private int pVentaH;
	private int cantidad;
	
	public CarritoCompraProducto() {

		this.id_CarritoCompra = 0;
		this.id_Producto = 0;
		this.pVentaH = 0;
		this.cantidad = 0;
	}
	
	public CarritoCompraProducto(long id_CarritoCompra, long id_Producto, int pVentaH, int cantidad) {

		this.id_CarritoCompra = id_CarritoCompra;
		this.id_Producto = id_Producto;
		this.pVentaH = pVentaH;
		this.cantidad = cantidad;
	}

	public long getId_CarritoCompra() {
		return id_CarritoCompra;
	}

	public void setId_CarritoCompra(long id_CarritoCompra) {
		this.id_CarritoCompra = id_CarritoCompra;
	}

	public long getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(long id_Producto) {
		this.id_Producto = id_Producto;
	}

	public int getpVentaH() {
		return pVentaH;
	}

	public void setpVentaH(int pVentaH) {
		this.pVentaH = pVentaH;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarritoCompraProducto [id_CarritoCompra=");
		builder.append(id_CarritoCompra);
		builder.append(", id_Producto=");
		builder.append(id_Producto);
		builder.append(", pVentaH=");
		builder.append(pVentaH);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append("]");
		return builder.toString();
	}
}
