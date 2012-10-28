package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Planet;

/**
 * Represents the window that shows up when the player is visiting a
 * Planet. Through this window, the player is given the options to 
 * trade, sell, or buy TradeGoods or Equipment; travel to another 
 * Planet; or view the current inventory and statistics.
 *  
 * @author Quirky Qwertys
 * @version 1.0 10.29.12
 */
public class PlanetWindow {

	private JFrame frame;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanetWindow window = new PlanetWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlanetWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(7, 3, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("");
		centerPanel.add(lblNewLabel_4);
		
		JButton btnNewButton1 = new JButton("Go to the Marketplace");
		centerPanel.add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("Go to the Shipyard");
		centerPanel.add(btnNewButton2);
		
		JButton btnNewButton3 = new JButton("Travel ");
		centerPanel.add(btnNewButton3);
		
		JButton btnNewButton4 = new JButton("Check Status");
		centerPanel.add(btnNewButton4);
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel label = new JLabel("");
		northPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		northPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to planet [name]! What would you like to do?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(lblNewLabel_1);
		
		JPanel eastPanel = new JPanel();
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("                          ");
		eastPanel.add(lblNewLabel_2);
		eastPanel.setLayout(new GridLayout(1, 7, 0, 0));
		
		JPanel westPanel = new JPanel();
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		
		JLabel lblNewLabel_3 = new JLabel("                          ");
		westPanel.add(lblNewLabel_3);
		eastPanel.setLayout(new GridLayout(1, 7, 0, 0));
	}
}