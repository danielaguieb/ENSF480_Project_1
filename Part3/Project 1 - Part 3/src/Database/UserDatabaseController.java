package Database;

import Domain.*;
import java.sql.SQLException;

public class UserDatabaseController extends Controller
{
	private String userTable;
	
	public UserDatabaseController()
	{
		super("userdatabase");
		userTable = "user";
	}
	
	public String register(OrdinaryBuyer ordinaryBuyerbuyer)
	{
		
		
		return null;
	}
	
//	String sql = "SELECT * FROM " + tableName + " WHERE ID=" + toolID;
//	ResultSet tool;
//	try {
//		statement = jdbc_connection.createStatement();
//		tool = statement.executeQuery(sql);
//		if(tool.next())
//		{
//			return new Tool(tool.getInt("ID"),
//							tool.getString("TOOLNAME"), 
//							tool.getInt("QUANTITY"), 
//							tool.getDouble("PRICE"), 
//							tool.getInt("SUPPLIERID"));
//		}
//	
//	} catch (SQLException e) { e.printStackTrace(); }
//	
//	return null;
	
}