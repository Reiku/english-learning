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

public class Home extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private HomeController controller;

	public Home(HomeController controller){
		this.setSize(480, 300);
		this.setTitle("Accueil - English Learning");
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
		JPanel adminPanel = new JPanel();
		JButton adminButton = new JButton("Espace enseignant");
		adminButton.setHorizontalAlignment(JButton.LEFT);
		adminButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.changeView(new AdminHome(new AdminController(controller.getModel())));
			} 
		});
		adminPanel.setPreferredSize(new Dimension(150, 35));
		if(App.user.isProf()){
			adminPanel.add(adminButton);
		}
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(150, 35));
		userPanel.add(userLabel);
		
		JPanel homePanel = new JPanel();
		homePanel.setPreferredSize(new Dimension(150, 125));
		//panHome.setBackground(Color.WHITE);
		
		JButton imgButton = new JButton("Images et sens");
		imgButton.setPreferredSize(new Dimension(125, 25));
		imgButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.imageSens();
			} 
		});
		JButton textButton = new JButton("Texte à trous");
		textButton.setPreferredSize(new Dimension(125, 25));
		textButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.texteTrous();
			} 
		});
		JButton dictButton = new JButton("Dictées");
		dictButton.setPreferredSize(new Dimension(125, 25));
		dictButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.dictees();
			} 
		});
		JButton statsButton = new JButton("Mes résultats");
		statsButton.setPreferredSize(new Dimension(125, 25));
		statsButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.stats();
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
		container.add(adminPanel, gbc);
		
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
