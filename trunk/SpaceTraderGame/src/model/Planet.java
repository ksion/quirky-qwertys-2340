package model;

import java.awt.*;
import java.util.Random;

public class Planet {
	Random rand = new Random();
	private String name;
	private int currentCondition, techLevel; 
	private Point position; 
	private TradeGood[] goods; 
	// private Vendor[] availVend;
	private String[] vowels = {"a","e","i","o","u","y"}, consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
	
	
	public Planet(Point p, String n, int tL, int cc){
		this.position = p;
		this.name = n;
		this.techLevel = tL; 
		this.currentCondition = cc;
	}
	public Planet(Point p){
		this.position = p;
		this.name = "Planet X";
		this.techLevel = rand.nextInt(8);
		this.currentCondition = rand.nextInt(13);
	}
	
	public Planet(){
		this.position = new Point(rand.nextInt(151), rand.nextInt(101));
		this.name = "Planet X";
		this.techLevel = rand.nextInt(8);
		this.currentCondition = rand.nextInt(13);
	}

	public void makeName(){
		int nameLength = rand.nextInt(8) + 3;
		String n = "";
		for(int i = 0; i <= (int)(nameLength/2); i++){
			if(nameLength % 2 == 0){
				n += vowels[rand.nextInt(6)];
				n += consonants[rand.nextInt(20)];
			}
			else{
				n += consonants[rand.nextInt(20)];
				n += vowels[rand.nextInt(6)];
			}
		}
		this.name = n;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public int getTechLevel(){
		return techLevel;
	}
	
	public int getCurrentCondition(){
		return currentCondition;
	}
	
	public String toString(){
		String info = "Name: " + name + "\nPosition: " + position + "\nCurrent Condition: " + currentCondition + "\nTech Level: " + techLevel;
		return info;
	}

}
