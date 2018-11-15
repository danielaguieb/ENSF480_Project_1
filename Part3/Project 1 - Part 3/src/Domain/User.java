package Domain;
import Presentation.*;

public abstract class User {

	private String username;
	private String password;
	private int userID;
	private LoginForm login;
	
	
	public User() {
		username = null;
		password = null;
		userID = 0;
		login = null;
	}

	public User(String u, String p, int i) {
		username = u;
		password = p;
		userID = i;
		login = null;
	}
	
	public User(String u, String p, int i, LoginForm l) {
		username = u;
		password = p;
		userID = i;
		login = l;
	}

}
