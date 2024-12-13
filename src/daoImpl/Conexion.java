package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static Conexion instancia;
	private Connection conexion;
	
	private Conexion() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancobd","root","valero633");
			this.conexion.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion() {								
		if(instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.conexion;
	}
	
	public void cerrarConexion() {
		try {
			this.conexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		instancia = null;
	}
}
