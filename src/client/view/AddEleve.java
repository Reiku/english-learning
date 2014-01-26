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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import client.App;
import client.controller.AdminController;
import client.listener.ExitListener;
import client.observer.Observer;

public class AddEleve extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private AdminController controller;
	private JTextField loginField;
	private JTextField passField;

	public AddEleve(AdminController controller){
		this.setSize(800, 400);
		this.setTitle("Ajout d'élève - Espace enseignant - English Learning");
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
				controller.changeView(new AdminHome(new AdminController(controller.getModel())));
			} 
		});
		backPanel.setPreferredSize(new Dimension(180, 35));
		backPanel.add(backButton);
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(180, 35));
		userPanel.add(userLabel);
		
		JPanel addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(420, 300));
		
		JLabel label = new JLabel("Login : ");
		label.setPreferredSize(new Dimension(70, 21));
		addPanel.add(label);
		
		loginField = new JTextField();
		loginField.setPreferredSize(new Dimension(150, 21));
		addPanel.add(loginField);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		label = new JLabel("Password : ");
		label.setPreferredSize(new Dimension(70, 21));
		addPanel.add(label);
		
		passField = new JTextField();
		passField.setPreferredSize(new Dimension(150, 21));
		addPanel.add(passField);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) {
				if(!loginField.getText().equals("") && !passField.getText().equals("")){
					controller.sendUser(loginField.getText(), passField.getText());
					loginField.setText("");
					passField.setText("");
				}
				
			} 
		});
		addPanel.add(valider);
		
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
		container.add(addPanel, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
