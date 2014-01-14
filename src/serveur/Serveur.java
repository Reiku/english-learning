package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import util.MySQL;

public class Serveur implements Runnable{
	private int port;
	private ServerSocket ss;
	private ArrayList<Client> clients;
	private MySQL db;
	
	public Serveur(int p){
		try {
			port = p;
			clients = new ArrayList<Client>();
			ss = new ServerSocket(port);
			db = new MySQL("62.210.243.12", "projet", "projet", "V5wS2mR7zwD6z3NP");
	        System.out.println("[Serveur] Démarré sur le port " + port);
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	public void run(){
		try {
			while(true){
				try {
					this.addClient(new Client(ss.accept(), this, db));
				} catch (IOException e) {
					System.err.println("[Erreur] " + e);
				}
			}
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				System.err.println("[Erreur] " + e);
			}
		}
	}
	
	synchronized public void addClient(Client client){
		System.out.println("[Serveur] Un client c'est connecté");
		clients.add(client);
	}
	
	synchronized public void delClient(Client client){
		System.out.println("[Serveur] Un client c'est déconnecté");
		clients.remove(client);
	}
	
	synchronized public void sendAll(String name, Object data){
		for(Client client : clients){
			client.send(name, data);
		}
	}
	
	public static void main(String[] args){
		new Thread(new Serveur(3829)).start();
	}

}
