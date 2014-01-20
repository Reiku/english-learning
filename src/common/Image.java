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
	
	public Image(String filepath){
		long start = System.currentTimeMillis();
		try {
			File file = new File(filepath);
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
	
	public void save(String folder){
		long start = System.currentTimeMillis();
		try {
			FileOutputStream fos = new FileOutputStream(folder + "/" + filename);
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
		System.out.println("Ecriture en " + (System.currentTimeMillis() - start)/1000f+" sec");
	}
}
