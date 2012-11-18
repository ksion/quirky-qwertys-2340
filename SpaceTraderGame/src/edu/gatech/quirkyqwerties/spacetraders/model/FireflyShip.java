/**
 * FireflyShip.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents a fairly efficient ship. It's not too expensive,
 * has plenty of cargo bays and can travel up to 17 parsecs on
 * a tank.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */
public class FireflyShip extends Ship{
	
	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 20;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVEL_RANGE = 17;

	/**
	 * Instantiates a Firefly ship.
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public FireflyShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Firefly";
		fuelCapacity = TRAVEL_RANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 25000; // $codepro.audit.disable numericLiterals
	}
	
	/**
	 * Empty method needed for flexjson serializer.
	 */
	protected FireflyShip(){ // $codepro.audit.disable emptyMethod
	}
}