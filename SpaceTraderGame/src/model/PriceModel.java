package model;
public class PriceModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Planet planet;
	
	public PriceModel(Planet planet) {
		this.planet = planet;
	}

	
	public int computeCost(TradeGood tg, TradeState ts) {
		double tax = planet.getTaxRate();
		
		
		return 0;
	}

}
