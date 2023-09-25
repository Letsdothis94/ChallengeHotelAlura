package Factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZon=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("Oracle1994!");
		pooledDataSource.setMaxPoolSize(20);
		
		this.dataSource = pooledDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
			System.out.println("On!");
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}
	
}
