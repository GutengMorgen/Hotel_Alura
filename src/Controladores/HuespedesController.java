package Controladores;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import DAO.HuespedesDAO;
import Factory.ConexionBase;
import models.Huespedes;

public class HuespedesController {
	
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		Connection consql = new ConexionBase().conectarBase();
		
		this.huespedesDAO = new HuespedesDAO(consql);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}
	
	public List<Huespedes> mostrar(){
		return this.huespedesDAO.mostrar();
	}
	
	public List<Huespedes> buscar(String id){
		return this.huespedesDAO.buscarId(id);
	}
	
	public void actualizar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva, Integer id) {
		this.huespedesDAO.actualizar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
	}
	
	public void eliminar(Integer id) {
		this.huespedesDAO.eliminar(id);
	}
}
