// $codepro.audit.disable overloadedMethods, importStyle, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
/**
 * PlayerWindow.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;
import edu.gatech.quirkyqwerties.spacetraders.model.Player;


/**
 * Creates the initial player creation GUI. 
 * 
 * @author Quirky Qwertys
 * @version 1.0
 */
public class PlayerWindow extends JPanel{

	/** Image used in the background of the window. */
	private final Image img;
	
	/** Field for user to insert his/her name. */
	private final JTextField textName;
	
	/** Field for user to insert pilot skill points. */
	private final JTextField textFieldPilot;
	
	/** Field for user to insert fighter skill points. */
	private final JTextField textField_Fighter;
	
	/** Field for user to insert engineer skill points. */
	private final JTextField textField_Engineer;
	
	/** Field for user to insert trader skill points. */
	private final JTextField textField_Trader;
	
	/** The "Create" button. */
	private final JButton createButton;

	/** The current game's game controller. */
	private final GameController controller;
	
	/** Box from which the game difficulty is selected. */
	private final JComboBox comboBoxDifficulty;
	
	/** Directory of the ship image used in the window. */
	private static final String SHIP_IMAGE_DIR = "/edu/gatech/quirkyqwerties/" +
			"spacetraders/view/shipIcon.png";
	
	/** Column width for the text fields. */
	private static final int WIDTH = 10;
	
	/** Number of available skills in Space Traders. */
	private static final int SKILLS = 4;
	
	/** Number of skill points that can be allocated. */
	private static final int DEFAULT_POINTS = 15;

	/**
	 * Launch the application.
	 */
	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final Image spaceIcon =
							ImageIO.read(getClass().getResource(SHIP_IMAGE_DIR));

					final JFrame frame = new JFrame("Create a New Space Trader " +
							"Character");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setContentPane(PlayerWindow.this);
					frame.setBounds(100, 100, 450, 300); // $codepro.audit.disable numericLiterals
					frame.setPreferredSize(new Dimension(650, 250)); // $codepro.audit.disable numericLiterals -->
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
	 * Creates the frame.
	 * 
	 * @param controller the game's current game controller
	 * @throws IOException 
	 */
	public PlayerWindow(GameController controller) throws IOException {
		this.controller = controller;
		setBackground(Color.black);
		final NewPlayerListener playerListener = new NewPlayerListener();
		
		setBorder(new EmptyBorder(150, 5, 5, 5)); // $codepro.audit.disable numericLiterals
		setLayout(new MigLayout("", "[][][grow][][][grow][grow][grow][][][grow][][][][]",
				"[][shrink 50][][][][][][grow,bottom]"));
		final JLabel lblHeader = new JLabel("Pick your options below to start a new" +
				" game.");
		lblHeader.setForeground(Style.SPACEAGE_COLOR);
		lblHeader.setFont(Style.SPACEAGE_NORMAL);
		add(lblHeader, "cell 1 0 14 1");

		img = ImageIO.read(getClass().getResource("/edu/gatech/quirkyqwerties/" +
				"spacetraders/view/Space.jpg"));//needed for MACs and Windows
		
		final JLabel lblNameLabel = new JLabel("Name");
		add(lblNameLabel, "cell 1 1,alignx trailing");
		lblNameLabel.setForeground(Style.SPACEAGE_COLOR);
		lblNameLabel.setFont(Style.SPACEAGE_NORMAL);
		
		textName = new JTextField();
		add(textName, "cell 2 1 4 1,growx");
		textName.setColumns(WIDTH); 
		textName.getDocument().addDocumentListener( playerListener );
		
		createButton = new JButton("Create");
		createButton.setEnabled(false);
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PlayerWindow.this.controller.newGame(getNewPlayer());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		add(createButton, "cell 9 7 2 1,alignx right");
		
		final JLabel lblSkillPointsDesc1 = new JLabel("Start the game with 15 skill " +
				"points. Allocate those points");
		add(lblSkillPointsDesc1, "cell 1 2 12 1");
		lblSkillPointsDesc1.setForeground(Style.SPACEAGE_COLOR);
		lblSkillPointsDesc1.setFont(Style.SPACEAGE_NORMAL);
		
		final JLabel lblSkillPointsDesc2 = new JLabel("between the 4 skills below:");
		add(lblSkillPointsDesc2, "cell 1 3 12 1");
		lblSkillPointsDesc2.setForeground(Style.SPACEAGE_COLOR);
		lblSkillPointsDesc2.setFont(Style.SPACEAGE_NORMAL);
		
		final JLabel lblPilot = new JLabel("Pilot");
		add(lblPilot, "cell 1 4,alignx trailing");
		lblPilot.setForeground(Style.SPACEAGE_COLOR);
		lblPilot.setFont(Style.SPACEAGE_NORMAL);
		
		textFieldPilot = new JTextField();
		add(textFieldPilot, "cell 2 4,growx");
		textFieldPilot.setColumns(WIDTH);
		textFieldPilot.getDocument().addDocumentListener(playerListener); 
		
		final JLabel lblFighter = new JLabel("Fighter");
		add(lblFighter, "cell 3 4");
		lblFighter.setForeground(Style.SPACEAGE_COLOR);
		lblFighter.setFont(Style.SPACEAGE_NORMAL);
		
		textField_Fighter = new JTextField();
		add(textField_Fighter, "cell 5 4,growx");
		textField_Fighter.setColumns(WIDTH);
		textField_Fighter.getDocument().addDocumentListener(playerListener); 
		
		final JLabel lblEngineer = new JLabel("Engineer");
		add(lblEngineer, "cell 6 4,alignx trailing");
		lblEngineer.setForeground(Style.SPACEAGE_COLOR);
		lblEngineer.setFont(Style.SPACEAGE_NORMAL);
		
		textField_Engineer = new JTextField();
		add(textField_Engineer, "cell 7 4,growx");
		textField_Engineer.setColumns(WIDTH);
		textField_Engineer.getDocument().addDocumentListener(playerListener);
		
		final JLabel lblTrader = new JLabel("Trader");
		add(lblTrader, "cell 9 4,alignx trailing");
		lblTrader.setForeground(Style.SPACEAGE_COLOR);
		lblTrader.setFont(Style.SPACEAGE_NORMAL);
		
		textField_Trader = new JTextField();
		add(textField_Trader, "cell 10 4,growx");
		textField_Trader.setColumns(WIDTH);
		textField_Trader.getDocument().addDocumentListener( playerListener ); 
		
		final JLabel lblDifficultyLevel = new JLabel("Select your difficulty level:");
		add(lblDifficultyLevel, "cell 1 5 5 1");
		lblDifficultyLevel.setForeground(Style.SPACEAGE_COLOR);
		lblDifficultyLevel.setFont(Style.SPACEAGE_NORMAL);
		
		final String[] comboBoxDefaults = {"Beginner", "Easy", "Normal",
				"Hard", "Impossible"};
		comboBoxDifficulty = new JComboBox(comboBoxDefaults);
		add(comboBoxDifficulty, "cell 6 5 4 1,growx");
	}
	
	/**
	 * Draws the background image on the GUI.
	 * @param g the graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
	}

	/**
	 * gets the Pilot skill points
	 * @return skill points
	 */
	public String getTextFieldPilot() {
		return textFieldPilot.getText();
	}

	/**
	 * Gets the fighter skill points.
	 * 
	 * @return fighter skill points
	 */
	public String getTextFieldFighter() {
		return textField_Fighter.getText();
	}
	
	/**
	 * Gets the engineer skill points.
	 * @return engineer skill points
	 */
	public String getTextFieldEngineer() {
		return textField_Engineer.getText();
	}
	
	/**
	 * Gets the trader skill points.
	 * 
	 * @return trader skill points
	 */
	public String getTextFieldTrader() {
		return textField_Trader.getText();
	}
	
	/**
	 * Gets the player's name.
	 * 
	 * @return player's name
	 */
	public String getTextFieldName(){
		return textName.getText();
	}
	
	/**
	 * Zeros out the different skill fields.
	 */
	public void resetSkillFieldZero() {
		textFieldPilot.setText("0");
		textField_Fighter.setText("0");
		textField_Engineer.setText("0");
		textField_Trader.setText("0");
	}
	
	/**
	 * Gets the values out of the fields to create the player.
	 * 
	 * @return the newly created player 
	 * @throws IOException 
	 */
	public Player getNewPlayer() throws IOException{
		final int[] skills = new int[SKILLS];
		skills[0] = getValue(textFieldPilot);
		skills[1] = getValue(textField_Fighter);
		skills[2] = getValue(textField_Engineer); // $codepro.audit.disable numericLiterals
		skills[3] = getValue(textField_Trader); // $codepro.audit.disable numericLiterals
		
		final String playerName = textName.getText();
		final Player newPlayer = new Player(playerName, skills);
		return newPlayer;
	}
	
	/**
	 * Set the action listener on the create button.
	 * 
	 * @param listener the action listener
	 */
	public void setCreateListener(ActionListener listener){
		createButton.addActionListener(listener);
	}
	
	/**
	 * Gets the value of the name field.
	 * 
	 * @param field the text field
	 * @return >1 if the player name is populated
	 */
	public int getValue(JTextField field){
		if (field.getText().equals("")){
			return 0;
		}
		try{
			return (Integer.parseInt(field.getText()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Clears out the text fields.
	 */
	public void clearFields(){
		textName.setText( "");
		textFieldPilot.setText("");
		textField_Fighter.setText("");
		textField_Engineer.setText("");
		textField_Trader.setText("");
		comboBoxDifficulty.setSelectedIndex(0);
	}
	
	/**
	 * Makes sure all the fields are populated correctly on 
	 * new player creation.
	 * 
	 * @author Quirky Qwertys
	 */
	private class NewPlayerListener implements DocumentListener { // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
		
		/**
		 * Checks for changes in the text fields.
		 * 
		 * @param arg0 the event of typing in a field
		 */
		@Override
		public void changedUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed() && isNameSet());
		}

		/**
		 * Checks newly entered information in the
		 * text fields.
		 * 
		 * @param arg0 the event of typing in a field
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed() && isNameSet());
		}

		/**
		 * Checks for information that has been deleted
		 * in the text fields.
		 * 
		 * @param arg0 the event of editing in a text field
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			createButton.setEnabled(areAllSkillPointsUsed() && isNameSet());
		}
		
		/**
		 * Method makes sure all the skill points are populated 
		 * and add up to 15.
		 * 
		 * @return true if the points add up to 15, false otherwise
		 */
		public boolean areAllSkillPointsUsed(){ // $codepro.audit.disable booleanMethodNamingConvention
			try {
				final int skillPointsUsed = getValue(textFieldPilot) 
						+ getValue(textField_Fighter)
						+ getValue(textField_Engineer)
						+ getValue(textField_Trader);
				return skillPointsUsed == DEFAULT_POINTS;
			} 
			catch(NumberFormatException ex){
				ex.printStackTrace();
				return false;
			}
		}
		
		/**
		 * Checks to see if the name is populated.
		 * 
		 * @return true if name is populated
		 */
		public boolean isNameSet(){
			if (textName.getText().equals("")){
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Creates a String containing information about the 
	 * player that is created.
	 * 
	 * @return the String with all the pertinent player info
	 */
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		try{
			final Player player = getNewPlayer();
			sb.append("Engineer skill: " + player.getEngineerSkill() + "\n");
			sb.append("Pilot skill: " + player.getPilotSkill() + "\n");
			sb.append("Fighter skill: " + player.getFighterSkill() + "\n");
			sb.append("Trader skill: " + player.getTraderSkill() + "\n");
			sb.append("Credits available: " + player.getMoney());
			return sb.toString();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			return "Error occurred while creating a player.";
		}
	}
}