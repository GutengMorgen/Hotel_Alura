package Controladores;

import java.sql.Connection;

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
}
