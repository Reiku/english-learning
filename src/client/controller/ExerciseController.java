package client.controller;

import javax.swing.JOptionPane;

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
		model.notifyObserver("Note : " + note.getNote() + " / 20", "Exercice fini", JOptionPane.INFORMATION_MESSAGE);
	}

}
