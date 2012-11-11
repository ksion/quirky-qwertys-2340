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
	
	private Ship ship;
	private Random random = new Random();
	
	/**
	 * Instantiates a pirate with a Ship.
	 */
	public Pirate(){
		ship = new Ship(random.nextInt(125) + 25);
	}
}
