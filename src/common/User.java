package common;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -800238617589219099L;
	private String login;
	private String password;
	
	public User(String log, String pass){
		setLogin(log);
		setPassword(pass);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
