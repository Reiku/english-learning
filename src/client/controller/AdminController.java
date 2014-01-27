package client.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import util.MD5;
import client.listener.ServerListener;

import common.User;
import common.exercise.Dictee;
import common.exercise.Exercise;
import common.exercise.Note;
import common.exercise.Sens;
import common.exercise.Trous;

public class AdminController extends AppController {
	public ArrayList<Exercise> sens;
	public ArrayList<Exercise> trous;
	public ArrayList<Exercise> dictees;
	public HashMap<String, ArrayList<Note>> notes;

	public AdminController(ServerListener model) {
		super(model);
	}
	
	public AdminController(ServerListener model, ArrayList<Exercise> sens, ArrayList<Exercise> trous, ArrayList<Exercise> dictees) {
		super(model);
		this.sens = sens;
		this.trous = trous;
		this.dictees = dictees;
	}
	
	public AdminController(ServerListener model, HashMap<String, ArrayList<Note>> notes){
		super(model);
		this.notes = notes;
	}

	public void sendUser(String login, String password) {
		User user = new User(login, MD5.getHashString(password));
		model.send("addUser", user);
	}

	public void sendSens(String mot, File file) {
		Sens exercise = new Sens(mot, file);
		model.send("addSens", exercise);
	}
	
	public void sendTrous(String texte) {
		Trous exercise = new Trous(texte);
		model.send("addTrous", exercise);
	}
	
	public void sendDictee(String phrase) {
		Dictee exercise = new Dictee(phrase);
		model.send("addDictee", exercise);
	}
	
	public void getAllExercises(){
		model.send("getAllExercises");
	}
	
	public void delSens(int[] ids){
		model.send("delSens", ids);
	}
	
	public void delTrous(int[] ids){
		model.send("delTrous", ids);
	}
	
	public void delDictee(int[] ids){
		model.send("delDictee", ids);
	}
	
	public void getAllStats(){
		model.send("getAllStats");
	}

}
