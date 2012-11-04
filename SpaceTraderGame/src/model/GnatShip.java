package model;

import java.awt.Point;

/**
 * Represents the default Ship a Traveler starts off with.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 */
public class GnatShip extends Ship {
	
	/** Number of cargo holds available in the Ship. */
	private static final int CARGOBAY = 15;
	
	/** Hull strength of the Ship. */
	private static final int HULL_STRENGTH = 100;
	
	/** Number of slots available to store weapons in the Ship. */
	private static final int WEAPONSLOTS = 1;
	
	/** Number of slots available to store gadgets in the Ship. */
	private static final int GADGETSLOTS = 1;
	
	/** Number of slots available to store gadgets for a Ship. */
	private static final int SHIELDSLOTS = 0;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVELRANGE = 14;
	
	/**
	 * Instantiates a Gnat ship.
	 * 
	 * @param hullStrength the strength of the Ship's hull
	 * @param point the location of the Ship
	 */
	public GnatShip(Point point){
		super(HULL_STRENGTH, CARGOBAY, point);
		name = "Gnat";
		fuelCapacity = TRAVELRANGE * fuelEconomy;
		fuelAmount = fuelCapacity;
		cost = 10000;
	}
	
	
	/* 
	 * Methods that will be added to code later:
	 * public Point travel(Planet p){return null;}
	 * public int battle(Traveler player, Equipment[x] e){return null;}
	 * public void selfDestruct(){}
     */
}