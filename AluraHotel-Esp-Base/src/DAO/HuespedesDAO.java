package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huespedes;

public class HuespedesDAO {

	private Connection con;
	
	public HuespedesDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huespedes huespedes) {
		String query = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
				+ "VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setObject(1, huespedes.getNombre());
			statement.setObject(2, huespedes.getApellido());
			statement.setObject(3, huespedes.getFechaDeNacimiento());
			statement.setString(4, huespedes.getNacionalidad());
			statement.setString(5, huespedes.getTelefono());
			statement.setInt(6, huespedes.getIdReserva());
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			 try (resultSet) {
                 while (resultSet.next()) {
                     huespedes.setId(resultSet.getInt(1));
                     System.out.println(String.format("Reserva registrada: %s", huespedes));
                 }
			 }
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Huespedes> listar() {
		List<Huespedes> huespedes = new ArrayList<>();
		try {
			String query = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
			try(PreparedStatement statement = con.prepareStatement(query)){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						huespedes.add(new Huespedes(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_nacimiento").toLocalDate(),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"),
								resultSet.getInt("id_reserva")));
					}
				}
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}		
	}

	public int eliminar(Integer id) {
		try {
			String query = "DELETE FROM huespedes WHERE id = ?";
			final PreparedStatement statement = con.prepareStatement(query);
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
