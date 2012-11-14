package controller;

import java.io.IOException;
import java.util.ArrayList;


import model.Game;
import model.Inventory;
import model.Planet;
import model.Player;
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
	public transient GameWindow gameWindow;
	private Game currentGame;
	
	
	
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
			gameWindow.showStartWindow();
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * creates the player window and the map window.
	 */
	public void newPlayer() {
		gameWindow.showNewPlayerWin();
	}	
	
	/**
	 * Shows a map of the Planets/SolarSystems in the Universe.
	 */
	public void showMap() {
		gameWindow.showMapWin(currentGame.getPlayer(), currentGame.getSolarSystem());
			
	}
	
	
	
	/**
	 * gets the player instance
	 * @return player
	 */
	public Player getPlayer(){
		return currentGame.getPlayer();
	}


	/**
	 * gets the ship's cargo
	 * @return ship's inventory
	 */
	public Inventory getCargo() {
		return currentGame.getPlayer().getCargo();
	}

	/**
	 * tells the game window to show the marketplace
	 */
	public void gotoMarketPlace() {
		gameWindow.showMarketPlace( currentGame.getPlayer(), currentGame.getCurrentPlanet().getTradableInventory() );
	}
	
	/**
	 * tells the game window to show the shipyard screen
	 */
	public void gotoShipyard() {
		gameWindow.showShipyard(currentGame.getPlayer());
		
	}

	/**
	 * tells game window to show the planet screen
	 */
	public void showPlanet(){
		gameWindow.showPlanet( currentGame.getCurrentPlanet(), currentGame.getPlayer() );
		//gameWindow.showPlanetVisitWin();
		
	}
	
	/**
	 * tells the game window to show the planet
	 */
	public void showPlanetVisit(){
		gameWindow.showPlanet( currentGame.getCurrentPlanet(),currentGame.getPlayer() );
	}
	
	/**
	 * tells the game window to show the pirate encounter
	 */
	public void showEncounter(){
		try {
			gameWindow.showEncounter(currentGame.getPlayer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean travelToPlanet(Planet planet) {
		boolean b = currentGame.getPlayer().getShip().travel(planet);
		if (b){
			currentGame.setCurrentPlanet(planet);
			currentGame.getCurrentPlanet().createInventory();
			//gameWindow.showPlanet( planet );
			gameWindow.showPlanetVisitWin(currentGame.getCurrentPlanet(), currentGame.getPlayer());
			return true;
		}
		return false;
	}


	public void newGame(Player newPlayer) {
		
		try{
			this.currentGame = new Game(newPlayer);	
			showPlanetVisit();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	/**
	 * shows the start window
	 * @throws IOException 
	 */
	public void showStart(){
		try {
			gameWindow.showStartWindow();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}



	public void loadGame() throws IOException {
		currentGame = Game.loadGame();
		gameWindow.showPlanet( currentGame.getCurrentPlanet(), currentGame.getPlayer() );
		currentGame.getPlayer().getShip().loadResources();
	}


/**
 * saves the current game
 * @throws IOException
 */
	public void save() throws IOException {
		currentGame.saveGame();
		
	}
	
	



	
}