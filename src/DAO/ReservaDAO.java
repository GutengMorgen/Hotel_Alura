package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Reserva> mostrar(){
		List<Reserva> reservas = new ArrayList<>();
		
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.execute();
			
			transformarResultados(reservas, prepared);

			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id){
		List<Reserva> reservas = new ArrayList<>();
		
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas WHERE id=?";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.setString(1, id);
			prepared.execute();
			
			transformarResultados(reservas, prepared);

			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizar(LocalDate dateE, LocalDate dateS, String Valor, String FormaPago, Integer Id) {
		String sql = "UPDATE reservas SET" + 
				" fecha_entrada=?, fecha_salida=?, valor=?, forma_pago=? WHERE id=?";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.setObject(1, Date.valueOf(dateE));
			prepared.setObject(2, Date.valueOf(dateS));
			prepared.setString(3, Valor);
			prepared.setString(4, FormaPago);
			prepared.setInt(5, Id);
			prepared.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultados(List<Reserva> reservas, PreparedStatement prepared) throws SQLException {
		try(ResultSet result = prepared.getResultSet()){
			while(result.next()) {
				int id = result.getInt("id");
				LocalDate fechaE = result.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate fechaS = result.getDate("fecha_salida").toLocalDate().plusDays(1);
				String valor = result.getString("valor");
				String formaPago = result.getString("forma_pago");
				
				Reserva producto = new Reserva(id, fechaE, fechaS, valor, formaPago);
				reservas.add(producto);
				
			}
		}
	}
	
	public void eliminar(Integer id) {
		String sql = "DELETE FROM reservas WHERE id=?";
		
		try {
			Statement statement = consql.createStatement();
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement prepared = consql.prepareStatement(sql);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			statement.execute("SET FOREIGN_KEY_CHECKS=1");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
