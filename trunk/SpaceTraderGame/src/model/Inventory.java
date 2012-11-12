package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents the attributes of inventory that traders are interested in
 * @author Quirky Qwertys
 *
 */
public class Inventory implements java.io.Serializable{
	private ArrayList<TradableItem> goods;
	private int maxItems;
	
	/**
	 * constructor with item max
	 * @param maxItems size of inventory
	 */
	public Inventory (int maxItems){
		this.maxItems = maxItems;
		goods = new ArrayList<TradableItem>();
	}
	/**
	 * sets inventory to size of int if one is not passed in; e.g. for planet
	 */
	public Inventory (){
		this(Integer.MAX_VALUE);
	}
	

	/**
	 * generates the inventory out of all possible goods 
	 * @param techLevel
	 * @param allowableTradeGoods
	 * @param maxQty
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
	 * gets the trade goods out of the inventory
	 * @return list of goods
	 */
	public List<TradableItem> getGoods(){
		return goods;
	}
	
	/**
	 * gets quantity of good
	 * @return quantity
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
	 * finds a tradable item in the list
	 * @param good
	 * @return current item if found; null otherwise
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