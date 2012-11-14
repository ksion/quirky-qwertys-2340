package model;

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
	
	public Game(Player player, Planet currentPlanet, Universe universe, SolarSystem solarSystem){
		this.player = player; 
		this.currentPlanet = currentPlanet;
		this.universe = universe;
		this.solarSystem = solarSystem;
		
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
	
	
	
	
}
