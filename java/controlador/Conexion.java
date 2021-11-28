package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private String bd="Grupo8_Equipo_7";
	private String url="jdbc:mariadb://prestamosvf.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/"+bd;
	private String user="admin";
	private String pass="Ciclo3_admi123";
	Connection conn = null;
	
	public Connection Conecta() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);
			//JOptionPane.showMessageDialog(null, "Conexión con BD exitosa");
		}catch(Exception e) {
			//JOptionPane.showMessageDialog(null, "Error en la conexión: "+e);
		}
		return conn;
	}
}
