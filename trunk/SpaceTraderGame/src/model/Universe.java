package model;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the universe or a container for all of the solar
 * systems that exist in the world of the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class Universe implements java.io.Serializable{
	
	/** Represents a list of all of the SolarSystems instantiated in the Universe. */
	private ArrayList<SolarSystem> solarSystems = new ArrayList<SolarSystem>();
	 
	/**
	 * Instantiates the universe with four default SolarSystems.
	 * @throws IOException 
	 */
	public Universe() throws IOException{
		SolarSystem system1 = new SolarSystem("Peony", new Point(1, 0), 0, 0.03, 0);
		SolarSystem system2 = new SolarSystem("Bronco", new Point(2, 0), 3, 0.05, 0);
		SolarSystem system3 = new SolarSystem("Anjous", new Point(3, 0), 1, 0.05, 3);
		SolarSystem system4 = new SolarSystem();
		
		ArrayList<Planet> planets1 = new ArrayList<Planet>();
		ArrayList<Planet> planets2 = new ArrayList<Planet>();
		ArrayList<Planet> planets3 = new ArrayList<Planet>();
		ArrayList<Planet> planets4 = new ArrayList<Planet>();
		
		planets1.add(new Planet(new Point(100, 100), system1, "Hibiscus", Planet.largePlanetPics[0]));
		planets1.add(new Planet(new Point(175, 225), system1, "Acacia", Planet.largePlanetPics[1]));
		planets1.add(new Planet(new Point(600,250), system1, "Pengua", Planet.largePlanetPics[2]));
		planets1.add(new Planet(new Point(398, 202), system1, "Abelia", Planet.largePlanetPics[3]));
		planets2.add(new Planet(new Point(), system2, "Mustang", Planet.largePlanetPics[1]));
		planets3.add(new Planet(new Point(), system3, "Seckel", Planet.largePlanetPics[2]));
		planets4.add(new Planet(new Point(), system4, "Toussaint", Planet.largePlanetPics[3]));
		
		system1.setPlanets(planets1);
		system2.setPlanets(planets2);
		system3.setPlanets(planets3);
		system4.setPlanets(planets4);
		
		add(system1);
		add(system2);
		add(system3);
		add(system4);
	}
	
	/** 
	 * Adds a SolarSystem to the Universe.
	 * 
	 * @param system the SolarSystem that will be added to the Universe
	 * @return true if the system is successfully added, false
	 * otherwise
	 */
	public boolean add(SolarSystem system){
		if (!solarSystems.contains(system)){
			solarSystems.add(system);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a SolarSystem from the Universe.
	 * 
	 * @param system the SolarSystem that will be removed
	 * @return true if the system was successfully removed,
	 * false otherwise
	 */
	public boolean remove(SolarSystem system){
		if (solarSystems == null || solarSystems.size() < 1)
			return false;
		else if (solarSystems.contains(system)){
			solarSystems.remove(system);
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves a list of all of the SolarSystems in the
	 * Universe.
	 * 
	 * @return a list of all the SolarSystems
	 */
	public ArrayList<SolarSystem> getSystems(){
		return solarSystems;
	}
	
	/**
	 * Sets the SolarSystems in the Universe to the specified
	 * list of systems.
	 * 
	 * @param systems the SolarSystems the Universe will have
	 */
	public void setSystems(ArrayList<SolarSystem> newSystems){
		solarSystems = newSystems;
	}
}