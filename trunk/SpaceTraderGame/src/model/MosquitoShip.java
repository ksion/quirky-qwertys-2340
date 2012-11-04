package model;

import java.awt.Point;

/**
 * This is the best ship type option for those who wish to become
 * pirates. It can travel up to 13 parsecs on a tank and has
 * a good hull strength. The user can carry the most amount of
 * weapons with this ship.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */
public class MosquitoShip extends Ship{
    
	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 15;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Number of slots available to store weapons in the Ship. */
	private static final int WEAPONSLOTS = 2;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int GADGETSLOTS = 1;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int SHIELDSLOTS = 1;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVELRANGE = 13;
	
	/** Cost of the Ship. */
	private static final int COST = 30000;

	/**
	 * Instantiates a Mosquito ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 */
	public MosquitoShip(Point point){
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Mosquito";
		fuelCapacity = TRAVELRANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
	}
}