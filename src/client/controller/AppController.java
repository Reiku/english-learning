package client.controller;

import client.ServerListener;

public abstract class AppController {
	protected ServerListener model;
	
	public AppController(ServerListener model){
		this.model = model;
	}

}
