package serveur;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.MySQL;

import core.Image;
import core.Packet;
import core.SocketThread;
import core.User;

public class ClientListener extends SocketThread {
	private Serveur serv;
	private MySQL db;
	
	public ClientListener(Socket socket, Serveur serveur, MySQL mysql) {
		super(socket);
		serv = serveur;
		db = mysql;
	}
	
	public void run(){
		super.run();
		serv.delClient(this);
	}
	
	protected void dataProcessing(Packet packet){
		System.out.println("[DataRead] Packet : " + packet.toShortString());
		switch(packet.getName()) {
			case "login":
				this.login((User)packet.getData());
				break;
			case "file":
				Image file = (Image)packet.getData();
				file.save("res2");
				this.send("fileSaved");
				break;
			case "logout":
				this.send("logout");
				this.stop();
				break;
			default:
				this.send("packetError");
				System.err.println("[Erreur] Packet inconnu : " + packet.getName());
		}
	}
	
	private void login(User user){
		try {
			ResultSet query = db.query(
				"SELECT * FROM users WHERE LOWER(login) = '" + user.getLogin().toLowerCase() + "' AND password = '" + user.getPassword() + "';"
			);
			if(query.next() && query.getRow() > 0){
				this.send("loginOk");
            } else{
            	this.send("loginFail");
            }
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
}