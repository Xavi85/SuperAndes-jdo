package uniandes.isis2304.SuperAndes.negocio;

public class PromocionProducto implements VOPromocionProducto {

	private long id_Promocion;
	private long id_Producto;
	private int stockInicial;
	private int stockActual;
	
	public PromocionProducto() {

		this.id_Promocion = 0;
		this.id_Producto = 0;
		this.stockInicial = 0;
		this.stockActual = 0;
	}
	
	public PromocionProducto(long id_Promocion, long id_Producto, int stockInicial, int stockActual) {

		this.id_Promocion = id_Promocion;
		this.id_Producto = id_Producto;
		this.stockInicial = stockInicial;
		this.stockActual = stockActual;
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

	public int getstockInicial() {
		return stockInicial;
	}

	public void setstockInicial(int stockInicial) {
		this.stockInicial = stockInicial;
	}

	public int getstockActual() {
		return stockInicial;
	}

	public void setstockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PromocionProducto [id_Promocion=");
		builder.append(id_Promocion);
		builder.append(", id_Producto=");
		builder.append(id_Producto);
		builder.append(", stockInicial=");
		builder.append(stockInicial);
		builder.append(", stockActual=");
		builder.append(stockActual);
		builder.append("]");
		return builder.toString();
	}
}
