package serveur;

import java.net.Socket;

import core.Packet;
import core.SocketThread;

public class Client extends SocketThread {
	private Serveur serv;
	
	public Client(Socket socket, Serveur serveur) {
		super(socket);
		serv = serveur;
	}
	
	public void run(){
		super.run();
		serv.delClient(this);
	}
	
	protected void dataProcessing(Packet packet){
		System.out.println("[DataRead] Packet : " + packet);
		switch(packet.getName()) {
			case "login":
				// Login code
				this.send("loginOk");
				break;
			case "logout":
				// Logout code
				//this.send("logout");
				this.stop();
				break;
			default:
				//this.send("packetError");
				System.err.println("[Erreur] Packet inconnu : " + packet.getName());
		}
	}
	
}
