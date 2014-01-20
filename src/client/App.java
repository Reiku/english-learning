package client;

import client.controller.LoginController;
import client.listener.ServerListener;
import client.view.Login;

public class App {
	public static ServerListener socket;
	
	public static void main(String[] args){
		socket = new ServerListener("127.0.0.1", 3829);
		LoginController loginController = new LoginController(socket);
		Login login = new Login(loginController);
		socket.setObserver(login);
	}
}
