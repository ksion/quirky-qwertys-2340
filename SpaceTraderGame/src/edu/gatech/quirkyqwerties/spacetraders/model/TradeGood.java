// $codepro.audit.disable numericLiterals
/**
 * TradeGood.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.util.Random;

/**
 * The Enum TradeGood.
 * @author Keanna
 * @version $Revision: 1.0 $
 */
public enum TradeGood {
	
	/** The water. */
	WATER(0, 0, 1, 45, 5, 5, 50, "Water"),
	
	/** The furs. */
	FURS(0, 0, 0, 250, 10, 20, 300, "Furs"),
	
	/** The food. */
	FOOD(1, 0, 1, 100, 3, 5, 120, "Food"),
	
	/** The ore. */
	ORE(2, 2, 3, 350, 20, 15, 350, "Ore"),
	
	/** The games. */
	GAMES(3, 1, 6, 250, 10, 10, 200, "Games"),
	
	/** The firearms. */
	FIREARMS(3, 1, 5, 1255, 35, 10, 1400, "Firearms"),
	
	/** The medicine. */
	MEDICINE(4, 1, 6, 550, 25, 5, 600,"Medicine"),
	
	/** The narcotics. */
	NARCOTICS(5, 0, 5, 3500, 125, 150, 2000, "Narcotics"),
	
	/** The robots. */
	ROBOTS(6, 4, 7, 5000, 150, 100, 3500, "Robots");
	
	/** The min tech level prod. */
	private final int minTechLevelProd;

	/** The min tech level use. */
	private final int minTechLevelUse;

	/** The tech level prod. */
	private final int techLevelProd;

	/** The base price. */
	private final int basePrice;

	/** The variance. */
	private final int variance;

	/** The increase price level. */
	private final int incrPriLevel;

	/** The min space price. */
	private final int minSpacePrice;

	/** The name of the good. */
	private final String name;

	/** The random number generator. */
	private final Random gen;

	/** The cost. */
	private int cost;
	
	/**
	 * Instantiates a trade good.
	 *
	 * @param minTechLPro the minimum tech level to produce good
	 * @param minTechLUse the minimum tech level for good to be used
	 * @param techLProd the tech level for production
	 * @param basePrice the good's base price
	 * @param incrPLevel price increase per tech level
	 * @param variance the variance
	 * @param minPriceSpace the minimum price good is sold for
	 * @param name the name of the good
	 */
	private TradeGood(int minTechLPro, int minTechLUse, int techLProd, // $codepro.audit.disable
			int basePrice, int incrPLevel, int variance, 
			int minPriceSpace, String name) {
		minTechLevelProd = minTechLPro;
		minTechLevelUse = minTechLUse;
		techLevelProd = techLProd;
		this.basePrice = basePrice;
		this.variance = variance;
		incrPriLevel = incrPLevel;
		minSpacePrice = minPriceSpace;
		gen = new Random();
		this.name = name;
	}
	
	/**
	 * Determines the cost of a trade good.
	 * 
	 * @param currentTechLevel the tech level of the planet
	 * @param inSpace whether or not 
	
	 * @return the price of the item */
	public int cost(int currentTechLevel, boolean inSpace) {
		int res = -1;
		final boolean ace = (gen.nextInt(variance) > (variance >> 1)) ? true : false;
		final int num = gen.nextInt(variance);
		
		if (this.minTechLevelUse <= currentTechLevel) {
			if (ace) {
				res = basePrice + (incrPriLevel * 
						Math.abs(techLevelProd - minTechLevelProd)) 
						- (basePrice * (num / 100));
			} else {
				res = minSpacePrice + (incrPriLevel * 
						Math.abs(techLevelProd - minTechLevelProd)) 
						+ (basePrice * (num / 100));
			}
		}
		
		if (res < 0) {
			res = basePrice + gen.nextInt(variance);
		}
		
		cost = res;
		return res;
	}

	/**
	 * Gets the name.
	 *
	
	 * @return the name */
	public String getName (){
		return name;
	}

	/**
	 * Gets the minimum tech level to use the good.
	 * 
	
	 * @return the tech level */
	public int getMinTechLevelUse() {
		return minTechLevelUse;
	}
	
	/**
	 * Retrieves the base price of an item.
	 * 
	
	 * @return the base price */
	public int getBasePrice(){
		return basePrice;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	/**
	 * Method toString.
	 * @return String
	 */
	public String toString(){
		String goodStr = "Trade Good: ";
		goodStr += name + ": " + cost + " Mtlu: " + minTechLevelUse;
		return goodStr;
	}
}