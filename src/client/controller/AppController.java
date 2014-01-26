package client.controller;

import client.listener.ServerListener;
import client.observer.Observer;

public abstract class AppController {
	protected ServerListener model;
	
	public AppController(ServerListener model){
		this.model = model;
	}
	
	public void changeView(Observer observer){
		model.getObserver().dispose();
		model.setObserver(observer);
	}
	
	public ServerListener getModel(){
		return model;
	}
	
	public void texteTrous(){
		model.send("getTrous");
	}
	
	public void imageSens(){
		model.send("getSens");
	}
	
	public void dictees(){
		model.send("getDictees");
	}
	
	public void stats(){
		model.send("getStats");
	}

}
