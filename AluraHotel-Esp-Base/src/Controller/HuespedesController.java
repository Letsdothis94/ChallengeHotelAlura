package Controller;

import java.util.List;

import DAO.HuespedesDAO;
import Factory.ConnectionFactory;
import model.Huespedes;

public class HuespedesController {
	
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		this.huespedesDAO = new HuespedesDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}
	
	public List<Huespedes> listar(){
		return this.huespedesDAO.listar();
	}

	public void eliminar(Integer id) {
		this.huespedesDAO.eliminar(id);
		
	}
	
}
