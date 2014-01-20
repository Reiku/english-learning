package client;

import javax.swing.JOptionPane;

import client.controller.LoginController;
import client.observer.Observable;
import client.observer.Observer;
import client.view.Login;
import core.Packet;
import core.SocketThread;

public class ServerListener extends SocketThread implements Observable {
	private Observer observer;
	
	public ServerListener(String ip, int port){
		super(ip, port);
	}
	
	public void run(){
		super.run();
		System.exit(0);
	}
	
	protected void dataProcessing(Packet packet){
		System.out.println("[DataRead] Packet : " + packet.toShortString());
		switch(packet.getName()) {
			case "loginOk":
				observer.dispose();
				LoginController loginController = new LoginController(this);
				Login login = new Login(loginController);
				this.setObserver(login);
				break;
			case "loginFail":
				this.notifyObserver("Login / Password incorrect !", "Erreur lors de la connexion", JOptionPane.ERROR_MESSAGE);
				break;
			case "logout":
				this.stop();
				break;
		}
	}
	
	public void setObserver(Observer obs){
		observer = obs;
	}
	
	public Observer getObserver(){
		return observer;
	}
	
	public void notifyObserver(String message, String title, int type){
		observer.update(message, title, type);
	}

}
