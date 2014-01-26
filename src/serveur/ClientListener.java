package serveur;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.MySQL;

import common.Packet;
import common.SocketThread;
import common.User;
import common.exercise.Dictee;
import common.exercise.Exercise;
import common.exercise.Note;
import common.exercise.Sens;
import common.exercise.Trous;


public class ClientListener extends SocketThread {
	private Serveur serv;
	private MySQL db;
	private User user;
	
	public ClientListener(Socket socket, Serveur serveur, MySQL mysql) {
		super(socket);
		serv = serveur;
		db = mysql;
	}
	
	public void run(){
		super.run();
		serv.delClient(this);
	}
	
	protected void dataProcessing(Packet packet){
		System.out.println("[DataRead] Packet : " + packet.toShortString());
		switch(packet.getName()) {
			case "login":
				this.login((User)packet.getData());
				break;
			case "logout":
				this.send("logout");
				this.stop();
				break;
			case "getTrous":
				this.sendTrous();
				break;
			case "getDictees":
				this.sendDictees();
				break;
			case "getSens":
				this.sendSens();
				break;
			case "getStats":
				this.sendStats();
				break;
			case "saveNote":
				this.saveNote((Note)packet.getData());
				break;
			default:
				this.send("packetError");
				System.err.println("[Erreur] Packet inconnu : " + packet.getName());
		}
	}
	
	private void login(User user){
		try {
			ResultSet query = db.query(
				"SELECT * FROM users WHERE LOWER(login) = '" + user.getLogin().toLowerCase() + "' AND password = '" + user.getPassword() + "';"
			);
			
			if(query.first()){
				User u = new User(query.getInt("id"), query.getString("login"), query.getBoolean("prof"));
				this.user = u;
				this.send("loginOk", u);
            } else{
            	this.send("loginFail");
            }
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	private void sendTrous(){
		try {
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			ResultSet query = db.query("SELECT * FROM trous");
			while(query.next()){
				exercises.add(new Trous(query.getInt("exercise_id"), query.getInt("id"), query.getString("texte")));
			}
			this.send("listTrous", exercises);
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	private void sendDictees(){
		try {
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			ResultSet query = db.query("SELECT * FROM dictees");
			while(query.next()){
				exercises.add(new Dictee(query.getInt("exercise_id"), query.getInt("id"), query.getString("texte")));
			}
			this.send("listDictees", exercises);
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	private void sendSens(){
		try {
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			ResultSet query = db.query("SELECT * FROM sens");
			while(query.next()){
				exercises.add(new Sens(query.getInt("exercise_id"), query.getInt("id"), query.getString("mot"), query.getString("imagepath")));
			}
			this.send("listSens", exercises);
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	private void sendStats(){
		try {
			HashMap<String, ArrayList<Note>> stats = new HashMap<String, ArrayList<Note>>();
			ResultSet query;
			ArrayList<Note> notes;
			
			notes = new ArrayList<Note>();
			query = db.query(
					"SELECT user_id, sens.id, note, best FROM stats, sens " +
					"WHERE stats.exercise_id = sens.exercise_id AND user_id = " + user.getId()
			);
			while(query.next()){
				notes.add(new Note(query.getInt("id"), query.getDouble("note"), query.getDouble("best")));
			}
			stats.put("sens", notes);
			
			notes = new ArrayList<Note>();
			query = db.query(
					"SELECT user_id, trous.id, note, best FROM stats, trous " +
					"WHERE stats.exercise_id = trous.exercise_id AND user_id = " + user.getId()
			);
			while(query.next()){
				notes.add(new Note(query.getInt("id"), query.getDouble("note"), query.getDouble("best")));
			}
			stats.put("trous", notes);
			
			notes = new ArrayList<Note>();
			query = db.query(
					"SELECT user_id, dictees.id, note, best FROM stats, dictees " +
					"WHERE stats.exercise_id = dictees.exercise_id AND user_id = " + user.getId()
			);
			while(query.next()){
				notes.add(new Note(query.getInt("id"), query.getDouble("note"), query.getDouble("best")));
			}
			stats.put("dictees", notes);
			
			this.send("listStats", stats);
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}
	
	private void saveNote(Note note){
		try {
			ResultSet query = db.query(
				"SELECT * FROM stats WHERE user_id = '" + user.getId() + "' AND exercise_id = '" + note.getExercise_id() + "';"
			);

			if(query.first()){
				if(note.getNote() > query.getDouble("note") && note.getNote() > query.getDouble("best")){
					db.execute("UPDATE stats SET best = '" + note.getNote() + "' WHERE id = " + query.getInt("id") + ";");
				}
            } else{
            	db.execute(
            			"INSERT INTO stats (user_id, exercise_id, note) " +
            			"VALUES ('" + user.getId() + "', '" + note.getExercise_id() + "', '" + note.getNote() + "');"
            	);
            }
		} catch (SQLException e) {
			System.err.println("[Erreur] " + e);
		}
	}

	public User getUser() {
		return user;
	}
	
}
