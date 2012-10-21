package model;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Represents a planet in the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class Planet {
	Random rand = new Random();
	private String name, picName;
	private final int HEIGHT = 451, WIDTH = 801, SIZE = 30;
	private int currentCondition, techLevel; 
	private Point position; 
	private TradeGood[] goods; 
	private static SolarSystem system;
	private static double tax;
	private String[] planetPics = {"src/view/blueGasPlanet.png","src/view/desertPlanet.png","src/view/desolatePlanet.png","src/view/jupiterPlanet.png", "src/view/redMineralPlanet.png"};
	// private Vendor[] availVend;
	private String[] vowels = {"a","e","i","o","u","y"}, consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
	
	
	public Planet(Point p, SolarSystem s, String n, int tL, int cc){
		position = p;
		system = s;
		name = n;
		techLevel = tL; 
		currentCondition = cc;
		picName = planetPics[rand.nextInt(5)];
		
		tax = s.getTaxRate();
	}
	
	public Planet(Point p, SolarSystem s){
		position = p;
		system = s;
		makeName();
		techLevel = rand.nextInt(8);
		currentCondition = rand.nextInt(13);
		picName = planetPics[rand.nextInt(5)];
	}
	
	public Planet(){
		position = new Point(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
		makeName();
		techLevel = rand.nextInt(8);
		currentCondition = rand.nextInt(13);
		picName = planetPics[rand.nextInt(5)];
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
	
	public TradeGood[] getTradeGoods(){
		return goods;
	}
	
	public String toString(){
		String info = "Name: " + name + "\nPosition: " + position + "\nCurrent Condition: " + currentCondition + "\nTech Level: " + techLevel;
		return info;
	}
	
	public void draw(Graphics g){
		Image img = null;
		try {
			img = ImageIO.read(new File(picName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int x = (int)position.getX() - 15;
		int y = (int)position.getY() - 15;
		if(x - 10 <= 0){
			x = 0;
		}
		if (y - 10 < 0){
			y = 0;
		}
		if(x + 40 >= WIDTH){
			x = WIDTH - SIZE;
		}
		if(y + 40 >= HEIGHT){
			x = HEIGHT - SIZE;
		}
		
		g.drawImage(img, x, y, SIZE, SIZE, null);
	}
}