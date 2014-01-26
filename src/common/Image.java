package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = -6744283352855627343L;
	private String filename;
	private byte[] data;
	
	public Image(File file){
		long start = System.currentTimeMillis();
		try {
			FileInputStream fis = new FileInputStream(file);
			filename = file.getName();
	        data = new byte[fis.available()];
	        fis.read(data);
	        fis.close();
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
		System.out.println("Lecture en " + (System.currentTimeMillis() - start)/1000f+" sec");
	}
	
	public Image(String filepath){
		this(new File(filepath));
	}
	
	public String save(String folder){
		long start = System.currentTimeMillis();
		String str = "";
		try {
			FileOutputStream fos = new FileOutputStream(folder + "/" + filename);
			fos.write(data);
			fos.close();
			str = folder + "/" + filename;
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
		System.out.println("Ecriture en " + (System.currentTimeMillis() - start)/1000f+" sec");
		return str;
	}
	
	public byte[] getImage(){
		return data;
	}
}
