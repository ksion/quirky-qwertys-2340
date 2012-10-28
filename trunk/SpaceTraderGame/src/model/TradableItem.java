package model;
/**
 * represents the items ready for trade
 * @author QuirkyQwertys
 *
 */

public class TradableItem {
	private TradeGood type;
	private int qty;
	private double price;
	
	public TradableItem(TradeGood type, int qty, double price){
		this.type = type;
		this.qty = qty;
		this.price = price;
	}
/**
 
 * @return
 */
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public TradeGood getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}
	
	public String getName(){
		return type.getName();
	}
	
	

}
