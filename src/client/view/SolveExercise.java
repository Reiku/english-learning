package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import util.ImagePanel;

import client.App;
import client.controller.ExerciseController;
import client.listener.ExitListener;
import client.observer.Observer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import common.exercise.Dictee;
import common.exercise.Note;
import common.exercise.Sens;
import common.exercise.Trous;

public class SolveExercise extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private ExerciseController controller;
	private JTextField[] fieldWords;
	private Trous exTrous;
	private Dictee exDictee;
	private Sens exSens;
	private Voice voice;
	private JTextArea proposition;

	public SolveExercise(ExerciseController controller){
		this.controller = controller;
		this.setSize(800, 400);
		this.setTitle(controller.getExercise().getName() + " - English Learning");
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
				if(controller.getExercise().getClass() == Trous.class){
					controller.texteTrous();
				} else if(controller.getExercise().getClass() == Dictee.class){
					controller.dictees();
				} else if(controller.getExercise().getClass() == Sens.class){
					controller.imageSens();
				}
			} 
		});
		backPanel.setPreferredSize(new Dimension(180, 35));
		backPanel.add(backButton);
		
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Login : " + App.user.getLogin());
		userPanel.setPreferredSize(new Dimension(180, 35));
		userPanel.add(userLabel);
		
		JPanel exoPanel = new JPanel();
		exoPanel.setPreferredSize(new Dimension(420, 300));
		//exoPanel.setBackground(Color.WHITE);
		
		if(controller.getExercise().getClass() == Trous.class){
			exTrous = (Trous)controller.getExercise();
			fieldWords = new JTextField[exTrous.words.length];
			for(int i = 0 ; i < exTrous.sentence.length ; i++){
				JLabel phrase = new JLabel(exTrous.sentence[i]);
				exoPanel.add(phrase);
				if(i < exTrous.sentence.length - 1){
					fieldWords[i] = new JTextField(exTrous.words[i].length());
					exoPanel.add(fieldWords[i]);
				}
			}
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(420, 15));
			exoPanel.add(panel);
			
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
					String[] words = new String[fieldWords.length];
					for(int i = 0 ; i < words.length ; i++){
						words[i] = fieldWords[i].getText();
					}
					controller.sendNote(new Note(exTrous.getExercise_id(), exTrous.correction(words)));
					controller.texteTrous();
				} 
			});
			exoPanel.add(valider);
		} else if(controller.getExercise().getClass() == Dictee.class){
			exDictee = (Dictee)controller.getExercise();
			VoiceManager voiceManager = VoiceManager.getInstance();
	        voice = voiceManager.getVoice("kevin16");
	        voice.allocate();
			
			JButton ecouter = new JButton("Écouter");
			ecouter.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) { 
			        voice.speak(exDictee.getPhrase());
				} 
			});
			exoPanel.add(ecouter);
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(420, 15));
			exoPanel.add(panel);
			
			JLabel label = new JLabel("Proposition : ");
			exoPanel.add(label);
			
			proposition = new JTextArea();
			proposition.setLineWrap(true);
			proposition.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
			proposition.setPreferredSize(new Dimension(400, 75));
			proposition.setBorder(BorderFactory.createLineBorder(Color.black));
			proposition.setForeground(Color.BLUE);
			exoPanel.add(proposition);
			
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(420, 15));
			exoPanel.add(panel);
			
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) {
					voice.deallocate();
					controller.sendNote(new Note(exDictee.getExercise_id(), exDictee.correction(proposition.getText())));
					controller.dictees();
				} 
			});
			exoPanel.add(valider);
		} else if(controller.getExercise().getClass() == Sens.class){
			exSens = (Sens)controller.getExercise();
			
			ImagePanel image = new ImagePanel();
			image.setBackground(exSens.getImage(), 200);
			exoPanel.add(image);
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(420, 10));
			exoPanel.add(panel);
			
			JLabel label = new JLabel("Proposition : ");
			exoPanel.add(label);
			
			proposition = new JTextArea();
			proposition.setLineWrap(true);
			proposition.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
			proposition.setPreferredSize(new Dimension(150, 20));
			proposition.setBorder(BorderFactory.createLineBorder(Color.black));
			proposition.setForeground(Color.BLUE);
			exoPanel.add(proposition);
			
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(420, 10));
			exoPanel.add(panel);
			
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent ae) {
					controller.sendNote(new Note(exSens.getExercise_id(), exSens.correction(proposition.getText())));
					controller.imageSens();
				} 
			});
			exoPanel.add(valider);
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
		container.add(exoPanel, gbc);
	}

	public void update(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}
}
