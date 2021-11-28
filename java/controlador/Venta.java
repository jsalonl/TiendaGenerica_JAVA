package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.VentaDTO;
import modelo.VentaDAO;
import modelo.DetalleVentaDTO;
import modelo.DetalleVentaDAO;
import modelo.ProductoDAO;

/**
 * Servlet implementation class Venta
 */
@WebServlet("/Venta")
public class Venta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Venta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VentaDAO vDAO = new VentaDAO();
		ProductoDAO pDAO = new ProductoDAO();
		DetalleVentaDAO dvDAO = new DetalleVentaDAO();
		PrintWriter salida= response.getWriter();
		
		/**
		 * Insertar Venta
		 */
		if(request.getParameter("insertar")!=null) {
			
			long codigoVenta, cedulaCliente, cedulaUsuario, codP1, codP2, codP3;
			int canP1, canP2, canP3;
			double ivaVenta, totalVenta, valorVenta;
			String respuesta="";
			
			//Parametros de factura
			codigoVenta = Long.parseLong(request.getParameter("codigoVenta"));
			cedulaCliente = Long.parseLong(request.getParameter("cedulaCliente"));
			cedulaUsuario = Long.parseLong(request.getParameter("cedulaUsuario"));
			ivaVenta = Double.parseDouble(request.getParameter("ivaVenta"));
			totalVenta = Double.parseDouble(request.getParameter("totalVenta"));
			valorVenta = Double.parseDouble(request.getParameter("valorVenta"));
			
			
			VentaDTO dto = new VentaDTO(codigoVenta, cedulaCliente, cedulaUsuario, ivaVenta, totalVenta, valorVenta);
			
			if(vDAO.insertarVenta(dto)) {
				
				double valorTotal, ivaProducto, precioProducto, valorIva;
				
				if(request.getParameter("codp1") != "") {
					
					//Parametros primer producto
					codP1 = Long.parseLong(request.getParameter("codp1"));
					canP1 = Integer.parseInt(request.getParameter("canp1"));
					
					precioProducto = pDAO.obtenerPorCodigo(codP1).getPrecioVenta();
					ivaProducto = pDAO.obtenerPorCodigo(codP1).getIvaCompra();
					
					valorTotal = precioProducto*canP1;
					valorIva = ((precioProducto*ivaProducto)/100)*canP1;
					valorVenta = valorTotal+valorIva;
					
					DetalleVentaDTO dvDTO = new DetalleVentaDTO(0, canP1, codP1, codigoVenta, valorTotal, valorVenta, valorIva);
					
					if(dvDAO.insertarDetalleVenta(dvDTO)) {
						respuesta = "[{\"estado\":\"Ok\"}]";
					}else{
						respuesta = "[{\"estado\":\"Error al insertar detalle de venta\"}]";
					}
				}
				
				if(request.getParameter("codp2") != "") {
					
					//Parametros segundo producto
					codP2 = Long.parseLong(request.getParameter("codp2"));
					canP2 = Integer.parseInt(request.getParameter("canp2"));
					
					precioProducto = pDAO.obtenerPorCodigo(codP2).getPrecioVenta();
					ivaProducto = pDAO.obtenerPorCodigo(codP2).getIvaCompra();
					
					valorTotal = precioProducto*canP2;
					valorIva = ((precioProducto*ivaProducto)/100)*canP2;
					valorVenta = valorTotal+valorIva;
					
					DetalleVentaDTO dvDTO = new DetalleVentaDTO(0, canP2, codP2, codigoVenta, valorTotal, valorVenta, valorIva);
					
					if(dvDAO.insertarDetalleVenta(dvDTO)) {
						respuesta = "[{\"estado\":\"Ok\"}]";
					}else{
						respuesta = "[{\"estado\":\"Error al insertar detalle de venta\"}]";
					}
				}
				
				if(request.getParameter("codp3") != "") {
					
					//Parametros segundo producto
					codP3 = Long.parseLong(request.getParameter("codp3"));
					canP3 = Integer.parseInt(request.getParameter("canp3"));
					
					precioProducto = pDAO.obtenerPorCodigo(codP3).getPrecioVenta();
					ivaProducto = pDAO.obtenerPorCodigo(codP3).getIvaCompra();
					
					valorTotal = precioProducto*canP3;
					valorIva = ((precioProducto*ivaProducto)/100)*canP3;
					valorVenta = valorTotal+valorIva;
					
					DetalleVentaDTO dvDTO = new DetalleVentaDTO(0, canP3, codP3, codigoVenta, valorTotal, valorVenta, valorIva);
					
					if(dvDAO.insertarDetalleVenta(dvDTO)) {
						respuesta = "[{\"estado\":\"Ok\"}]";
					}else{
						respuesta = "[{\"estado\":\"Error al insertar detalle de venta\"}]";
					}
				}
			
			}else {
				respuesta = "[{\"estado\":\"Error al insertar venta\"}]";
			}
			salida.println(respuesta);	
		}
		
	}

}
