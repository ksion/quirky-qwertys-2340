/**
 * Shipyard.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents the shipyard where the user may trade 
 * his/her old ship for a new a new one.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.05.12
 */
public class Shipyard{
	
	/** Number of all the types of ships available in the game.*/
	private static final int SHIP_TYPES = 5;
	
	/** List of all the ships in the Shipyard. */
	private static final Ship[] SHIPS = new Ship[SHIP_TYPES];
	
	/**
	 * Instantiates a shipyard with all of the ships
	 * that exist in the Space Trader universe.
	 * 
	 * @param s the player's current ship
	 * @throws IOException 
	 */
	public Shipyard(Ship s) throws IOException{
		final Point point = s.getLocation();
		final FleaShip flea = new FleaShip(point);
		final GnatShip gnat = new GnatShip(point);
		final FireflyShip firefly = new FireflyShip(point);
		final MosquitoShip mosquito = new MosquitoShip(point);
		final BumblebeeShip bee = new BumblebeeShip(point);
		
		SHIPS[0] = flea;
		SHIPS[1] = gnat;
		SHIPS[2] = firefly; // $codepro.audit.disable numericLiterals
		SHIPS[3] = mosquito; // $codepro.audit.disable numericLiterals
		SHIPS[4] = bee; // $codepro.audit.disable numericLiterals
	}
	
	/**
	 * Retrieves a list of all the ships in the Shipyard.
	 * 
	 * @return the list of ships
	 */
	public Ship[] getShips(){
		return SHIPS;
	}
	
	/**
	 * Returns a String containing all of the ships available
	 * in the shipyard.
	 * 
	 * @return a String with all available ships in shipyard
	 */
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		for (Ship ship : SHIPS)
			sb.append(ship.getName() + "\n");
		return sb.toString();
	}
}