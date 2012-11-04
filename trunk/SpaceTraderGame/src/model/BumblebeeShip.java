package model;

import java.awt.Point;

/**
 * Represents the most expensive ship in the Space Trader
 * universe. It is the strongest ship type with the largest
 * amount of cargo bays. The user can carry the most 
 * shields and gadgets in this ship.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */
public class BumblebeeShip extends Ship{

	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 25;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Number of slots available to store weapons in the Ship. */
	private static final int WEAPONSLOTS = 1;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int GADGETSLOTS = 2;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int SHIELDSLOTS = 2;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVELRANGE = 15;
	
	/** Cost of the Ship. */
	private static final int COST = 60000;

	/**
	 * Instantiates a Firefly ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 */
	public BumblebeeShip(Point point){
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Firefly";
		fuelCapacity = TRAVELRANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
	}
}