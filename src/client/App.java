package client;

public class App {
	public static void main(String[] args){
		ClientSocket socket = new ClientSocket("127.0.0.1", 3829);
		socket.send("logout");
	}
}
