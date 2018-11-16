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
	
	public String register(OrdinaryBuyer ordinaryBuyer)
	{
		String sql = "SELECT * FROM " + buyerTable + " WHERE userID = " + ordinaryBuyer.;
		
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