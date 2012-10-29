package model;
/**
 * represents the items ready for trade
 * @author QuirkyQwertys
 *
 */

public class TradableItem {
	private TradeGood type;
	private int qty;
	private int price;
	
	
	/**
	 * constructor for tradable items that will go into inventory;
	 * @param type trade good
	 * @param qty qty in inventory
	 * @param price trade good price
	 */
	public TradableItem(TradeGood type, int qty, int price){
		this.type = type;
		this.qty = qty;
		this.price = price;
	}
/**
 * gets the qty of the trade good
 * @return qty
 */
	public int getQty() {
		return qty;
	}
/**
 * sets the qty of the trade good
 * @param qty
 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * returns the type of trade good
	 * @return
	 */
	public TradeGood getType() {
		return type;
	}

	/**
	 * gets the price of the trade good
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * gets the name of the trade good
	 * @return name
	 */
	public String getName(){
		return type.getName();
	}
	
	

}
