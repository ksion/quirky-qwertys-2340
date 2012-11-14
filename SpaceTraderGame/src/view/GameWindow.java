package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Inventory;
import model.Planet;
import model.Player;
import model.SolarSystem;

import controller.GameController;
/**
 * Parent Container for all the game screens.
 * 
 * @author Quirky Qwertys
 */
public class GameWindow extends JPanel {
	private static final long serialVersionUID = 1L;

	GameController controller;
	
	PlayerWindow playerWin;
	PlanetVisitWindow planetVisitWin;
	StartWindow startWin;
	EncounterPanel encounterPanel;
	CardLayout deck;
	
	/**
	 * Instantiates the game window.
	 * 
	 * @param controller the game controller for a game
	 */
	public GameWindow(GameController controller) throws IOException {
		this.controller = controller;
		setBackground(Color.black);
		
		planetVisitWin = new PlanetVisitWindow(controller);
		playerWin = new PlayerWindow(controller);
		startWin = new StartWindow(controller);
		encounterPanel = new EncounterPanel(controller);
		deck = new CardLayout();
		setLayout(deck);
		add(playerWin, "player");
		add(startWin, "StartWindow");
		add(planetVisitWin, "planetVisit");
		add(encounterPanel,"encounter");
	}
	
	/**
	 * Launches the main game window.
	 */
	public void launch(){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					JFrame frame = new JFrame();
					frame.setPreferredSize(new Dimension(1024, 768));
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setFocusableWindowState(true);
					frame.setLocationByPlatform(true);
			
					frame.add(GameWindow.this);
					frame.pack();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}

/**
 * Sets the planet and player and shows the planet
 * the player is visiting.
 * 
 * @param planet the planet the player is in
 * @param p the current player
 */
	public void showPlanet(Planet planet, Player p) {
		planetVisitWin.setPlanet(planet);
		planetVisitWin.setPlayer(p);
		planetVisitWin.showCard("bluegreen");
		deck.show(this, "planetVisit");
	}

	
/**
 * Shows the shipyard card.
 * 
 * @param player the current player
 */
	public void showShipyard(Player player) {
		planetVisitWin.getShipyardWin().setPlayer(player);
		planetVisitWin.showCard("shipyard");
		
	}

/**
 * Shows the marketplace card.
 * 
 * @param player the current player
 * @param inventory the player's inventory
 */
	public void showMarketPlace(Player player, Inventory inventory) {
		planetVisitWin.getTradeWindow().setPlayer(player);
		planetVisitWin.getTradeWindow().setOtherInventory(inventory);
		planetVisitWin.showCard("trade");
		
	}

/**
 * Shows the player window where the player selects
 * his/her skill points.
 */
	public void showNewPlayerWin() {
		playerWin.clearFields();
		deck.show(this, "player");
	}

/**
 * Shows the map window.
 * 
 * @param player the current player
 * @param solarSystem the current solar system the player is in
 */
	public void showMapWin(Player player, SolarSystem solarSystem) {
		planetVisitWin.getMapWin().setPlayer(player);
		planetVisitWin.getMapWin().setSolarSystem(solarSystem);
		planetVisitWin.setPlayer(player);
		planetVisitWin.showCard("map");
		deck.show(this,"planetVisit");
	}
	
	/**
	 * Shows the planet visit window.
	 * 
	 * @param p the planet
	 * @param player the current player
	 */
	public void showPlanetVisitWin(Planet p, Player player) {
		showPlanet(p, player);
		deck.show(this,"planetVisit");
		
	}
	
	/**
	 * Shows the new game/load game window.
	 * 
	 * @param gc the game controller of a game
	 */
	public void showStartWindow() throws IOException {
		deck.show(this, "StartWindow");
	}

	/**
	 * Shows the pirate encounter window.
	 *  
	 * @param p the current player
	 */
	public void showEncounter(Player p) throws IOException {
		encounterPanel.setPlayer(p);
		deck.show(this,"encounter");
	}
}