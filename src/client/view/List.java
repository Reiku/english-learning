package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import client.App;
import client.controller.ExerciseController;
import client.controller.HomeController;
import client.controller.ListController;
import client.listener.ExitListener;
import client.observer.Observer;

import common.exercise.Exercise;

public class List extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private ListController controller;

	public List(ListController controller){
		this.controller = controller;
		this.setSize(800, 400);
		this.setTitle(controller.getType() + " - Liste des exercices - English Learning");
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
		
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280, controller.exercises.size() * 10 + 25));
		listPanel.setBackground(Color.WHITE);
		
		for(final Exercise e : controller.exercises){
			JButton exButton = new JButton("Exercice " + e.getId());
			exButton.setPreferredSize(new Dimension(125, 25));
			exButton.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
					controller.changeView(new SolveExercise(new ExerciseController(controller.getModel(), e)));
				} 
			});
			listPanel.add(exButton);
		}
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setPreferredSize(new Dimension(420, 300));
		
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
		container.add(scrollPane, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
