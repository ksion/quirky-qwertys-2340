package model;

import java.awt.Point;

import javax.swing.JOptionPane;

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
    protected TradeGood[] cargoHold = new TradeGood[20];
	Inventory cargo;
	
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

	/** Strength of the Ship's hull. */
	protected int hullStrength;
	
	/** Current x-coordinate of the Ship. */
	protected double currentX = 0;
	
	/** Current y-coordinate of the Ship. */
	protected double currentY = 0;
	
	/** Represents the name of the Ship. */
	protected String name = "Ship";

	/** Represents whether the ship is in space or a planet. */
	protected boolean flight = false;
	
	/** Represents the maximum distance the Ship can travel in one trip. */
	protected final double MAX_DISTANCE = 15; 
	
	/** Maximum amount of fuel or fuel capacity a Ship can have. */	
	protected int fuelCapacity = 64;
	
	/** Cost of the Ship. */
	protected Integer cost = 10000;
	
	/**
	 * Instantiates a Ship with specified hull strength.
	 */
	public Ship(int hullStrength, int inventorySlots, Point p){
		this.hullStrength = hullStrength;
		this.currentX = p.getX();
		this.currentY = p.getY();
		cargo = new Inventory(inventorySlots);
	}
	
	
	/**
	 * this method will change the position of the ship to the be at the planet
	 * passed in if it is within range of the ship.
	 * 
	 * @param p the planet to travel to.
	 */
	public boolean travel(Planet p) {
		boolean canTravel = false;
		if(inRange(p)){
			canTravel = true;
			flight = true;
			double gameDistance = findDistance(p);
			fuelAmount -= gameDistance/fuelEconomy;
			
			currentX = p.getPosition().getX();
			currentY = p.getPosition().getY();		
		}
		else{
			JOptionPane.showMessageDialog(null, "This planet is too far away to travel to.", "Woops!", JOptionPane.ERROR_MESSAGE);
		}
		return canTravel;
	}
	
	/**
	 * Finds the distance between the Ship and a given 
	 * Planet.
	 * 	
	 * @param p the Planet the Ship may be traveling to
	 * @return the distance between the Ship and the Planet
	 */
	public double findDistance(Planet p){
		Point planetPos = p.getPosition();
		double 	planetX = planetPos.getX(), 
		    	planetY = planetPos.getY();
		double pixelDistance = 
				Math.pow(Math.pow(currentX - planetX, 2) + 	Math.pow(currentY - planetY, 2),.5);
		double gameDistance = pixelDistance/20;
	return gameDistance;
	}
			/*
			 - during this time where flight is true, an encounter
			 - needs to be generated...
			 - somewhere flight has to be turned back to false
			*/
   /**
	* Checks whether a Planet is within the Ship's range to
	* travel to it.
    * 
    * @param p the Planet to which the Ship may travel
    * @return true if the Planet is within the Ship's range, 
    * otherwise false
	*/	
	private boolean inRange(Planet p) {
		double distance = findDistance(p);
		double fuelNeeded = Math.ceil(distance / fuelEconomy);
		
		if (distance <= MAX_DISTANCE && fuelAmount >= fuelNeeded){
			fuelAmount -= fuelNeeded;
			return true;
		}
		return false;
	}

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
	
	/**
	 * Retrieves the current cargo in the Ship.
	 * 
	 * @return the Ship's inventory
	 */
	public Inventory getCargo() {
		return cargo;
	}
	
	
	/**
	 * Gets the remaining fuel level. 
	 * @return the int representing the fuel level
	 */
	public int getFuelAmount() {
		return fuelAmount;
	}

	/**
	 * sets the fuel level
	 * @param fuelAmount 
	 */
	public void setFuelAmount(int fuelAmount) {
		this.fuelAmount = fuelAmount;
	}

	/**
	 * Gets the fuel capacity.
	 * 
	 * @return the ship's fuel capacity
	 */
	public int getFuelCapacity(){
		return fuelCapacity;
	}
	
	/**
	 * Gets the maximum amount of fuel that ship needs
	 * to have a full tank.
	 * 
	 * @return the maximum amount of fuel needed 
	 */
	public int getMaxFuel(){
		return getFuelCapacity() - getFuelAmount();
	}
	
	/**
	 * Retrieves the ship's original cost.
	 * 
	 * @return the ship's cost
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * Retrieves the ship's location.
	 * 
	 * @return the ship's location
	 */
	public Point getLocation(){
		return new Point((int)currentX, (int)currentY);
	}

	/**
	 * Has information about the Ship's main characteristics.
	 * 
	 * @return a String containing the Ship's current state
	 */
	public String toString(){
		String shipStr = "Ship: ";
		shipStr += name + ": \n" + 
		"Damage Sustained: " + damageSustained + "\n" + 
		"Distance Traveled: " + distanceTraveled + "\n" + 
		"Fuel economy: " + fuelEconomy + "\n"+ 
		"Fuel amount: " + fuelAmount + "\n" +
		"Escape Pod: " + escapePod + "\n" +
		"Insurance: " + insurance + "\n" +
		"Hull Strength: " + hullStrength + "\n" +
		"Cargo Hold: ";
		
		//for (TradeGood good: cargoHold){
		//	shipStr += good.toString() + "\n"; 
		//}
		
		return shipStr;
	}
}