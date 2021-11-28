package modelo;

public class ReporteVentasClienteDTO {
	
	private long cedulaCliente;
	private String nombreCliente;
	private double valorTotalVentas;
	
	public ReporteVentasClienteDTO(long cedulaCliente, String nombreCliente, double valorTotalVentas) {
		this.cedulaCliente = cedulaCliente;
		this.nombreCliente = nombreCliente;
		this.valorTotalVentas = valorTotalVentas;
	}

	public long getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public double getValorTotalVentas() {
		return valorTotalVentas;
	}

	public void setValorTotalVentas(double valorTotalVentas) {
		this.valorTotalVentas = valorTotalVentas;
	}
	
}
