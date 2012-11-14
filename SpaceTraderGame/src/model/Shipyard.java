package model;

import java.awt.Point;
import java.io.IOException;

/**
 * Represents the shipyard where the user may trade 
 * his/her old ship for a new a new one. Fuel can also
 * be bought in the shipyard.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.05.12
 */
public class Shipyard {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** List of all the ships in the Shipyard. */
	private Ship[] ships = new Ship[5];
	
	/**
	 * Instantiates a shipyard with all of the ships
	 * that exist in the Space Trader universe.
	 * @throws IOException 
	 */
	public Shipyard(Ship s) throws IOException{
		Point p = s.getLocation();
		FleaShip flea = new FleaShip(p);
		GnatShip gnat = new GnatShip(p);
		FireflyShip firefly = new FireflyShip(p);
		MosquitoShip mosquito = new MosquitoShip(p);
		BumblebeeShip bee = new BumblebeeShip(p);
		
		ships[0] = flea;
		ships[1] = gnat;
		ships[2] = firefly;
		ships[3] = mosquito;
		ships[4] = bee;
	}
	
	/**
	 * Retrieves a list of all the ships in the Shipyard.
	 * 
	 * @return the list of ships
	 */
	public Ship[] getShips(){
		return ships;
	}
}