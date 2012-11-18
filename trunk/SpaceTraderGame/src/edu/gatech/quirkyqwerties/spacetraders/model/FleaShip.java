/**
 * FleaShip.java
 * @version 1.0
 * copyright 2012
 */

package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents the cheapest ship type in the game. It has few
 * cargo holds, a weak hull and no weapon, shield or gadget slots.
 * However, it can hold the most amount of fuel out of all the 
 * ship types and can travel longer distances.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */

public class FleaShip extends Ship{
	
	/** Number of cargo holds available for the FleaShip. */
	private static final int CARGOBAY = 10;
	
	/** Hull strength of a FleaShip */
	private static final int HULL_STRENGTH = 25;
	
	/** Range of parsecs Ship can travel before it runs out of fuel for a FleaShip. */
	private static final int TRAVEL_RANGE = 20;
	
	/**
	 * Instantiates a Flea ship.
	 * 
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public FleaShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Flea";
		fuelCapacity = TRAVEL_RANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 2000; // $codepro.audit.disable numericLiterals
	}
	
	/**
	 * Empty method needed for flexjson serializer.
	 */
	protected FleaShip(){ // $codepro.audit.disable emptyMethod
	}
}