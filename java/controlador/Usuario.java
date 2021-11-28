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

import modelo.UsuarioDAO;
import modelo.UsuarioDTO;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/Usuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuario() {
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
		
		UsuarioDAO uDAO= new UsuarioDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		/**
		 * Insertar usuario
		 */
		if(request.getParameter("insertar")!=null) {
			
			long cedula;
			String email, nombre, password, usuario, respuesta;
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			email = request.getParameter("email");
			nombre = request.getParameter("nombre");
			usuario = request.getParameter("usuario");
			password = request.getParameter("password");
			
			UsuarioDTO dto = new UsuarioDTO(cedula, email, nombre, password, usuario);
			
			if(uDAO.insertarUsuario(dto)) {
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
			String email, nombre, password, usuario, respuesta;
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			email = request.getParameter("email");
			nombre = request.getParameter("nombre");
			usuario = request.getParameter("usuario");
			password = request.getParameter("password");
			
			UsuarioDTO dto = new UsuarioDTO(cedula, email, nombre, password, usuario);
			
			if(uDAO.actualizarUsuario(dto)) {
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
			UsuarioDTO usuario = null;
			usuario = uDAO.obtenerPorCedula(cedula);
			if(uDAO.eliminarUsuario(usuario)) {
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
			
			cedula = Long.parseLong(request.getParameter("cedula"));
			UsuarioDTO usuario = null;
			usuario = uDAO.obtenerPorCedula(cedula);
			if(usuario != null) {
				salida.println(json.toJson(usuario));
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
				salida.println(respuesta);
			}
		}
		
		/**
		 * Convertir lista en formato JSON
		 */
		if(request.getParameter("listar")!=null) {
			ArrayList<UsuarioDTO> lista=new ArrayList<>();
			lista=uDAO.listarUsuarios();
			salida.println(json.toJson(lista));	
		}

	}

}
