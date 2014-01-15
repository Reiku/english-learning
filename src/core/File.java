package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class File implements Serializable {
	private static final long serialVersionUID = -6744283352855627343L;
	private String filename;
	private byte[] data;
	
	@SuppressWarnings("resource")
	public File(String filepath){
		long start = System.currentTimeMillis();
		try {
			java.io.File file = new java.io.File(filepath);
			FileInputStream fis = new FileInputStream(file);
			filename = file.getName();
	        data = new byte[fis.available()];
	        fis.read(data);
	         
	        /*int x = 0;
			for(byte b : data){
				x++;
				System.out.print("[FileRead] " + filepath + " : " + (int)((float)x/data.length*100) + "%\r");
			}
			System.out.print("[FileRead] " + filepath + " : Done !\n");*/  
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
			
			/*int x = 0;
			for(byte b : data){
				x++;
				System.out.print("[FileWrite] " + folder + "/" + filename + " : " + (int)((float)x/data.length*100) + "%\r");
			}
			System.out.print("[FileWrite] " + folder + "/" + filename + " : Done !\n");*/
			fos.close();
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
		System.out.println("Ecriture en " + (System.currentTimeMillis() - start)/1000f+" sec");
	}
}
