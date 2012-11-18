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
	 * Sara Harvey
	 */
	@Before
	public void setUp(){ // $codepro.audit.disable accessorMethodNamingConvention
		testInventory = new Inventory(5);
		
	}
	
	/**
	 * test adding a new trade good via purchase method from inventory.java
	 * Sara Harvey
	 */
	@Test
	public void testPurchase(){
		assertEquals("assertion getUsedSpace() failed", 0, testInventory.getUsedSpace());
		final TradableItem item = new TradableItem(TradeGood.FIREARMS, 2, 5); 
		final boolean isAdded = testInventory.purchase(item, 2);
		assertTrue("assertion purchase failed", isAdded);
		assertEquals("assertion getusedspace failed", 2, testInventory.getUsedSpace());
		assertEquals("assertion", 0, item.getQty());
	}
	
	/**
	 * test adding a second existing tradegood via purchase method
	 * Sara Harvey
	 */
	@Test
	public void testPurchaseExistingTradeGood(){
		assertTrue("assertion true failed", testInventory.purchase(
				new TradableItem(TradeGood.FIREARMS, 2, 5), 2) );
		assertEquals("assertion purchase failed", 2, testInventory.getUsedSpace());
		assertTrue("assertion true failed", testInventory.purchase(
				new TradableItem(TradeGood.FIREARMS, 3, 5), 3) );
		assertEquals("assertion getusedspace failed", 5, testInventory.getUsedSpace());
	}
	
	/**
	 * test to make sure that can't add a trade good if inventory is full
	 * Sara Harvey
	 */
	@Test
	public void testPurchaseTradeGoodLimits(){
		boolean isAdded = testInventory.purchase(
				new TradableItem(TradeGood.FIREARMS, 3, 5), 3);
		assertEquals("assertion purchase failed", 3, testInventory.getUsedSpace());
		isAdded = testInventory.purchase(
				new TradableItem(TradeGood.FIREARMS, 3, 5), 3);
		assertFalse("assertion purchase failed", isAdded);
		assertEquals("assertion getusedspace failed", 3, testInventory.getUsedSpace());
		
	}
	
	

}
