/**
 * Ship.java
 * @version 1.0
 * copyright 2012
 */

package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
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
public class Ship {


    protected TradeGood[] cargoHold = new TradeGood[20];
	
    protected Inventory cargo;
    
    private transient Image shipIcon;

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
	protected int fuelCapacity = 64;
	
	/** Cost of the Ship. */
	protected Integer cost = 10000;
	
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
	
	public void loadResources() throws IOException {
		shipIcon = ImageIO.read(getClass().getResource("/edu/gatech/quirkyqwerties/spacetraders/view/shipIcon.png"));
	}
	
	/** 
	 * Instantiates a Ship with specified hull strength.
	 * 
	 * @param hullStrength the ship's hull strength
	 * @throws IOException 
	 */
	public Ship(int hullStrength) throws IOException{
		this.hullStrength = hullStrength;
		loadResources();
	}
	
	/**
	 * Needed by serialization
	 */
	protected Ship() {
	}
	
	/**
	 * This method will change the position of the ship to the 
	 * be at the planet passed in if it is within range of the ship.
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
			
			setLocation(p.getPosition());	
		}
		else{
			if (!inRange(p) && fuelAmount != 0){
				JOptionPane.showMessageDialog(null, "This planet is too far away to travel to.", 
						                      "Woops!", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Your ship is out of fuel. You can't travel anywhere.", 
	                                          "Woops!", JOptionPane.ERROR_MESSAGE);				
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
		Point planetPos = p.getPosition();
		double 	planetX = planetPos.getX(), 
		    	planetY = planetPos.getY();
		double pixelDistance = 
				Math.sqrt( Math.pow(currentX - planetX, 2) + Math.pow(currentY - planetY, 2) );
		double gameDistance = pixelDistance/20;
		return gameDistance;
	}

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
		return new Point((int)currentX, (int)currentY);
	}
	
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
	 * Attack a pirate's Ship.
	 * 
	 * @param pirateShip the ship that corresponds to a pirate
	 */
	public void attack(Ship pirateShip){
		Random random = new Random();
		int playerStrength = getHullStrength();
		int damage = (int) (playerStrength * (random.nextFloat() * 0.31 + 0.10)); 
		pirateShip.setDamageSustained(pirateShip.getDamageSustained() + damage);
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
		"Fuel economy: " + fuelEconomy + "\n"+ 
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
		
		g.drawImage(shipIcon, getLocation().x, getLocation().y, 20, 20, null);
	}
}