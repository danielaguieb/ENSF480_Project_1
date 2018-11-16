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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public LoginForm getLogin() {
		return login;
	}

	public void setLogin(LoginForm login) {
		this.login = login;
	}

}
