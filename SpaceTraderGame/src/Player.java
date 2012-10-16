/**
 * Represents the playable space trader character.
 * It is through Player that the user can play this game.
 * The Player can travel to other planets and trade, sell,
 * or buy goods with other non-playable character traders.  
 * 
 * @author Qwirky Qwertys
 * @version 1.0 10.07.12
 *
 */
public class Player {
	
	/** Name of the Player. */
	private String name;
	
	/** Skill determines how well Player pilots the Ship. */
	private static int pilotSkill = 0;
	
	/** Skill determines how well Player handle Weapons. */
	private static int fighterSkill = 0;
	
	/** Skill determines prices Player must pay for TradeGoods, 
	    Ships, and Equipment. */
	private static int traderSkill = 0;
	
	/** Skill determines how well Player can maintain Ship. */
	private static int engineerSkill = 0;
	
	/** Initial amount of money Player has. */
	private static int money = 1000;
	
	/** Current Ship the player owns. */ 
	Ship myShip = new GnatShip(24);
	
	// /** Player's inventory of TradeGoods. */
	// TradeGood[] inventory = new TradeGood[20];
	
	/** 
	 * Instantiates a Player with a given name and skill
	 * points that can be allocated to the Player's different
	 * skills.
	 * 
	 * @param name the name of the Player
	 * @param skills array of skill points corresponding
	 *        to each of the Player's skills
	 */
	public Player(String name, int[] skills){
		this.name = name;
		pilotSkill = skills[0];
		fighterSkill = skills[1];
		traderSkill = skills[2];
		engineerSkill = skills[3];
		
	}
	
	/**
	 * Adds points to the Player's pilot skill.
	 * 
	 * @param pilotPoints number of points that will be
	 * 	      added to the Player's current pilot skill.
	 */
	public void addPilotPoints(int pilotPoints){
		pilotSkill += pilotPoints;
	}
	
	/**
	 * Adds points to the Player's fighter skill.
	 * 
	 * @param fighterPoints number of points that will be
	 * 	      added to the Player's current fighter skill.
	 */
	public void addFighterPoints(int fighterPoints){
		fighterSkill += fighterPoints;
	}	
	
	/**
	 * Adds points to the Player's trader skill.
	 * 
	 * @param traderPoints number of points that will be
	 * 	      added to the Player's current trader skill.
	 */
	public void addTraderPoints(int traderPoints){
		traderSkill += traderPoints;
	}	
	
	/**
	 * Adds points to the Player's engineer skill.
	 * 
	 * @param engineerPoints number of points that will be
	 * 	      added to the Player's current engineer skill.
	 */
	public void addEngineerPoints(int engineerPoints){
		engineerSkill += engineerPoints;
	}	
	
	/**
	 * Gets the current amount of money a Player has.
	 * 
	 * @return the amount of money Player has
	 */
	public int getMoney(){
		return money;
	}
	
	public String toString(){
		String playerString = "";
		playerString += "You've created a player named " + name + ".\n";
		playerString += "Pilot skill points: " + pilotSkill + "\n";
		playerString += "Fighter skill points: " + fighterSkill + "\n";
		playerString += "Trader skill points: " + traderSkill + "\n";
		playerString += "Engineer skill points: " + engineerSkill + "\n";
		playerString += "Your current ship is a " + myShip.getName() + "\n";
		playerString += "You have " + money + " credits available. \n";
		return playerString;
	}
	/* 
	 * Methods that will be added to code later:
	 * public int sell(Item i){return null;}
	 * public int buy(Item i){return null;}
	 * public int trade(Item i){return null;}
	 * public void takeTurn(Planet p){}
	 * public void hire(Mercenary m){}
	 * public void fire(Mercenary m){}
	 * public void pay(Mercenary m){}*/
}