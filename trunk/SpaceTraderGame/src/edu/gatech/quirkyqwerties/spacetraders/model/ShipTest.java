package edu.gatech.quirkyqwerties.spacetraders.model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class ShipTest {
	
	SolarSystem solarSystem; 
	Planet planet1, planet2;
	Ship ship;
	
	@Before
	public void todo() throws IOException{
		solarSystem = new SolarSystem("The best", new Point(100, 100), 0, .45, 0);
		planet1 = new Planet(new Point(50, 50), solarSystem, "Hayden", Planet.largePlanetPics[0]);
		planet2 = new Planet(new Point(5000, 5000), solarSystem, "Lyann", Planet.largePlanetPics[1]);
		solarSystem.canAdd(planet1);
		solarSystem.canAdd(planet2);
		ship = new Ship(5, 5, new Point(50, 50));
	}

	@Test
	public void testCanTravel() {
		assertTrue("can't travel", ship.canTravel(planet1));
		assertFalse("can't travel", ship.canTravel(planet2));
	}

	@Test
	public void testIsInRange(){
		assertTrue("not in range", ship.isInRange(planet1));
		assertFalse("not in range", ship.isInRange(planet2));
	}
	
	

}
