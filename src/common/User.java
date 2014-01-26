package common;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -800238617589219099L;
	private int id;
	private String login;
	private String password;
	private boolean prof;
	
	public User(String log, String pass){
		login = log;
		password = pass;
	}
	
	public User(int id, String log, boolean prof){
		this(log, "");
		this.id = id;
		this.prof = prof;
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

	public boolean isProf() {
		return prof;
	}

	public void setProf(boolean prof) {
		this.prof = prof;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
