package client.controller;

import java.io.File;

import util.MD5;
import client.listener.ServerListener;

import common.User;
import common.exercise.Dictee;
import common.exercise.Sens;
import common.exercise.Trous;

public class AdminController extends AppController {

	public AdminController(ServerListener model) {
		super(model);
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

}
