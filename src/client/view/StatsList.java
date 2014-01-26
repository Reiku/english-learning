package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import client.App;
import client.controller.HomeController;
import client.controller.StatsListController;
import client.listener.ExitListener;
import client.observer.Observer;

import common.exercise.Note;

public class StatsList extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private StatsListController controller;

	public StatsList(StatsListController controller){
		this.controller = controller;
		this.setSize(800, 400);
		this.setTitle("Mes statistiques - English Learning");
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(ExitListener.EXIT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComposant();
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	private void initComposant(){
		JPanel backPanel = new JPanel();
		JButton backButton = new JButton("Retour");
		backButton.setHorizontalAlignment(JButton.LEFT);
		backButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				controller.changeView(new Home(new HomeController(controller.getModel())));
			} 
		});
		backPanel.setPreferredSize(new Dimension(180, 35));
		backPanel.add(backButton);
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(180, 35));
		userPanel.add(userLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		for (String key : controller.notes.keySet()) {
			ArrayList<Note> notes = controller.notes.get(key);
			JPanel listPanel = new JPanel();
			listPanel.setPreferredSize(new Dimension(280, 300));
			listPanel.setBackground(Color.WHITE);
			
			for(Note note : notes){
				String noteStr = "Exercice " + note.getExercise_id() + " : " + note.getNote() + " / 20";
				if(note.getBest() != 0.0){
					noteStr += " - Meilleur note : " + note.getBest() + " / 20";
				}
				JLabel noteLabel = new JLabel(noteStr, JLabel.CENTER);
				noteLabel.setPreferredSize(new Dimension(400, 15));
				listPanel.add(noteLabel);
			}
			
			JScrollPane scrollPane = new JScrollPane(listPanel);
			scrollPane.setPreferredSize(new Dimension(420, 300));
			
			String title = "";
			if(key.equals("sens")){
				title = "Images & sens";
			} else if(key.equals("trous")){
				title = "Textes à trous";
			} else if(key.equals("dictees")){
				title = "Dictées";
			}
			tabbedPane.addTab(title, scrollPane);
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		container.setLayout(new GridBagLayout());	
		gbc.anchor = GridBagConstraints.NORTH;
		
		gbc.weighty = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(backPanel, gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 2;
		container.add(userPanel, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		container.add(tabbedPane, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
