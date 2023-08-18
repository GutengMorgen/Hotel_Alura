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
	
	public List<Huespedes> mostrar(){
		List<Huespedes> huespedes = new ArrayList<>();
		
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.execute();
			
			transformarResultados(huespedes, prepared);

			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<>();
		
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id=?";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.setString(1, id);
			prepared.execute();
			
			transformarResultados(huespedes, prepared);

			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva, Integer id) {
		String sql = "UPDATE huespedes SET" + 
				" nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=?, id_reserva=? WHERE id=?";
		
		try(PreparedStatement prepared = consql.prepareStatement(sql)){
			prepared.setString(1, nombre);
			prepared.setString(2, apellido);
			prepared.setObject(3, Date.valueOf(fechaNacimiento));
			prepared.setString(4, nacionalidad);
			prepared.setString(5, telefono);
			prepared.setInt(6, idReserva);
			prepared.setInt(7, id);
			prepared.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminar(Integer id) {
		String sql = "DELETE FROM huespedes WHERE id=?";
		
		try {
			PreparedStatement prepared = consql.prepareStatement(sql);
			prepared.setInt(1, id);
			prepared.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultados(List<Huespedes> huespedes, PreparedStatement prepared) throws SQLException {
		try(ResultSet result = prepared.getResultSet()){
			while(result.next()) {
				int id = result.getInt("id");
				String nombre = result.getString("nombre");
				String apellido = result.getString("apellido");
				LocalDate fechaNacimiento = result.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
				String nacionalidad = result.getString("nacionalidad");
				String telefono = result.getString("telefono");
				Integer idReserva = result.getInt("id_reserva");
				
				Huespedes producto = new Huespedes(idReserva, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
				huespedes.add(producto);
			}
		}
	}
}
