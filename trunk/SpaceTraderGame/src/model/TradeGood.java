package model;

public class TradeGood {
	protected static int cost;
	private String name;
	private int bP;
	private int ttp;
	private int mtlp;
	private int mtlu;
	private int var;
	private int minSpace;
	private int maxSpace;
	
	public TradeGood(String name, int basePrice, 
			int techLevel, int minTechLPro, 
			int minTechLUse, int vari, 
			int minPriceSpace, int maxPriceSpace) {
		this.name = name;
		bP = basePrice;
		ttp = techLevel;
		mtlp = minTechLPro;
		mtlu = minTechLUse;
		var = vari;
		minSpace = minPriceSpace;
		maxSpace = maxPriceSpace;
		cost = PriceModel.computeCost(this, Trade.TradeState);
	}

	public String getName() {return name;}
	public int getBasePrice() {return bP;}
	public int getTechLevel() {return ttp;}
	public int getMinTechPro() {return mtlp;}
	public int getMinTechUse() {return mtlu;}
	public int getVariance() {return var;}
	public int getMinSpacePrice() {return minSpace;}
	public int getMaxSpacePrice() {return maxSpace;}
}
