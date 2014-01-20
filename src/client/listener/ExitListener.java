package client.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import client.App;

public class ExitListener {
	public static WindowListener EXIT = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir quittez ?", "Confirmer la fermeture", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
               App.socket.send("logout");
            }
        }
    };
	
}
