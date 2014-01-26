package common.exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trous extends Exercise {
	private static final long serialVersionUID = -8738599732877773920L;
	private int trous_id;
	private String texte;
	public String[] sentence;
	public String[] words;
	
	public Trous(int exercise_id, int trous_id, String texte){
		super(exercise_id);
		this.trous_id = trous_id;
		this.texte = texte;
		
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m1 = p.matcher(texte);
		Matcher m2 = p.matcher(texte);
		
		sentence = m1.replaceAll("___").split("___");
		words = new String[sentence.length - 1];
		
		int x = 0;
		while(m2.find() && x < words.length) {
		    words[x] = m2.group(1);
		    x++;
		}
	}
	
	public Trous(String texte){
		super(0);
		this.texte = texte;
	}
	
	public String getName(){
		return "Texte à trous exercice " + trous_id;
	}

	public double correction(String[] s) {
		int cpt = 0;
		
		for(int i = 0 ; i < words.length ; i++){
			if(words[i].equals(s[i]))
				cpt++;
		}
		
		double note = (cpt * 20) / (double) words.length;
		return arrondi(note);
	}

	public int getId() {
		return trous_id;
	}

	public String getTexte() {
		return texte;
	}

}
