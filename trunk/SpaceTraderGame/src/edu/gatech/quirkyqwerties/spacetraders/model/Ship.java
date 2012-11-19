/**
 * Ship.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Represents the vehicle used by the Player to travel
 * from one planet to another. The Ship may be used to
 * engage in battle with other NPC travelers. It also
 * serves as storage for all of the Player's Equipment 
 * and TradeGoods.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.07.12
 */
public class Ship{

	/** Represents the ship's cargo. */
    protected Inventory cargo;
    
    /** Image representing icon of the ship. */
    protected Image shipIcon;
    
    /** Dimensions of ship image icon. */
    private static final int WIDTH = 20, HEIGHT = 20;

	/** Amount of damage Ship has sustained. */
	protected int damageSustained = 0;
	
	/** Distance Ship has traveled. */
	protected int distanceTraveled = 0;
	
	/** Rate at which Ship uses up fuel as it travels. For every
	    four parsecs the Ship travels, the Ship's fuel amount 
	    goes down by one ton of fuel. */
	protected int fuelEconomy = DEFAULT_ECONOMY;
	
	/** Default fuel economy that the ship has. */
	private static final int DEFAULT_ECONOMY = 4;
	
	/** Amount of fuel Ship currently has. */
	protected int fuelAmount = DEFAULT_FUEL;
	
	/** Default fuel amount ship starts of with. */
	private static final int DEFAULT_FUEL = 56; 

	/** Strength of the Ship's hull. */
	protected int hullStrength;
	
	/** Current x-coordinate of the Ship. */
	private double currentX = 0;
	
	/** Current y-coordinate of the Ship. */
	protected double currentY = 0;
	
	/** Represents the name of the Ship. */
	protected String name = "Ship";

	/** Represents whether the ship is in space or a planet. */
	protected boolean flight = false;
	
	/** Maximum amount of fuel or fuel capacity a Ship can have. */	
	protected int fuelCapacity = DEFAULT_FUEL;
	
	/** Cost of the Ship. */
	protected Integer cost = DEFAULT_COST;
	
	/** Default cost of a ship. */
	private static final int DEFAULT_COST = 10000;
	
	/** Number used to determine damage. */
	private static final float PERCENT = 0.20f;
	
	/** Possible min percentage of hull strength used to determine damage. */
	private static final float MIN_PERCENT = 0.10f;
	
	/** Error message for when planet is out-of-range for ship. */
	private static final String OUT_OF_RANGE_MESSAGE = "This planet is out " +
	                                               "of range. Click on a " +
	                                               "closer planet or check " +
	                                               "to see if you have enough" +
	                                               " fuel to travel.";

	/** Error message when ship is out of fuel. */
	private static final String OUT_OF_FUEL_MESSAGE = "Your ship is out of fuel."; 
	
	/** Maximum number of parsecs ship can travel. */
	private static final int MAX_PARSECS = 20;
	
	/**
	 * Instantiates a Ship with specified hull strength, inventory 
	 * slots and a location.
	 * 
	 * @param hullStrength the ship's hull strength
	 * @param inventorySlots the number of cargo bays
	 * @param p the ship's location
	 * @throws IOException 
	 */
	public Ship(int hullStrength, int inventorySlots, Point p) throws IOException{
		this.hullStrength = hullStrength;
		this.currentX = p.getX();
		this.currentY = p.getY();
		cargo = new Inventory(inventorySlots);
		loadResources();
	}
	
	/**
	 * Empty method needed for flexjson serializer.
	 */
	protected Ship(){ // $codepro.audit.disable emptyMethod -->
	}
	
	/**
	 * Loads the ship icon.
	 * 
	 * @throws IOException
	 */
	public final void loadResources() throws IOException{
		shipIcon = ImageIO.read(getClass().getResource("/edu/gatech/" +
	                            "quirkyqwerties/spacetraders/view/" +
				                "shipIcon.png"));
	}
	
	/** 
	 * Instantiates a Ship with specified hull strength.
	 * 
	 * @param strength the ship's hull strength
	 * @throws IOException 
	 */
	public Ship(int strength) throws IOException{
		hullStrength = strength;
		loadResources();
	}
	
	/**
	 * This method will change the position of the ship to the 
	 * be at the planet passed in if it is within range of the ship.
	 * 
	 * @param p the planet to travel to.
	 * @return true if ship can travel, false otherwise
	 */
	public boolean canTravel(Planet p) {
		boolean canTravel = false;
		if(isInRange(p)){
			canTravel = true;
			flight = true;
			final double gameDistance = findDistance(p);
			fuelAmount -= gameDistance / fuelEconomy;
			
			setLocation(p.getPosition());
		}
		else{
			if (!isInRange(p) && fuelAmount != 0){
				JOptionPane.showMessageDialog(null, OUT_OF_RANGE_MESSAGE, "Woops!", 
						                      JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, OUT_OF_FUEL_MESSAGE, "Woops!", 
	                                          JOptionPane.ERROR_MESSAGE);
			}
		}
		System.out.println("You are in planet " + p.getName() + " at " + getLocation());
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
		final Point planetPos = p.getPosition();
		final double planetX = planetPos.getX(), 
		    	planetY = planetPos.getY();
		
		// Use basic distance formula
		final double pixelDistance = 
				Math.sqrt(Math.pow(currentX - planetX, 2) + Math.pow(currentY -  // $codepro.audit.disable numericLiterals
						  planetY, 2)); // $codepro.audit.disable numericLiterals
		return pixelDistance / MAX_PARSECS;
	}

   /**
	* Checks whether a Planet is within the Ship's range to
	* travel to it.
    * 
    * @param p the Planet to which the Ship may travel
    * @return true if the Planet is within the Ship's range, 
    * otherwise false
	*/	
	public boolean isInRange(Planet p) {
		final double distance = findDistance(p);
		final double fuelNeeded = Math.ceil(distance / fuelEconomy);
		
		if (fuelAmount >= fuelNeeded){
			fuelAmount -= fuelNeeded;
			return true;
		}
		return false;
	}

	/**
	 * Gets the name of the Ship. 
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
	 * 
	 * @return the int representing the fuel level
	 */
	public int getFuelAmount() {
		return fuelAmount;
	}

	/**
	 * Sets the fuel level.
	 * 
	 * @param fuelAmount the new fuel level
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
	 * Retrieves the hull strength of the ship.
	 * 
	 * @return ship's hull strength
	 */
	public int getHullStrength(){
		return hullStrength;
	}
	
	/**
	 * Sets the hull strength of the ship.
	 * 
	 * @param newStrength the hull strength
	 */
	public void setHullStrength(int newStrength){
		hullStrength = newStrength;
	}
	
	/**
	 * Retrieves the ship's location.
	 * 
	 * @return the ship's location
	 */
	public Point getLocation(){
		return new Point((int) currentX, (int) currentY); // $codepro.audit.disable lossOfPrecisionInCast
	}
	
	/**
	 * Sets the location of a ship to a new 
	 * point. 
	 * 
	 * @param p the Ship's new coordinates
	 */
	public void setLocation(Point p){
		currentX = p.x;
		currentY = p.y;
	}
	
	/**
	 * Updates the amount of damage a ship has sustained.
	 * 
	 * @param damageSustained the amount of damage
	 */
	public void setDamageSustained(int damageSustained) {
		this.damageSustained = damageSustained;
	}
	
	/**
	 * Gets the amount of damage sustained.
	 * 
	 * @return the damage sustained by the Ship
	 */
	public int getDamageSustained() {
		return damageSustained;
	}
	
	/**
	 * Attack a pirate's Ship based on 10-100% of the 
	 * hull strength.
	 * 
	 * @param pirateShip the ship that corresponds to a pirate
	 * @return amount of damage resulting from the attack
	 */
	public int attack(Ship pirateShip){
		final Random random = new Random();
		final int playerStrength = getHullStrength();
		final int damage = (int) (playerStrength * (random.nextFloat() *  // $codepro.audit.disable lossOfPrecisionInCast
				                  PERCENT + MIN_PERCENT)); 
		pirateShip.setDamageSustained(pirateShip.getDamageSustained() + damage);
		return damage;
	}

	/**
	 * Sets the inventory of a Player.
	 *
	 * @param i the inventory
	 */
	public void setCargo(Inventory i){
		cargo = i;
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
		"Fuel economy: " + fuelEconomy + "\n" + 
		"Fuel amount: " + fuelAmount + "\n" +
		"Hull Strength: " + hullStrength + "\n" +
		"Cargo Hold: ";
		return shipStr;
	}
	
	/**
	 * Draws a visual representation of the ship
	 *  
	 * @param g the graphics object
	 */
	public void drawShip(Graphics g){
		g.drawImage(shipIcon, getLocation().x, getLocation().y, WIDTH, HEIGHT, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
	}
}