package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Inventory;
import model.Planet;
import model.Player;
import view.GameWindow;
import view.MapWindow;
import view.PlanetWindow;
import view.PlayerWindow;
import view.TradeWindow;

/**
 * Controls the game flow
 * @author Quirky Qwertys
 * @version 1.0 
 */
public class GameController {
	
	private Player newPlayer;
	private Planet currentPlanet;
	
	
	
	private GameWindow gameWindow;
	
	
	/**
	 * Main method to start the game
	 * @param args
	 */
	
	public static void main(String[] args){
		//launch the player creation window;
				
		GameController gc = new GameController();
		gc.start();		
	}
	
	
	/**
	 * Starts a new Space Traders game.
	 */
	public void start(){
		try{
			
			gameWindow = new GameWindow(this);
			gameWindow.launch();
			newPlayer();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * creates the player window and the map window.
	 */
	public void newPlayer(){
			
		System.out.println("Create Player");
		gameWindow.showNewPlayerWin();
	}	
	
	/**
	 * Shows a map of the Planets/SolarSystems in the Universe.
	 */
	public void showMap() {
		gameWindow.showMapWin(newPlayer);
			
	}
	
	/**
	 * gets the player instance
	 * @return player
	 */
	public Player getPlayer(){
		return newPlayer;
	}


	/**
	 * gets the ship's cargo
	 * @return ship's inventory
	 */
	public Inventory getCargo() {
		return newPlayer.getCargo();
	}


	public void gotoMarketPlace() {
		gameWindow.showMarketPlace( newPlayer, currentPlanet.getInventory() );
	}

	public void gotoShipyard() {
		gameWindow.showShipyard(newPlayer);
		
	}

	public void showPlanet(){
		gameWindow.showPlanet( currentPlanet );
		
	}

	public boolean travelToPlanet(Planet planet) {
		boolean b = newPlayer.getShip().travel(planet);
		if (b){
			currentPlanet = planet;
			planet.createInventory();
			gameWindow.showPlanet( planet );
			return true;
		}
		return false;
	}


	public void newGame(Player newPlayer) {
		this.newPlayer = newPlayer;
		showMap();
		
	}
}