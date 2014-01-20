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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ExitListener;
import client.controller.LoginController;
import client.observer.Observer;

public class Login extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private LoginController controller;
	private JTextField loginField;
	private JPasswordField passField;
	

	public Login(LoginController controller){
		this.setSize(800, 600);
		this.setTitle("English Learning");
		this.addWindowListener(ExitListener.EXIT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComposant();
		this.setContentPane(container);
		this.setVisible(true);
		this.controller = controller;
	}
	
	private void initComposant(){
		JPanel panLogin = new JPanel();
		panLogin.setPreferredSize(new Dimension(230, 90));
		//panLogin.setBackground(Color.WHITE);
		
		JLabel loginLabel = new JLabel("Login :");
		loginField = new JTextField();
		loginLabel.setLabelFor(loginField);
		loginLabel.setPreferredSize(new Dimension(70, 21));
		loginField.setPreferredSize(new Dimension(150, 21));
		
		JLabel passLabel = new JLabel("Password :");
		passField = new JPasswordField();
		passLabel.setLabelFor(passField);
		passLabel.setPreferredSize(new Dimension(70, 21));
		passField.setPreferredSize(new Dimension(150, 21));
		
		JButton loginButton = new JButton("Connexion");
		loginButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(!loginField.getText().equals("") && !String.valueOf(passField.getPassword()).equals("")){
					controller.setUser(loginField.getText(), String.valueOf(passField.getPassword()));
					passField.setText("");
				}
			} 
		});
		
		panLogin.add(loginLabel);
		panLogin.add(loginField);
		
		panLogin.add(passLabel);
		panLogin.add(passField);
		
		panLogin.add(loginButton);
		
		GridBagConstraints gbc = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		container.add(panLogin, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
