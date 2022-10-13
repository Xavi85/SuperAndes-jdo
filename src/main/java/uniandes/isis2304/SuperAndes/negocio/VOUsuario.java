package uniandes.isis2304.SuperAndes.negocio;

public interface VOUsuario {

	public long getId();

	public long getnDocumento();

	public String getTipoDocumento();

	public String getNombre();

	public String getCorreo();
	
	public String getPais();
	
	public String getCiudad();

	public String getDireccion();

	public Integer getPuntos();

	public long getId_TipoUsuario();

	public Long getId_Sucursal();

	@Override
	public String toString();
}
