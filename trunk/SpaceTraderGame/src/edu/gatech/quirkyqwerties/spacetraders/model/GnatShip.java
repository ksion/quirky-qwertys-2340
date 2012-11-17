package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents the default Ship a Traveler starts off with.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 */
public class GnatShip extends Ship implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 15;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVEL_RANGE = 14;
	
	
	/**
	 * Instantiates a Gnat ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public GnatShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Gnat";
		fuelCapacity = TRAVEL_RANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 10000;
	}

	/**
	 * Needed by serialization
	 */
	protected GnatShip() {
		super();
	}
}