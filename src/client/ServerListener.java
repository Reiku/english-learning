package client;

import core.Packet;
import core.SocketThread;

public class ServerListener extends SocketThread {
	
	public ServerListener(String ip, int port){
		super(ip, port);
	}
	
	public void run(){
		super.run();
		System.exit(0);
	}
	
	protected void dataProcessing(Packet packet){
		System.out.println("[DataRead] Packet : " + packet.toShortString());
		switch(packet.getName()) {
			case "loginOk":
				System.out.println("LOGIN OK !");
				break;
			case "loginFail":
				System.out.println("LOGIN FAIL !");
				break;
			case "logout":
				this.stop();
				break;
		}
	}

}
