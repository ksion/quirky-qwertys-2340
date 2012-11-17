package model;

import java.util.Random;
import javax.swing.*;
import view.EncounterPanel;
import controller.GameController;

/**
 * Represents a random event in the Space Trader universe
 * which can consist of a pirate encounter, finding a good,
 * or losing a good.
 * 
 * @author Quirky Qwertys
 */
public class RandomEvent {
	
	private static final long serialVersionUID = 1L;
	
	/** Represents the player of the game. */
	private Player player;
	
	/** Represents a Random generator. */
	private Random random = new Random();
	
	/**
	 * Instantiates a random event.
	 * 
	 * @param gc the game controller
	 */
	public RandomEvent(GameController gc){
		this.player = gc.getPlayer();
		int event = random.nextInt(3);
		System.out.println(event);
		switch(event){
			case 0:
				break;
			case 1:	
				gc.showEncounter();
				break;
			case 2:
				findItem();
				break;
		}
	}
	
	/**
	 * Generates a random item that is added to the
	 * user's inventory.
	 */
	private void findItem(){
		Inventory i = player.getShip().getCargo();
		if (i.getUsedSpace() < i.getMaxItems()){
			TradeGood[] goods = TradeGood.values();
			TradeGood good = goods[random.nextInt(9)];
			TradableItem item = new TradableItem(good, 1, good.getBasePrice());

			i.addTradeGood(item);
			player.getShip().setCargo(i);
			JOptionPane.showMessageDialog(null, good.getName() + " has been added to your cargo.");
		}
	}
}