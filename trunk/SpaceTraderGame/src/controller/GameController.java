package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Inventory;
import model.Planet;
import model.Player;
import model.SaveLoad;
import model.SolarSystem;
import model.Universe;
import view.GameWindow;

/**
 * Controls the game flow
 * @author Quirky Qwertys
 * @version 1.0 
 */
public class GameController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Player newPlayer;
	public Planet currentPlanet;
	private Universe universe;
	private SolarSystem solarSystem;
	public transient GameWindow gameWindow;
	
	
	
	/**
	 * Main method to start the game
	 * @param args
	 */
	
	public static void main(String[] args){
		//launch the player creation window;
		GameController gc = new GameController();
	}
	


	/**
	 * Starts a new Space Traders game.
	 */
	public GameController(){
		try{
			gameWindow = new GameWindow(this);
			gameWindow.launch();
			gameWindow.showStartWindow(this);
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * creates the player window and the map window.
	 */
	public void newPlayer(){
		//System.out.println("Create Player");
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
	
	/**
	 * tells the game window to show the pirate encounter
	 */
	public void showEncounter(){
		gameWindow.showEncounter(newPlayer);
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
		
		try{
			this.newPlayer = newPlayer;
		
			createUniverse();
			currentPlanet = solarSystem.getPlanets().get(0);//set the starting planet to the first in the list
			currentPlanet.createInventory();
		
			showPlanetVisit();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	public void createUniverse() throws IOException{
		/** Represents the universe in Space Trader game. */
		universe = new Universe();
		solarSystem = universe.getSystems().get(0);
	}



	
}