package client.listener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import client.App;
import client.controller.HomeController;
import client.controller.ListController;
import client.controller.StatsListController;
import client.observer.Observable;
import client.observer.Observer;
import client.view.Home;
import client.view.List;
import client.view.StatsList;

import common.Packet;
import common.SocketThread;
import common.User;
import common.exercise.Exercise;
import common.exercise.Note;

public class ServerListener extends SocketThread implements Observable {
	private Observer observer;
	
	public ServerListener(String ip, int port){
		super(ip, port);
	}
	
	public void run(){
		super.run();
		System.exit(0);
	}
	
	@SuppressWarnings("unchecked")
	protected void dataProcessing(Packet packet){
		List list = null;
		System.out.println("[DataRead] Packet : " + packet.toShortString());
		switch(packet.getName()) {
			case "loginOk":
				App.user = (User)packet.getData();
				observer.dispose();
				Home home = new Home(new HomeController(this));
				this.setObserver(home);
				break;
			case "loginFail":
				this.notifyObserver("Login / Password incorrect !", "Erreur lors de la connexion", JOptionPane.ERROR_MESSAGE);
				break;
			case "logout":
				this.stop();
				break;
			case "listTrous":
				observer.dispose();
				list = new List(new ListController(this, "Texte à trous", (ArrayList<Exercise>)packet.getData()));
				this.setObserver(list);
				break;
			case "listDictees":
				observer.dispose();
				list = new List(new ListController(this, "Dictées", (ArrayList<Exercise>)packet.getData()));
				this.setObserver(list);
				break;
			case "listSens":
				observer.dispose();
				list = new List(new ListController(this, "Images & sens", (ArrayList<Exercise>)packet.getData()));
				this.setObserver(list);
				break;
			case "listStats":
				observer.dispose();
				StatsList statsList = new StatsList(new StatsListController(this, (HashMap<String, ArrayList<Note>>)packet.getData()));
				this.setObserver(statsList);
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
