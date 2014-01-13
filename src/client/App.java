package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class App {
	public static void main(String[] args){
		try {
			Socket socket = new Socket("127.0.0.1", 3829);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            String str = "zzz";
            while(true){
            	System.out.println("[RECEPTION]" + str);
            	out.println(str + "|~|");
            	System.out.println("[ENVOI]" + str);
				str = in.readLine();
            }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
