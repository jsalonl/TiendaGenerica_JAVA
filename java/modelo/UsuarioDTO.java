package modelo;

public class UsuarioDTO {
	
	private long cedulaUsuario;
	private String emailUsuario;
	private String nombreUsuario;
	private String password;
	private String usuario;
	
	public UsuarioDTO(long cedulaUsuario, String emailUsuario, String nombreUsuario, String password, String usuario) {
		this.cedulaUsuario = cedulaUsuario;
		this.emailUsuario = emailUsuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.usuario = usuario;
	}

	public long getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(long cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
		
}
