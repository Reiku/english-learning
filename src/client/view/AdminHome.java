package client.view;

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
import javax.swing.WindowConstants;

import client.App;
import client.controller.AdminController;
import client.controller.HomeController;
import client.listener.ExitListener;
import client.observer.Observer;

public class AdminHome extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private AdminController controller;

	public AdminHome(AdminController controller){
		this.setSize(480, 300);
		this.setTitle("Espace enseignant - English Learning");
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(ExitListener.EXIT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComposant();
		this.setContentPane(container);
		this.setVisible(true);
		this.controller = controller;
	}
	
	private void initComposant(){
		JPanel backPanel = new JPanel();
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				controller.changeView(new Home(new HomeController(controller.getModel())));
			} 
		});
		backPanel.setPreferredSize(new Dimension(100, 35));
		backPanel.add(backButton);
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(100, 35));
		userPanel.add(userLabel);
		
		JPanel homePanel = new JPanel();
		homePanel.setPreferredSize(new Dimension(250, 125));
		//panHome.setBackground(Color.WHITE);
		
		JButton imgButton = new JButton("Ajouter un élève");
		imgButton.setPreferredSize(new Dimension(200, 25));
		imgButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.changeView(new AddEleve(new AdminController(controller.getModel())));
			} 
		});
		JButton textButton = new JButton("Ajouter un exercice");
		textButton.setPreferredSize(new Dimension(200, 25));
		textButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.changeView(new AddExercise(new AdminController(controller.getModel())));
			} 
		});
		JButton dictButton = new JButton("Supprimer un exercice");
		dictButton.setPreferredSize(new Dimension(200, 25));
		dictButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				
			} 
		});
		JButton statsButton = new JButton("Voir les résultats");
		statsButton.setPreferredSize(new Dimension(200, 25));
		statsButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				
			} 
		});
		
		homePanel.add(imgButton);
		homePanel.add(textButton);
		homePanel.add(dictButton);
		homePanel.add(statsButton);
		
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
		container.add(homePanel, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
