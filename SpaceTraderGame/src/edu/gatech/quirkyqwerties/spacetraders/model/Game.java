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
	
	/**
	 * needed for jackson to create an empty object to populate
	 */
	protected Game(){
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
	
	/**
	 * saves the game object using flexjson
	 * @throws IOException
	 */
	public void saveGame() throws IOException{
		JSONSerializer newSerializer = new JSONSerializer();
		File directoryName = new File(System.getProperty("user.home"),".spacetraders");
		if (!directoryName.exists()){
			directoryName.mkdirs();
		}
		FileWriter fw = new FileWriter(new File(directoryName, "spacetraders.json"));
		try{
			newSerializer.deepSerialize(this,fw);
		}
		finally{
			fw.close();
		}
	}
	
	/**
	 * loads the game object using jackson
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Game loadGame() throws IOException{
		File gameFile = new File(System.getProperty("user.home"),
				".spacetraders/spacetraders.json");
		if (gameFile.exists()){
			JSONDeserializer<Game> newDeserializer = new JSONDeserializer<Game>();
			FileReader fr = new FileReader(new File(System.getProperty("user.home"),
					".spacetraders/spacetraders.json"));
			try{
				Game newGame = newDeserializer.deserialize(fr);
				return newGame;
			}
			finally{
				fr.close();
			}
		}
		else return null;
	}
	
	
	
	
	
	
}
