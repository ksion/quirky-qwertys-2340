package view;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import edu.gatech.quirkyqwerties.spacetraders.model.*;
import edu.gatech.quirkyqwerties.spacetraders.controller.*;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.awt.event.*;
import java.io.IOException;

/**
 * Represents the window that pops up if a pirate encounter
 * is generated.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.11.12
 */
public class EncounterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Pirate pirate;
	private Player player;
	private JLabel damageInfo;
	private JLabel yourDamagePoints;
	private JLabel pirateDamagePoints;
	private JButton btnFlee;
	private Random random = new Random();
	private GameController gc;
	Image background;
	
	/**
	 * Create the panel.
	 */
	public EncounterPanel(GameController gc) {
		this.gc = gc;
		
		try {
			background =ImageIO.read(getClass().getResource("/view/pirateflag.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout(0, 0));
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x66,0x66,0x66,125));
		panel.setBorder(new LineBorder(Color.RED, 2,true));
		//tablePanel.setOpaque(false);
		add(panel, BorderLayout.CENTER);
		
		
	
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][grow][][][][grow][]"));
		
		JLabel message = Style.createRedLabel();
		message.setText("You've encountered a pirate! You may fight or flee the pirate." +
									" If you attack, ");
		panel.add(message, "cell 0 0 3 1");
		
		JLabel message2 = Style.createRedLabel();
		message2.setText("keep in mind that once damage points exceed a ship's hull strength," +
										  " the");
		panel.add(message2, "cell 0 1 3 1");
		
		JLabel message3 = Style.createRedLabel();
		message3.setText("ship may be destroyed. If you flee, you may lose some credits.");
		panel.add(message3, "cell 0 2 3 1");
		
		JLabel label = new JLabel("");
		panel.add(label, "cell 0 3 3 1");
		
		yourDamagePoints = Style.createRedLabel();
		panel.add(yourDamagePoints, "cell 1 5");
		
		JLabel yourDamage = Style.createRedLabel();
		yourDamage.setText("Your ship's damage:");
		panel.add(yourDamage, "flowx,cell 0 5");
		
		pirateDamagePoints = Style.createRedLabel();
		pirateDamagePoints.setText("0");
		panel.add(pirateDamagePoints, "cell 1 6");
		
		JLabel pirateDamage = Style.createRedLabel();
		pirateDamage.setText("Pirate ship's damage:" );
		panel.add(pirateDamage, "cell 0 6");		
		
		damageInfo = Style.createRedLabel();
		panel.add(damageInfo, "cell 0 8 3 1");
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new AttackListener());
		panel.add(btnAttack, "cell 0 10,alignx center,aligny center");
		
	    btnFlee = new JButton(" Flee ");
		btnFlee.addActionListener(new FleeListener());
		panel.add(btnFlee, "cell 2 10,alignx center");
		
		
	}
	
	/**
	 * Allows the user to attack a pirate ship. Once the
	 * user attacks, the pirate retaliates. Also, the user
	 * loses the option to escape.
	 * 
	 * @author Quirky Qwertys
	 */
	private class AttackListener implements ActionListener{
		
		/**
		 * When the user attacks, damage is dealt to the 
		 * pirate's ship. The pirate immediately attacks.
		 * If either ship gets 100 points worth of damage
		 * or more, the encounter ends and the player may
		 * gain/lose.
		 */
		public void actionPerformed(ActionEvent event){
			int playerDamage = player.getShip().getDamageSustained();
			int pirateDamage = pirate.getShip().getDamageSustained();

			if ((playerDamage < player.getShip().getHullStrength() && 
				pirateDamage < pirate.getShip().getHullStrength())){
				int currDamage = pirate.getShip().getDamageSustained();
				player.getShip().attack(pirate.getShip());
				damageInfo.setText("You dealt " + Integer.toString(pirate.getShip().getDamageSustained() 
					               - currDamage) + " points of damage to the pirate's ship!");
				pirateDamagePoints.setText(Integer.toString(pirate.getShip().getDamageSustained()));
				pirate.attack(player.getShip());
				yourDamagePoints.setText(Integer.toString(player.getShip().getDamageSustained()));
				repaint();
				if (player.getShip().getDamageSustained() >= player.getShip().getHullStrength()){
					JOptionPane.showMessageDialog(null, "Your ship has been destroyed and you've lost all items. GAME OVER!");
					gc.showStart();
					
				}
				else if (pirate.getShip().getDamageSustained() >= pirate.getShip().getHullStrength()){
					int money = random.nextInt((int) (player.getMoney() *.20)) ;
					player.addMoney(money);
					JOptionPane.showMessageDialog(null, "You just gained " + money + " credits.");
					
					gc.showPlanet();					
				}
			}	
		}
	}
	
	/**
	 * Allows the user to flee from a pirate.
	 * 
	 * @author Quirky Qwertys
	 */
	private class FleeListener implements ActionListener{
		
		/**
		 * Ends the user's encounter with the pirate. The user
		 * can lose up to 20% of his/her credits when he/she flees.
		 */
		public void actionPerformed(ActionEvent event){
			if (player.getMoney() != 0){
				int money = random.nextInt((int) (player.getMoney() *.20)) ;
				player.addMoney(-1 * money);
				JOptionPane.showMessageDialog(null, "You just lost " + money + " credits.");
				gc.showPlanet();
			}
		}
	}

	/**
	 * Sets the player.
	 * 
	 * @param player the player
	 */
	public void setPlayer(Player player) throws IOException {
		this.player = player;
		pirate = new Pirate();
		damageInfo.setText("");
		pirateDamagePoints.setText(Integer.toString(pirate.getShip().getDamageSustained()) );
		yourDamagePoints.setText(Integer.toString(player.getShip().getDamageSustained()));
	}
	
	/**
	 * Draws the background image on the GUI.
	 * @param g the graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}