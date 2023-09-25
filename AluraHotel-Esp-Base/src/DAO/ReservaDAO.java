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

import model.Reserva;

public class ReservaDAO {
	
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
		String query = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)"
				+ "VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setObject(1, reserva.getFechaEntrada());
			statement.setObject(2, reserva.getFechaSalida());
			statement.setString(3, reserva.getValor());
			statement.setString(4, reserva.getFormaPago());
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			 try (resultSet) {
                 while (resultSet.next()) {
                     reserva.setId(resultSet.getInt(1));
                     System.out.println(String.format("Reserva registrada: %s", reserva));
                 }
			 }
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Reserva> listar(){
		List<Reserva> reservas = new ArrayList<>();
		try {
			String query = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
			try (PreparedStatement statement = con.prepareStatement(query)){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						reservas.add(new Reserva(resultSet.getInt("id"), 
								resultSet.getDate("fecha_entrada").toLocalDate(), 
								resultSet.getDate("fecha_salida").toLocalDate(), 
								resultSet.getString("valor"), 
								resultSet.getString("forma_de_pago")));
					}
				}
			}
			return reservas;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Reserva> buscarId(String id){
		List<Reserva> reservas = new ArrayList<>();
		try {
			String query = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";
			try (PreparedStatement statement = con.prepareStatement(query)){
				statement.setString(1, id);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						reservas.add(new Reserva(resultSet.getInt("id"), 
								resultSet.getDate("fecha_entrada").toLocalDate(), 
								resultSet.getDate("fecha_salida").toLocalDate(), 
								resultSet.getString("valor"), 
								resultSet.getString("forma_de_pago")));
					}
				}
			}
			return reservas;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		try {
			String query = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_Pago = ? WHERE id = ?";
			final PreparedStatement statement = con.prepareStatement(query);
			try(statement){
				statement.setObject(1, fechaE);
				statement.setObject(2, fechaS);
				statement.setString(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
				statement.execute();
				System.out.println("Modificando una fila");
			}
		} catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public int eliminar(Integer id) {
		try {
			String query = "DELETE FROM reservas WHERE ID = ?";
			final PreparedStatement statement = con.prepareStatement(query);
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
