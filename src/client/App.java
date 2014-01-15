package client;

import util.MD5;
import core.File;
import core.User;

public class App {
	
	public static void main(String[] args){
		ServerListener socket = new ServerListener("127.0.0.1", 3829);
		User user = new User("lagetj", MD5.getHashString("test"));
		File file = new File("D:/OBS/Broadcast.mp4");
		//File file = new File("res/english.jpg");
		
		socket.send("file", file);
		//socket.send("login", user);
		
		/*Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		while(!str.equals("quit")){
			System.out.println("[Scanner] " + str);
			socket.send(str);
			str = sc.nextLine();
		}*/
	}
	
}
