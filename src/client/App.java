package client;

import client.controller.LoginController;
import client.listener.ServerListener;
import client.view.Login;

public class App {
	public static ServerListener socket;
	
	public static void main(String[] args){
		socket = new ServerListener("81.251.232.69", 3829);
		LoginController loginController = new LoginController(socket);
		Login login = new Login(loginController);
		socket.setObserver(login);
	}
}
