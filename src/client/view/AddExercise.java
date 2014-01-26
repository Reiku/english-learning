package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import client.App;
import client.controller.AdminController;
import client.listener.ExitListener;
import client.observer.Observer;

public class AddExercise extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private AdminController controller;
	private File file;
	private JTextField motField;
	private JTextArea textArea;
	private JTextArea textAreaDictee;

	public AddExercise(AdminController controller){
		this.setSize(800, 400);
		this.setTitle("Ajout d'exercice - Espace enseignant - English Learning");
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
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(420, 250));
		
		JButton fileButton = new JButton("Choisir un fichier");
		final JLabel fileLabel = new JLabel();
		fileLabel.setPreferredSize(new Dimension(200, 20));
		addPanel.add(fileButton);
		addPanel.add(fileLabel);
		fileButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				fileLabel.setText(file.getName());
			} 
		});
		
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		JLabel label = new JLabel("Mot : ");
		addPanel.add(label);
		motField = new JTextField();
		motField.setPreferredSize(new Dimension(150, 21));
		addPanel.add(motField);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) {
				if(!motField.getText().equals("") && file != null){
					controller.sendSens(motField.getText(), file);
				}
			} 
		});
		addPanel.add(valider);
		tabbedPane.addTab("Images & sens", addPanel);
		
		addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(420, 250));
		
		label = new JLabel("Mettez les mots à cacher entre crochets. Exemple : This [is] an example !");
		addPanel.add(label);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
		textArea.setPreferredSize(new Dimension(400, 75));
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		addPanel.add(textArea);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) {
				if(!textArea.getText().equals("")){
					controller.sendTrous(textArea.getText());
				}
			} 
		});
		addPanel.add(valider);
		tabbedPane.addTab("Textes à trous", addPanel);
		
		addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(420, 250));
		
		label = new JLabel("Phrase à dictée : ");
		addPanel.add(label);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		textAreaDictee = new JTextArea();
		textAreaDictee.setLineWrap(true);
		textAreaDictee.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
		textAreaDictee.setPreferredSize(new Dimension(400, 75));
		textAreaDictee.setBorder(BorderFactory.createLineBorder(Color.black));
		addPanel.add(textAreaDictee);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(420, 10));
		addPanel.add(panel);
		
		valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) {
				if(!textAreaDictee.getText().equals("")){
					controller.sendDictee(textAreaDictee.getText());
				}
			} 
		});
		addPanel.add(valider);
		tabbedPane.addTab("Dictées", addPanel);
		
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
