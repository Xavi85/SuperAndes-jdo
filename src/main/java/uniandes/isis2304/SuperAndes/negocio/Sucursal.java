package uniandes.isis2304.SuperAndes.negocio;

public class Sucursal implements VOSucursal {
	
	private long id;
	private String nombre;
	private String pais;
	private String ciudad;
	private String direccion;
	private long id_Supermercado;
	
	public Sucursal() {

		this.id = 0;
		this.nombre = "";
		this.pais = "";
		this.ciudad = "";
		this.direccion = "";
		this.id_Supermercado = 0;
	}
	
	public Sucursal(long id, String nombre, String pais, String ciudad, String direccion, long id_Supermercado) {

		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.id_Supermercado = id_Supermercado;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getId_Supermercado() {
		return id_Supermercado;
	}

	public void setId_Supermercado(long id_Supermercado) {
		this.id_Supermercado = id_Supermercado;
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", ciudad=" + ciudad + ", direccion="
				+ direccion + ", id_Supermercado=" + id_Supermercado + "]";
	}	
}