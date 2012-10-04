import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class PlayerWindow extends JPanel {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textFieldPilot;
	private JTextField textField_Fighter;
	private JTextField textField_Engineer;
	private JTextField textField_Trader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerWindow player = new PlayerWindow();
					JFrame frame = new JFrame();
					frame.setContentPane( player );
					frame.setBounds(100, 100, 450, 300);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerWindow() {
//		setTitle("Player Creation");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("", "[][][grow][][][grow][grow][grow][][][grow][][][][]", "[][shrink 50][][][][][][grow,bottom]"));
		
		JLabel lblHeader = new JLabel("Pick your options below to start a new game.");
		add(lblHeader, "cell 1 0 14 1");
		
		JLabel lblNameLabel = new JLabel("Name");
		add(lblNameLabel, "cell 1 1,alignx trailing");
		
		textName = new JTextField();
		add(textName, "cell 2 1 4 1,growx");
		textName.setColumns(10);
		
		JLabel lblSkillPointsDesc1 = new JLabel("Start the game with 15 skill points. Allocate those points");
		add(lblSkillPointsDesc1, "cell 1 2 12 1");
		
		JLabel lblSkillPointsDesc2 = new JLabel("between the 4 skills below:");
		add(lblSkillPointsDesc2, "cell 1 3 12 1");
		
		JLabel lblPilot = new JLabel("Pilot");
		add(lblPilot, "cell 1 4,alignx trailing");
		
		textFieldPilot = new JTextField();
		textFieldPilot.setText("0");
		add(textFieldPilot, "cell 2 4,growx");
		textFieldPilot.setColumns(10);
		
		JLabel lblFighter = new JLabel("Fighter");
		add(lblFighter, "cell 3 4");
		
		textField_Fighter = new JTextField();
		textField_Fighter.setText("0");
		add(textField_Fighter, "cell 5 4,growx");
		textField_Fighter.setColumns(10);
		
		JLabel lblEngineer = new JLabel("Engineer");
		add(lblEngineer, "cell 6 4,alignx trailing");
		
		textField_Engineer = new JTextField();
		textField_Engineer.setText("0");
		add(textField_Engineer, "cell 7 4,growx");
		textField_Engineer.setColumns(10);
		
		JLabel lblTrader = new JLabel("Trader");
		add(lblTrader, "cell 9 4,alignx trailing");
		
		textField_Trader = new JTextField();
		textField_Trader.setText("0");
		add(textField_Trader, "cell 10 4,growx");
		textField_Trader.setColumns(10);
		
		JLabel lblDifficultyLevel = new JLabel("Select your difficulty level:");
		add(lblDifficultyLevel, "cell 1 5 5 1");
		
		String[]comboBoxDefaults = {"Beginner","Easy","Normal","Hard","Impossible"};
		JComboBox comboBoxDifficulty = new JComboBox(comboBoxDefaults);
		add(comboBoxDifficulty, "cell 6 5 4 1,growx");
		
		JButton btnCreatePlayer = new JButton("Create");
		add(btnCreatePlayer, "cell 9 7 2 1,alignx right");		
	}

}
