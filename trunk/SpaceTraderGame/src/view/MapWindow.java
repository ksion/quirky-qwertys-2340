package view;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
	
	/** Represents the universe in Space Trader game. */
	private Universe universe;
	
	/**
	 * Creates the panel.
	 */
	public MapWindow(){
		universe = new Universe();
	}
	
	/**
	 * Paints all of the SolarSystems and planets.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image background = null;
		try {
			background = ImageIO.read(new File("src/view/starsBackground.jpeg"));
		} 
		catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, null);
		ArrayList<SolarSystem> systems = universe.getSystems();
		SolarSystem solarSystem = systems.get(0);
		for(Planet p : solarSystem.getPlanets()){
			p.draw(g);
		}
	}
	
	/**
	 * Main method creates the navigation window.
	 */
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new MapWindow());
		frame.pack();
	}
}