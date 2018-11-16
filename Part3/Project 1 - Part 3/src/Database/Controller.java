package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class Controller
{
	protected Connection jdbc_connection;
	protected PreparedStatement statement;
	protected String databaseName;
	protected String connectionInfo = "jdbc:mysql://localhost:3306/",  
			  				login          = "root",
			  				password       = "password";
	
	public Controller(String databaseName)
	{
		try {
			this.databaseName = databaseName;
			connectionInfo += databaseName;
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			statement = null;
		} catch (SQLException e) {
			System.out.println("Error: document database cannot be connected to!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: the jdbc is not properly installed");
		}
	}
}