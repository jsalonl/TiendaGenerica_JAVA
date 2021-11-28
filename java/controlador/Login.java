package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.UsuarioDAO;
import modelo.UsuarioDTO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
      HttpSession sesion = request.getSession();
      if(request.getParameter("out")!=null){
          sesion.invalidate();
          response.sendRedirect("index.jsp");
      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		UsuarioDAO dao = new UsuarioDAO();
		PrintWriter salida= response.getWriter();
		
		if(request.getParameter("login")!=null) {
			String u, p, respuesta;
			u = request.getParameter("usuario");
			p= request.getParameter("password");
			UsuarioDTO usuario = null;
			try {
				usuario = dao.loginUsuario(u,p);
				if(usuario!=null) {
					sesion.setAttribute("usuario", usuario.getCedulaUsuario());
					sesion.setAttribute("nombreUsuario", usuario.getNombreUsuario());
					respuesta = "[{\"estado\":\"Ok\"}]";
					salida.println(respuesta);	
				}else {
					respuesta = "[{\"estado\":\"Usuario o password equivocados\"}]";
					salida.println(respuesta);	
				}
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
