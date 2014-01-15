package serveur;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Properties;

import util.MySQL;

public class Serveur implements Runnable{
	private ServerSocket ss;
	private ArrayList<Client> clients;
	private MySQL db;
	
	public Serveur(){
    	try {
    		Properties properties = new Properties();
    		properties.load(new FileInputStream("config.properties"));
			clients = new ArrayList<Client>();
			ss = new ServerSocket(Integer.valueOf(properties.getProperty("serverport")));
			db = new MySQL(properties.getProperty("dbhost"), properties.getProperty("dbname"), properties.getProperty("dbuser"), properties.getProperty("dbpassword"));
	        System.out.println("[Serveur] Démarré sur le port " + properties.getProperty("serverport"));
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
		new Thread(new Serveur()).start();
	}

}
