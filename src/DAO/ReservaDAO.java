package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Reserva;

public class ReservaDAO {
	private Connection consql;

	public ReservaDAO(Connection consql) {
		super();
		this.consql = consql;
	}
	
	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago)" +
					"VALUES (?,?,?,?)";
			try(PreparedStatement prepared = consql.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				prepared.setObject(1, reserva.getDateE());
				prepared.setObject(2, reserva.getDateS());
				prepared.setString(3, reserva.getValor());
				prepared.setString(4, reserva.getFormaPago());
				
				int rowsAffected = prepared.executeUpdate();

	            if (rowsAffected > 0) {
	                try (ResultSet result = prepared.getGeneratedKeys()) {
	                    while (result.next()) {
	                        reserva.setId(result.getInt(1));
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
