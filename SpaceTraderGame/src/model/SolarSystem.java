package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class SolarSystem {
	private Point location;
	private String name;
	private ArrayList<Planet> planets;
	private int count = 0;
	private int techLevel;
	private int resources;
	private double taxRate;
	
	public SolarSystem(String n, Point p, ArrayList<Planet> pnts, int tL, double tR, int rS ) {
		this.name = n;
		this.planets = pnts;
		this.location = p;
		this.techLevel = tL;
		this.taxRate = tR;
		this.resources = rS;
		count = planets.size();
	}
	
	public SolarSystem(String n, ArrayList<Planet> pnts) {
		this(n, new Point(0, 0), pnts, 5, 0.07, 7);
	}
	
	public SolarSystem() {
		name = "Random System";
		planets = new ArrayList<Planet>(3);
	}
	
	/**
	 * Adds a Planet to the Solar System's underlying
	 * array. Returns a boolean: true if add
	 * was successful, false if otherwise.
	 * @param Planet object
	 * @return boolean
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
	 * Removes a Planet from the underlying List collection.
	 * Returns a boolean: true if add was successful, false
	 * if otherwise.
	 * @param Planet object
	 * @return boolean
	 */
	public boolean remove(Planet object) {
		boolean ace = false;
		int idx = planets.indexOf(object);
		if (idx >= 0){
			planets.remove(idx);
		}
		return ace;
	}
	
	/**
	 * Returns the planets within the Solar System
	 * as an ArrayList
	 * @return ArrayList<Planet> - of type Planet
	 */
	public ArrayList<Planet> getSolarSystem() {
		return planets;
	}
	
	/**
	 * Returns the location of the Solar System 
	 * relative to the universe, determined at
	 * creation.
	 * @return Point
	 */
	public Point getLocation() {
		return location;
	}
	
	/**
	 * Returns an int that represents the 
	 * level of technological development of the
	 * Solar System. This value influences production
	 * and price of goods sold within the S.System.
	 * @return int
	 */
	public int getTechLevel() {
		return techLevel;
	}
	
	/**
	 * Returns an int that represents the resource 
	 * the Solar System has in abundance. This value 
	 * influences the price and quality of trade goods.
	 * @return int
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
	 * Returns a double that must be accounted for in all
	 * trade affairs. The number must be added to the base
	 * price.
	 * @return double
	 */
	public double getTaxRate() {
		return taxRate;
	}
	
	/**
	 * Sets the tax rate of the Solar System.
	 * @param double new tax rate
	 */
	public void setTaxRate(double taxRate) {
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
