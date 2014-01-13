package client;

import core.SocketThread;

public class ServerListener extends SocketThread {
	
	public ServerListener(String ip, int port){
		super(ip, port);
	}
	
	public void run(){
		super.run();
		System.exit(0);
	}
	
	protected void dataProcessing(String packet){
		System.out.println("[DataRead] Packet : " + packet);
		String[] data = packet.split(REGEXP_SEP);
		switch(data[0]) {
			case "logout":
				// Logout code
				this.stop();
				break;
		}
	}

}
