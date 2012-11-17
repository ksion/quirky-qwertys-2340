/**
 * Game.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Holds the Game object to aid in loading and saving.
 * @author QuirkyQwertys
 * @version 1.0
 */

public class Game {
	
	/** Player in the game */
	private Player player;
	
	/** Planet the player is currently on */
	private Planet currentPlanet;
	
	/** Universe the player is currently in */
	private Universe universe;
	
	/** Solar System the player is currently in */
	private SolarSystem solarSystem;
	
	/**
	 * constructor to start the game
	 * @param player
	 * @throws IOException
	 */
	public Game(Player player) throws IOException{
		this.player = player; 
		createUniverse();
	}
	
	/**
	 * needed for flex json to create an empty object to populate
	 */
	protected Game(){ // $codepro.audit.disable emptyMethod
	}

	/**
	 * gets the player
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * sets the player
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * gets the current planet
	 * @return current planet
	 */
	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	/**
	 * sets the current planet
	 * @param currentPlanet
	 */
	public void setCurrentPlanet(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	/**
	 * gets the universe
	 * @return the universe
	 */
	public Universe getUniverse() {
		return universe;
	}

	/**
	 * sets the universe
	 * @param universe
	 */
	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	/**
	 * gets the solar system for the game
	 * @return the solar system
	 */
	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	/**
	 * sets the solar system
	 * @param solarSystem
	 */
	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}
	
	/**
	 * creates the initial universe
	 * @throws IOException
	 */
	public final void createUniverse() throws IOException{
		/** Represents the universe in Space Trader game. */
		universe = new Universe();
		solarSystem = universe.getSystems().get(0);
		currentPlanet = solarSystem.getPlanets().get(0);//set the starting planet to the first in the list
		currentPlanet.createInventory();
	}
	
	/**
	 * saves the game object using flexjson
	 * @throws IOException
	 */
	public void saveGame() throws IOException{
		final JSONSerializer newSerializer = new JSONSerializer();
		final File directoryName = new File(System.getProperty("user.home"),
				".spacetraders");
		if (!directoryName.exists()){
			if (!directoryName.mkdirs()){
				System.out.println("Error Making Directory");
			}
			
		}
		final FileWriter fw = new FileWriter(new File(directoryName,
				"spacetraders.json"));
		try{
			newSerializer.deepSerialize(this, fw);
		}
		finally{
			fw.close();
		}
	}
	
	/**
	 * loads the game object using jackson
	 * @return the Game object
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Game loadGame() throws IOException{
		final File gameFile = new File(System.getProperty("user.home"),
				".spacetraders/spacetraders.json");
		if (gameFile.exists()){
			final JSONDeserializer<Game> newDeserializer = new JSONDeserializer<Game>();
			final FileReader fr = new FileReader(new File(System.getProperty("user.home"),
					".spacetraders/spacetraders.json"));
			try{
				final Game newGame = newDeserializer.deserialize(fr);
				return newGame;
			}
			finally{
				fr.close();
			}
		}
		else return null;
	}
	
	/**
	 * Game's toString method
	 * @return String representation of the Game
	 */
	public String toString(){
		final StringBuilder gameStr = new StringBuilder();
		gameStr.append("Game: ");
		gameStr.append("Planet Name: " + currentPlanet.getName());
		gameStr.append("Solar System: " + solarSystem.getName());
		
		return gameStr.toString();
	}
	
	
	
	
	
	
}
