package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Inventory;
import model.Planet;
import model.Player;

import controller.GameController;

public class GameWindow extends JPanel {
	GameController controller;
	
	MapWindow mapWin;
	PlanetWindow planetWin;
	PlayerWindow playerWin;
	ShipyardWindow shipyardWin;
	TradeWindow tradeWin;
	CardLayout deck;
	
	public GameWindow(GameController controller) throws IOException {
		this.controller = controller;
		
		mapWin = new MapWindow(controller);
		planetWin = new PlanetWindow(controller);
		playerWin = new PlayerWindow(controller);
		shipyardWin = new ShipyardWindow(controller);
		tradeWin = new TradeWindow(controller);
		deck = new CardLayout();
		setLayout(deck);
		add(mapWin, "map");
		add(planetWin, "planet");
		add(playerWin, "player");
		add(shipyardWin, "shipyard");
		add(tradeWin, "trade");
		
	}
	
	
	public void launch(){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					JFrame frame = new JFrame();
					frame.setPreferredSize(new Dimension(800, 450));
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


	public void showPlanet(Planet planet) {
		planetWin.setPlanet(planet);
		deck.show(this, "planet");
	}


	public void showShipyard(Player player) {
		shipyardWin.setPlayer(player);
		deck.show(this, "shipyard");
		
	}


	public void showMarketPlace(Player player, Inventory inventory) {
		tradeWin.setPlayer(player);
		tradeWin.setOtherInventory(inventory);
		deck.show(this, "trade");
		
	}


	public void showNewPlayerWin() {
		deck.show(this, "player");
		
	}


	public void showMapWin(Player player) {
		mapWin.setPlayer(player);
		deck.show(this,"map");
		
	}
}
