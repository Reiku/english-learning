package client.controller;

import java.util.ArrayList;
import java.util.HashMap;

import common.exercise.Note;

import client.listener.ServerListener;

public class StatsListController extends AppController {
	public HashMap<String, ArrayList<Note>> notes;
	
	public StatsListController(ServerListener model, HashMap<String, ArrayList<Note>> notes) {
		super(model);
		this.notes = notes;
	}
	
}
