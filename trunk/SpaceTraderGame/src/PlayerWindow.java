import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import java.io.File;
import java.io.IOException;



public class PlayerWindow extends JPanel {

	private static Image img, spaceIcon;
	private static Color c = new Color(0, 255, 0);
	private static Font f = new Font("Space Age", 1, 12);
	private static final long serialVersionUID = 1L;
	private static JTextField textName;
	private static JTextField textFieldPilot;
	private static JTextField textField_Fighter;
	private static JTextField textField_Engineer;
	private static JTextField textField_Trader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					spaceIcon = ImageIO.read(new File("src\\shipIcon.png"));
					PlayerWindow player = new PlayerWindow();
					JFrame frame = new JFrame();
					frame.setContentPane( player );
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
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("", "[][][grow][][][grow][grow][grow][][][grow][][][][]", "[][shrink 50][][][][][][grow,bottom]"));		
		JLabel lblHeader = new JLabel("Pick your options below to start a new game.");
		lblHeader.setForeground(c);
		lblHeader.setFont(f);
		add(lblHeader, "cell 1 0 14 1");
		
		img = ImageIO.read(new File("src\\Space.jpg"));
		
		JLabel lblNameLabel = new JLabel("Name");
		add(lblNameLabel, "cell 1 1,alignx trailing");
		lblNameLabel.setForeground(c);
		lblNameLabel.setFont(f);
		
		textName = new JTextField();
		add(textName, "cell 2 1 4 1,growx");
		textName.setColumns(10);
		
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
		textFieldPilot.setText("0");
		add(textFieldPilot, "cell 2 4,growx");
		textFieldPilot.setColumns(10);
		
		JLabel lblFighter = new JLabel("Fighter");
		add(lblFighter, "cell 3 4");
		lblFighter.setForeground(c);
		lblFighter.setFont(f);
		
		textField_Fighter = new JTextField();
		textField_Fighter.setText("0");
		add(textField_Fighter, "cell 5 4,growx");
		textField_Fighter.setColumns(10);
		
		JLabel lblEngineer = new JLabel("Engineer");
		add(lblEngineer, "cell 6 4,alignx trailing");
		lblEngineer.setForeground(c);
		lblEngineer.setFont(f);
		
		textField_Engineer = new JTextField();
		textField_Engineer.setText("0");
		add(textField_Engineer, "cell 7 4,growx");
		textField_Engineer.setColumns(10);
		
		JLabel lblTrader = new JLabel("Trader");
		add(lblTrader, "cell 9 4,alignx trailing");
		lblTrader.setForeground(c);
		lblTrader.setFont(f);
		
		textField_Trader = new JTextField();
		textField_Trader.setText("0");
		add(textField_Trader, "cell 10 4,growx");
		textField_Trader.setColumns(10);
		
		JLabel lblDifficultyLevel = new JLabel("Select your difficulty level:");
		add(lblDifficultyLevel, "cell 1 5 5 1");
		lblDifficultyLevel.setForeground(c);
		lblDifficultyLevel.setFont(f);
		
		String[]comboBoxDefaults = {"Beginner","Easy","Normal","Hard","Impossible"};
		JComboBox comboBoxDifficulty = new JComboBox(comboBoxDefaults);
		add(comboBoxDifficulty, "cell 6 5 4 1,growx");
		
		JButton btnCreatePlayer = new JButton("Create");
		btnCreatePlayer.addActionListener(new PWButtonListener(textName.getText(), this));
		// btnCreatePlayer.setEnabled(false);
		add(btnCreatePlayer, "cell 9 7 2 1,alignx right");		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	public static String getTextFieldPilot() {
		return textFieldPilot.getText();
	}

	public static String getTextFieldFighter() {
		return textField_Fighter.getText();
	}

	public static String getTextFieldEngineer() {
		return textField_Engineer.getText();
	}

	public static String getTextFieldTrader() {
		return textField_Trader.getText();
	}
	
	public static String getTextFieldName(){
		return textName.getText();
	}

	public void setSkillFieldZero() {
		textFieldPilot.setText("0");
		textField_Fighter.setText("0");
		textField_Engineer.setText("0");
		textField_Trader.setText("0");
	}
}
