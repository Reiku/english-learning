package client.controller;

import util.MD5;
import client.ServerListener;
import core.User;

public class LoginController extends AppController {

	public LoginController(ServerListener model) {
		super(model);
	}
	
	public void setUser(String login, String password){
		User user = new User(login, MD5.getHashString(password));
		model.send("login", user);
	}

}
