package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents the attributes of inventory that traders are interested in
 * @author Quirky Qwertys
 *
 */
public class Inventory {
	private ArrayList<TradableItem> goods;
	private int maxItems;
	
	public Inventory (int maxItems){
		this.maxItems = maxItems;
		goods = new ArrayList<TradableItem>();
	}
	
	public Inventory (){
		this(Integer.MAX_VALUE);
	}
	
	public void generate(int techLevel, TradeGood[] allowableTradeGoods, int maxQty, boolean inSpace ){
		Random rand = new Random();
		goods = new ArrayList<TradableItem>();
		for (TradeGood good: allowableTradeGoods){
			int qty = rand.nextInt(maxQty);
			if (qty>0){
				goods.add(new TradableItem(good, rand.nextInt(maxQty),good.cost(techLevel,inSpace )) );
			}
			 
		}	
		
	}
	
	public List<TradableItem> getGoods(){
		return goods;
	}
	
	/**
	 * Stores a TradeGood in the Ship, if there is space
	 * available.
	 * 
	 * @param good the TradeGood that will be stored
	 * @return true if the good was stored, false otherwise
	 */
//	public boolean addTradeGood(TradeGood good){
//		if (tradeGoodCounter < CARGOHOLDS){
//			if (goodsInventory.containsKey(good)){
//				Integer newValue = goodsInventory.get(good);
//				goodsInventory.put(good, ++newValue);
//			}
//			else
//				goodsInventory.put(good, 1);
//			tradeGoodCounter++;
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Removes a TradeGood from the Ship.
	 * 
	 * @param good the TradeGood that will be removed
	 * @return true if the TradeGood was removed, otherwise return false
	 */
//	public boolean removeTradeGood(TradeGood good){
//		if (tradeGoodCounter == 0)
//			return false;
//		else if (goodsInventory.containsKey(good)){
//				Integer quantity = goodsInventory.get(good);
//				if (quantity == 1)
//					goodsInventory.remove(good);
//				else
//					goodsInventory.put(good, --quantity);
//				tradeGoodCounter--;
//				return true;
//		}
//		return false;
//	}
	
	/**
	 * Checks the number of cargo holds available to store 
	 * TradeGoods.
	 * 
	 * @return the number of cargo holds available
	 */
//	public int holdsAvailable(){
//		return CARGOHOLDS - tradeGoodCounter;
//	}
}
