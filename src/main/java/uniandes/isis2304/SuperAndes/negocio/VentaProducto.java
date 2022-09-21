package uniandes.isis2304.SuperAndes.negocio;

public class VentaProducto implements VOVentaProducto {
	
	private long id_Venta;
	private long id_Producto;
	private int pVentaH;
	private int cantidad;
	
	public VentaProducto() {

		this.id_Venta = 0;
		this.id_Producto = 0;
		this.pVentaH = 0;
		this.cantidad = 0;
	}
	
	public VentaProducto(long id_Venta, long id_Producto, int pVentaH, int cantidad) {

		this.id_Venta = id_Venta;
		this.id_Producto = id_Producto;
		this.pVentaH = pVentaH;
		this.cantidad = cantidad;
	}

	public long getId_Venta() {
		return id_Venta;
	}

	public void setId_Venta(long id_Venta) {
		this.id_Venta = id_Venta;
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
		builder.append("VentaProducto [id_Venta=");
		builder.append(id_Venta);
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
