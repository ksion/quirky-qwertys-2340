package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;

import controller.GameController;

import model.Planet;
import model.Ship;

/**
 * Represents the window that shows up when the player is visiting a
 * Planet. Through this window, the player is given the options to 
 * trade, sell, or buy TradeGoods or the Ship; travel to another 
 * Planet; or view the current inventory and statistics.
 *  
 * @author Quirky Qwertys
 * @version 1.0 10.29.12
 */
public class PlanetWindow {
	private Planet planet;
	private JFrame frame;
	private GameController controller;
	private Image img;
	private Color c = new Color(0, 255, 0);
	private Font f = new Font("Space Age", 1, 12);

	/**
	 * Create the application.
	 * 
	 * @param planet the current Planet in which the Ship is located  
	 */
	public PlanetWindow(Planet planet, GameController gc) {
		this.planet = planet;
		controller = gc;
		initialize();
		try{
			frame.setTitle("Planet " + planet.getName());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Image spaceIcon = ImageIO.read(new File("src/view/shipIcon.png"));
			frame.setIconImage(spaceIcon);
			frame.pack();
			frame.setVisible(true);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param p the current Planet in which the Ship is located  
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setPreferredSize(new Dimension(650, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(7, 3, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("");
		centerPanel.add(lblNewLabel_4);
		
		JButton btnNewButton1 = new JButton("Go to the Marketplace");
		centerPanel.add(btnNewButton1);
		btnNewButton1.addActionListener(new MarketPlaceListener());
		
		centerPanel.add(new JLabel(""));
		
		JButton btnNewButton2 = new JButton("Go to the Shipyard");
		centerPanel.add(btnNewButton2);
		btnNewButton2.addActionListener(new ShipyardListener());
		
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel label = new JLabel("");
		northPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		northPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to " + planet.getName() + "! What would you like to do?");
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
	
	/**
	 * Opens the marketplace window, so that the user may trade, buy,
	 * or sell TradeGoods.
	 */
	private class MarketPlaceListener implements ActionListener{
		
		/**
		 * When the "Go to the Marketplace" button is clicked, the 
		 * marketplace window pops up.
		 * 
		 * @param ae the event that corresponds to when the button is clicked
		 */
		public void actionPerformed(ActionEvent ae){
			JFrame frame = new JFrame();
			TradeWindow tradeWindow;
			try {
				tradeWindow = new TradeWindow();
				tradeWindow.setPlayer(controller.getPlayer());
				tradeWindow.setOtherInventory(planet.getInventory());
				frame.setTitle("Marketplace");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setContentPane(tradeWindow);
				frame.setBounds(100, 100, 450, 300);
				frame.setPreferredSize(new Dimension(650, 250));
				frame.setVisible(true);
				frame.pack();
			} 
			catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
	}
	/**
	 * Opens the shipyard window, so that the user may trade 
	 * his/her ship.
	 * @param event the event that corresponds to when the button is clicked
	 */
	private class ShipyardListener implements ActionListener{
		
		/**
		 * When the "Go to the Shipyard" button is clicked the shipyard
		 * window pops up.
		 */
		public void actionPerformed(ActionEvent event){
			JFrame frame = new JFrame();
			ShipyardWindow shipyardWindow;
			try{
			    shipyardWindow = new ShipyardWindow(controller.getPlayer());
			    frame.setTitle("Shipyard");
			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setContentPane(shipyardWindow);
				frame.setBounds(100, 100, 450, 300);
				frame.setPreferredSize(new Dimension(650, 250));
				frame.setVisible(true);
				frame.pack();			    
			}
			catch (Exception e){
			    e.printStackTrace();
			}
		}
	}
}