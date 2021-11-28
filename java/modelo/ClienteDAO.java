package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Conexion;

public class ClienteDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * Funcion para insertar clientes en DB
	 * @param dto
	 * @return
	 */
	public boolean insertarCliente(ClienteDTO dto) {
		boolean result = false;
		try {
			String sql = "INSERT INTO cliente VALUES(?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCedulaCliente());
			ps.setString(2, dto.getDireccionCliente());
			ps.setString(3, dto.getEmailCliente());
			ps.setString(4, dto.getNombreCliente());
			ps.setString(5, dto.getTelefonoCliente());
			result = ps.executeUpdate()>0;
			connect.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para listar clientes desde la DB
	 * @return
	 */
	public ArrayList<ClienteDTO> listarClientes(){
		
		ClienteDTO cDTO = null;
		ArrayList<ClienteDTO> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cliente";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			while (rs.next()) {
				cDTO = new ClienteDTO(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5)
				);
				lista.add(cDTO);
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar los clientes: "+ex);
		}
		
		return lista;
	}
	
	/**
	 * Funcion para buscar por codigo de producto
	 * @param codigo
	 * @return
	 */
	public ClienteDTO obtenerPorCedula(long cedula){
		ClienteDTO cliente = null;
 
		try {
			String sql = "SELECT * FROM cliente WHERE cedula_cliente= ? ";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, cedula);
			rs = ps.executeQuery();
	 
			if (rs.next()) {
				cliente= new ClienteDTO(
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
		return cliente;
	}
	
	/**
	 * Funcion para editar cliente
	 * @param dto
	 * @return
	 */
	public boolean actualizarCliente(ClienteDTO dto) {
		boolean result = false;
		try {
			String sql = "UPDATE cliente SET direccion_cliente=?, email_cliente=?, nombre_cliente=?, telefono_cliente=? WHERE cedula_cliente=?";
			ps = connect.prepareStatement(sql);
			ps.setString(1, dto.getDireccionCliente());
			ps.setString(2, dto.getEmailCliente());
			ps.setString(3, dto.getNombreCliente());
			ps.setString(4, dto.getTelefonoCliente());
			ps.setLong(5, dto.getCedulaCliente());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para eliminar cliente
	 * @param dto
	 * @return
	 */
	public boolean eliminarCliente(ClienteDTO dto) {
		boolean result = false;
		try {
			String sql = "DELETE FROM cliente WHERE cedula_cliente=?";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCedulaCliente());
			result = ps.executeUpdate()>0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
