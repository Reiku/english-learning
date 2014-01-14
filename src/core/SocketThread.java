package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public abstract class SocketThread implements Runnable {
	protected final String REGEXP_SEP = "\\|~\\|";
	protected final String SEP = "|~|";
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private boolean stop;
	
	private void init(){
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
			stop = false;
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		} finally {
			new Thread(this).start();
		}
	}
	
	public SocketThread(Socket socket){
		sock = socket;
		this.init();
	}
	
	public SocketThread(String ip, int port){
		try {
			sock = new Socket(ip, port);
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		} finally {
			this.init();
		}
	}
	
	public void run() {
		String data = "";
		Packet packet;
        
		try {
			while(data != null && !stop){
				data = in.readLine();
				packet = new Packet(data);
				this.dataProcessing(packet);
			}
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		} finally {
			try {
				in.close();
				out.close();
				sock.close();
			} catch (IOException e) {
				System.err.println("[Erreur] " + e);
			}
		}
	}
	
	public void send(String name){
		this.send(name, null);
	}
	
	public void send(String name, Object data){
		Packet packet = new Packet(name, data);
		out.println(packet);
		System.out.println("[DataWrite] Packet : " + packet);
		
	}
	
	protected void stop(){
		stop = true;
	}
	
	protected abstract void dataProcessing(Packet packet);
	
}
