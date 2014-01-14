package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    private Statement instruction;
    
    public MySQL(String host, String db, String user, String password){
	    try{
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection connexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, user, password);
	        instruction = connexion.createStatement();
	    } catch (Exception e) {
	        System.err.println("[Erreur] " + e);
	    }
    }
    
    public ResultSet query(String query) throws SQLException{
    	return instruction.executeQuery(query);
    }
}