package edu.gatech.quirkyqwerties.spacetraders.model;

import java.util.Random;

public enum TradeGood {
	WATER(0, 0, 1, 45, 5, 5, 50, "Water"),
	FURS(0, 0, 0, 250, 10, 20, 300, "Furs"),
	FOOD(1, 0, 1, 100, 3, 5, 120, "Food"),
	ORE(2, 2, 3, 350, 20, 15, 350, "Ore"),
	GAMES(3, 1, 6, 250, 10, 10, 200, "Games"),
	FIREARMS(3, 1, 5, 1255, 35, 10, 1400, "Firearms"),
	MEDICINE(4, 1, 6, 550, 25, 5, 600,"Medicine"),
	NARCOTICS(5, 0, 5, 3500, 125, 150, 2000, "Narcotics"),
	ROBOTS(6, 4, 7, 5000, 150, 100, 3500, "Robots");
	
	private int minTechLevelProd;
	private int minTechLevelUse;
	private int techLevelProd;
	private int basePrice;
	private int variance;
	private int incrPriLevel;
	private int minSpacePrice;
	private String name;
	private Random gen;
	private int cost;
	
	/**
	 * Instantiates a trade good.
	 * 
	 * @param minTechLPro the minimum tech level to produce good
	 * @param minTechLUse the minimum tech level for good to be used
	 * @param techLProd the tech level for production
	 * @param basePrice the good's base price
	 * @param incrPLevel price increase per tech level
	 * @param variancei the range that price may variancey in regards to base price
	 * @param minPriceSpace the minimum price good is sold for
	 * @param name the name of the good
	 */
	TradeGood(int minTechLPro, int minTechLUse, int techLProd,
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
	 * @return the price of the item
	 */
	public int cost(int currentTechLevel, boolean inSpace) {
		int res = -1;
		boolean ace = (gen.nextInt(variance) > (variance / 2)) ? true : false;
		int num = gen.nextInt(variance);
		
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
		
		return res;
	}
		
		/*int ace = -1;
		Random gen = new Random(); 
		int var = gen.nextInt(variance);
		double vari = (double) var / 100;
		if (inSpace && this.minTechLevelUse <= currentTechLevel) {
			if (headTail < 1)
				ace = (int) (minSpacePrice + incrPriLevel * (techLevelProd - minTechLevelProd) + (basePrice * vari));
			else
				ace = (int) (minSpacePrice + incrPriLevel * (techLevelProd - minTechLevelProd) - (basePrice * vari));
		} else if (!inSpace && this.minTechLevelUse <= currentTechLevel) {
			if (headTail < 2)
				ace = (int) ((basePrice + incrPriLevel * Math.abs((techLevelProd - minTechLevelProd))) + (basePrice * vari));
			else
				ace = (int) ((basePrice + incrPriLevel * Math.abs((techLevelProd - minTechLevelProd))) - (basePrice * vari));
		}
		
	}
	/**
	 * Gets the name of the TradeGood.
	 * 
	 * @return the name of trade good
	 */
	public String getName (){
		return name;
	}

	/**
	 * Gets the minimum tech level to use the good.
	 * 
	 * @return the tech level
	 */
	public int getMinTechLevelUse() {
		return minTechLevelUse;
	}
	
	/**
	 * Retrieves the base price of an item.
	 * 
	 * @return the base price
	 */
	public int getBasePrice(){
		return basePrice;
	}
	
	public String toString(){
		String goodStr = "Trade Good: ";
		goodStr += name + ": " + cost + " Mtlu: " + minTechLevelUse;
		return goodStr;
	}
}