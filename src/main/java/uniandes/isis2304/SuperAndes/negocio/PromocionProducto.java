package uniandes.isis2304.SuperAndes.negocio;

public class PromocionProducto implements VOPromocionProducto {

	private long id_Promocion;
	private long id_Producto;
	
	public PromocionProducto() {

		this.id_Promocion = 0;
		this.id_Producto = 0;
	}
	
	public PromocionProducto(long id_Promocion, long id_Producto) {

		this.id_Promocion = id_Promocion;
		this.id_Producto = id_Producto;
	}

	public long getId_Promocion() {
		return id_Promocion;
	}

	public void setId_Promocion(long id_Promocion) {
		this.id_Promocion = id_Promocion;
	}

	public long getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(long id_Producto) {
		this.id_Producto = id_Producto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PromocionProducto [id_Promocion=");
		builder.append(id_Promocion);
		builder.append(", id_Producto=");
		builder.append(id_Producto);
		builder.append("]");
		return builder.toString();
	}
}
