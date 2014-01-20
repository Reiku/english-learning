package client.controller;

import common.User;

import util.MD5;
import client.listener.ServerListener;

public class LoginController extends AppController {

	public LoginController(ServerListener model) {
		super(model);
	}
	
	public void setUser(String login, String password){
		User user = new User(login, MD5.getHashString(password));
		model.send("login", user);
	}

}
