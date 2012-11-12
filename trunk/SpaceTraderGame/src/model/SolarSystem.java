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
public class SolarSystem implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Location of a SolarSystem in the Universe. */
	private Point location;
	
	/** Name of the SolarSystem. */
	private String name;
	
	/** Planets in the SolarSystem. */
	private ArrayList<Planet> planets;
	
	/** The tech level of a SolarSystem can be from 0-7. */
	private int techLevel;
	
	/** The resource type of a SolarSystem can range from levels 0-13. */
	private int resources;
	
	/** Tax rate that applies to any transaction. */
	private double taxRate;
		
	/**
	 * Instantiates a SolarSystem with a specific name, position,
	 * tech level, tax rate and resources.
	 *  
	 * @param n the name of the SolarSystem
	 * @param p the (x, y) coordinate of the SolarSystem (in the Universe)
	 * @param tL the tech level of the SolarSystem which can be from 0-7
	 * @param tR the tax rate used in the SolarSystem
	 * @param rS the resources available in the SolarSystem
	 */
	public SolarSystem(String n, Point p, int tL, double tR, int rS ) {
		name = n;
		location = p;
		techLevel = tL;
		taxRate = tR;
		resources = rS;
		planets = new ArrayList<Planet>();
	}
	
	/**
	 * Instantiates a SolarSystem with a default name, coordinates, tax 
	 * rate, and number of planets.
	 * 
	 * @param planets the group of Planets in the SolarSystem 
	 */
	public SolarSystem() {
		this("Baton Rouge", new Point(0, 0), 5, 0.07, 7);
	}
	
	/**
	 * Adds a Planet to the Solar System. 
	 * 
	 * @param planet the Planet that will be added to the SolarSystem
	 * @return true if the Planet isn't a copy or repeat of another 
	 * Planet, false otherwise
	 */
	public boolean add(Planet planet) {
		if (!planets.contains(planet)) {
			planets.add(planet);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a Planet from the SolarSystem.
     *
	 * @param planet the Planet that will be removed from the SolarSystem
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
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	/**
	 * Retrieves the name of a SolarSystem.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the SolarSystem to have a specified group of 
	 * Planets.
	 */
	public void setPlanets(ArrayList<Planet> newPlanets) {
		planets = newPlanets;
	}
	
	/**
	 * Returns the location of the SolarSystem relative to  
	 * the Universe.
	 * 
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
}