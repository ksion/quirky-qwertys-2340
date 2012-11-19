/**
 * AttackTest.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Pirate class attack method.
 *  
 * @author Annette Almonte Malagon
 * @version 1.0 11.18.12
 */
public class AttackTest{
	
	/** Instance of a pirate from the game. */
	private Pirate pirate1;
	
	/** Instance of another pirate from the game. */
	private Pirate pirate2;
	
	/** Reference to the first pirate's ship. */
	private Ship ship1;
	
	/** Reference to the second pirate's ship. */
	private Ship ship2;
	
	/**
	 * Sets up a Pirate instance used for testing.
	 */
	@Before
	public void setUp() throws Exception{
		pirate1 = new Pirate();
		ship1 = pirate1.getShip();
		
		pirate2 = new Pirate();
		ship2 = pirate2.getShip();
	}
	
	/**
	 * Checks whether the pirate's ship has been 
	 * correctly instantiated with a valid hull
	 * strength.
	 */
	@Test
	public void testPirateHullStrength(){	
		assertTrue("Invalid hull strength", ship1.getHullStrength() >= 70);
		assertTrue("Invalid hull strength", ship1.getHullStrength() < 130);
		assertTrue("Invalid hull strength", ship2.getHullStrength() >= 70);
		assertTrue("Invalid hull strength", ship2.getHullStrength() < 130);
	}
	
	/**
	 * Checks if attacks yield more than 0 damage
	 * points. 
	 */
	@Test
	public void testAttack(){
		int damage1 = pirate1.attack(ship2);
		int damage2 = pirate2.attack(ship1);
		assertTrue("Attack did no damage", damage1 > 0);
		assertTrue("Attack did no damage", damage2 > 0);
	}
	
	/**
	 * Checks if ship sustains damage after an attack.
	 */
	@Test
	public void testShipDamage(){
		pirate1.attack(ship2);
		pirate2.attack(ship1);
		assertTrue("No damage was sustained", ship1.getDamageSustained() > 0);
		assertTrue("No damage was sustained", ship2.getDamageSustained() > 0);
	}
	
	/**
	 * Checks whether the pirate's ship is destroyed
	 * after a series of attacks.
	 */
	@Test
	public void testDestructiveAttacks(){
		ship1.getHullStrength();
		ship2.getHullStrength();
		boolean turn = true;
		while (!pirate1.isDestroyed() && !pirate2.isDestroyed()){
			if (turn == true){
				pirate1.attack(ship2);
				turn = false;
			}
			else{
				pirate2.attack(ship1);
				turn = true;
			}
		}
		if (pirate1.isDestroyed()){
			assertTrue("Ship was not destroyed", ship1.getDamageSustained() >= 
					ship1.getHullStrength());
		}
		else{
			assertTrue("Ship was not destroyed", ship2.getDamageSustained() >= 
					ship2.getHullStrength());
		}
	}
}