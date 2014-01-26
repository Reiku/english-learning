package client.controller;

import java.util.ArrayList;

import client.listener.ServerListener;

import common.exercise.Exercise;

public class ListController extends AppController {
	private String type;
	public ArrayList<Exercise> exercises;

	public ListController(ServerListener model, String type, ArrayList<Exercise> exercises) {
		super(model);
		this.type = type;
		this.exercises = exercises;
	}

	public String getType() {
		return type;
	}
	
}
