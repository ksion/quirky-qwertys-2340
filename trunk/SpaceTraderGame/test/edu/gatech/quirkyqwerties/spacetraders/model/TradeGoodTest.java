package edu.gatech.quirkyqwerties.spacetraders.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TradeGoodTest {
	
	/** Inventory for Test **/
	private Inventory inven1;
	
	/** Max Capacity of an inventory **/
	private final int maxQty = 10;
	
	/** Current techlevel **/
	private int techLevel;
	
	/** Random generator **/
	private Random gen;
	
	@Before
	public void setUp() {
		gen = new Random();
		
		inven1 = new Inventory(maxQty);
		
		techLevel = 5;
		
		TradeGood[] goods = TradeGood.values();
		
		List<TradableItem> items1 = new ArrayList<TradableItem>();
		
		for (int i = 0; i < maxQty - 1; i++) {
			int num = gen.nextInt(goods.length);
			TradableItem thing = new TradableItem(goods[num], 
					1, goods[num].cost(techLevel, false));
			items1.add(thing);
		}
		
		inven1.setGoods(items1);
	}

	/** Test to see if the TradeGood price varies each visit
	 * to the planet **/
	@Test
	public void priceTest1() {
		List<TradableItem> things = inven1.getGoods(); 
		TradeGood thing = things.get(0).getType();
		assertFalse("Price does not change from visit to visit", 
				thing.cost(techLevel, false) == thing.cost(techLevel, false));
		
	}
	
	/** Test to see if the TradeGood price varies by techLevel **/
	@Test
	public void priceTest2() {
		List<TradableItem> things = inven1.getGoods(); 
		TradeGood thing = things.get(0).getType();
		assertFalse("Price does not change by techLevel", 
				thing.cost(techLevel, false) == thing.cost(techLevel + 3, false));
	}

}
