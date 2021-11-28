package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Conexion;

public class ReporteDAO{
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Funcion para listar Usuarios
	 * @return
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
	 * Funcion para listar clientes
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
	 * Funcion para listar ventas por cliente
	 * @return
	 */
	public ArrayList<ReporteVentasClienteDTO> listarVentasPorCliente(){
		
		ReporteVentasClienteDTO rvcDTO = null;
		ArrayList<ReporteVentasClienteDTO> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT c.cedula_cliente, c.nombre_cliente, SUM(v.valor_venta) FROM venta  AS v LEFT JOIN cliente AS c ON v.cedula_cliente = c.cedula_cliente GROUP BY c.cedula_cliente";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			while (rs.next()) {
				rvcDTO = new ReporteVentasClienteDTO(
					rs.getLong(1),
					rs.getString(2),
					rs.getDouble(3)
				);
				lista.add(rvcDTO);
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar los clientes: "+ex);
		}
		
		return lista;
	}
	
	
}
