package Database;

import Domain.*;
import java.sql.SQLException;

public class UserDatabaseController extends Controller
{
	private String buyerTable;
	private String operatorTable;
	
	public UserDatabaseController()
	{
		super("userdatabase");
		buyerTable = "buyer";
		operatorTable = "operator";
	}
	
	public void register(OrdinaryBuyer ordinaryBuyer)
	{
		String sql = "UPDATE " + buyerTable + 
				" SET registered = 1" +
				" WHERE userID = " + ordinaryBuyer.getUserID()
				+ ";";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	

	public void unregister(RegisteredBuyer regBuyer)
	{
		String sql = "UPDATE " + buyerTable + 
				" SET registered = 0" +
				" WHERE userID = " + regBuyer.getUserID()
				+ ";";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
}