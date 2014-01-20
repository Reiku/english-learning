package serveur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Properties;

import util.MySQL;

public class Serveur implements Runnable{
	private ServerSocket ss;
	private ArrayList<ClientListener> clients;
	private MySQL db;
	
	public Serveur(){
    	try {
    		Properties properties = new Properties();
    		File res = new File("res");
    		File config = new File("config.txt");
    		
    		if(!res.exists()){
    			res.mkdir();
    			System.out.println("[Serveur] Création du dossier \"res\"");
    		}
    		
    		if(!config.exists()){
    			config.createNewFile();
    			FileOutputStream output = new FileOutputStream(config);
    			properties.setProperty("serverport", "server_port");
    			properties.setProperty("dbhost", "database_ip");
    			properties.setProperty("dbname", "database_name");
    			properties.setProperty("dbuser", "database_user");
    			properties.setProperty("dbpassword", "database_password");
    			properties.store(output, null);
    			output.close();
    			System.out.println("[Serveur] Création du fichier \"config.txt\"");
    			System.out.println("[Serveur] Veuillez remplir le fichier \"config.txt\"");
    			System.out.println("Appuyer sur une Entrer pour relancer le serveur une fois le fichier \"config.txt\" rempli");
    			System.in.read();
    			new Serveur();
    		} else {
    			FileInputStream input = new FileInputStream(config);
    			properties.load(input);
    			input.close();
    			clients = new ArrayList<ClientListener>();
    			ss = new ServerSocket(Integer.valueOf(properties.getProperty("serverport")));
    			db = new MySQL(properties.getProperty("dbhost"), properties.getProperty("dbname"), properties.getProperty("dbuser"), properties.getProperty("dbpassword"));
    			System.out.println("[Serveur] Démarré sur le port " + properties.getProperty("serverport"));
    	        new Thread(this).start();
    		}
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	public void run(){
		try {
			while(true){
				try {
					this.addClient(new ClientListener(ss.accept(), this, db));
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
	
	synchronized public void addClient(ClientListener client){
		System.out.println("[Serveur] Un client c'est connecté");
		clients.add(client);
	}
	
	synchronized public void delClient(ClientListener client){
		System.out.println("[Serveur] Un client c'est déconnecté");
		clients.remove(client);
	}
	
	synchronized public void sendAll(String name, Object data){
		for(ClientListener client : clients){
			client.send(name, data);
		}
	}
	
	public static void main(String[] args){
		new Serveur();
	}

}
