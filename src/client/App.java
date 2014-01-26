package client;

import client.controller.LoginController;
import client.listener.ServerListener;
import client.view.Login;

import common.User;

public class App {
	public static ServerListener socket;
	public static User user;
	
	public static void main(String[] args){	
		socket = new ServerListener("62.210.243.12", 3829);
		Login login = new Login(new LoginController(socket));
		socket.setObserver(login);
	}
}
