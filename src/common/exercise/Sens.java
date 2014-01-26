package common.exercise;

import java.io.File;

import common.Image;

public class Sens extends Exercise{
	private static final long serialVersionUID = 9180313270738695841L;
	private int sens_id;
	private String mot;
	public Image image;
	
	public Sens(int exercise_id, int sens_id, String mot, String path){
		super(exercise_id);
		this.sens_id = sens_id;
		this.mot = mot;
		this.image = new Image(path);
	}
	
	public Sens(String mot, File file){
		super(0);
		this.mot = mot;
		this.image = new Image(file);
	}
	
	public String getName(){
		return "Image & sens exercice " + sens_id;
	}

	public double correction(String s) {
		Double note = 0.0;
		
		if(s.equals(mot)){
			note = 20.0;
		} 
		
		return note;
	}
	
	public String getMot(){
		return mot;
	}

	public int getId() {
		return sens_id;
	}

	public byte[] getImage() {
		return image.getImage();
	}
	
}
