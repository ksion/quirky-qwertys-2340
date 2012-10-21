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

public class MapWindow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Planet[] planets = new Planet[5];
	
	/**
	 * Create the panel.
	 */
	public MapWindow() {
		SolarSystem s1 = new SolarSystem();
		for(int i = 0; i < 5; i++){
			s1.add(new Planet());
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image background = null;
		try {
			background = ImageIO.read(new File("src/view/starsBackground.jpeg"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, null);
		for(Planet p: planets){
			p.draw(g);
		}
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new MapWindow());
		frame.pack();
	}
}
