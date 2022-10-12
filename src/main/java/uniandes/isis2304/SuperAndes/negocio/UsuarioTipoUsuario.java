package uniandes.isis2304.SuperAndes.negocio;

public class UsuarioTipoUsuario implements VOUsuarioTipoUsuario {

	private long id_Usuario;
	private long id_TipoUsuario;
	
	public UsuarioTipoUsuario() {

		this.id_Usuario = 0;
		this.id_TipoUsuario = 0;
	}
	
	public UsuarioTipoUsuario(long id_Usuario, long id_TipoUsuario) {

		this.id_Usuario = id_Usuario;
		this.id_TipoUsuario = id_TipoUsuario;
	}

	public long getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(long id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public long getId_TipoUsuario() {
		return id_TipoUsuario;
	}

	public void setId_TipoUsuario(long id_TipoUsuario) {
		this.id_TipoUsuario = id_TipoUsuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PromocionProducto [id_Usuario=");
		builder.append(id_Usuario);
		builder.append(", id_TipoUsuario=");
		builder.append(id_TipoUsuario);
		builder.append("]");
		return builder.toString();
	}
}
