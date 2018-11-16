package Database;

import Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseController extends Controller
{
	private String buyerTable;
	private String operatorTable; //might not even be used
	
	public UserDatabaseController()
	{
		super("userdatabase");
		buyerTable = "buyer";
		operatorTable = "operator";
	}
	
	public void makePayments(OrdinaryBuyer buyer, Double payment)
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = " + buyer.getUserID() + ";";
		ResultSet result;
		try {
			statement = jdbc_connection.createStatement();
			result = statement.executeQuery(sql);
			if(result.next()){
				Double previous_payment = result.getDouble("outstanding_payments");
				Double new_payment = previous_payment - payment;
				if (new_payment < 0.0) new_payment = 0.0;
				sql = "UPDATE " + buyerTable + 
						" SET outstanding_payments = " + new_payment +
						" WHERE userID = " + buyer.getUserID() + ";";
				statement = jdbc_connection.createStatement();
				statement.executeUpdate(sql);
			}
			else {
				System.out.println("Error: Book not found");
				
			}
		} catch (SQLException e) { e.printStackTrace(); }
		
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