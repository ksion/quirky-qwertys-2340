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
 * Parent Container for all the game screens
 * @author Quirky Qwertys
 *
 */
public class GameWindow extends JPanel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	GameController controller;
	
	MapWindow mapWin;
	PlanetWindow planetWin;
	PlayerWindow playerWin;
	ShipyardWindow shipyardWin;
	TradeWindow tradeWin;
	PlanetVisitWindow planetVisitWin;
	StartWindow startWin;
	CardLayout deck;
	
	public GameWindow(GameController controller) throws IOException {
		this.controller = controller;
		setBackground(Color.black);
		
		planetVisitWin = new PlanetVisitWindow(controller);
		mapWin = new MapWindow(controller);
		planetWin = new PlanetWindow(controller);
		playerWin = new PlayerWindow(controller);
		startWin = new StartWindow(controller);
		//shipyardWin = new ShipyardWindow(controller);
		//tradeWin = new TradeWindow(controller);
		deck = new CardLayout();
		setLayout(deck);
		add(mapWin, "map");
		add(planetWin, "planet");
		add(playerWin, "player");
		add(startWin, "StartWindow");
		//add(shipyardWin, "shipyard");
		//add(tradeWin, "trade");
		add(planetVisitWin, "planetVisit");
		
	}
	
	/**
	 * launches the main game window
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
 * sets the planet and player
 * @param planet
 * @param p
 */
	public void showPlanet(Planet planet, Player p) {
		planetVisitWin.setPlanet(planet);
		planetVisitWin.setPlayer(p);
		//deck.show(this, "planet");
		planetVisitWin.showCard("bluegreen");
		deck.show(this, "planetVisit");
	}

	
/**
 * shows the shipyard card
 * @param player
 */
	public void showShipyard(Player player) {
		planetVisitWin.getShipyardWin().setPlayer(player);
		//deck.show(this, "shipyard");
		planetVisitWin.showCard("shipyard");
		
	}

/**
 * shows the marketplace card
 * @param player
 * @param inventory
 */
	public void showMarketPlace(Player player, Inventory inventory) {
		planetVisitWin.getTradeWindow().setPlayer(player);
		planetVisitWin.getTradeWindow().setOtherInventory(inventory);
		//deck.show(this, "trade");
		planetVisitWin.showCard("trade");
		
	}

/**
 * shows the player window
 */
	public void showNewPlayerWin() {
		deck.show(this, "player");
		
	}

/**
 * shows the map window
 * @param player
 * @param solarSystem
 */
	public void showMapWin(Player player, SolarSystem solarSystem) {
		planetVisitWin.getMapWin().setPlayer(player);
		planetVisitWin.getMapWin().setSolarSystem(solarSystem);
		planetVisitWin.setPlayer(player);
		planetVisitWin.showCard("map");
		deck.show(this,"planetVisit");
		
	}
	
	/**
	 * shows the planet visit window
	 * @param p
	 * @param player
	 */
	public void showPlanetVisitWin(Planet p, Player player) {
		showPlanet(p, player);
		deck.show(this,"planetVisit");
		
	}
	
	/**
	 * shows the new game window
	 */
	public void showStartWindow(GameController gc) {
		startWin = new StartWindow(gc);
		deck.show(this, "StartWindow");
	}
}
