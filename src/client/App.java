package client;

import java.util.ArrayList;
import core.User;

public class App {
	
	public static void main(String[] args){
		ServerListener socket = new ServerListener("127.0.0.1", 3829);
		User user = new User("joris", "1105022293N");
		ArrayList<String> al = new ArrayList<String>();
		al.add("Bonjouuuur");
		al.add("nimp");
		al.add("nimp");
		al.add("nimp");
		al.add("Bonjouuuur");
		socket.send("login", user);
		
		/*Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		while(!str.equals("quit")){
			System.out.println("[Scanner] " + str);
			socket.send(str);
			str = sc.nextLine();
		}*/
	}
	
}
