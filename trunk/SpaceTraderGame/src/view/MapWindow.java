package view;

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
	
	/** Represents the universe in Space Trader game. */
	private Universe universe = new Universe();
	ArrayList<SolarSystem> systems = universe.getSystems();
	SolarSystem solarSystem = systems.get(0);
	
	/**
	 * Creates the panel.
	 */
	public MapWindow(){
		addMouseListener(new MouseOver());
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
					JFrame frame = new JFrame();
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
	 * Creates an Adapter to catch where the mouse is pressed on
	 * the map.
	 */
	private class MouseOver extends MouseAdapter{
		/**
		 * Detects when a Planet has been clicked.
		 * 
		 * @param m the event corresponding to when the mouse is pressed
		 */
		public void mousePressed(MouseEvent m){
			Point point = m.getPoint();
			for(Planet planet: solarSystem.getPlanets()){
				if(planet.inRange(point)){
					planet.createInventory();
					new PlanetWindow(planet,gc);		
				}
			}
		}
	}
}