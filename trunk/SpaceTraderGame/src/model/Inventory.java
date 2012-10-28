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
}
