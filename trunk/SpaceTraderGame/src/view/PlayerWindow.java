package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import model.Player;
import net.miginfocom.swing.MigLayout;
import java.io.File;
import java.io.IOException;


/**
 * Creates the initial player creation GUI 
 * @author Quirky Qwertys
 * @version 1.0
 */
public class PlayerWindow extends JPanel {


	private Image img;
	private Color c = new Color(0, 255, 0);
	private Font f = new Font("Space Age", 1, 12);
	private final long serialVersionUID = 1L;
	private JTextField textName;
	private JTextField textFieldPilot;
	private JTextField textField_Fighter;
	private JTextField textField_Engineer;
	private JTextField textField_Trader;
	private JButton createButton;

	/**
	 * Launch the application.
	 */
	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Image spaceIcon = ImageIO.read(new File("src/view/shipIcon.png"));//needed for MACs and Windows
					//PlayerWindow player = new PlayerWindow();
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setContentPane(PlayerWindow.this);
					frame.setBounds(100, 100, 450, 300);
					frame.setPreferredSize(new Dimension(650, 250));
					frame.setIconImage(spaceIcon);
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public PlayerWindow() throws IOException {
//		setTitle("Player Creation");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		NewPlayerListener playerListener = new NewPlayerListener();
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("", "[][][grow][][][grow][grow][grow][][][grow][][][][]", "[][shrink 50][][][][][][grow,bottom]"));		
		JLabel lblHeader = new JLabel("Pick your options below to start a new game.");
		lblHeader.setForeground(c);
		lblHeader.setFont(f);
		add(lblHeader, "cell 1 0 14 1");
		
		img = ImageIO.read(new File("src/view/Space.jpg"));//needed for MACs and Windows
		
		JLabel lblNameLabel = new JLabel("Name");
		add(lblNameLabel, "cell 1 1,alignx trailing");
		lblNameLabel.setForeground(c);
		lblNameLabel.setFont(f);
		
		textName = new JTextField();
		add(textName, "cell 2 1 4 1,growx");
		textName.setColumns(10);
		textName.getDocument().addDocumentListener( playerListener );
		
		createButton = new JButton("Create");
		createButton.setEnabled(false);
		add(createButton, "cell 9 7 2 1,alignx right");	
		
		JLabel lblSkillPointsDesc1 = new JLabel("Start the game with 15 skill points. Allocate those points");
		add(lblSkillPointsDesc1, "cell 1 2 12 1");
		lblSkillPointsDesc1.setForeground(c);
		lblSkillPointsDesc1.setFont(f);
		
		JLabel lblSkillPointsDesc2 = new JLabel("between the 4 skills below:");
		add(lblSkillPointsDesc2, "cell 1 3 12 1");
		lblSkillPointsDesc2.setForeground(c);
		lblSkillPointsDesc2.setFont(f);
		
		JLabel lblPilot = new JLabel("Pilot");
		add(lblPilot, "cell 1 4,alignx trailing");
		lblPilot.setForeground(c);
		lblPilot.setFont(f);
		
		textFieldPilot = new JTextField();
		//textFieldPilot.setText("0");
		add(textFieldPilot, "cell 2 4,growx");
		textFieldPilot.setColumns(10);
		textFieldPilot.getDocument().addDocumentListener( playerListener ); 
		
		JLabel lblFighter = new JLabel("Fighter");
		add(lblFighter, "cell 3 4");
		lblFighter.setForeground(c);
		lblFighter.setFont(f);
		
		textField_Fighter = new JTextField();
		//textField_Fighter.setText("0");
		add(textField_Fighter, "cell 5 4,growx");
		textField_Fighter.setColumns(10);
		textField_Fighter.getDocument().addDocumentListener( playerListener ); 
		
		JLabel lblEngineer = new JLabel("Engineer");
		add(lblEngineer, "cell 6 4,alignx trailing");
		lblEngineer.setForeground(c);
		lblEngineer.setFont(f);
		
		textField_Engineer = new JTextField();
		//textField_Engineer.setText("0");
		add(textField_Engineer, "cell 7 4,growx");
		textField_Engineer.setColumns(10);
		textField_Engineer.getDocument().addDocumentListener( playerListener );
		
		JLabel lblTrader = new JLabel("Trader");
		add(lblTrader, "cell 9 4,alignx trailing");
		lblTrader.setForeground(c);
		lblTrader.setFont(f);
		
		textField_Trader = new JTextField();
		//textField_Trader.setText("0");
		add(textField_Trader, "cell 10 4,growx");
		textField_Trader.setColumns(10);
		textField_Trader.getDocument().addDocumentListener( playerListener ); 
		
		JLabel lblDifficultyLevel = new JLabel("Select your difficulty level:");
		add(lblDifficultyLevel, "cell 1 5 5 1");
		lblDifficultyLevel.setForeground(c);
		lblDifficultyLevel.setFont(f);
		
		String[]comboBoxDefaults = {"Beginner","Easy","Normal","Hard","Impossible"};
		JComboBox comboBoxDifficulty = new JComboBox(comboBoxDefaults);
		add(comboBoxDifficulty, "cell 6 5 4 1,growx");
		
			
	}
	/**
	 * draws the background image on the GUI
	 * @param g - the graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	/**
	 * gets the Pilot skill points
	 * @return skill points
	 */
	public String getTextFieldPilot() {
		return textFieldPilot.getText();
	}

	/**
	 * gets the fighter skill points
	 * @return sill points
	 */
	public String getTextFieldFighter() {
		return textField_Fighter.getText();
	}
/**
 * gets the engineer skill points
 * @return skill points
 */
	public String getTextFieldEngineer() {
		return textField_Engineer.getText();
	}
/**
 * gets the trader skill points
 * @return skill points
 */
	public String getTextFieldTrader() {
		return textField_Trader.getText();
	}
	/**
	 * gets the player's name
	 * @return player's name
	 */
	public String getTextFieldName(){
		return textName.getText();
	}
/**
 * zeros out the skills fields
 */
	public void setSkillFieldZero() {
		textFieldPilot.setText("0");
		textField_Fighter.setText("0");
		textField_Engineer.setText("0");
		textField_Trader.setText("0");
	}
/**
 * gets the values out of the fields to create the player
 * @return player created
 */
	public Player getNewPlayer(){
		int[] skills = new int[4];
		skills[0] = getValue(textFieldPilot);
		skills[1] = getValue(textField_Fighter);
		skills[2] = getValue(textField_Engineer);
		skills[3] = getValue(textField_Trader);
		String playerName = textName.getText();
		
		Player newPlayer = new Player(playerName, skills);
		return newPlayer;
	}
	/**
	 * set the action listener on the create button
	 * @param listener - the action listener
	 */
	public void setCreateListener(ActionListener listener){
		createButton.addActionListener(listener);
	}
	/**
	 * gets the value of the name field
	 * @param field the text field
	 * @return >1 if the player name is populated
	 */
	public int getValue( JTextField field ) {
		if (field.getText().equals("")){
			return 0;
		}
		else return (Integer.parseInt(field.getText()) );
		
	}
	
	/**
	 * inner class to make sure all the fields are populated correctly on new player creation
	 * @author Quirky Qwertys
	 *
	 */
	private class NewPlayerListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed()&&isNameSet());
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed()&&isNameSet());
			
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed()&&isNameSet());
			
			
		}
		
		/**
		 * method to make sure all the skill points are populated and add up to 15
		 * @return true if the points add up to 15; false otherwise
		 */
		public boolean areAllSkillPointsUsed() {
			try {
				int skillPointsUsed = getValue(textFieldPilot) 
						+ getValue(textField_Fighter)
						+ getValue(textField_Engineer)
						+ getValue(textField_Trader);
				return skillPointsUsed == 15;
			} catch( NumberFormatException ex ) {
				return false;				
			}
		}
		/**
		 * checks to see if the name is populated
		 * @return true if name is populated
		 */
		public boolean isNameSet(){
			if (textName.getText().equals("")){
				return false;
			}
			else return true;
		}
	}
}