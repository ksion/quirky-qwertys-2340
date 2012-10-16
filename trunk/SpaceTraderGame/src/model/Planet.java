package model;

import java.awt.*;
import java.util.Random;

public class Planet {
	Random rand = new Random();
	private String name;
	private int currentCondition, techLevel; 
	private Point position; 
	private TradeGood[] goods; 
	private Vendor[] availVend; 
	
	public Planet(Point p, int cc, int tL, String n){
		this.position = p;
		this.currentCondition = cc; 
		this.techLevel = tL;
		this.name = n;
	}
	public Planet(Point p){
		this(p, rand.nextInt(13), rand.nextInt(8), "Planet X");
	}
	
	public Planet(){
		Point p = new Point(rand.nextInt(151), rand.nextInt(101));
		this(p, rand.nextInt(13), rand.nextInt(8), "Planet X");
	}
	
	public toString(){
		String info = "Name: " + name + "Position: " + position + "Current Condition: " + currentCondition + "Tech Level: " + techLevel;
		
	}
	
}
