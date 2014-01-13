package client;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args){
		ClientSocket socket = new ClientSocket("127.0.0.1", 3829);
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		
		while(!str.equals("quit")){
			System.out.println("[Scanner] " + str);
			socket.send(str);
			str = sc.nextLine();
		}
	}
	
}
