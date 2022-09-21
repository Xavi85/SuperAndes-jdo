package uniandes.isis2304.SuperAndes.negocio;

public interface VOProveedor {

	public long getId();

	public long getNit();

	public String getNombre();

	public int getCalificacion();

	@Override
	public String toString();	
}