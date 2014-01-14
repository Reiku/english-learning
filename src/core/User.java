package core;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -800238617589219099L;
	public String login;
	public String password;
	
	public User(String log, String pass){
		login = log;
		password = pass;
	}
	
}
