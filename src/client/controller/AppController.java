package client.controller;

import client.listener.ServerListener;

public abstract class AppController {
	protected ServerListener model;
	
	public AppController(ServerListener model){
		this.model = model;
	}

}
