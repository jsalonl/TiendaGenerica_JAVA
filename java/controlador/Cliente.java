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

import modelo.ClienteDAO;
import modelo.ClienteDTO;

/**
 * Servlet implementation class Cliente
 */
@WebServlet("/Cliente")
public class Cliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cliente() {
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
		ClienteDAO cDAO= new ClienteDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		/**
		 * Insertar usuario
		 */
		if(request.getParameter("insertar")!=null) {
			
			long cedula;
			String direccion, email, nombre, telefono, respuesta;
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			direccion = request.getParameter("direccion");
			email = request.getParameter("email");
			nombre = request.getParameter("nombre");
			telefono = request.getParameter("telefono");

			ClienteDTO dto = new ClienteDTO(cedula, direccion, email, nombre, telefono);
			
			if(cDAO.insertarCliente(dto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		/**
		 * Editar usuario
		 */
		if(request.getParameter("editar")!=null) {
			
			long cedula;
			String direccion, email, nombre, telefono, respuesta;
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			direccion = request.getParameter("direccion");
			email = request.getParameter("email");
			nombre = request.getParameter("nombre");
			telefono = request.getParameter("telefono");

			ClienteDTO dto = new ClienteDTO(cedula, direccion, email, nombre, telefono);
			
			if(cDAO.actualizarCliente(dto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		/**
		 * Borrar usuario
		 */
		if(request.getParameter("borrar")!=null) {
			
			long cedula;
			String respuesta;
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			ClienteDTO cliente = null;
			cliente = cDAO.obtenerPorCedula(cedula);
			if(cDAO.eliminarCliente(cliente)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		/**
		 * Ver usuario
		 */
		if(request.getParameter("ver")!=null) {
			
			long cedula;
			String respuesta;
			if(request.getParameter("cedula")==null || request.getParameter("cedula")==""){
				respuesta = "[{\"estado\":\"Error\"}]";
				salida.println(respuesta);
			}else {
				cedula = Long.parseLong(request.getParameter("cedula"));
				ClienteDTO cliente = null;
				cliente = cDAO.obtenerPorCedula(cedula);
				if(cliente!= null) {
					salida.println(json.toJson(cliente));
				}else {
					respuesta = "[{\"estado\":\"Error\"}]";
					salida.println(respuesta);
				}
			}
			
		}
		
		/**
		 * Convertir lista en formato JSON
		 */
		if(request.getParameter("listar")!=null) {
			ArrayList<ClienteDTO> lista=new ArrayList<>();
			lista=cDAO.listarClientes();
			salida.println(json.toJson(lista));	
		}
	}

}
