package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Conexion;

public class ProveedorDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * Funcion para insertar proveedor en DB
	 * @param dto
	 * @return
	 */
	public boolean insertarProveedor(ProveedorDTO dto) {
		boolean result = false;
		try {
			String sql = "INSERT INTO proveedor VALUES(?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getNitProveedor());
			ps.setString(2, dto.getCiudadProveedor());
			ps.setString(3, dto.getDireccionProveedor());
			ps.setString(4, dto.getNombreProveedor());
			ps.setString(5, dto.getTelefonoProveedor());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para listar proveedores desde la DB
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ProveedorDTO> listarProveedores(){
		
		ProveedorDTO pDTO = null;
		ArrayList<ProveedorDTO> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM proveedor";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			while (rs.next()) {
				pDTO = new ProveedorDTO(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5)
				);
				lista.add(pDTO);
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar los proveedores: "+ex);
		}
		
		return lista;
	}
	
	/**
	 * Funcion para buscar por Nit de proveedor
	 * @param nit
	 * @return
	 */
	public ProveedorDTO obtenerPorNit(long nit){
		ProveedorDTO proveedor = null;
 
		try {
			String sql = "SELECT * FROM proveedor WHERE nit_proveedor= ? ";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, nit);
			rs = ps.executeQuery();
	 
			if (rs.next()) {
				proveedor = new ProveedorDTO(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5)
				);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return proveedor;
	}
	
	/**
	 * Funcion para editar proveedor
	 * @param dto
	 * @return
	 */
	public boolean actualizarProveedor(ProveedorDTO dto) {
		boolean result = false;
		try {
			String sql = "UPDATE proveedor  SET ciudad_proveedor=?, direccion_proveedor=?, nombre_proveedor=?, telefono_proveedor=? WHERE nit_proveedor=?";
			ps = connect.prepareStatement(sql);
			ps.setString(1, dto.getCiudadProveedor());
			ps.setString(2, dto.getDireccionProveedor());
			ps.setString(3, dto.getNombreProveedor());
			ps.setString(4, dto.getTelefonoProveedor());
			ps.setLong(5, dto.getNitProveedor());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para eliminar proveedor
	 * @param dto
	 * @return
	 */
	public boolean eliminarProveedor(ProveedorDTO dto) {
		boolean result = false;
		try {
			String sql = "DELETE FROM proveedor WHERE nit_proveedor=?";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getNitProveedor());
			result = ps.executeUpdate()>0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
