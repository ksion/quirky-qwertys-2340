package model;

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

public class FleaShip extends Ship implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Number of cargo holds available for the FleaShip. */
	private static final int CARGOBAY = 10;
	
	/** Hull strength of a FleaShip */
	private static final int HULL_STRENGTH = 25;
	
	/** Number of slots available to store weapons for a FleaShip. */
	private static final int WEAPONSLOTS = 0;
	
	/** Number of slots available to store gadgets for a FleaShip. */
	private static final int GADGETSLOTS = 0;
	
	/** Number of slots available to store shields for a FleaShip. */
	private static final int SHIELDSLOTS = 0;
	
	/** Range of parsecs Ship can travel before it runs out of fuel for a FleaShip. */
	private static final int TRAVELRANGE = 20;
	
	/**
	 * Instantiates a Flea ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public FleaShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Flea";
		fuelCapacity = TRAVELRANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 2000;
	}
	
	protected FleaShip() {
	}
}