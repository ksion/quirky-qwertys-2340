package model;

import java.awt.Point;

/**
 * Represents the default Ship a Traveler starts off with.
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 */

public class GnatShip extends Ship {
	
	/** Strength of the Ship's hull. */
	private static int hullStrength; 
	
	/** Current x-coordinate of the Ship. */
	private double currentX = 0;
	
	/** Current y-coordinate of the Ship. */
	private double currentY = 0;
	
	/**
	 * Instantiates a Gnat Ship.
	 * 
	 * @param hullStrength the strenght of the Ship's hull
	 * @param point the location of the Ship
	 */
	public GnatShip(int hullStrength, Point point){
		super(hullStrength, point);
		name = "Gnat Ship";
	}
	
	/* 
	 * Methods that will be added to code later:
	 * public Point travel(Planet p){return null;}
	 * public int battle(Traveler player, Equipment[x] e){return null;}
	 * public void selfDestruct(){}
     */
}
