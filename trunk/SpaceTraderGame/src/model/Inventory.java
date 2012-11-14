package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the attributes of inventory that traders
 * are interested in.
 * 
 * @author Quirky Qwertys
 *
 */
public class Inventory {

	private static final long serialVersionUID = 1L;
	private ArrayList<TradableItem> goods;
	private int maxItems;
	
	/**
	 * Instantiates an inventory with a specified capacity.
	 * 
	 * @param maxItems size of inventory
	 */
	public Inventory (int maxItems){
		this.maxItems = maxItems;
		goods = new ArrayList<TradableItem>();
	}
	/**
	 * Instantiates an inventory with a default capacity.
	 */
	public Inventory(){
		this(Integer.MAX_VALUE);
	}
	

	/**
	 * Generates the inventory out of all possible goods.
	 *  
	 * @param techLevel the current tech level
	 * @param allowableTradeGoods trade goods permitted in the system
	 * @param maxQty the maximum quantity allowed for a good
	 * @param inSpace
	 */

	public void generate(int techLevel, List<TradeGood> allowableTradeGoods, int maxQty, boolean inSpace){

		Random rand = new Random();
		goods = new ArrayList<TradableItem>();
		for (TradeGood good: allowableTradeGoods){
			int qty = rand.nextInt(maxQty);
			if (qty > 0){
				goods.add(new TradableItem(good, rand.nextInt(maxQty),good.cost(techLevel, inSpace)));
			}
		} 
		
	}
	
	/**
	 * Gets the trade goods out of the inventory.
	 * 
	 * @return the list of goods
	 */
	public List<TradableItem> getGoods(){
		return goods;
	}
	
	/**
	 * Gets quantity of a good.
	 * 
	 * @return the quantity
	 */
	public int getUsedSpace(){
		int quantity = 0;
		for (TradableItem good: goods){
			quantity = quantity + good.getQty();
		}
		return quantity;
	}
	
	/**
	 * Gets the capacity of the inventory.
	 * 
	 * @return the max capacity
	 */
	public int getMaxItems(){
		return maxItems;
	}
	
	/**
	 * Stores a TradeGood in the Ship, if there is space
	 * available.
	 * 
	 * @param good the TradeGood that will be stored
	 * @return true if the good was stored, false otherwise
	 */
	public boolean addTradeGood(TradableItem good){
		if (getUsedSpace() < maxItems){
			TradableItem found = findItem(good);
			if (found!=null){
				found.setQty(found.getQty()+1);
			}
			else{
				TradableItem newItem = new TradableItem(good.getType(),1,good.getPrice());
				goods.add(newItem);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Finds a Tradable item in the list
	 * @param good the tradable item
	 * 
	 * @return current item if found, null otherwise
	 */
	public TradableItem findItem(TradableItem good){
		for (TradableItem current: goods){
			if (current.getType().equals(good.getType())){
				return current;
			}	
		}
		return null;
	}
}