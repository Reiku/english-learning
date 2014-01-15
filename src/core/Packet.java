package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.bind.DatatypeConverter;

public class Packet {
	private String name;
	private String data;
	
	public Packet(String n, Object obj){
		name = n;
		this.setData(obj);
	}
	
	public Packet(String pack){
		String[] packet = pack.split("\\|~\\|");
		name = packet[0];
		data = packet[1];
	}

	public String getName() {
		return name;
	}

	public Object getData() {
		Object obj = null;
		
		try {
			ByteArrayInputStream buffer = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(data));
			ObjectInputStream ois = new ObjectInputStream(buffer);
			obj = ois.readObject();
	    } catch (IOException e) {
	    	System.err.println("[Erreur] " + e);
	    } catch (ClassNotFoundException e) {
	    	System.err.println("[Erreur] " + e);
	    }
		
		return obj;
	}

	public void setData(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			data = DatatypeConverter.printBase64Binary(baos.toByteArray());
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	public String toString(){
		return name + "|~|" + data;
	}
	
	public String toShortString(){
		if(data.length() <= 100){
			return name + "|~|" + data;
		} else {
			return name + "|~|" + data.substring(0, 100);
		}
	}
	
}
