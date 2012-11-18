package view;

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
	private GameController controller;
	private Planet planet;
	private JLabel planetNameLabel;
	/*private Image img;
	private Color c = new Color(0, 255, 0);
	private Font f = new Font("Space Age", 1, 12);*/
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 * 
	 * @param planet the current Planet in which the Ship is located  
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
	 * @param p the current Planet in which the Ship is located  
	 */
	private void initialize() {
		setLayout( new BorderLayout() );
		
		JPanel centerPanel = new JPanel();
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
		northPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel label = new JLabel("");
		northPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		northPanel.add(lblNewLabel);
		
		planetNameLabel = new JLabel("");
		planetNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(planetNameLabel);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("                          ");
		eastPanel.add(lblNewLabel_2);
		eastPanel.setLayout(new GridLayout(1, 7, 0, 0));
		
		JPanel westPanel = new JPanel();
		
		JLabel lblNewLabel_3 = new JLabel("                          ");
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
	 */
	private class MarketPlaceListener implements ActionListener{
		
		/**
		 * When the "Go to the Marketplace" button is clicked, the 
		 * marketplace window pops up.
		 * 
		 * @param ae the event that corresponds to when the button is clicked
		 */
		public void actionPerformed(ActionEvent ae){
			controller.gotoMarketPlace();
		}
	}
	/**
	 * Opens the ship yard window, so that the user may trade 
	 * his/her ship.
	 * @param event the event that corresponds to when the button is clicked
	 */
	private class ShipyardListener implements ActionListener{
		
		/**
		 * When the "Go to the Shipyard" button is clicked the shipyard
		 * window pops up.
		 */
		public void actionPerformed(ActionEvent event){
			controller.gotoShipyard();
		}
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
		
		planetNameLabel.setText("Welcome to " + planet.getName() + "! What would you like to do?");		
	}
}