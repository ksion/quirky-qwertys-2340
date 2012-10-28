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
	
	/** Name of the Planet and the name of the corresponding image file. */
	private String name, picName;
	
	/** Dimensions of the images used to represent Planets. */
	private final int HEIGHT = 451, WIDTH = 801, SIZE = 30;
	
	/** Location of Planet in a SolarSystem. */
	private Point position; 
	
	/** 
	 * Tech level of a planet. The higher the level, the lower the price of natural goods 
	 * and the higher the price for industrial goods.
	 */
	private int techLevel;
	
	/** Trade goods available on the Planet. */
	private Inventory tradableInventory;
	
	/** The SolarSystem the Planet belongs to. */
	private static SolarSystem system; 
	
	/** Tax that applies to all trade transactions in the Planet. */
	private static double tax;
	
	/** List of images used to represent a Planet in the GUI. */
	private String[] planetPics = {"src/view/blueGasPlanet.png","src/view/desertPlanet.png",
			"src/view/desolatePlanet.png","src/view/jupiterPlanet.png", 
			"src/view/redMineralPlanet.png"};
	// private Vendor[] availVend;
	
	/** List of letters that can be used to randomly generate a planet name. */
	private String[] vowels = {"a","e","i","o","u","y"}, consonants = 
		{"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
	
	
	/** 
	 * Instantiates a Planet given a specified coordinate, 
	 * SolarSystem, and name.
	 * 
	 * @param p the (x, y) coordinate of the Planet 
	 * @param s the SolarSystem the Planet belongs to
	 * @param n the name of the Planet
	 */
	public Planet(Point p, SolarSystem s, String n){
		position = p;
		system = s;
		name = n;
		picName = planetPics[rand.nextInt(5)];
		
		techLevel = s.getTechLevel();
		tax = s.getTaxRate();
	}

	/** 
	 * Instantiates a Planet given a specified coordinate, 
	 * and SolarSystem. The Planet's name is randomly 
	 * generated.
	 * 
	 * @param p the (x, y) coordinate of the Planet 
	 * @param s the SolarSystem the Planet belongs to
	 * @param n the name of the Planet
	 */
	public Planet(Point p, SolarSystem s){
		position = p;
		system = s;
		makeName();
		techLevel = s.getTechLevel();
		picName = planetPics[rand.nextInt(5)];
	}
	
    /**
     * Generates a random name for the Planet.
     */
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
		name = n;
	}
	/**
	 * generates the subset of planet inventory based on all allowable inventory
	 */
	public void createInventory(){
		tradableInventory = new Inventory();
		//TODO: in another method, figure out the allowable trade goods to pass in instead of TradeGood.values()
		tradableInventory.generate(this.getTechLevel(), TradeGood.values(), 10, false);
	}
	
	/**
	 * Gets the name of the Planet.
	 * 
	 * @param n the new name of the Planet
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name of the Planet to the specified input.
	 * 
	 * @param n the new name of the Planet
	 */
	public void setName(String n){
		name = n;
	}
	
	/**
	 * Retrieves the position of a Planet.
	 * 
	 * @return the position of the Planet
	 */
	public Point getPosition(){
		return position;
	}
	
	/**
	 * Retrieves the tech level of the Planet.
	 * 
	 * @return the Planet's tech level
	 */
	public int getTechLevel(){
		return techLevel;
	}
	
	/**
	 * Retrieves a list of all of the TradeGoods available 
	 * in the Planet.
	 * 
	 * @return the list of Inventory
	 */
	public Inventory getInventory(){
		return tradableInventory;
	}
	
	/**
	 * Retrieves the tax rate of the given planet.
	 * @return tax
	 */
	public static double getTaxRate() {
		return tax;
	}
	
	/**
	 * Creates a String with information about the Planet's name,
	 * position, and tech level.
	 * 
	 * @return a String containing the Planet's name, position 
	 * and tech level
	 */
	public String toString(){
		String info = "Name: " + name + "\nPosition: " + position + "\nTech Level: " + techLevel;
		return info;
	}
	
	/**
	 * Draws the Planet.
	 * 
	 * @param g default graphics param used to draw the image 
	 * in MapWindow
	 */
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
	
	/**
	 * Checks to see whether the mouse is over a Planet in the MapWindow.
	 * 
	 * @param p the current x- and y-coordinates of the mouse
	 * @return true if the mouse is over a Planet, false otherwise
	 */
	public boolean inRange(Point p){
		boolean inRange = false;
		
		int distance = (int) (Math.pow(Math.pow((position.getX() - p.getX()), 2) + 
				Math.pow(position.getY() - p.getY(), 2), .5));
		
		if (distance < 16){
			inRange = true; 
		}
		return inRange;
	}
}