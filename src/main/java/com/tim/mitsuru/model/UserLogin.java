package  com.tim.mitsuru.model;
public class UserLogin {

	private String username;

	private String password;


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserLogin() {
		
	}
}

