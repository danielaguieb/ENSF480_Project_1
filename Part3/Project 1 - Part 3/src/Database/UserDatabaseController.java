package Database;

import Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDatabaseController extends Controller
{
	private String buyerTable;
	private String operatorTable; //might not even be used
	private PromotionList promotionList;
 
	
	public UserDatabaseController()
	{
		super("userdatabase");
		buyerTable = "buyer";
		operatorTable = "operator";
		promotionList = PromotionList.getInstance();
		promotionList.setObservers(getPromotionBuyers());
	}
	
	public void addToOutstandingPayments(int userID, String docName, double price)
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = ?";
		ResultSet result;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, userID);
			result = statement.executeQuery();
			if(result.next()){
				double currentPayments = result.getDouble("outstanding_payments");
				double newPayments = currentPayments + price;
				sql = "UPDATE " + buyerTable + 
						" SET outstanding_payments = ?" +
						" WHERE userID = ?";
				statement = jdbc_connection.prepareStatement(sql);
				statement.setDouble(1, newPayments);
				statement.setInt(2, userID);
				statement.executeUpdate(); 
			}
			else {
				System.out.println("Error: User's outstanding payments could not be updated");
			}
		} catch (SQLException e) { e.printStackTrace(); }	
	}
	
	public double checkPayments(int userID)
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = ?";
		ResultSet result;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, userID);
			result = statement.executeQuery();
			if(result.next()){
				return result.getDouble("outstanding_payments");
			}
			else {
				System.out.println("Error: Book not found");
				
			}
		} catch (SQLException e) { e.printStackTrace(); }	
		return -1;
	}
	
	public void makePayments(int userID, Double payment)
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = ?";
		ResultSet result;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, userID);
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
				statement.setInt(2, userID);
				statement.executeUpdate();
			}
			else {
				System.out.println("Error: Book not found");
				
			}
		} catch (SQLException e) { e.printStackTrace(); }		
	}
	
	public void register(int userID)
	{
		String sql = "UPDATE " + buyerTable + 
				" SET registered = ? WHERE userID = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, userID);
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
		
	public String getInfo(String username, String password, String tableName)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";
		ResultSet result;
		String resultInfo = "";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1,username);
			statement.setString(2, password);
			result = statement.executeQuery();
			if(result.next()){
				resultInfo += Integer.toString(result.getInt("userID")) + ";" +
						result.getString("name") + ";" +
						Integer.toString(result.getInt("registered")) + ";" +
						result.getString("outstanding_payments");
			}
			else {
				System.out.println("Error: User Info could not be found");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return resultInfo;

	}
	
	public ArrayList<Observer> getPromotionBuyers()
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE registered = ?";
		ResultSet result;
		ArrayList<Observer> observers = new ArrayList<Observer>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1,1);
			result = statement.executeQuery();
			while(result.next()){
				observers.add(new RegisteredBuyer(result.getString("username"), 
						result.getString("password"), result.getInt("userID")));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return observers;
	}
	
	public void unsubscribe(int userID) {
		RegisteredBuyer registeredBuyer = new RegisteredBuyer(null, null, userID);
		promotionList.remove(registeredBuyer); 
		// move unsuscribe from here to the database
	}
	
	//remove the name field maybe?
	
}