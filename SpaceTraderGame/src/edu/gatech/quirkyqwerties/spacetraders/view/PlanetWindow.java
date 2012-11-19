/** 
 * PlanetWindow.java
 * @author QuirkyQwertys
 */
// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString, whiteSpaceUsage, fieldJavadoc
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;
import edu.gatech.quirkyqwerties.spacetraders.model.Planet;

/**
 * Represents the window that shows up when the player is visiting a
 * Planet. Through this window, the player is given the options to 
 * trade, sell, or buy TradeGoods or the Ship; travel to another 
 * Planet; or view the current inventory and statistics.
 *  
 * @author Quirky Qwertys
 * @version 1.0 10.29.12
 */
public class PlanetWindow extends JPanel {
	private final GameController controller;

	private Planet planet;

	private JLabel planetNameLabel;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 * 
	
	 * @param controller GameController
	 */
	public PlanetWindow(GameController controller) {
		this.controller = controller;
		initialize();
		/*
		try{
			//frame.setTitle("Planet " + planet.getName());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Image spaceIcon = ImageIO.read(new File("src/view/shipIcon.png"));
			frame.setIconImage(spaceIcon);
			frame.pack();
			frame.setVisible(true);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		*/
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 *
	 */
	private void initialize() {
		setLayout( new BorderLayout() );
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(7, 3, 0, 0));
		
		final JLabel lblNewLabel_4 = new JLabel("");
		centerPanel.add(lblNewLabel_4);
		
		final JButton btnNewButton1 = new JButton("Go to the Marketplace");
		centerPanel.add(btnNewButton1);
		btnNewButton1.addActionListener(new MarketPlaceListener());
		
		centerPanel.add(new JLabel(""));
		
		final JButton btnNewButton2 = new JButton("Go to the Shipyard");
		centerPanel.add(btnNewButton2);
		btnNewButton2.addActionListener(new ShipyardListener());

		final JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		final JLabel label = new JLabel("");
		northPanel.add(label);
		
		final JLabel lblNewLabel = new JLabel("");
		northPanel.add(lblNewLabel);
		
		planetNameLabel = new JLabel("");
		planetNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(planetNameLabel);
		
		final JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		final JLabel lblNewLabel_2 = new JLabel("                          ");
		eastPanel.add(lblNewLabel_2);
		eastPanel.setLayout(new GridLayout(1, 7, 0, 0));
		
		final JPanel westPanel = new JPanel();
		
		final JLabel lblNewLabel_3 = new JLabel("                          ");
		westPanel.add(lblNewLabel_3);
		eastPanel.setLayout(new GridLayout(1, 7, 0, 0));

		add( northPanel, BorderLayout.NORTH );
		add( eastPanel, BorderLayout.EAST );
		add( westPanel, BorderLayout.WEST );
		add( centerPanel, BorderLayout.CENTER );
	}
	
	/**
	 * Opens the marketplace window, so that the user may trade, buy,
	 * or sell TradeGoods.
	 * @author Keanna
	 */
	private class MarketPlaceListener implements ActionListener{
		
		/**
		 * When the "Go to the Marketplace" button is clicked, the 
		 * marketplace window pops up.
		 * 
		 * @param ae the event that corresponds to when the button is clicked
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		public void actionPerformed(ActionEvent ae){
			controller.gotoMarketPlace();
		}
	}

	/**
	 * Opens the ship yard window, so that the user may trade 
	 * his/her ship.
	 * @param event the event that corresponds to when the button is clicked <!-- // $codepro.audit.disable typeJavadoc -->
	 * @author QuirkyQwerties
	 */
	private class ShipyardListener implements ActionListener{
		
		/**
		 * When the "Go to the Shipyard" button is clicked the shipyard
		 * window pops up.
		 * @param event ActionEvent
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		public void actionPerformed(ActionEvent event){
			controller.gotoShipyard();
		}
	}

	/**
	 * Method getPlanet.
	 * @return Planet
	 */
	public Planet getPlanet() {
		return planet;
	}

	/**
	 * Method setPlanet.
	 * @param planet Planet
	 */
	public void setPlanet(Planet planet) {
		this.planet = planet;
		
		planetNameLabel.setText("Welcome to " + 
		planet.getName() + "! What would you like to do?");		
	}
	
	
}