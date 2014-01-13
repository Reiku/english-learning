package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Serveur implements Runnable{
	private int port;
	private ServerSocket ss;
	private ArrayList<ClientThread> clients;
	private int nbClients;
	
	public Serveur(int p){
		try {
			port = p;
			clients = new ArrayList<ClientThread>();
			nbClients = 0;
			ss = new ServerSocket(port);
			System.out.println("--------");
	        System.out.println("Serveur d�marr� sur le port " + port);
	        System.out.println("--------");
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	public void run(){
		try {
			while(true){
				try {
					this.addClient(new ClientThread(ss.accept(), this));
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
	
	synchronized public void addClient(ClientThread client){
		nbClients++;
		clients.add(client);
	}
	
	synchronized public void delClient(ClientThread client){
		nbClients--;
		clients.remove(client);
	}
	
	synchronized public void sendAll(String data){
		for(ClientThread client : clients){
			client.send(data);
		}
	}
	
	public static void main(String[] args){
		new Thread(new Serveur(3829)).start();
	}

	public int getNbClients() {
		return nbClients;
	}

}
