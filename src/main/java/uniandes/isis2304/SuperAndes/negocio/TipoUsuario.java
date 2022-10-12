package uniandes.isis2304.SuperAndes.negocio;

public class TipoUsuario implements VOTipoUsuario {
	
	private long id;
	private String tipo;
	
	public TipoUsuario() {

		this.id = 0;
		this.tipo = "";
	}
	
	public TipoUsuario(long id, String tipo) {

		this.id = id;
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoUsuario [id=");
		builder.append(id);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}	
}