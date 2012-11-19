// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods, numericLiterals, lossOfPrecisionInCast, emptyMethod
// emptyMethod, variableShouldBeFinal, numericLiterals

/** the containing package and all its necessary imports */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;
import edu.gatech.quirkyqwerties.spacetraders.model.Planet;
import edu.gatech.quirkyqwerties.spacetraders.model.Player;
import edu.gatech.quirkyqwerties.spacetraders.model.RandomEvent;
import edu.gatech.quirkyqwerties.spacetraders.model.SolarSystem;

/**
 * Represents the navigation window for the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class MapWindow extends JPanel implements java.io.Serializable{
	
	/***/
	private static final long serialVersionUID = 1L;
	
	/** the game controller the window will be added to */
	private final  GameController gc;
	
	/** the background image for the window */
	private final Image background; // $codepro.audit.disable hidingInheritedFields
	
	/***/
	@SuppressWarnings("unused")
	
	/** the picture of the ship */
	final private Image shipIcon;
	
	/** the displayed name of the planet when the mouse is moved over it */
	protected JLabel planetName;
	
	/** all the labels that show the players skills location and fuel level */
	protected JLabel fuelLevel, skillPilot, 
	skillTrader, skillEngineer, skillFighter, shipLocation;
	
	/** the player of the game. used for the stats displayed on the window */
	protected Player player;
	
	/** the solar system the player is in */
	private SolarSystem solarSystem;
	
	/**
	 * Creates a map window based on the information provided
	 * by the game controller.
	 * 
	 * @param gc the game controller
	
	 * @throws IOException  */
	public MapWindow(GameController gc) throws IOException{
		this.gc = gc;
		background =ImageIO.read(getClass().getResource(
				"/edu/gatech/quirkyqwerties/spacetraders/view/starsBackground.jpeg"));
		shipIcon = ImageIO.read(getClass().getResource(
				"/edu/gatech/quirkyqwerties/spacetraders/view/shipIcon.png"));
		setLayout(new BorderLayout());
		addMouseListener(new MouseOver());
		addMouseMotionListener(new MouseMove());
		planetName = new JLabel();
		planetName.setForeground(Color.white);
		add(planetName);
		final JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5)); 
		labelPanel.setOpaque(false);
		labelPanel.setBackground(null);

		fuelLevel = Style.createSkillLabel();
		skillPilot = Style.createSkillLabel();
		skillTrader = Style.createSkillLabel();
		skillEngineer = Style.createSkillLabel();
		skillFighter = Style.createSkillLabel();
		shipLocation = Style.createSkillLabel();

		labelPanel.add(fuelLevel);
		labelPanel.add(skillPilot);
		labelPanel.add(skillTrader);
		labelPanel.add(skillEngineer);
		labelPanel.add(skillFighter);
		labelPanel.add(shipLocation);

		add(labelPanel, BorderLayout.SOUTH);
		
	
	}
	
	/**
	 * to string method
	
	 * @return nothing */
	@Override
	public String toString(){
		return null;
	}
	
	/**
	 * Paints all of the SolarSystems and Planets.
	 * 
	 * @param g the default Graphics object
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
		
		for(Planet p : solarSystem.getPlanets()){
			p.draw(g);
		}
	player.getShip().drawShip(g);
	}
	
	/**
	 * Sets the player and displays relevant stats.
	
	 * @param player Player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		fuelLevel.setText("Fuel level: " + player.getShip().getFuelAmount());
		skillPilot.setText("Pilot Skill: " + player.getPilotSkill());
		skillTrader.setText("Trader Skill: " + player.getTraderSkill());
		skillEngineer.setText("Engineer Skill: " + player.getEngineerSkill());
		skillFighter.setText("Fighter Skill: " + player.getFighterSkill());
		shipLocation.setText("Location: x = " + 
		player.getShip().getLocation().x + " y = " + player.getShip().getLocation().y);
	}

	/**
	 * Sets the solar system.
	 * 
	 * @param solarSystem the solar system
	 */
	public void setSolarSystem(SolarSystem solarSystem){
		this.solarSystem = solarSystem;
	}

	/**
	 * Creates an Adapter to catch where the mouse is pressed on
	 * the map.
	 * @author Hayden
	 */
	private class MouseOver extends MouseAdapter{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
		/**
		 * Detects when a Planet has been clicked.
		 * if the planet clicked is in the range of the ship, 
		 * it will travel there and open the trade window. 
		 * If not it will tell the user that the planet is not
		 *  in range.
		 * @param m the event corresponding to when the mouse is pressed
		 * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
		 */
		public void mousePressed(MouseEvent m){
			final Point point = m.getPoint();
			for(Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(point)){
					int choice = JOptionPane.showConfirmDialog(null, 
							"Would you like to travel to this planet?", 
							"Travel", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION){
						if( gc.canTravelToPlanet( planet )) {
							new RandomEvent(gc);
							fuelLevel.setText("Fuel level: " + 
							player.getShip().getFuelAmount());
							shipLocation.setText("Location: x = " + 
							player.getShip().getLocation().x + " y = " 
									+ player.getShip().getLocation().y);
							repaint();
						}
					}
				}
			}
		}
	}
	
	/**
	 * This class listens for mouse movements. As of now 
	 * all it does is display the name of the planet when
	 * the mouse is over it.
	 * 
	 * @author Quirky Qwertys
	 */
	private class MouseMove implements MouseMotionListener{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
		
		/**
		 * @see java.awt.event.MouseMotionListener#mouseDragged(MouseEvent)
		 * @param MouseEvent e
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		/**
		 * Checks to see whether the cursor is hovering over
		 * a planet and displays the planet's corresponding
		 * name if it is doing so.
		 * @see java.awt.event.MouseMotionListener#mouseMoved(MouseEvent)
		 * @param MouseEvent m
		 */
		@Override
		public void mouseMoved(MouseEvent m) {
			final Point mPoint = m.getPoint();
			
			/** 
			 * @param the point that the mouse currently holds
			 */
			move(mPoint);
			for (Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(mPoint)){
					int size = planet.getSize();
					Point pPos = planet.getPosition();
					planetName.setBounds(
							(int) pPos.getX() - size,
							(int) pPos.getY() - size,
							100, 
							15
							);
					planetName.setText(planet.getName());
				}
			}
		}
	}
	
	/** 
	 * Draws the ship, rotates it and moves it to 
	 * the specified point. 
	 * @param p Point
	 */
	public void move(Point p){
		final double x = player.getShip().getLocation().x,
				y = player.getShip().getLocation().y;
		
		final Graphics2D g2d;
		final Image shipIcon = null;
		final double dx = p.x - x, dy = p.y - y;
		final double degree = Math.atan(dy / dx);
		final AffineTransform at = new AffineTransform();
		at.rotate(degree);
		at.translate(p.x, p.y);
		
		final BufferedImage bShipIcon = new BufferedImage(
				100, 100, BufferedImage.TYPE_INT_ARGB);
		g2d = bShipIcon.createGraphics();
		g2d.drawImage(shipIcon, at, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
	}
}