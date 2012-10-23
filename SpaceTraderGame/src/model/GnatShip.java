package model;
/**
 * Represents the default Ship a Traveler starts off with.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 */
public class GnatShip extends Ship {
	
	/** Strength of the Ship's hull. */
	protected static int hullStrength; 
	
	public GnatShip(int hullStrength){
		super(hullStrength);
		name = "Gnat Ship";
	}
	
	/* 
	 * Methods that will be added to code later:
	 * public Point travel(Planet p){return null;}
	 * public int battle(Traveler player, Equipment[x] e){return null;}
	 * public void selfDestruct(){}
     */
}