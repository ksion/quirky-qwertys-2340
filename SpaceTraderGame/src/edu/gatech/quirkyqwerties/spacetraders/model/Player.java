package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Point;
import java.io.IOException;

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
	private int pilotSkill = 0;
	
	/** Skill determines how well Player handle Weapons. */
	private  int fighterSkill = 0;
	
	/** Skill determines prices Player must pay for TradeGoods, 
	    Ships, and Equipment. */
	private int traderSkill = 0;
	
	/** Skill determines how well Player can maintain Ship. */
	private  int engineerSkill = 0;
	
	/** Initial amount of money Player has. */
	private int money = 1000;
	
	/** Current Ship the player owns. */ 
	Ship myShip;
		
	/** 
	 * Instantiates a Player with a given name and skill
	 * points that can be allocated to the Player's different
	 * skills.
	 * 
	 * @param name the name of the Player
	 * @param skills array of skill points corresponding
	 *        to each of the Player's skills
	 * @throws IOException 
	 */
	public Player(String name, int[] skills) throws IOException{
		this.name = name;
		pilotSkill = skills[0];
		fighterSkill = skills[1];
		traderSkill = skills[2];
		engineerSkill = skills[3];
		myShip = new GnatShip(new Point(100,100));
	}
	
	/**
	 * Need by serialization
	 */
	protected Player() {
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
	
	/**
	 * Retrieves the player's ship.
	 * 
	 * @return the ship
	 */
	public Ship getShip() {
		return myShip;
	}
	
	/**
	 * Retrieves the player's inventory.
	 * 
	 * @return the player's inventory
	 */
	public Inventory getCargo() {
		return myShip.getCargo();
	}
	

	

	/**
	 * Adds credits to the players current amount
	 * 
	 * @param update the amount of money added
	 */
	public void addMoney(int update){
		money = money + update;
	}
	
	protected void setMoney(int money){
		this.money = money;
	}
	
	
	/**
	 * Gets the pilot skill level.
	 * 
	 * @return pilotSkill level
	 */
	public int getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * Gets the Fighter skill level.
	 * 
	 * @return fighter skill level
	 */
	public int getFighterSkill() {
		return fighterSkill;
	}

	/**
	 * Gets the trader skill level.
	 * 
	 * @return trader skill level
	 */
	public int getTraderSkill() {
		return traderSkill;
	}

	/**
	 * Gets the engineer skill level.
	 * 
	 * @return engineer skill level
	 */
	public int getEngineerSkill() {
		return engineerSkill;
	}
	
	/**
	 * Sets the Player's ship to a given type.
	 * 
	 * @param s the player's new ship
	 */
	public void setShip(Ship s){
		myShip = s;
	}

	/**
	 * String that represents major player stats.
	 * 
	 * @return a string with the player's stats
	 */
	public String toString(){
		String playerString = 
		name + "\n" +
		money + "\n" +
		pilotSkill + "\n"+
		fighterSkill + "\n"+
		traderSkill + "\n"+
		engineerSkill + "\n"+
		myShip.getName() + "\n" + 
		myShip.toString();
		
		return playerString;
	}
}