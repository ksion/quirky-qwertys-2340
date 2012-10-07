/**
 * Represents the vehicle used by the Player to travel
 * from one planet to another. The Ship may be used to
 * engage in battle with other NPC travelers. It also
 * serves as storage for all of the Player's Equipment 
 * and TradeGoods.
 * 
 * @author Qwirky Qwerties
 * @version 1.0 10.07.12
 */
public class Ship {
    // /** Array used to store all of the Player's TradeGoods.*/
    // TradeGood[] cargoHold = new TradeGood[20]
	
	// /** Array used to store all of the Ship's Equipment. */
	// Equipment[] myEquipment = new Equipment[10];
	
	/** Amount of damage Ship has sustained. */
	private int damageSustained = 0;
	
	/** Distance Ship has traveled. */
	private int distanceTraveled = 0;
	
	/** Rate at which Ship uses up fuel as it travels. For every
	    four parsecs the Ship travels, the Ship's fuel amount 
	    goes down by one ton of fuel. */
	private int fuelEconomy = 4;
	
	/** Amount of fuel Ship currently has. */
	private int fuelAmount = 16;
	
	/** Marker used to keep track of whether or not Ship has an 
	    escape pod. */
	private boolean escapePod = false;
	
	/** Marker used to keep track of whether or not Ship is insured. */
	private boolean insurance = false;
	
	/** Number of cargo holds available for the equipment. */
	private static final int CARGOBAY = 15;
	
	/** Number of slots available to store weapons. */
	private static final int WEAPONSLOTS = 1;
	
	/** Number of slots available to store gadgets. */
	private static final int GADGETSLOTS = 1;
	
	/** Number of slots available to store gadgets. */
	private static final int SHIELDSLOTS = 0;
	
	/** Range of parsecs Ship can travel before it runs out of fuel. */
	private static final int TRAVELRANGE = 14;
	
	/** Number of rooms available for crew. */
	private static final int CREWQUARTERS = 0;	
	
	/** Strength of the Ship's hull. */
	private int hullStrength;
	
	/** Current x-coordinate of the Ship. */
	private int currentX = 0;
	
	/** Current y-coordinate of the Ship. */
	private int currentY = 0;

	/**
	 * Instantiates a Ship with specified hull strength.
	 */
	// Later: include Point or X and Y coordinates in constructor
	public Ship(int hullStrength){
		this.hullStrength = hullStrength;
	}
	
	
}