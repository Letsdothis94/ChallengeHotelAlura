package tests;

import java.sql.Connection;
import java.sql.SQLException;

import Factory.ConnectionFactory;

public class ConnectionTest {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.recuperaConexion();
		System.out.println("Conexion inicializada");
		System.out.println("Cerrando Conexion...");
		con.close();
		System.out.println("done");
	}
	
}
