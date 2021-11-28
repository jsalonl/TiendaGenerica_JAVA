package modelo;

public class ClienteDTO {

	private long cedulaCliente;
	private String direccionCliente;
	private String emailCliente;
	private String nombreCliente;
	private String telefonoCliente;
	
	public ClienteDTO(long cedulaCliente, String direccionCliente, String emailCliente, String nombreCliente,
			String telefonoCliente) {
		this.cedulaCliente = cedulaCliente;
		this.direccionCliente = direccionCliente;
		this.emailCliente = emailCliente;
		this.nombreCliente = nombreCliente;
		this.telefonoCliente = telefonoCliente;
	}

	public long getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	
}
