package uniandes.isis2304.SuperAndes.negocio;

public class Usuario implements VOUsuario {
	
	private long id;
	private long nDocumento;
    private String tipoDocumento;
    private String nombre;
    private String correo;
    private String direccion;
    private Integer puntos;
    private long id_TipoUsuario;
    private Long id_Sucursal;
    
    public Usuario() {

		this.id = 0;
		this.nDocumento = 0;
		this.tipoDocumento = "";
		this.nombre = "";
		this.correo = "";
		this.direccion = "";
		this.puntos = 0;
		this.id_TipoUsuario = 0;
		this.id_Sucursal = (long) 0;
	}
    
	public Usuario(long id, long nDocumento, String tipoDocumento, String nombre, String correo, String direccion,
			Integer puntos, long id_TipoUsuario, Long id_Sucursal) {

		this.id = id;
		this.nDocumento = nDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
		this.puntos = puntos;
		this.id_TipoUsuario = id_TipoUsuario;
		this.id_Sucursal = id_Sucursal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getnDocumento() {
		return nDocumento;
	}

	public void setnDocumento(long nDocumento) {
		this.nDocumento = nDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public long getId_TipoUsuario() {
		return id_TipoUsuario;
	}

	public void setId_TipoUsuario(long id_TipoUsuario) {
		this.id_TipoUsuario = id_TipoUsuario;
	}

	public Long getId_Sucursal() {
		return id_Sucursal;
	}

	public void setId_Sucursal(Long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nDocumento=");
		builder.append(nDocumento);
		builder.append(", tipoDocumento=");
		builder.append(tipoDocumento);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", correo=");
		builder.append(correo);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", puntos=");
		builder.append(puntos);
		builder.append(", id_TipoUsuario=");
		builder.append(id_TipoUsuario);
		builder.append(", id_Sucursal=");
		builder.append(id_Sucursal);
		builder.append(", id_Supermercado=");
		builder.append("]");
		return builder.toString();
	}
}
