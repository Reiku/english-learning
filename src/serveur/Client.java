package serveur;

import java.net.Socket;

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
	
	protected void dataProcessing(String packet){
		System.out.println("[DataRead] Packet : " + packet);
		String[] data = packet.split(REGEXP_SEP);
		switch(data[0]) {
			case "login":
				// Login code
				this.send("login");
				break;
			case "logout":
				// Logout code
				this.send("logout");
				this.stop();
				break;
			default:
				this.send("packetError");
				System.err.println("[Erreur] Packet inconnu : " + data[0]);
		}
	}
	
}
