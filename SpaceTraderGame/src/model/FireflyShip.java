package model;

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
public class FireflyShip extends Ship implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 20;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Number of slots available to store weapons in the Ship. */
	private static final int WEAPONSLOTS = 1;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int GADGETSLOTS = 1;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int SHIELDSLOTS = 1;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVELRANGE = 17;

	/**
	 * Instantiates a Firefly ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public FireflyShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Firefly";
		fuelCapacity = TRAVELRANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 25000;
	}
	
	protected FireflyShip() {
	}
}