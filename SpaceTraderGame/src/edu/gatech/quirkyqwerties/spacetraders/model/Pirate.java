/**
 * Pirate.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.io.IOException;
import java.util.Random;

/**
 * Generates a pirate in the game by creating 
 * a Ship with a random hull strength.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.14.12
 */
public class Pirate {
	
	/** Represents the pirate's ship. */
	private final Ship pirateShip;
	
	/** Random number generator. */
	private final Random random = new Random();
	
	/** Possible middle value for the pirate ship's hull strength. */
	private static final int MIDDLE_STRENGTH = 60;
	
	/** Possible minimum value for the pirate ship's hull strength. */
	private static final int MIN_STRENGTH = 70;
	
	/** Number used to determine damage. */
	private static final float PERCENT = 0.25f;
	
	/** Possible min percentage of hull strength used to determine damage. */
	private static final float MIN_PERCENT = 0.10f;
	
	/**
	 * Instantiates a pirate with a Ship.
	 * 
	 * @throws IOException 
	 */
	public Pirate() throws IOException{
		pirateShip = new Ship(random.nextInt(MIDDLE_STRENGTH) + MIN_STRENGTH);
	}
	
	/**
	 * Attack the player's Ship. Deals damage based on 
	 * 10-35% of the hull strength value  
	 * 
	 * @param playerShip the ship that corresponds to the player
	 */
	public void attack(Ship playerShip){
		final int pirateStrength = pirateShip.getHullStrength();
		final int damage = (int) (pirateStrength * (random.nextFloat() * PERCENT  +  // $codepro.audit.disable lossOfPrecisionInCast
				MIN_PERCENT)); 
		playerShip.setDamageSustained(playerShip.getDamageSustained() + damage);
	}
	
	/**
	 * Retrieves a Pirate's Ship.
	 * 
	 * @return the pirate's ship
	 */
	public Ship getShip(){
		return pirateShip;
	}
	
	/**
	 * Creates a String with information about the
	 * pirate ship's hull strength.
	 * 
	 * @return information containing the ship's hull 
	 * strength.
	 */
	public String toString(){
		return "Pirate ship's hull strength: " + pirateShip.getHullStrength();
	}
}