package view;

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
import edu.gatech.quirkyqwerties.spacetraders.model.*;
import edu.gatech.quirkyqwerties.spacetraders.controller.*;

/**
 * Represents the navigation window for the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class MapWindow extends JPanel implements java.io.Serializable{
		
	private static final long serialVersionUID = 1L;
	private GameController gc;
	private Image background;
	protected JLabel planetName;
	protected JLabel fuelLevel, skillPilot, skillTrader, skillEngineer, skillFighter, shipLocation;
	protected Player player;

	private SolarSystem solarSystem;
	
	/**
	 * Creates a map window based on the information provided
	 * by the game controller.
	 * 
	 * @param gc the game controller
	 * @throws IOException 
	 */
	public MapWindow(GameController gc) throws IOException{
		this.gc = gc;
		background =ImageIO.read(getClass().getResource("/view/starsBackground.jpeg"));
		setLayout(new BorderLayout());
		addMouseListener(new MouseOver());
		addMouseMotionListener(new MouseMove());
		planetName = new JLabel();
		planetName.setForeground(Color.white);
		add(planetName);
		JPanel labelPanel = new JPanel();
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
	 * Paints all of the SolarSystems and Planets.
	 * 
	 * @param g the default Graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null);
		
		for(Planet p : solarSystem.getPlanets()){
			p.draw(g);
		}
		player.getShip().drawShip(g);	
	}
	
	/**
	 * Main method creates the navigation window.
	 
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new MapWindow());
		frame.pack();
		frame.setVisible(true);
	}
	*/
		
	/**
	 * Sets the player and displays relevant stats.
	 * 
	 * @param the current player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		fuelLevel.setText("Fuel level: " + player.getShip().getFuelAmount());
		skillPilot.setText("Pilot Skill: "+ player.getPilotSkill());
		skillTrader.setText("Trader Skill: "+ player.getTraderSkill());
		skillEngineer.setText("Engineer Skill: " + player.getEngineerSkill());
		skillFighter.setText("Fighter Skill: " + player.getFighterSkill());
		shipLocation.setText("Location: x = " + player.getShip().getLocation().x + " y = " + player.getShip().getLocation().y);
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
	 */
	private class MouseOver extends MouseAdapter{
		/**
		 * Detects when a Planet has been clicked.
		 * if the planet clicked is in the range of the ship, 
		 * it will travel there and open the trade window. 
		 * If not it will tell the user that the planet is not
		 *  in range.
		 * @param m the event corresponding to when the mouse is pressed
		 */
		public void mousePressed(MouseEvent m){
			Point point = m.getPoint();
			for(Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(point)){
					int choice = JOptionPane.showConfirmDialog(null, "Would you like to travel to this planet?", "Travel", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION){
						if( gc.canTravelToPlanet( planet )) {
							new RandomEvent(gc);
							fuelLevel.setText("Fuel level: " + player.getShip().getFuelAmount());
							shipLocation.setText("Location: x = " + player.getShip().getLocation().x + " y = " + player.getShip().getLocation().y);
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
	private class MouseMove implements MouseMotionListener{

		@Override
		/**
		 * Checks to see whether the cursor is hovering over
		 * a planet and displays the planet's corresponding
		 * name if it is doing so.
		 */
		public void mouseMoved(MouseEvent m) {
			Point mPoint = m.getPoint();
			move(mPoint);
			for (Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(mPoint)){
					int size = planet.getSize();
					Point pPos = planet.getPosition();
					planetName.setBounds(
							(int)pPos.getX() - size,
							(int)pPos.getY() - size,
							100, 
							15
							);
					planetName.setText(planet.getName());
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	/** 
	 * Draws the ship, rotates it and moves it to 
	 * the specified point. 
	 */
	public void move(Point p){
		double x = player.getShip().getLocation().x,
				y = player.getShip().getLocation().y;
		
		Graphics2D g2d;
		Image shipIcon = null;
		double 	dx = p.x - x, 
				dy = p.y - y;
		double degree = Math.atan(dy/dx);
		AffineTransform at = new AffineTransform();
		at.rotate(degree);
		at.translate(p.x, p.y);
		
		BufferedImage bShipIcon = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		g2d = bShipIcon.createGraphics();
		g2d.drawImage(shipIcon, at, null);
	}
}