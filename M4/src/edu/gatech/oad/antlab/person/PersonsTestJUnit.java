package edu.gatech.oad.antlab.person;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * These JUnits are just here for testing M4.
 * You don't have to use them, I just like to
 * practice using them.
 * @author Keanna Sion
 * @version 1.0
 */
public class PersonsTestJUnit {

	@Test
	public void person1Test() {
		Person1 thing = new Person1("Sara");
		String ace = thing.toString("gtg123b");
		assertTrue("Person1 fails to rotate", 
				ace.equals("Sarag123bgt"));
	}
	
	@Test
	public void person2Test() {
		Person2 name = new Person2("Annette");
		String text = name.toString("gtg123b");
		assertFalse("Person2 fails to randomize ",
				text.equals("Annettegtg123b"));
		assertEquals("Length of input changes " , 
				14, text.length());
	}
	
	@Test
	public void person3Test() {
		Person3 thing = new Person3("Keanna");
		String ace = thing.toString("gtg123b");
		assertTrue("Person3 fails to reverse",
				ace.equals("Keannab321gtg"));
	}
	
	@Test
	public void person4Test() {
		fail("Not yet implemented");
	}

	@Test
	public void person5Test() {
		fail("Not yet implemented");
	}
}
