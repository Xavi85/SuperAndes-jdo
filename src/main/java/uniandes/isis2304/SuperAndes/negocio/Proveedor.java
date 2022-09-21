package uniandes.isis2304.SuperAndes.negocio;

public class Proveedor implements VOProveedor {

	private long id;
	private long nit;
	private String nombre;
	private int calificacion;
	
	public Proveedor() {

		this.id = 0;
		this.nit = 0;
		this.nombre = "";
		this.calificacion = 0;
	}
	
	public Proveedor(long id, long nit, String nombre, int calificacion) {

		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
		this.calificacion = calificacion;
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

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nit=" + nit + ", nombre=" + nombre + ", calificacion=" + calificacion + "]";
	}
}