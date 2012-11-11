package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

import model.Player;
import model.Pirate;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;

/**
 * Represents the window that pops up if a pirate encounter
 * is generated.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.11.12
 */
public class EncounterPanel extends JPanel {

	private Pirate pirate = new Pirate();
	private Player player;
	private JLabel damageInfo;
	private JLabel yourDamagePoints;
	private JLabel pirateDamagePoints;
	private JButton btnFlee;
	
	/**
	 * Create the panel.
	 */
	public EncounterPanel(Player player) {
		this.player = player;
		
		setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][grow][][][grow][]"));
		
		JLabel message = new JLabel("You've encountered a pirate! You may fight or flee the pirate." +
									" If you attack, ");
		add(message, "cell 0 0 3 1");
		
		JLabel message2 = new JLabel("keep in mind that ships can only take up to 100 points of damage " +
										  " before");
		add(message2, "cell 0 1 3 1");
		
		JLabel message3 = new JLabel("they are destroyed. If you flee, you may lose some credits.");
		add(message3, "cell 0 2 3 1");
		
		JLabel yourDamage = new JLabel("Your ship's damage:");
		add(yourDamage, "flowx,cell 0 4 3 1");
		
		JLabel pirateDamage = new JLabel("Pirate ship's damage:" );
		add(pirateDamage, "cell 0 5 3 1");		
		
		yourDamagePoints = new JLabel("0");
		add(yourDamagePoints, "cell 1 4");
		
		pirateDamagePoints = new JLabel("0");
		add(pirateDamagePoints, "cell 1 5");
		
		damageInfo = new JLabel("");
		add(damageInfo, "cell 0 7 3 1");
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new AttackListener());
		add(btnAttack, "cell 0 9,alignx center,aligny center");
		
	    btnFlee = new JButton(" Flee ");
		btnFlee.addActionListener(new FleeListener());
		add(btnFlee, "cell 2 9,alignx center");
		
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
			
			btnFlee.setEnabled(false);
			
			if (player.getShip().getDamageSustained() < 100){
				int currDamage = pirate.getShip().getDamageSustained();
				player.getShip().attack(pirate.getShip());
				damageInfo.setText("You dealt " + Integer.toString(pirate.getShip().getDamageSustained() 
					               - currDamage) + " points of damage to the pirate's ship!");
				pirateDamagePoints.setText(Integer.toString(pirate.getShip().getDamageSustained()));
				pirate.attack(player.getShip());
				yourDamagePoints.setText(Integer.toString(player.getShip().getDamageSustained()));
			}
			else{
			    damageInfo.setText("Your ship has been destroyed and you've lost all items!");
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
		 * can lose up to 40% of his/her credits when they flee.
		 */
		public void actionPerformed(ActionEvent event){
			Random random = new Random();
			int money = random.nextInt((int) (player.getMoney() *.40)) ;
			player.setMoney(-1 * money);
			damageInfo.setText("You just lost " + money + " credits.");
		}
	}
}