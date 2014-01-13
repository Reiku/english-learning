package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Serveur implements Runnable{
	private int port;
	private ServerSocket ss;
	private ArrayList<ServeurClient> clients;
	private int nbClients;
	
	public Serveur(int p){
		try {
			port = p;
			clients = new ArrayList<ServeurClient>();
			nbClients = 0;
			ss = new ServerSocket(port);
			System.out.println("--------");
	        System.out.println("Serveur démarré sur le port " + port);
	        System.out.println("--------");
		} catch (IOException e) {
			System.out.println("[Erreur] : " + e);
		}
	}
	
	public void run(){
		try {
			while(true){
				try {
					new ServeurClient(ss.accept(), this);
					//System.out.println("Client " + nbClients + " connécté !");
				} catch (IOException e) {
					System.out.println("[Erreur] : " + e);
				}
			}
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				System.out.println("[Erreur] : " + e);
			}
		}
	}
	
	synchronized public void addClient(ServeurClient client){
		nbClients++;
		clients.add(client);
	}
	
	synchronized public void delClient(ServeurClient client){
		nbClients--;
		clients.remove(client);
	}
	
	synchronized public void sendAll(String data){
		for(ServeurClient client : clients){
			client.send(data);
		}
	}
	
	public static void main(String[] args){
		new Thread(new Serveur(3829)).start();
	}
}
