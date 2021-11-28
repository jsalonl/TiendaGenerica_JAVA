package modelo;

public class DetalleVentaDTO {

	private long codigoDetalleVenta;
	private int cantidadProducto;
	private long codigoProducto;
	private long codigoVenta;
	private double valorTotal;
	private double valorVenta;
	private double valorIva;
	
	public DetalleVentaDTO(long codigoDetalleVenta, int cantidadProducto, long codigoProducto, long codigoVenta,
			double valorTotal, double valorVenta, double valorIva) {
		this.codigoDetalleVenta = codigoDetalleVenta;
		this.cantidadProducto = cantidadProducto;
		this.codigoProducto = codigoProducto;
		this.codigoVenta = codigoVenta;
		this.valorTotal = valorTotal;
		this.valorVenta = valorVenta;
		this.valorIva = valorIva;
	}

	public long getCodigoDetalleVenta() {
		return codigoDetalleVenta;
	}

	public void setCodigoDetalleVenta(long codigoDetalleVenta) {
		this.codigoDetalleVenta = codigoDetalleVenta;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public long getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(long codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public double getValorIva() {
		return valorIva;
	}

	public void setValorIva(double valorIva) {
		this.valorIva = valorIva;
	}
	
}
