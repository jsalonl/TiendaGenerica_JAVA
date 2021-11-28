package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import modelo.ClienteDTO;
import modelo.ReporteDAO;
import modelo.ReporteVentasClienteDTO;
import modelo.UsuarioDTO;

/**
 * Servlet implementation class Reporte
 */
@WebServlet("/Reporte")
public class Reporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reporte() {
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
		
		ReporteDAO rDAO = new ReporteDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		/**
		 * Convertir lista Usuarios en formato JSON
		 */
		if(request.getParameter("listarUsuarios")!=null) {
			ArrayList<UsuarioDTO> lista=new ArrayList<>();
			lista=rDAO.listarUsuarios();
			salida.println(json.toJson(lista));	
		}
		
		/**
		 * Convertir lista Clientes en formato JSON
		 */
		if(request.getParameter("listarClientes")!=null) {
			ArrayList<ClienteDTO> lista=new ArrayList<>();
			lista=rDAO.listarClientes();
			salida.println(json.toJson(lista));	
		}
		
		/**
		 * Convertir lista Clientes en formato JSON
		 */
		if(request.getParameter("listarVentasCliente")!=null) {
			ArrayList<ReporteVentasClienteDTO> lista=new ArrayList<>();
			lista=rDAO.listarVentasPorCliente();
			salida.println(json.toJson(lista));	
		}
	
	}

}
