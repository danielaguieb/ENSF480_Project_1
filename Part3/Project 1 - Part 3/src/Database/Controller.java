package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Controller
{
	protected Connection jdbc_connection;
	protected Statement statement;
	protected String databaseName;
	protected static String connectionInfo = "jdbc:mysql://localhost:3306/",  
			  				login          = "root",
			  				password       = "password";
	
	public Controller()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			databaseName = "documentdatabase";
		} catch (SQLException e) {
			System.out.println("Error: document database cannot be connected to!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: the jdbc is not properly installed");
		}
	}
}