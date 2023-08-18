package Factory;

import java.sql.Connection;
import java.sql.SQLException;

public class testConexion {
	
	public static void main(String[] args) throws SQLException {
		ConexionBase base = new ConexionBase();
		Connection connection = base.conectarBase();
		
		System.out.println("start");
		
		connection.close();
		
		System.out.println("close");
	}
}
