package uniandes.isis2304.SuperAndes.negocio;

public class Supermercado implements VOSupermercado {
	
	private long id;
	private long nit;
	private String nombre;
	
	public Supermercado() {

		this.id = 0;
		this.nit = 0;
		this.nombre = "";
	}

	public Supermercado(long id, long nit, String nombre) {

		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Supermercado [id=" + id + ", nit=" + nit + ", nombre=" + nombre + "]";
	}
}
