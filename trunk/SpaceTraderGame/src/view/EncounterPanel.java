package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import model.Player;
import model.Pirate;
import controller.GameController;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.awt.event.*;

/**
 * Represents the window that pops up if a pirate encounter
 * is generated.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.11.12
 */
public class EncounterPanel extends JFrame implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pirate pirate = new Pirate();
	private Player player;
	private JLabel damageInfo;
	private JLabel yourDamagePoints;
	private JLabel pirateDamagePoints;
	private JButton btnFlee;
	private Random random = new Random();
	private JPanel panel;
	private GameController gc;
	
	/**
	 * Create the panel.
	 */
	public EncounterPanel(GameController gc) {
		this.gc = gc;
		this.player = gc.getPlayer();
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][grow][][][][grow][]"));
		
		JLabel message = new JLabel("You've encountered a pirate! You may fight or flee the pirate." +
									" If you attack, ");
		panel.add(message, "cell 0 0 3 1");
		
		JLabel message2 = new JLabel("keep in mind that ships can only take up to 100 points of damage " +
										  " before");
		panel.add(message2, "cell 0 1 3 1");
		
		JLabel message3 = new JLabel("they are destroyed. If you flee, you may lose some credits.");
		panel.add(message3, "cell 0 2 3 1");
		
		JLabel label = new JLabel("");
		panel.add(label, "cell 0 3 3 1");
		
		yourDamagePoints = new JLabel(Integer.toString(player.getShip().getDamageSustained()));
		panel.add(yourDamagePoints, "cell 1 5");
		
		JLabel yourDamage = new JLabel("Your ship's damage:");
		panel.add(yourDamage, "flowx,cell 0 5");
		
		pirateDamagePoints = new JLabel("0");
		panel.add(pirateDamagePoints, "cell 1 6");
		
		JLabel pirateDamage = new JLabel("Pirate ship's damage:" );
		panel.add(pirateDamage, "cell 0 6");		
		
		damageInfo = new JLabel("");
		panel.add(damageInfo, "cell 0 8 3 1");
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new AttackListener());
		panel.add(btnAttack, "cell 0 10,alignx center,aligny center");
		
	    btnFlee = new JButton(" Flee ");
		btnFlee.addActionListener(new FleeListener());
		panel.add(btnFlee, "cell 2 10,alignx center");
		
		getContentPane().add(panel);
		setTitle("Pirate Encounter");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(new Dimension(510, 300));
		pack();
		setVisible(true);

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
		 */
		public void actionPerformed(ActionEvent event){
			int playerDamage = player.getShip().getDamageSustained();
			int pirateDamage = pirate.getShip().getDamageSustained();
			if (playerDamage < 100 && pirateDamage < 100){
				int currDamage = pirate.getShip().getDamageSustained();
				player.getShip().attack(pirate.getShip());
				damageInfo.setText("You dealt " + Integer.toString(pirate.getShip().getDamageSustained() 
					               - currDamage) + " points of damage to the pirate's ship!");
				pirateDamagePoints.setText(Integer.toString(pirate.getShip().getDamageSustained()));
				pirate.attack(player.getShip());
				yourDamagePoints.setText(Integer.toString(player.getShip().getDamageSustained()));
				if (player.getShip().getDamageSustained() >= 100){
					JOptionPane.showMessageDialog(null, "Your ship has been destroyed and you've lost all items. GAME OVER!");
					gc = new GameController();
					dispose();
				}
				else if (pirate.getShip().getDamageSustained() >= 100){
					int money = random.nextInt((int) (player.getMoney() *.20)) ;
					player.setMoney(money);
					JOptionPane.showMessageDialog(null, "You just gained " + money + " credits.");
					dispose();
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
		 * can lose up to 20% of his/her credits when t flee.
		 */
		public void actionPerformed(ActionEvent event){
			if (player.getMoney() != 0){
				int money = random.nextInt((int) (player.getMoney() *.20)) ;
				player.setMoney(-1 * money);
				JOptionPane.showMessageDialog(null, "You just lost " + money + " credits.");
				dispose();
			}
		}
	}
}