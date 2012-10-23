package model;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Represents the vehicle used by the Player to travel
 * from one planet to another. The Ship may be used to
 * engage in battle with other NPC travelers. It also
 * serves as storage for all of the Player's Equipment 
 * and TradeGoods.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 */
public class Ship {
    // /** Array used to store all of the Player's TradeGoods.*/
    // TradeGood[] cargoHold = new TradeGood[20]
	
	// /** Array used to store all of the Ship's Equipment. */
	// Equipment[] myEquipment = new Equipment[10];
	
	/** Amount of damage Ship has sustained. */
	protected int damageSustained = 0;
	
	/** Distance Ship has traveled. */
	protected int distanceTraveled = 0;
	
	/** Rate at which Ship uses up fuel as it travels. For every
	    four parsecs the Ship travels, the Ship's fuel amount 
	    goes down by one ton of fuel. */
	protected int fuelEconomy = 4;
	
	/** Amount of fuel Ship currently has. */
	protected int fuelAmount = 16;
	
	/** Marker used to keep track of whether or not Ship has an 
	    escape pod. */
	protected boolean escapePod = false;
	
	/** Marker used to keep track of whether or not Ship is insured. */
	protected boolean insurance = false;
	
	/** Number of cargo holds available for the equipment. */
	protected static final int CARGOBAY = 15;
	
	/** Number of slots available to store weapons. */
	protected static final int WEAPONSLOTS = 1;
	
	/** Number of slots available to store gadgets. */
	protected static final int GADGETSLOTS = 1;
	
	/** Number of slots available to store gadgets. */
	protected static final int SHIELDSLOTS = 0;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	protected static final int TRAVELRANGE = 14;
	
	/** Number of rooms available for crew. */
	protected static final int CREWQUARTERS = 0;	
	
	/** Strength of the Ship's hull. */
	protected int hullStrength;
	
	/** Current x-coordinate of the Ship. */
	protected int currentX = 0;
	
	/** Current y-coordinate of the Ship. */
	protected int currentY = 0;
	
	/** Represents the name of the Ship. */
	protected static String name = "Ship";

	/** Represents whether the ship is in space or a planet */
	protected static boolean flight = false;
	
	/**
	 * Instantiates a Ship with specified hull strength.
	 */
	// Later: include Point or X and Y coordinates in constructor
	public Ship(int hullStrength){
		this.hullStrength = hullStrength;
	}
	
		/*I think the ship should contain the travel(Planet p) method
		public Point travel(Planet p) {
			flight = true;
			Point2D current = new Point(currentX, currentY);
			int dist = (int) Math.sqrt((p.getPosition().distance(current)));
			if (dist < fuelAmount*fuelEconomy) {
				currentX = p.getPosition().x;
				currentY = p.getPosition().y;
				fuelAmount -= (dist / fuelEconomy);
			}
			return new Point(currentX, currentY);
			//-------------
			 - during this time where flight is true, an encounter
			 - needs to be generated...
			 - somewhere flight has to be turned back to false
			//-------------
		}*/
	
	/**
	 * Gets the name of the Ship. 
	 * This class may be deleted later--it's purpose is
	 * to make sure that Player instantiates the Ship
	 * correctly.
	 * 
	 * @return the name/type of the Ship
	 */
	public String getName(){
		return name;
	}
}