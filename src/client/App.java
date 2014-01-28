package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import client.controller.LoginController;
import client.listener.ServerListener;
import client.view.Login;
import common.User;

public class App {
	public static ServerListener socket;
	public static User user;
	
	public static void main(String[] args){	
		Properties properties = new Properties();
		try {
			File config = new File("config.txt");
			FileInputStream input = new FileInputStream(config);
			properties.load(input);
			input.close();
			socket = new ServerListener(properties.getProperty("serverip"), Integer.valueOf(properties.getProperty("serverport")));
			Login login = new Login(new LoginController(socket));
			socket.setObserver(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
