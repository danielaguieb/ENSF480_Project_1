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
	
	
	
}