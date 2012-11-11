package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//import view.TradeWindow.InventoryTableModel;

import model.Planet;
import model.Player;

import controller.GameController;

public class PlanetVisitWindow extends JPanel{
	
	private GameController gc;
	private JPanel menuPanel;
	private JPanel planetContainerPanel;
	private BackgroundPanel planetPanel;
	private JButton btnMap, btnMarket, btnShipyard, btnSaveGame;
	private CardLayout deck;
	private ShipyardWindow shipyardWin;
	private TradeWindow tradeWin;
	private Image starsImg;
	private Player player;
	private Planet planet;
	private MapWindow mapWin;
	
	public PlanetVisitWindow(GameController gc) throws IOException{
		this.gc = gc;
		
		
		shipyardWin = new ShipyardWindow(gc);
		tradeWin = new TradeWindow(gc);
		mapWin = new MapWindow(gc);
		deck = new CardLayout();
		setLayout(new BorderLayout());
		menuPanel = new JPanel();
		BoxLayout bl = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(bl);
		Color transBlue = new Color(0,0,255,125);
		menuPanel.setBackground(transBlue);
		
		//menuPanel.setOpaque(false);
		btnMap = new JButton("Map");
		//btnMap.setForeground(Color.WHITE);
		//btnMap.setBackground(transBlue);
		
		//Border thickBorder = new LineBorder(Color.BLUE, 2);
		//btnMap.setBorder(thickBorder);
		btnMap.setOpaque(false);
		
		btnMap.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PlanetVisitWindow.this.gc.showMap();
				
			}
			
		});
		btnMarket = new JButton("Marketplace");
		Dimension btnSize = btnMarket.getPreferredSize();
		btnMap.setPreferredSize(btnSize);
		//btnMarket.setForeground(Color.WHITE);
		//btnMarket.setBackground(transBlue);
		//btnMarket.setContentAreaFilled( false );
		//btnMarket.setBorder(thickBorder);
		//btnMarket.setOpaque(false);
		btnMarket.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetVisitWindow.this.gc.gotoMarketPlace();
				
			}
			
		});
		btnShipyard = new JButton("Shipyard");
		btnShipyard.setPreferredSize(btnSize);
		//btnShipyard.setForeground(Color.WHITE);
		btnShipyard.setBackground(transBlue);
		//btnShipyard.setBorder(thickBorder);
		btnShipyard.setOpaque(false);
		btnShipyard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetVisitWindow.this.gc.gotoShipyard();
				
			}
			
			
		});
		
		btnSaveGame = new JButton("Save and Exit");
		btnSaveGame.addActionListener(new SaveListener());
		
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		menuPanel.add(btnMap);
		menuPanel.add(btnMarket);
		menuPanel.add(btnShipyard);
		menuPanel.add(btnSaveGame);
		menuPanel.add(Box.createVerticalStrut(130));
		add(menuPanel,BorderLayout.WEST);
		planetContainerPanel = new JPanel(deck);
		planetContainerPanel.setOpaque(false);
		add(planetContainerPanel,BorderLayout.CENTER);
		try{
			Image img = ImageIO.read(getClass().getResource("/view/blueGreenPlanet.png"));
			starsImg = ImageIO.read(getClass().getResource("/view/starsBackground.jpeg"));
			planetPanel = new BackgroundPanel(img, BackgroundPanel.ACTUAL);
			planetPanel.setOpaque(false);
			planetContainerPanel.add(planetPanel, "bluegreen");
			//deck.show(planetContainerPanel,"bluegreen");
		}
		catch(IOException ex){}
		
		planetContainerPanel.add(tradeWin,"trade");
		planetContainerPanel.add(shipyardWin, "shipyard");
		planetContainerPanel.add(mapWin, "map");
		
		
		
		
		
		
		
	}
	
	
	/**
	 * gets the deck
	 * @return deck of the container panel
	 */
	public CardLayout getDeck() {
		return deck;
	}


/**
 * displays the appropriate panel from the deck
 * @param name of panel
 */
	public void showCard(String name) {
		deck.show(planetContainerPanel,name);
	}
	
	/**
	 * adds a panel to the card layout of planetContainerPanel
	 * @param panel JPanel to add
	 * @param name to reference the JPanel
	 */
	public void addToContainer(JPanel panel, String name){
		planetContainerPanel.add(panel,name);
	}
	



	/**
	 * paint method to paint the stars
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(starsImg, 0, 0, null);
		
	}

/**
 * returns the instance of the Shipyard Window
 * @return ShipyardWindow
 */
	public ShipyardWindow getShipyardWin() {
		
		return shipyardWin;
	}

	/**
	 * returns the instance of the Trade Window
	 * @return TradeWindow
	 */
public TradeWindow getTradeWindow() {
	return tradeWin;
}

/**
 * sets the Player instance
 * @param p the player
 */
public void setPlayer(Player p){
	player = p;
	
}

/**
 * SaveListener inner class for the save button
 * @author Quirky Qwertys
 *
 */
private class SaveListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileWriter("src/model/Player.txt"));
			output.write(player.toString());
			output.write(player.getShip().toString());
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		finally {
			output.close();
		}
		System.exit(0);
	}
}

/**
 * sets the Planet instance
 * @param planet
 */
public void setPlanet(Planet planet) {
	this.planet = planet;
	
}

/**
 * gets the map window
 * @return the mapWin
 */
public MapWindow getMapWin() {
	
	return mapWin;
}

}
