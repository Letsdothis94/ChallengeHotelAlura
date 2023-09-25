package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import Factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> listar(){
		return this.reservaDAO.listar();
	}
	
	public int eliminar(Integer id) {
		return this.reservaDAO.eliminar(id);
	}
	
	public List<Reserva> buscar(String id) {
		return this.reservaDAO.buscarId(id);
	}
	
	public void modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		this.reservaDAO.modificar(id, fechaE, fechaS, valor, formaPago);
	}
	
}
