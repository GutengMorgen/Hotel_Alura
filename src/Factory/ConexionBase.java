package Factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionBase {
	public DataSource dataSource;
	
	public ConexionBase() {
		ComboPooledDataSource comboPooled = new ComboPooledDataSource();
		comboPooled.setJdbcUrl("jdbc:mysql://localhost:3306/hotelalura"); //connection to the schema
		comboPooled.setUser("root"); //read from a file
		comboPooled.setPassword("kEo2543");
		this.dataSource = comboPooled;
	}
	
	public Connection conectarBase() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
