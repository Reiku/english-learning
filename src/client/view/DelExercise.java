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
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import client.App;
import client.controller.AdminController;
import client.listener.ExitListener;
import client.observer.Observer;

import common.exercise.Exercise;

public class DelExercise extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private AdminController controller;

	public DelExercise(AdminController controller){
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
				controller.changeView(new AdminHome(new AdminController(controller.getModel())));
			} 
		});
		backPanel.setPreferredSize(new Dimension(180, 35));
		backPanel.add(backButton);
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(180, 35));
		userPanel.add(userLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280, 300));
		listPanel.setBackground(Color.WHITE);
		
		for(final Exercise e : controller.sens){
			JButton exButton = new JButton("Exercice " + e.getId());
			exButton.setPreferredSize(new Dimension(125, 25));
			exButton.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
					int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir supprimer cet exercice ?", "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		            int[] ids = new int[2];
		            ids[0] = e.getExercise_id();
		            ids[1] = e.getId();
					if (confirm == 0) {
		               controller.delSens(ids);
		            }
				} 
			});
			listPanel.add(exButton);
		}
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setPreferredSize(new Dimension(420, 300));
		tabbedPane.addTab("Images & sens", scrollPane);
		
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280, 300));
		listPanel.setBackground(Color.WHITE);
		
		for(final Exercise e : controller.trous){
			JButton exButton2 = new JButton("Exercice " + e.getId());
			exButton2.setPreferredSize(new Dimension(125, 25));
			exButton2.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
					int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir supprimer cet exercice ?", "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		            int[] ids = new int[2];
		            ids[0] = e.getExercise_id();
		            ids[1] = e.getId();
					if (confirm == 0) {
		               controller.delTrous(ids);
		            }
				} 
			});
			listPanel.add(exButton2);
		}
		
		scrollPane = new JScrollPane(listPanel);
		scrollPane.setPreferredSize(new Dimension(420, 300));
		tabbedPane.addTab("Textes à trous", scrollPane);
		
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280, 300));
		listPanel.setBackground(Color.WHITE);
		
		for(final Exercise e : controller.dictees){
			JButton exButton3 = new JButton("Exercice " + e.getId());
			exButton3.setPreferredSize(new Dimension(125, 25));
			exButton3.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
					int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir supprimer cet exercice ?", "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		            int[] ids = new int[2];
		            ids[0] = e.getExercise_id();
		            ids[1] = e.getId();
					if (confirm == 0) {
		               controller.delDictee(ids);
		            }
				} 
			});
			listPanel.add(exButton3);
		}
		
		scrollPane = new JScrollPane(listPanel);
		scrollPane.setPreferredSize(new Dimension(420, 300));
		tabbedPane.addTab("Dictées", scrollPane);

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
