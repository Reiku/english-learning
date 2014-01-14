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
		System.out.println("[DataRead] Packet : " + packet);
		/*switch(data[0]) {
			case "logout":
				// Logout code
				this.stop();
				break;
		}*/
	}

}
