package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.GameController;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import model.Planet;
import model.Player;
import model.SolarSystem;
import model.Universe;

/**
 * Represents the navigation window for the Space Trader game.
 * 
 * @author Quirky Qwertys
 * @version 1.0 10.22.12
 */
public class MapWindow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GameController gc;
	protected JLabel planetName;
	protected JLabel fuelLevel, skillPilot, skillTrader, skillEngineer, skillFighter;
	protected Player player;
	
	/** Represents the universe in Space Trader game. */
	private Universe universe = new Universe();
	ArrayList<SolarSystem> systems = universe.getSystems();
	SolarSystem solarSystem = systems.get(0);
	
	/**
	 * I also added a player parameter to the constructor so that the travel method for the ship from the game controller can be called.
	 * Creates the panel.
	 * @param p the player from the game controller
	 */
	public MapWindow(){
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

		fuelLevel = Style.createNormalLabel();
		skillPilot = Style.createNormalLabel();
		skillTrader = Style.createNormalLabel();
		skillEngineer = Style.createNormalLabel();
		skillFighter = Style.createNormalLabel();

		labelPanel.add(fuelLevel);
		labelPanel.add(skillPilot);
		labelPanel.add(skillTrader);
		labelPanel.add(skillEngineer);
		labelPanel.add(skillFighter);

		add(labelPanel, BorderLayout.SOUTH);
		
	
	}
	
	/**
	 * Paints all of the SolarSystems and Planets.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image background = null;
		try {
			background = ImageIO.read(new File("src/view/starsBackground.jpeg"));
		} 
		catch (IOException e){
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, null);
		
		for(Planet p : solarSystem.getPlanets()){
			p.draw(g);
		}
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
	
	public void launch(final GameController gc){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
			
				try{
					MapWindow.this.gc = gc;
					JFrame frame = new JFrame(solarSystem.getName() + " System");
					frame.setPreferredSize(new Dimension(800, 450));
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setFocusableWindowState(true);
					frame.setLocationByPlatform(true);
			
					frame.add(MapWindow.this);
					frame.pack();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}
	
	
	/**
	 * sets the player
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		fuelLevel.setText("Fuel level: " + player.getShip().getFuelAmount());
		skillPilot.setText("Pilot Skill: "+ player.getPilotSkill());
		skillTrader.setText("Trader Skill: "+ player.getTraderSkill());
		skillEngineer.setText("Engineer Skill: " + player.getEngineerSkill());
		skillFighter.setText("Fighter Skill: " + player.getFighterSkill());
	}



	/**
	 * Creates an Adapter to catch where the mouse is pressed on
	 * the map.
	 */
	private class MouseOver extends MouseAdapter{
		/**
		 * Detects when a Planet has been clicked.
		 * if the planet clicked is in the range of the ship, it will travel there and open the trade window. 
		 * If not it will tell the user that the planet is not in range.
		 * @param m the event corresponding to when the mouse is pressed
		 */
		public void mousePressed(MouseEvent m){
			Point point = m.getPoint();
			for(Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(point)){
					int choice = JOptionPane.showConfirmDialog(null, "Would you like to travel to this planet?", "Travel", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION){
						boolean b = player.getShip().travel(planet);
						if (b){
							planet.createInventory();
							new PlanetWindow(planet,gc);	
						}
					}
						
				}
			}
		}
	}
	
	/**
	 * this class listens for mouse movements. As of now all it does is display the name 
	 * of the planet when the mouse is over it
	 * @author Hayden
	 *
	 */
	private class MouseMove implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent m) {
			Point mPoint = m.getPoint();
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
	}
}