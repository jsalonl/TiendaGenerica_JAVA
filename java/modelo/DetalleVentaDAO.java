package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Conexion;

public class DetalleVentaDAO {
	
	Conexion conn = new Conexion();
	Connection connect = conn.Conecta();
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Funcion para insertar detalle de venta
	 * @param dto
	 * @return
	 */
	public boolean insertarDetalleVenta(DetalleVentaDTO dto) {
		
		boolean result = false;
		try {
			String sql = "INSERT INTO detalle_venta VALUES(?,?,?,?,?,?,?)";
			ps = connect.prepareStatement(sql);
			ps.setLong(1, dto.getCodigoDetalleVenta());
			ps.setInt(2, dto.getCantidadProducto());
			ps.setLong(3, dto.getCodigoProducto());
			ps.setDouble(4, dto.getCodigoVenta());
			ps.setDouble(5, dto.getValorTotal());
			ps.setDouble(6, dto.getValorVenta());
			ps.setDouble(7, dto.getValorIva());
			result = ps.executeUpdate()>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;	
	}
}
