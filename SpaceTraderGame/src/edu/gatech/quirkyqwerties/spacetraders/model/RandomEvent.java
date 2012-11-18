/**
 * RandomEvent.java
 * @version 1.0
 * copyright 2012
 */

package edu.gatech.quirkyqwerties.spacetraders.model;

import java.util.Random;
import javax.swing.JOptionPane;
import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;

/**
 * Represents a random event in the Space Trader universe
 * which can consist of a pirate encounter, finding a good,
 * or losing a good.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.12.12
 */
public class RandomEvent{
	
	/** Represents the player of the game. */
	private Player player; // $codepro.audit.disable variableShouldBeFinal
	
	/** Represents a random number generator. */
	private Random random = new Random(); // $codepro.audit.disable variableShouldBeFinal
	
	/** Represents an event type. */
	private Integer event; // $codepro.audit.disable variableShouldBeFinal
	
	/** Number of types of trade goods that exist in the game.*/
	private static final int ITEM_TYPES = 9;
	
	/** Number of possible events in the game. */
	private static final int EVENT_TYPES = 3;
	
	/**
	 * Instantiates a random event.
	 * 
	 * @param gc the game controller
	 */
	public RandomEvent(GameController gc){
		this.player = gc.getPlayer();
		event = random.nextInt(EVENT_TYPES);
		System.out.println(event);
		switch(event){
			case 0:
				break;
			case 1:
				gc.showEncounter();
				break;
			case 2: // $codepro.audit.disable numericLiterals
				findItem();
				break;
			default:
				break;
		}
	}
	
	/**
	 * Generates a random item that is added to the
	 * user's inventory.
	 */
	private void findItem(){
		final Inventory inventory = player.getShip().getCargo();
		if (inventory.getUsedSpace() < inventory.getMaxItems()){
			final TradeGood[] goods = TradeGood.values();
			final TradeGood good = goods[random.nextInt(ITEM_TYPES)];
			final TradableItem item = new TradableItem(good, 1, good.getBasePrice());

			if (inventory.purchase(item, 1)){
				player.getShip().setCargo(inventory);
			}
			JOptionPane.showMessageDialog(null, good.getName() + " has been added to " +
					                      "your cargo.");
		}
	}
	
	/**
	 * Creates a String with the event that was
	 * produced.
	 * 
	 * @return String with event name
	 */
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		if (this.event != null){
			if (this.event == 0){
				sb.append("No encounters/events.");
			}
			else if (this.event == 1){
				sb.append("Pirate encounter!");
			}
			else{
				sb.append("You found an item!");
			}
		}
		return sb.toString();
	}
}