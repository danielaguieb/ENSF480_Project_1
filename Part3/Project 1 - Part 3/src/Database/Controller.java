package Database;

import java.sql.Connection;
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
}