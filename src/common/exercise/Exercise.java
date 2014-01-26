package common.exercise;

import java.io.Serializable;

public abstract class Exercise implements Serializable {
	private static final long serialVersionUID = 210113318601373236L;
	protected int exercise_id;
	
	public Exercise(int id){
		exercise_id = id;
	}
	
	public abstract int getId();
	public abstract String getName();

	public int getExercise_id() {
		return exercise_id;
	}
	
	protected double arrondi(double val) {
		return (Math.floor(val*100.0))/100;
	}
}
