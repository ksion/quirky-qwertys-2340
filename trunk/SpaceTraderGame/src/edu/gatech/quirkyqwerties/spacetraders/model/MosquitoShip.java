/**
 * MosquitoShip.java
 * @version 1.0
 * copyright 2012
 */

package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

/**
 * This is the best ship type option for those who wish to become
 * pirates. It can travel up to 13 parsecs on a tank and has
 * a good hull strength. The user can carry the most amount of
 * weapons with this ship.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */
public class MosquitoShip extends Ship {
    

	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 15;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVEL_RANGE = 13;

	/**
	 * Instantiates a Mosquito ship.
	 * 
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public MosquitoShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Mosquito";
		fuelCapacity = TRAVEL_RANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 30000; // $codepro.audit.disable numericLiterals
	}
	
	/**
	 * empty constructor needed for json serialization
	 */
	protected MosquitoShip() { // $codepro.audit.disable emptyMethod
	}
}