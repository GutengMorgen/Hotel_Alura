package Controladores;

import java.sql.Connection;
import java.util.List;

import DAO.ReservaDAO;
import Factory.ConexionBase;
import models.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection consql = new ConexionBase().conectarBase();
		
		this.reservaDAO = new ReservaDAO(consql);
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> mostrar(){
		return this.reservaDAO.mostrar();
	}
	
	public List<Reserva> buscar(String id){
		return this.reservaDAO.buscarId(id);
	}
}
