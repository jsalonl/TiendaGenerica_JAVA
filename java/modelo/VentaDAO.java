package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Conexion;

public class VentaDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * Funcion para tomar consecutivo
	 * @return ultimo id
	 */
	public long consecutivoVenta(){
		long id=1;
		try {
			String sql = "SELECT MAX(codigo_venta) FROM venta";
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
	 
			if (rs.next()) {
				id = rs.getLong(1)+1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return id;
	}
	
	/**
	 * Funcion para insertar venta en DB
	 * @param dto
	 * @return
	 */
	public boolean insertarVenta(VentaDTO dto) {
		
		boolean result = false;
		try {
			String sql = "INSERT INTO venta VALUES(?,?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCodigoVenta());
			ps.setLong(2, dto.getCedulaCliente());
			ps.setLong(3, dto.getCedulaUsuario());
			ps.setDouble(4, dto.getIvaVenta());
			ps.setDouble(5, dto.getTotalVenta());
			ps.setDouble(6, dto.getValorVenta());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}

}
