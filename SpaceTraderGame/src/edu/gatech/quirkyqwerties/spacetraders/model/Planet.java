// $codepro.audit.disable
package edu.gatech.quirkyqwerties.spacetraders.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import flexjson.JSON;

/**
 * Represents a planet in the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class Planet {
	
	/** The rand. */
	private Random rand = new Random();
	
	/** Name of the Planet and the name of the corresponding image file. */
	private String name;
	
	/** The pic name. */
	private Image picName;
	
	/** The large pic name. */
	private Image largePicName;
	
	/** The pic name str. */
	private String picNameStr;
	
	/** The large pic name str. */
	private String largePicNameStr;
	
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
	
	/** Tax that applies to all trade transactions in the Planet. */
	private double tax;
	
	/** List of images used to represent a Planet in the GUI. */
	private String[] planetPics = {"/edu/gatech/quirkyqwerties/spacetraders/view/blueGasPlanet.png",
		"/edu/gatech/quirkyqwerties/spacetraders/view/desertPlanet.png",
		"/edu/gatech/quirkyqwerties/spacetraders/view/desolatePlanet.png",
		"/edu/gatech/quirkyqwerties/spacetraders/view/jupiterPlanet.png", 
		"/edu/gatech/quirkyqwerties/spacetraders/view/redMineralPlanet.png"};
	
	private final int PICNUM = 5;
	
	/** List of images to represent the large planets in the GUI *. */
	
	public static String[] largePlanetPics = {"/edu/gatech/quirkyqwerties/spacetraders/view/aquaplanet.jpg", 
		"/edu/gatech/quirkyqwerties/spacetraders/view/transparentplanet.jpg",
		"/edu/gatech/quirkyqwerties/spacetraders/view/marbleplanet.jpg",
		"/edu/gatech/quirkyqwerties/spacetraders/view/graniteplanet.jpg"};
	
	/**
	 * Instantiates a Planet given a specified coordinate,
	 * SolarSystem, and name.
	 *
	 * @param p the (x, y) coordinate of the Planet
	 * @param s the SolarSystem the Planet belongs to
	 * @param n the name of the Planet
	 * @param largePlanetName the large planet name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Planet(Point p, SolarSystem s, String n, String largePlanetName) throws IOException{
		position = p;
		name = n;
		setPicNameStr(planetPics[rand.nextInt(PICNUM)]);
		setLargePicNameStr(largePlanetName);
		techLevel = s.getTechLevel();
		tax = s.getTaxRate();
	}
	
	/**
	 * Needed by serialization process.
	 */
	protected Planet() {
	}
	
	/**
	 * generates the subset of planet inventory based on all allowable inventory.
	 */
	public void createInventory(){
		tradableInventory = new Inventory(Integer.MAX_VALUE);
		TradeGood[] possiItems = TradeGood.values();
		ArrayList<TradeGood> passedItems = new ArrayList<TradeGood>();
		for (int i = 0; i < possiItems.length; i++) {
			if (possiItems[i].getMinTechLevelUse() <= this.getTechLevel()) {
				passedItems.add(possiItems[i]);
			}
		}
		passedItems.trimToSize();
		tradableInventory.generate(this.getTechLevel(), passedItems, 15, false);
	}
	
	/**
	 * Gets the name of the Planet.
	 * 
	 * @return the name
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
	 * Gets the pic name str.
	 *
	 * @return the pic name str
	 */
	public String getPicNameStr() {
		return picNameStr;
	}

	/**
	 * Sets the pic name str.
	 *
	 * @param picNameStr the new pic name str
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setPicNameStr(String picNameStr) throws IOException {
		this.picNameStr = picNameStr;
		picName = ImageIO.read(getClass().getResource(picNameStr));
	}

	/**
	 * Gets the large pic name str.
	 *
	 * @return the large pic name str
	 */
	public String getLargePicNameStr() {
		return largePicNameStr;
	}

	/**
	 * Sets the large pic name str.
	 *
	 * @param largePicNameStr the new large pic name str
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setLargePicNameStr(String largePicNameStr) throws IOException {
		this.largePicNameStr = largePicNameStr;	
		largePicName = ImageIO.read(getClass().getResource(largePicNameStr));
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
	 * Sets the tech level.
	 *
	 * @param level the new tech level
	 */
	protected void setTechLevel(int level) {
		this.techLevel = level;
	}
	
	/**
	 * Retrieves a list of all of the TradeGoods available 
	 * in the Planet.
	 * 
	 * @return the list of Inventory
	 */
	public Inventory getTradableInventory(){
		return tradableInventory;
	}
	
	/**
	 * Sets the tradable inventory.
	 *
	 * @param tradableInventory the new tradable inventory
	 */
	protected void setTradableInventory(Inventory tradableInventory) {
		this.tradableInventory = tradableInventory;
	}

	/**
	 * Retrieves the tax rate of the given planet.
	 * @return tax
	 */
	public double getTaxRate() {
		return tax;
	}
	
	/**
	 * Sets the tax rate.
	 *
	 * @param rate the new tax rate
	 */
	protected void setTaxRate(double rate) {
		this.tax = rate;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize(){
		return SIZE;
	}
	
	/**
	 * Gets the pic name.
	 *
	 * @return the pic name
	 */
	@JSON(include = false)
	public Image getPicName() {
		return picName;
	}
	
	/**
	 * Gets the large pic name.
	 *
	 * @return the large pic name
	 */
	@JSON(include = false)
	public Image getLargePicName() {
		return largePicName;
	}

	/**
	 * Creates a String with information about the Planet's name,
	 * position, and tech level.
	 * 
	 * @return a String containing the Planet's name, position 
	 * and tech level
	 */
	public String toString(){
		String info = "Name: " + name + "\nPosition: " + position + 
				"\nTech Level: " + techLevel;
		return info;
	}
	
	/**
	 * Draws the Planet.
	 * 
	 * @param g default graphics param used to draw the image 
	 * in MapWindow
	 */
	public void draw(Graphics g){
		
		int x = (int)(position.getX() - SIZE/2);
		int y = (int)(position.getY() - SIZE/2);
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
		
		g.drawImage(picName, x, y, SIZE, SIZE, null);
	}
	
	/**
	 * Checks to see whether the mouse is over a Planet in the MapWindow.
	 * 
	 * @param p the current x- and y-coordinates of the mouse
	 * @return true if the mouse is over a Planet, false otherwise
	 */
	public boolean inRange(Point p){ // $codepro.audit.disable booleanMethodNamingConvention
		boolean inRange = false;
		
		int distance = (int) (Math.pow(Math.pow((position.getX() - p.getX()), 2) + 
				Math.pow(position.getY() - p.getY(), 2), .5));
		
		if (distance < 16){
			inRange = true; 
		}
		return inRange;
	}
	
}