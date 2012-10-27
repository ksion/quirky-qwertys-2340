package model;

import java.util.Random;

public enum TradeGood {
	WATER(0, 0, 1, 45, 5, 5, 50),
	FURS(0, 0, 0, 250, 10, 20, 300),
	FOOD(1, 0, 1, 100, 3, 5, 120),
	ORE(2, 2, 3, 350, 20, 15, 350),
	GAMES(3, 1, 6, 250, -10, 10, 200),
	FIREARMS(3, 1, 5, 1255, 35, 10, 1400),
	MEDICINE(4, 1, 6, 550, -25, 5, 600),
	NARCHOTICS(5, 0, 5, 3500, -125, 150, 2000),
	ROBOTS(6, 4, 7, 5000, -150, 100, 3500);
	
	private int mtlp;
	private int mtlu;
	private int ttp;
	private int bP;
	private int var;
	private int ipl;
	private int minSpace;
	private Random gen = new Random();
	
	TradeGood(int minTechLPro, int minTechLUse, int techLProd,
			int basePrice, int incrPLevel, int vari, 
			int minPriceSpace) {
		mtlp = minTechLPro;
		mtlu = minTechLUse;
		ttp = techLProd;
		bP = basePrice;
		var = vari;
		ipl = incrPLevel;
		minSpace = minPriceSpace;
	}
	
	int cost(int currentTechLevel, boolean inSpace) {
		int ace = -1;
		int variance = gen.nextInt(var);
		if (inSpace && this.mtlu < currentTechLevel) {			
			ace = minSpace + ipl * (ttp - mtlp) + variance;
		} else if (!inSpace && this.mtlu < currentTechLevel) {
			ace = bP + ipl * (ttp - mtlp) + variance;
		}
		return ace;
	}
}

