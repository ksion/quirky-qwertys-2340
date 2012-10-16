package model;
import java.awt.Point;
import java.util.List;


public interface SolarSystem {
	
	/**
	 * Adds a Planet to the Solar System's underlying
	 * List collection. Returns a boolean: true if add
	 * was successful, false if otherwise.
	 * @param Planet object
	 * @return boolean
	 */
	public boolean add(Planet object);
	
	/**
	 * Removes a Planet from the underlying List collection.
	 * Returns a boolean: true if add was successful, false
	 * if otherwise.
	 * @param Planet object
	 * @return boolean
	 */
	public boolean remove(Planet object);
	
	/**
	 * Returns the planets within the Solar System
	 * as an ordered collection. 
	 * @return List - of objects of type Planet
	 */
	public List<Planet> getSolarSystem();
	
	/**
	 * Returns the location of the Solar System 
	 * relative to the universe, determined at
	 * creation.
	 * @return Point
	 */
	public Point getLocation();
	
	/**
	 * Returns an int that represents the 
	 * level of technological development of the
	 * Solar System. This value influences production
	 * and price of goods sold within the S.System.
	 * @return int
	 */
	public int getTechLevel();
	
	/**
	 * Returns an int that represents the resource 
	 * the Solar System has in abundance. This value 
	 * influences the price and quality of trade goods.
	 * @return int
	 */
	public int getResources();
	
	/**
	 * Returns an int that represents the type of 
	 * government that holds within the S.System. This
	 * value influences the number of police, pirates,
	 * and traders there could possibly be afloat when
	 * traveling.
	 * @return int 
	 */
	 public int getPoliSystem();
	
	/**
	 * Returns a double that must be accounted for in all
	 * trade affairs. The number must be added to the base
	 * price.
	 * @return double
	 */
	public double getTaxRate();
	
	/**
	 * Sets the tax rate of the Solar System.
	 * @param double new tax rate
	 */
	public void setTaxRate(double taxRate);
	
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
	public int genEncounter();
}
