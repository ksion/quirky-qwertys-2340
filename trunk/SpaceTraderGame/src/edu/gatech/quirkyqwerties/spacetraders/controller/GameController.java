/**
 * GameController.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.controller;

import java.io.IOException;

import edu.gatech.quirkyqwerties.spacetraders.model.Game;
import edu.gatech.quirkyqwerties.spacetraders.model.Inventory;
import edu.gatech.quirkyqwerties.spacetraders.model.Planet;
import edu.gatech.quirkyqwerties.spacetraders.model.Player;
import edu.gatech.quirkyqwerties.spacetraders.view.GameWindow;

/**
 * Controls the game flow.
 * @author Quirky Qwertys
 * @version 1.0 
 */
public class GameController{
	
	/** gamewindow UI parent element*/
	private GameWindow gameWindow;
	
	/** game object */
	private Game currentGame;
	
	/**
	 * Main method to start the game.
	 * @param args default argument for main class
	 */
	
	public static void main(String[] args){
		// launch the player creation window
		new GameController().start();
	}

	/**
	 * launches the game window
	 */
	public void start(){
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
	 * Creates the player window and the map window.
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
	 * Gets the player instance.
	 * @return the player
	 */
	public Player getPlayer(){
		return currentGame.getPlayer();
	}

	/**
	 * Gets the ship's cargo
	 * @return the ship's inventory
	 */
	public Inventory getCargo(){
		return currentGame.getPlayer().getCargo();
	}

	/**
	 * Tells the game window to show the marketplace.
	 */
	public void gotoMarketPlace(){
		gameWindow.showMarketPlace( currentGame.getPlayer(), 
				currentGame.getCurrentPlanet().getTradableInventory() );
	}
	
	/**
	 * Tells the game window to show the shipyard screen.
	 */
	public void gotoShipyard(){
		gameWindow.showShipyard(currentGame.getPlayer());
	}

	/**
	 * Tells game window to show the planet screen.
	 */
	public void showPlanet(){
		gameWindow.showPlanet(currentGame.getCurrentPlanet(), currentGame.getPlayer());
	}
	
	/**
	 * Tells the game window to show the planet.
	 */
	public void showPlanetVisit(){
		gameWindow.showPlanet(currentGame.getCurrentPlanet(), currentGame.getPlayer());
	}
	
	/**
	 * Tells the game window to show the pirate encounter.
	 */
	public void showEncounter(){
		try {
			gameWindow.showEncounter(currentGame.getPlayer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * sets the current planet when the user tries to travel
	 * @param planet
	 * @return true if travel was successful
	 */
	public boolean canTravelToPlanet(Planet planet){
		final boolean b = currentGame.getPlayer().getShip().canTravel(planet);
		if (b){
			currentGame.setCurrentPlanet(planet);
			currentGame.getCurrentPlanet().createInventory();
			gameWindow.showPlanetVisitWin(currentGame.getCurrentPlanet(), 
					currentGame.getPlayer());
			return true;
		}
		return false;
	}

	/**
	 * Starts a new game.
	 * @param newPlayer the new player
	 */
	public void newGame(Player newPlayer){
		try{
			this.currentGame = new Game(newPlayer);
			showPlanetVisit();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	/**
	 * Shows the start window.
	 * @throws IOException 
	 */
	public void showStart(){
		gameWindow.showStartWindow();
	}

	/**
	 * loads the game using the saved game file in the user directory
	 * @throws IOException
	 */
	public void loadGame() throws IOException{
		currentGame = Game.loadGame();
		gameWindow.showPlanet( currentGame.getCurrentPlanet(), currentGame.getPlayer() );
		currentGame.getPlayer().getShip().loadResources();
	}

    /**
     * Saves the current game.
     * @throws IOException
     */
	public void save() throws IOException{
		currentGame.saveGame();
	}
	
	/**
	 * overrides the toString
	 * @return a string for the gamecontroller
	 */
	public String toString(){
		return "GameController";
	}
}