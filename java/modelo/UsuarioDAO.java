package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Conexion;

public class UsuarioDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * Función para insertar Usuarios en DB
	 * @param dto
	 * @return true/false
	 */
	public boolean insertarUsuario(UsuarioDTO dto) {
		
		boolean result = false;
		try {
			String sql = "INSERT INTO usuario VALUES(?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCedulaUsuario());
			ps.setString(2, dto.getEmailUsuario());
			ps.setString(3, dto.getNombreUsuario());
			ps.setString(4, dto.getPassword());
			ps.setString(5, dto.getUsuario());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Función para listar Usuarios desde BD
	 * @return Lista
	 */
	public ArrayList<UsuarioDTO> listarUsuarios(){
		
		UsuarioDTO uDTO = null;
		ArrayList<UsuarioDTO> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM usuario";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			while (rs.next()) {
				uDTO = new UsuarioDTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				lista.add(uDTO);
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar los libros: "+ex);
		}
		
		return lista;
	}
	
	/**
	 * Funcion para buscar usuario por cedula
	 * @param cedula
	 * @return UsuarioDTO
	 */
	public UsuarioDTO obtenerPorCedula(long cedula){
		UsuarioDTO usuario = null;
 
		try {
			String sql = "SELECT * FROM usuario WHERE cedula_usuario= ? ";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, cedula);
			rs = ps.executeQuery();
	 
			if (rs.next()) {
				usuario = new UsuarioDTO(
						rs.getLong("cedula_usuario"),
						rs.getString("email_usuario"),
						rs.getString("nombre_usuario"),
						rs.getString("password"),
						rs.getString("usuario")
						);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	/**
	 * Funcion para actualizar Usuario
	 * @param dto
	 * @return true/false
	 */
	public boolean actualizarUsuario(UsuarioDTO dto) {
		boolean result = false;
		try {
			String sql = "UPDATE usuario SET email_usuario=?, nombre_usuario=?, password=?, usuario=? WHERE cedula_usuario=?";
			ps = connect.prepareStatement(sql);
			ps.setString(1, dto.getEmailUsuario());
			ps.setString(2, dto.getNombreUsuario());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getUsuario());
			ps.setLong(5, dto.getCedulaUsuario());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * Funcion para eliminar usuario
	 * @param dto
	 * @return true/false
	 */
	public boolean eliminarUsuario(UsuarioDTO dto) {
		boolean result = false;
		try {
			String sql = "DELETE FROM usuario WHERE cedula_usuario=?";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCedulaUsuario());
			result = ps.executeUpdate()>0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Funcion Login del sistema
	 * @param u usuario
	 * @param p password
	 * @return
	 * @throws SQLException
	 */
	public UsuarioDTO loginUsuario(String u, String p) throws SQLException{
		UsuarioDTO usuario = null;
 
		String sql = "SELECT * FROM usuario WHERE usuario= ? AND password=?";
		ps = connect.prepareStatement(sql);
		ps.setString(1, u);
		ps.setString(2, p);
		rs = ps.executeQuery();
 
		if (rs.next()) {
			usuario = new UsuarioDTO(
					rs.getLong("cedula_usuario"),
					rs.getString("email_usuario"),
					rs.getString("nombre_usuario"),
					rs.getString("password"),
					rs.getString("usuario")
					);
		}
		return usuario;
	}
	
}
