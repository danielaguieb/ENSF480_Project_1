package Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DocumentDatabaseController extends Controller
{
	private String bookTable;
	private String journalTable;
	private String magazineTable;
	
	public DocumentDatabaseController()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			databaseName = "documentdatabase";
			bookTable = "book";
			journalTable = "journal";
			magazineTable = "magazine";
		} catch (SQLException e) {
			System.out.println("Error: document database cannot be connected to!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: the jdbc is not properly installed");
		}
	}
}