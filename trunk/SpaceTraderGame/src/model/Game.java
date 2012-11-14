package model;

import java.io.IOException;

/**
 * class to hold the Game object to aid in loading and saving
 * @author QuirkyQwertys
 *1.0
 */

public class Game {
	private Player player;
	private Planet currentPlanet;
	private Universe universe;
	private SolarSystem solarSystem;
	
	public Game(Player player) throws IOException{
		this.player = player; 
		createUniverse();

		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	public void setCurrentPlanet(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}
	
	public void createUniverse() throws IOException{
		/** Represents the universe in Space Trader game. */
		universe = new Universe();
		solarSystem = universe.getSystems().get(0);
		currentPlanet = solarSystem.getPlanets().get(0);//set the starting planet to the first in the list
		currentPlanet.createInventory();
	}
	
	
	
	
	
	
}
