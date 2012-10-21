package model;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents a solar system in the Space Trader game. The solar 
 * system consists of a group of planets and has a particular 
 * tech level, specific resources, and a tax rate which applies 
 * to all of the planets that are within the system.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 *
 */
public class SolarSystem {
	private Point location;
	private String name;
	private ArrayList<Planet> planets;
	private int techLevel;
	private int resources;
	private static double taxRate;
	private static int count = 0;
	
	/**
	 * Instantiates a SolarSystem with a specific name, position,
	 * tech level, tax rate and resources.
	 *  
	 * @param n the name of the SolarSystem
	 * @param p the (x, y) coordinate of the SolarSystem (in the Universe)
	 * @param tL the tech level of the SolarSystem
	 * @param tR the tax rate used in the SolarSystem
	 * @param rS the resources available in the SolarSystem
	 */
	public SolarSystem(String n, Point p, int tL, double tR, int rS ) {
		name = n;
		location = p;
		techLevel = tL;
		taxRate = tR;
		resources = rS;
		count = planets.size();
	}
	
	/**
	 * Instantiates a SolarSystem with a default name, coordinates, tax 
	 * rate, and number of planets.
	 * 
	 * @param planets the group of Planets in the SolarSystem 
	 */
	public SolarSystem(ArrayList<Planet> planets) {
		this("Baton Rouge", new Point(0, 0), 5, 0.07, 7);
	}
	
	/**
	 * Adds a Planet to the Solar System. 
	 * 
	 * @param Planet object
	 * @return true Planet isn't a copy or repeat of another Planet, 
	 * false otherwise
	 */
	public boolean add(Planet object) {
		boolean ace = false;
		if (!planets.contains(object)) {
			planets.add(object);
			ace = true;
			count++;
		}
		return ace;
	}
	
	/**
	 * Removes a Planet from the SolarSystem.
     *
	 * @param planet the Planet that will be removed from
	 * the SolarSystem
	 * @return true if the Planet exists and can be removed, false
	 * otherwise
	 */
	public boolean remove(Planet planet) {
		boolean ace = false;
		int idx = planets.indexOf(planet);
		if (idx >= 0 && planets.contains(planet)){
			planets.remove(idx);
			ace = true;
		}
		return ace;
	}
	
	/**
	 * Gets the all of the Planets within the SolarSystem.
	 * 
	 * @return the list with all of the Planets in the SolarSystem
	 */
	public ArrayList<Planet> getSolarSystem() {
		return planets;
	}
	
	/**
	 * Sets the SolarSystem to have a specified group of 
	 * Planets.
	 */
	public void setSolarSystem(ArrayList<Planet> newPlanets) {
		planets = newPlanets;
	}
	
	/**
	 * Returns the location of the SolarSystem relative to  
	 * the Universe.
	 * @return the location (or (x, y) coordinate) of the SolarSystem
	 */
	public Point getLocation() {
		return location;
	}
	
	/**
	 * Gets the technological development level of the
	 * SolarSystem. This value influences production
	 * and price of goods sold within the SolarSystem.
	 * 
	 * @return the tech level of the SolarSystem
	 */
	public int getTechLevel() {
		return techLevel;
	}
	
	/**
	 * Gets an int that represents the resource the 
	 * SolarSystem has in abundance. This value influences
	 * the price and quality of trade goods.
	 * 
	 * @return the abundant resource in the SolarSystem 
	 */
	public int getResources() {
		return resources;
	}
	
	/**
	 * Returns an int that represents the type of 
	 * government that holds within the S.System. This
	 * value influences the number of police, pirates,
	 * and traders there could possibly be afloat when
	 * traveling.
	 * @return int 
	 */
	 public int getPoliSystem() {
		return 0;
	}
	
	/**
	 * Retrieves the tax rate that must be accounted for in all
	 * trade affairs. The number must be added to the base
	 * price.
	 * 
	 * @return the current tax rate in the SolarSystem
	 */
	public double getTaxRate() {
		return taxRate;
	}
	
	/**
	 * Sets the tax rate of the SolarSystem.
	 * 
	 * @param taxRate the new tax rate of the SolarSystem
	 */
	public void setTaxRate(double newTaxRate) {
	    taxRate = newTaxRate;
	}
	
	/**
	 * Returns an int that represents the type of encounter
	 * that occurs when the player is traveling. Encounters
	 * are not strictly subject to police, pirate, and trader
	 * encounters. Encounters may also be good luck or bad
	 * luck occasions, such as finding trade goods floating 
	 * around or hitting a meteor which causes damage to the
	 * ship.
	 * @return int
	 */
	public int genEncounter() {
		return 0;
	}
}