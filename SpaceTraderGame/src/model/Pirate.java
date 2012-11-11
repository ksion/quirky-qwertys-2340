package model;

import java.util.Random;

/**
 * Generates a pirate in the game by creating 
 * a random Ship
 * 
 * @author Quirky Qwertys
 *
 */
public class Pirate{
	
	private Ship pirateShip;
	private Random random = new Random();
	
	/**
	 * Instantiates a pirate with a Ship.
	 */
	public Pirate(){
		pirateShip = new Ship(random.nextInt(126) + 25);
	}
	
	/**
	 * Attack the player's Ship.
	 * 
	 * @param playerShip the ship that corresponds to the player
	 */
	public void attack(Ship playerShip){
		int pirateStrength = pirateShip.getHullStrength();
		int damage = (int) (pirateStrength * (random.nextFloat() * 0.41 + 0.10)); 
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
}