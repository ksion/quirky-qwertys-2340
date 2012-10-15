import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public interface SolarSystem {

	public List<Planet> getSolarSystem();
	
	public Point getLocation();
	
	public int getTechLevel();
	
	public double genTaxRate();
	
	public Point getWormhole();
	
	public Encounter genEncounter();
}
