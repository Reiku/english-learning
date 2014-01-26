package client.controller;

import client.listener.ServerListener;

import common.exercise.Exercise;
import common.exercise.Note;

public class ExerciseController extends AppController {
	private Exercise exercise;

	public ExerciseController(ServerListener model, Exercise exercise) {
		super(model);
		this.exercise = exercise;
	}

	public Exercise getExercise() {
		return exercise;
	}
	
	public void sendNote(Note note){
		model.send("saveNote", note);
	}

}
