// $codepro.audit.disable numericLiterals, disallowTestAnnotation
/**
 * InventoryTest.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*; // $codepro.audit.disable importStyle

/**
 * class to test inventory methods
 * @author Sara Harvey
 * @version 1.0
 */
public class InventoryTest { // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
	
	/** test inventory */
	private Inventory testInventory;
	
	/**
	 * setup the preconditions for the tests
	 */
	@Before
	public void setUp(){ // $codepro.audit.disable accessorMethodNamingConvention
		testInventory = new Inventory(5);
		
	}
	
	/**
	 * test adding a new trade good via addTradeGood method from inventory.java
	 */
	@Test
	public void testAddNewTradeGood(){
		assertEquals("assertion getUsedSpace() failed", 0, testInventory.getUsedSpace());
		final boolean isAdded = testInventory.addTradeGo(
				new TradableItem(TradeGood.FIREARMS, 2, 5));
		assertTrue("assertion canAddTradeGood failed", isAdded);
		assertEquals("assertion getusedspace failed", 2, testInventory.getUsedSpace());
	}
	
	/**
	 * test adding a second existing tradegood via canAddTradeGood method
	 */
	@Test
	public void testAddExistingTradeGood(){
		assertTrue("assertion true failed", testInventory.addTradeGo(
				new TradableItem(TradeGood.FIREARMS, 2, 5)) );
		assertEquals("assertion canAddTradeGood failed", 2, testInventory.getUsedSpace());
		assertTrue("assertion true failed", testInventory.addTradeGo(
				new TradableItem(TradeGood.FIREARMS, 3, 5)) );
		assertEquals("assertion getusedspace failed", 5, testInventory.getUsedSpace());
	}
	
	/**
	 * test to make sure that can't add a trade good if inventory is full
	 */
	@Test
	public void testAddTradeGoodLimits(){
		boolean isAdded = testInventory.addTradeGo(
				new TradableItem(TradeGood.FIREARMS, 3, 5));
		assertEquals("assertion canAddTradeGood failed", 3, testInventory.getUsedSpace());
		isAdded = testInventory.addTradeGo(
				new TradableItem(TradeGood.FIREARMS, 3, 5));
		assertFalse("assertion canAddTradeGood failed", isAdded);
		assertEquals("assertion getusedspace failed", 3, testInventory.getUsedSpace());
		
	}
	
	

}
