package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Factory.ConexionBase;

public class Usuarios {
	private String usuarios;
	private String contrasena;
	
	
	public Usuarios() {
	}


	public Usuarios(String usuarios, String contrasena) {
		this.usuarios = usuarios;
		this.contrasena = contrasena;
	}


	public String getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public static boolean validarUser(String usuario, String contrasena) {
		ConexionBase base = new ConexionBase();
		Connection consql = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			consql = base.conectarBase();
			statement = consql.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contrasena=?");
			statement.setString(1, usuario);
			statement.setString(2, contrasena);
			result = statement.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(result != null)
					result.close();
				if(statement != null)
					statement.close();
				if(consql != null)
					consql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
