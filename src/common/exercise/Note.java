package common.exercise;

import java.io.Serializable;

public class Note implements Serializable{
	private static final long serialVersionUID = -8038355306275112191L;
	private int exercise_id;
	private double note;
	private double best;
	
	public Note(int exercise_id, double note){
		this.exercise_id = exercise_id;
		this.note = note;
	}
	
	public Note(int exercise_id, double note, double best){
		this(exercise_id, note);
		this.best = best;
	}

	public double getNote() {
		return note;
	}

	public int getExercise_id() {
		return exercise_id;
	}
	
	public double getBest() {
		return best;
	}
	
}
