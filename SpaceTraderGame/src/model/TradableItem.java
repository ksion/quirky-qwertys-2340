package model;
/**
 * Represents the items (TradeGoods) that can be traded,
 * bought or sold.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.27.12
 */

public class TradableItem {
	private TradeGood type;
	private int qty;
	private int price;
	
	
	/**
	 * Instantiates a tradeable item that will go into 
	 * the inventory.
	 * @param type the trade good 
	 * @param qty the quantity in inventory
	 * @param price the price of the trade good 
	 */
	public TradableItem(TradeGood type, int qty, int price){
		this.type = type;
		this.qty = qty;
		this.price = price;
	}
/**
 * Gets the quantity of the trade good
 * @return the quantity
 */
	public int getQty() {
		return qty;
	}
/**
 * Sets the quantity of the trade good
 * @param qty the new quantity of the trade good
 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * Retrieves the type of trade good
	 * @return the trade good
	 */
	public TradeGood getType() {
		return type;
	}

	/**
	 * Gets the price of the trade good
	 * @return the price of the good
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Gets the name of the trade good
	 * @return the name of the good
	 */
	public String getName(){
		return type.getName();
	}
}