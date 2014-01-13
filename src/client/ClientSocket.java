package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket implements Runnable {
	private final String REGEXP_SEP = "\\|~\\|";
	private final String SEP = "|~|";
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientSocket(String ip, int port){
		try {
			sock = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		} finally {
			new Thread(this).start();
		}
	}
	
	public void run() {
		String packet = "";
		String[] data;
        
		try {
			while(packet != null){
				packet = in.readLine();
				data = packet.split(REGEXP_SEP);
				System.out.println("[DataRead] Packet : " + packet);
				switch(data[0]) {
					case "login":
						// Login code
						//this.send("login");
						break;
					case "logout":
						// Logout code
						packet = null;
						break;
					default:
						//this.send("packetError");
						System.err.println("[Erreur] Packet inconnu : " + data[0]);
				}
			}
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		} finally {
			try {
				in.close();
				out.close();
				sock.close();
				System.exit(0);
			} catch (IOException e) {
				System.err.println("[Erreur] " + e);
			}
		}
	}

	public void send(String data){
		out.println(data + SEP);
		System.out.println("[DataWrite] Packet : " + data + SEP);
	}

}
