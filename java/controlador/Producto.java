package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.gson.Gson;

import modelo.ProductoDAO;
import modelo.ProductoDTO;

/**
 * Servlet implementation class Producto
 */
@WebServlet("/Producto")
@MultipartConfig
public class Producto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Producto() {
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
		
		ProductoDAO pDAO = new ProductoDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		/**
		 * Funcion agregar por CSV
		 */
		if(request.getParameter("cargar")!=null) {
			
			Part archivo= request.getPart("archivo");
			String Url="C:/laragon/data/mysql/"; //ruta del archivo
			String respuesta="";
			
			if(archivo.getContentType().equals("application/vnd.ms-excel")) {
				try {
					InputStream file= archivo.getInputStream();
					File copia= new File(Url+"producto.csv");
					FileOutputStream escribir= new FileOutputStream(copia);
					int num=file.read();
					while(num !=-1) {
						escribir.write(num);
						num=file.read();
					}
					file.close();
					escribir.close();
					if(pDAO.cargarProductos(Url+"producto.csv")) {
						respuesta = "[{\"estado\":\"Ok\"}]";
					}else{
						respuesta = "[{\"estado\":\"Error en insercion en base de datos\"}]";
					}
				}catch(Exception e) {
					respuesta = "[{\"estado\":\"Error al cargar el archivo\"}]";
				}
			}else{
				respuesta = "[{\"estado\":\"Archivo no permitido\"}]";
			}
			salida.println(respuesta);	
		}
		
		/**
		 * Editar producto
		 */
		if(request.getParameter("editar")!=null) {
			
			long codigo, nitProveedor;
			double ivaCompra, precioCompra, precioVenta;
			String nombre, respuesta;
			
			codigo= Long.parseLong(request.getParameter("codigo"));
			ivaCompra = Double.parseDouble(request.getParameter("ivaCompra"));
			nitProveedor = Long.parseLong(request.getParameter("nitProveedor"));
			nombre = request.getParameter("nombre");
			precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
			precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
			
			ProductoDTO dto = new ProductoDTO(codigo, ivaCompra, nitProveedor, nombre, precioCompra, precioVenta);
			
			if(pDAO.actualizarProducto(dto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		/**
		 * Borrar producto
		 */
		if(request.getParameter("borrar")!=null) {
			
			long codigo;
			String respuesta;
			
			codigo = Long.parseLong(request.getParameter("codigo"));
			ProductoDTO producto = null;
			producto = pDAO.obtenerPorCodigo(codigo);
			if(pDAO.eliminarProducto(producto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		/**
		 * Ver Producto
		 */
		if(request.getParameter("ver")!=null) {
			
			long codigo;
			String respuesta;
			if(request.getParameter("codigo")==null || request.getParameter("codigo")==""){
				respuesta = "[{\"estado\":\"Error\"}]";
				salida.println(respuesta);
			}else {
				codigo = Long.parseLong(request.getParameter("codigo"));
				ProductoDTO producto = null;
				producto = pDAO.obtenerPorCodigo(codigo);
				if(producto != null) {
					salida.println(json.toJson(producto));
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
			ArrayList<ProductoDTO> lista=new ArrayList<>();
			lista=pDAO.listarProductos();
			salida.println(json.toJson(lista));	
		}
		
	}

}
