package edu.gatech.quirkyqwerties.spacetraders.model;

import java.util.Random;

public enum TradeGood implements java.io.Serializable{
	WATER(0, 0, 1, 45, 5, 5, 50, "Water"),
	FURS(0, 0, 0, 250, 10, 20, 300, "Furs"),
	FOOD(1, 0, 1, 100, 3, 5, 120, "Food"),
	ORE(2, 2, 3, 350, 20, 15, 350, "Ore"),
	GAMES(3, 1, 6, 250, 10, 10, 200, "Games"),
	FIREARMS(3, 1, 5, 1255, 35, 10, 1400, "Firearms"),
	MEDICINE(4, 1, 6, 550, 25, 5, 600,"Medicine"),
	NARCOTICS(5, 0, 5, 3500, 125, 150, 2000, "Narcotics"),
	ROBOTS(6, 4, 7, 5000, 150, 100, 3500, "Robots");
	private static final long serialVersionUID = 1L;
	
	private int mtlp;
	private int mtlu;
	private int ttp;
	private int bP;
	private int var;
	private int ipl;
	private int minSpace;
	private String name;
	private int cost;
	
	/**
	 * Instantiates a trade good.
	 * 
	 * @param minTechLPro the minimum tech level to produce good
	 * @param minTechLUse the minimum tech level for good to be used
	 * @param techLProd the tech level for production
	 * @param basePrice the good's base price
	 * @param incrPLevel price increase per tech level
	 * @param vari the range that price may vary in regards to base price
	 * @param minPriceSpace the minimum price good is sold for
	 * @param name the name of the good
	 */
	TradeGood(int minTechLPro, int minTechLUse, int techLProd,
			int basePrice, int incrPLevel, int vari, 
			int minPriceSpace, String name) {
		mtlp = minTechLPro;
		mtlu = minTechLUse;
		ttp = techLProd;
		bP = basePrice;
		var = vari;
		ipl = incrPLevel;
		minSpace = minPriceSpace;
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
		int ace = -1;
		Random gen = new Random(); 
		int headTail = gen.nextInt(3);
		int varia = gen.nextInt(var);
		double variance = (double) varia / 100;
		if (inSpace && this.mtlu <= currentTechLevel) {
			if (headTail < 1)
				ace = (int) (minSpace + ipl * (ttp - mtlp) + (bP * variance));
			else
				ace = (int) (minSpace + ipl * (ttp - mtlp) - (bP * variance));
		} else if (!inSpace && this.mtlu <= currentTechLevel) {
			if (headTail < 2)
				ace = (int) ((bP + ipl * Math.abs((ttp - mtlp))) + (bP * variance));
			else
				ace = (int) ((bP + ipl * Math.abs((ttp - mtlp))) - (bP * variance));
		}
		cost = ace;
		return ace;
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
		// TODO Auto-generated method stub
		return mtlu;
	}
	
	/**
	 * Retrieves the base price of an item.
	 * 
	 * @return the base price
	 */
	public int getBasePrice(){
		return bP;
	}
	
	public String toString(){
		String goodStr = "Trade Good: ";
		goodStr += name + ": " + cost + " Mtlu: " + mtlu;
		return goodStr;
	}
}