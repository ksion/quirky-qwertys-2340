package model;

import java.util.Random;
import view.EncounterPanel;
/**
 * Represents a random event in the Space Trader universe
 * which can consist of a pirate encounter, finding a good,
 * or losing a good.
 * 
 * @author Quirky Qwertys
 */
public class RandomEvent{
	
	private Random random = new Random();
	
	public RandomEvent(Player player){
		int event = random.nextInt(2);
		switch(event){
			case 0:
				break;
			case 1:
				new EncounterPanel(player);
		}
	}
}
