package common.exercise;

public class Dictee extends Exercise {
	private static final long serialVersionUID = -2141876553236292630L;
	private int dictee_id;
	private String phrase;
	
	public Dictee(int exercise_id, int dictee_id, String phrase){
		super(exercise_id);
		this.dictee_id = dictee_id;
		this.phrase = phrase;
	}
	
	public String getName(){
		return "Dictée exercice " + dictee_id;
	}
	
	//Getter 
	public String getPhrase(){ //Getter phrase
		return this.phrase;
	}
	
	//Setter
	public void setPhrase(String s){ //Setter phrase
		this.phrase = s;
	}
	
	//Methode pour calculer la marge d'erreur (Si oubli/ajout d'espace)
	public int margeErr(int ph, int prop){		
		return Math.abs(ph-prop);
	}
	
	//Methode pour vérifier si on cherche toujours dans le tableau
	public int verifDepMin(int actu, int marge){
		if(actu-marge<0) return 0;
		else return actu-marge;	
	}
	
	public int verifDepMax(int actu, int marge, int limite){
		if(actu+marge>limite) return limite-1;
		else return actu+marge;
	}
	
	//Methode pour corriger 
	public double correction(String s){
		int cpt = 0;  
		String [] ph = this.phrase.split("\\s+");
		String [] prop = s.split("\\s+");
		if(ph.length==prop.length){ //Si l'utilisateur n'a pas oublié d'espace
			for(int i=0;i<ph.length;i++){
				if(ph[i].equals(prop[i])) cpt++;
			}
		}
		else{
			int k = margeErr(ph.length,prop.length);
			cpt = 0; //init du compteur 
			int j; //Variable d'analyse
			boolean trouve; //Variable d'analyse
			for(int i=0;i<prop.length;i++){ //Boucle pour parcourir le tab contenant la prop
				trouve = false; //Init variable d'analyse
				j = verifDepMin(i,k);
				while(!trouve && j<=verifDepMax(i,k*2,ph.length)){
					if(prop[i].equals(ph[j])){
						trouve = true;
						cpt++;
					}
					else{
						j++;
					}
				}
				j = 0; trouve = false; //réinit des variables
			}
		}
		double note = (double)(cpt);
		note=arrondi((note/ ph.length) * 20);
		return note;
	}

	public int getId() {
		return dictee_id;
	}

}
