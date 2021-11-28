package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Conexion;

public class ProductoDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * Funcion para insertar productos en DB
	 * @param dto
	 * @return
	 */
	public boolean insertarProducto(ProductoDTO dto) {
		boolean result = false;
		try {
			String sql = "INSERT INTO producto VALUES(?,?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCodigoProducto());
			ps.setDouble(2, dto.getIvaCompra());
			ps.setLong(3, dto.getNitProveedor());
			ps.setString(4, dto.getNombreProducto());
			ps.setDouble(5, dto.getPrecioCompra());
			ps.setDouble(6, dto.getPrecioVenta());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para cargar productos desde CSV en DB
	 * @param URL
	 * @return
	 */
	public boolean cargarProductos(String URL) {
		
		boolean resul=false;
		try {
			
		String sql="load data infile '"+URL+"' into table producto fields terminated by ',' lines terminated by '\r\n' IGNORE 1 LINES";
		ps = connect.prepareStatement(sql);
		resul=ps.executeUpdate()>0;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return resul;
	}
	
	/**
	 * Funcion para listar productos desde la DB
	 * @return
	 */
	public ArrayList<ProductoDTO> listarProductos(){
		
		ProductoDTO pDTO = null;
		ArrayList<ProductoDTO> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM producto";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			while (rs.next()) {
				pDTO = new ProductoDTO(
					rs.getLong(1),
					rs.getDouble(2),
					rs.getLong(3),
					rs.getString(4),
					rs.getDouble(5),
					rs.getDouble(6)
				);
				lista.add(pDTO);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * Funcion para buscar por codigo de producto
	 * @param codigo
	 * @return
	 */
	public ProductoDTO obtenerPorCodigo(long codigo){
		ProductoDTO producto = null;
 
		try {
			String sql = "SELECT * FROM producto WHERE codigo_producto= ? ";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, codigo);
			rs = ps.executeQuery();
	 
			if (rs.next()) {
				producto = new ProductoDTO(
					rs.getLong(1),
					rs.getDouble(2),
					rs.getLong(3),
					rs.getString(4),
					rs.getDouble(5),
					rs.getDouble(6)
				);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	/**
	 * Funcion para editar producto
	 * @param dto
	 * @return
	 */
	public boolean actualizarProducto(ProductoDTO dto) {
		boolean result = false;
		try {
			String sql = "UPDATE producto SET iva_compra=?, nit_proveedor=?, nombre_producto=?, precio_compra=?, precio_venta=? WHERE codigo_producto=?";
			ps = connect.prepareStatement(sql);
			ps.setDouble(1, dto.getIvaCompra());
			ps.setLong(2, dto.getNitProveedor());
			ps.setString(3, dto.getNombreProducto());
			ps.setDouble(4, dto.getPrecioCompra());
			ps.setDouble(5, dto.getPrecioVenta());
			ps.setLong(6, dto.getCodigoProducto());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para eliminar producto
	 * @param dto
	 * @return
	 */
	public boolean eliminarProducto(ProductoDTO dto) {
		boolean result = false;
		try {
			String sql = "DELETE FROM producto WHERE codigo_producto=?";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCodigoProducto());
			result = ps.executeUpdate()>0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
