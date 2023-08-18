package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Huespedes;
import models.Reserva;

public class HuespedesDAO {
	private Connection consql;

	public HuespedesDAO(Connection consql) {
		super();
		this.consql = consql;
	}
	
	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)" +
					"VALUES (?,?,?,?,?,?)";
			try(PreparedStatement prepared = consql.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				prepared.setString(1, huespedes.getNombre());
				prepared.setString(2, huespedes.getApellido());
				prepared.setObject(3, huespedes.getFechaNacimiento());
				prepared.setString(4, huespedes.getNacionalidad());
				prepared.setString(5, huespedes.getTelefono());
				prepared.setInt(6, huespedes.getIdReserva());
				
				int rowsAffected = prepared.executeUpdate();

	            if (rowsAffected > 0) {
	                try (ResultSet result = prepared.getGeneratedKeys()) {
	                    while (result.next()) {
	                    	huespedes.setId(result.getInt(1));
	                    }
	                }
	            }

//	            System.out.println(reserva.toString());
				
			};
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
