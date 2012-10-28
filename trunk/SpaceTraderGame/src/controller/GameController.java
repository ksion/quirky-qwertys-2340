package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Player;

import view.MapWindow;
import view.PlayerWindow;

/**
 * Controls the game flow
 * @author Quirky Qwertys
 * @version 1.0 
 */
public class GameController {
	
	/**
	 * Main method to start the game
	 * @param args
	 */
	public static void main(String[] args){
		//launch the player creation window;
				
		GameController gc = new GameController();
		gc.start();		
	}
	
	
	/**
	 * Starts a new Space Traders game.
	 */
	public void start(){
		newGame();	
	}
	
	
	/**
	 * creates the player window and the map window.
	 */
	public void newGame(){
		final PlayerWindow playerWin;
			
		try {
			playerWin = new PlayerWindow();
			playerWin.setCreateListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Create Player");
					playerWin.setVisible(false);
					Player newPlayer = playerWin.getNewPlayer();
					showMap();
					
				}
				
			});
			playerWin.launch();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	
	/**
	 * Shows a map of the Planets/SolarSystems in the Universe.
	 */
	public void showMap(){
		MapWindow mapWin = new MapWindow();
		mapWin.launch();	
	}
}