package Controladores;

import java.sql.Connection;

import DAO.HuespedesDAO;
import DAO.ReservaDAO;
import Factory.ConexionBase;
import models.Huespedes;
import models.Reserva;

public class HuespedesController {
	
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		Connection consql = new ConexionBase().conectarBase();
		
		this.huespedesDAO = new HuespedesDAO(consql);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}
}
