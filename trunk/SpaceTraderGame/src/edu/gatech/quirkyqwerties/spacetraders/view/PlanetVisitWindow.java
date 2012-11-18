// $codepro.audit.disable numericLiterals
/**
 * PlanetVisitWindow.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


//import view.TradeWindow.InventoryTableModel;



import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;
import edu.gatech.quirkyqwerties.spacetraders.model.Planet;
import edu.gatech.quirkyqwerties.spacetraders.model.Player;

/**
 * Class to display various game screens once a new game is created
 * @author Quirky Qwertys
 * @version 1.0
 *
 */
public class PlanetVisitWindow extends BackgroundPanel{
	
	/** serialize version */
	private static final long serialVersionUID = 1L;
	
	/** game controller instance */
	private final GameController gc;
	
	/** the menu panel */
	private final JPanel menuPanel;
	
	/** the container for styling */
	private final JPanel planetContainerPanel;
	
	/** planet panel */
	private final JPanel planetPanel;
	
	/** buttons on the window */
	private final JButton btnMap, btnMarket, btnShipyard, 
	btnSaveGame, btnLocalDock, btnNewGame;
	
	/** the card layout instance */
	private final CardLayout deck;
	
	/** instance of the shipyard window */
	private final ShipyardWindow shipyardWin;
	
	/** instance of the trade window */
	private final TradeWindow tradeWin;
	
	/** background image for the window */
	private Image backgroundImg;
	
	/** map window instance */
	private final MapWindow mapWin;
	
	/** labels */
	private final JLabel planetStatsName, planetStatsName2, planetStatsTechLevel;
	
	/** creates the window 
	 * @param gc 
	 * @throws IOException
	 */
	public PlanetVisitWindow(GameController gc) throws IOException{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
		super((Image) null);
		this.gc = gc;
		
		planetStatsName = new JLabel();
		planetStatsName.setOpaque(false);
		planetStatsName2 = new JLabel();
		planetStatsName2.setOpaque(false);
		planetStatsTechLevel = new JLabel();
		planetStatsTechLevel.setOpaque(false);
		shipyardWin = new ShipyardWindow(gc);
		tradeWin = new TradeWindow(gc);
		mapWin = new MapWindow(gc);
		deck = new CardLayout();
		setLayout(new BorderLayout());
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

		menuPanel.setBackground( new Color( 0x66, 0x66, 0x66, 175 ) );
		menuPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(
				new Color(0x5d, 0xdf, 0xfb, 255), 2, true), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		btnMap = new JButton("Map");
		btnMap.setOpaque(false);
		btnMap.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PlanetVisitWindow.this.gc.showMap();
				
			}
			
		});
		
		btnLocalDock=new JButton("Local Dock");
		btnLocalDock.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PlanetVisitWindow.this.gc.showPlanet();
				
			}
			
		});
		btnMarket = new JButton("Marketplace");
		final Dimension btnSize = btnMarket.getPreferredSize();
		btnMap.setPreferredSize(btnSize);
		btnMarket.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetVisitWindow.this.gc.gotoMarketPlace();
				
			}
			
		});
		btnShipyard = new JButton("Shipyard");
		btnShipyard.setPreferredSize(btnSize);

		btnShipyard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetVisitWindow.this.gc.gotoShipyard();
				
			}
						
		});
		
		btnSaveGame = new JButton("Save");
		btnSaveGame.addActionListener(new SaveListener());
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				PlanetVisitWindow.this.gc.showStart();
				
			}
			
		});
				
		menuPanel.add(planetStatsName);
		menuPanel.add(planetStatsName2);
		menuPanel.add(planetStatsTechLevel);
		menuPanel.add(Box.createVerticalGlue());
		
		menuPanel.add(btnLocalDock);
		menuPanel.add(btnMap);
		menuPanel.add(btnMarket);
		menuPanel.add(btnShipyard);
		menuPanel.add(btnSaveGame);
		menuPanel.add(btnNewGame);
		menuPanel.add(Box.createVerticalStrut(130));
		add(menuPanel, BorderLayout.WEST);
		planetContainerPanel = new JPanel(deck);
		planetContainerPanel.setOpaque(false);
		add(planetContainerPanel, BorderLayout.CENTER);
		
		planetPanel = new JPanel();
		planetPanel.setOpaque(false);
				
		planetContainerPanel.add(planetPanel, "bluegreen");
		
		planetContainerPanel.add(tradeWin, "trade");
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
		deck.show(planetContainerPanel, name);
	}
	
	/**
	 * adds a panel to the card layout of planetContainerPanel
	 * @param panel JPanel to add
	 * @param name to reference the JPanel
	 */
	public void addToContainer(JPanel panel, String name){
		planetContainerPanel.add(panel, name);
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
 * empty needed for the flex json serializer
 * @param p the player
 */
public void setPlayer(Player p){ // $codepro.audit.disable emptyMethod
	
}

/**
 * overrides the toString
 * @return a string for the planetvisitwindow
 */
public String toString(){
	return "PlanetVisitWindow";
}

/**
 * SaveListener inner class for the save button
 * @author Quirky Qwertys
 *
 */
private class SaveListener implements ActionListener{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
	
	/**
	 * action performed upon event
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			gc.save();
			JOptionPane.showMessageDialog(getTopLevelAncestor(), 
					"Game saved successfully!");
			
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(getTopLevelAncestor(), 
					"Unable to save the game.");
		} 
	}
}

/**
 * sets the Planet instance
 * @param planet
 */
public void setPlanet(Planet planet) {
	backgroundImg = planet.getLargePicName();
	planetStatsName.setForeground(new Color(0x5d, 0xdf, 0xfb, 255));
	planetStatsName.setFont(Style.ARIAL_NORMAL);
	planetStatsName2.setForeground(new Color(0x5d, 0xdf, 0xfb, 255));
	planetStatsName2.setFont(Style.ARIAL_NORMAL);
	planetStatsName.setText("Welcome to ");
	planetStatsName2.setText("Planet " + planet.getName() + ".");
	planetStatsTechLevel.setFont(Style.ARIAL_NORMAL);
	planetStatsTechLevel.setForeground(new Color(0x5d, 0xdf, 0xfb, 255));
	planetStatsTechLevel.setText("Tech Level: " + planet.getTechLevel());
	setImage(backgroundImg);
	
	
}

/**
 * gets the map window
 * @return the mapWin
 */
public MapWindow getMapWin() {
	
	return mapWin;
}




}
