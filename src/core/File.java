package core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File implements Serializable {
	private static final long serialVersionUID = -6744283352855627343L;
	private String filename;
	private byte[] data;
	
	public File(String filepath){
		try {
			Path path = Paths.get(filepath);
			filename = path.getFileName().toString();
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	public void save(String folder){
		try {
			FileOutputStream fos = new FileOutputStream(folder + "/" + filename);
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			System.err.println("[Erreur] " + e);
		}
	}
}
