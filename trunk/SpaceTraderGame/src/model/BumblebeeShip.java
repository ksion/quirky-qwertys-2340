package model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents the most expensive ship in the Space Trader
 * universe. It is the strongest ship type with the largest
 * amount of cargo bays. The user can carry the most 
 * shields and gadgets in this ship.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 11.05.12
 */
public class BumblebeeShip extends Ship implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 25;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVEL_RANGE = 15;


	/**
	 * Instantiates a Firefly ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 * @throws IOException 
	 */
	public BumblebeeShip(Point point) throws IOException{
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Bumblebee";
		fuelCapacity = TRAVEL_RANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 60000;
	}
	
	protected BumblebeeShip() {		
	}
}