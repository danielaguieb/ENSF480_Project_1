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
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = ?";
		ResultSet result;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, buyer.getUserID());
			result = statement.executeQuery();
			if(result.next()){
				Double previous_payment = result.getDouble("outstanding_payments");
				Double new_payment = previous_payment - payment;
				if (new_payment < 0.0) new_payment = 0.0;
				sql = "UPDATE " + buyerTable + 
						" SET outstanding_payments = ?" +
						" WHERE userID = ?";
				statement = jdbc_connection.prepareStatement(sql);
				statement.setDouble(1, new_payment);
				statement.setInt(2, buyer.getUserID());
				statement.executeUpdate();
			}
			else {
				System.out.println("Error: Book not found");
				
			}
		} catch (SQLException e) { e.printStackTrace(); }
		
	}
	
	public void register(OrdinaryBuyer ordinaryBuyer)
	{
		String sql = "UPDATE " + buyerTable + 
				" SET registered = ? WHERE userID = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, ordinaryBuyer.getUserID());
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	

	public void unregister(RegisteredBuyer regBuyer)
	{
		String sql = "UPDATE " + buyerTable + 
				" SET registered = ? WHERE userID = ?" ;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setInt(2, regBuyer.getUserID());
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
}