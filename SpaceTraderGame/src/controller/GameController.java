package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import model.Inventory;
import model.Planet;
import model.Player;
import model.SolarSystem;
import model.Universe;
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
	private Universe universe;
	private ArrayList<SolarSystem> systems;
	private SolarSystem solarSystem;
	
	
	
	
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
		gameWindow.showMapWin(newPlayer, solarSystem);
			
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

	/**
	 * tells the game window to show the marketplace
	 */
	public void gotoMarketPlace() {
		gameWindow.showMarketPlace( newPlayer, currentPlanet.getInventory() );
	}
	
	/**
	 * tells the game window to show the shipyard screen
	 */
	public void gotoShipyard() {
		gameWindow.showShipyard(newPlayer);
		
	}

	/**
	 * tells game window to show the planet screen
	 */
	public void showPlanet(){
		gameWindow.showPlanet( currentPlanet, newPlayer );
		//gameWindow.showPlanetVisitWin();
		
	}
	
	/**
	 * tells the game window to show the planet
	 */
	public void showPlanetVisit(){
		gameWindow.showPlanet( currentPlanet,newPlayer );
	}

	public boolean travelToPlanet(Planet planet) {
		boolean b = newPlayer.getShip().travel(planet);
		if (b){
			currentPlanet = planet;
			planet.createInventory();
			//gameWindow.showPlanet( planet );
			gameWindow.showPlanetVisitWin(planet, newPlayer);
			return true;
		}
		return false;
	}


	public void newGame(Player newPlayer) {
		this.newPlayer = newPlayer;
		createUniverse();
		currentPlanet = solarSystem.getPlanets().get(0);//set the starting planet to the first in the list
		currentPlanet.createInventory();
		//showMap();
		showPlanetVisit();
		
	}
	
	public void createUniverse(){
		/** Represents the universe in Space Trader game. */
		universe = new Universe();
		systems = universe.getSystems();
		solarSystem = systems.get(0);
	}
}