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
	protected static String name = "Ship";

	/** Represents whether the ship is in space or a planet */
	protected  boolean flight = false;
	
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
		 * @param p the planet to travel to.
		 */
	
		public void travel(Planet p) {
			
			if(inRange(p)){
				flight = true;
				Point planetPos = p.getPosition();
				double 	planetX = planetPos.getX(), 
						planetY = planetPos.getY();
				double pixelDistance = 
						Math.pow(
							Math.pow(currentX - planetX, 2) + 
							Math.pow(currentY - planetY, 2)
							,.5);
				double gameDistance = pixelDistance/20;
				fuelAmount -= gameDistance/fuelEconomy;
				
				currentX = planetX;
				currentY = planetY;
				
			}
			else{
				JOptionPane.showMessageDialog(null, "This planet is too far away to travel to.", "Woops!", JOptionPane.ERROR_MESSAGE);
			}
		}
			
			
			/*
			 - during this time where flight is true, an encounter
			 - needs to be generated...
			 - somewhere flight has to be turned back to false
			*/
	
	private boolean inRange(Planet p) {
			// TODO Auto-generated method stub
			// This one is yours Annette :)
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
	
	public Inventory getCargo() {
		return cargo;
	}
	
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