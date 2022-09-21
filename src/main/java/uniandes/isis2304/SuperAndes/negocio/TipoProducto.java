package uniandes.isis2304.SuperAndes.negocio;

public class TipoProducto implements VOTipoProducto {
	
	private long id;
	private String nombre;
	private String tipoAlmacen;
	private String categoria;
	private String subCategoria;
	
	public TipoProducto() {

		this.id = 0;
		this.nombre = "";
		this.tipoAlmacen = "";
		this.categoria = "";
		this.subCategoria = "";
	}
		
	public TipoProducto(long id, String nombre, String tipoAlmacen, String categoria, String subCategoria) {

		this.id = id;
		this.nombre = nombre;
		this.tipoAlmacen = tipoAlmacen;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoAlmacen() {
		return tipoAlmacen;
	}

	public void setTipoAlmacen(String tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	@Override
	public String toString() {
		return "TipoProducto [id=" + id + ", nombre=" + nombre + ", tipoAlmacen=" + tipoAlmacen + ", categoria="
				+ categoria + ", subCategoria=" + subCategoria + "]";
	}
}