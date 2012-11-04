package view;

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.BorderLayout;
import java.util.List;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import model.Player;
import model.Ship;

/**
 * Represents the shipyard where the user may trade 
 * his/her old ship for a new a new one. Fuel can also
 * be bought in the shipyard.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.05.12
 */
public class ShipyardWindow extends JPanel {
	
	private Ship ship;
	private Player player;
	
	private JTable table;
	private JButton btnBuy;
	private JFormattedTextField ftf;
	private JSpinner spinner;
	private JLabel currFuelLbl;
	private JLabel lblCreditsAvailable;
	
	/** Maximum amount of fuel a player can buy. */
	private int maxFuel;
	
	/** Price of fuel per ton. */
	private final int PRICE = 100;

	/**
	 * Create the panel.
	 */
	public ShipyardWindow(Player p) {
		player = p;
		ship = p.getShip();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][grow][][][grow][]"));
		
		JLabel lblWelcomeToThe = new JLabel("You may trade your ship for a new one or buy fuel here.");
		panel.add(lblWelcomeToThe, "cell 0 0 3 1");
		
		JLabel lblYourShip = new JLabel("Your Ship: " + ship.getName());
		panel.add(lblYourShip, "cell 0 1");
		
		JLabel lblOtherShips = new JLabel("Ships for Sell");
		panel.add(lblOtherShips, "cell 2 1");
		
		JLabel shipLbl = new JLabel("Cost of your ship: " + ship.getCost());
		panel.add(shipLbl, "cell 0 2");
		
		JButton btnTrade = new JButton("Trade");
		panel.add(btnTrade, "cell 1 2");
		
		table = new JTable();
		panel.add(table, "cell 2 2,grow");
		
		JLabel lblCurrentFuelAmount = new JLabel("Current Fuel Amount");
		panel.add(lblCurrentFuelAmount, "cell 0 4 2 1");
		
		JLabel lblFuelForSell = new JLabel("Qty. Fuel to Buy");
		panel.add(lblFuelForSell, "cell 2 4");
		
		currFuelLbl = new JLabel(Integer.toString(ship.getFuelAmount()) + " tons / " + 
				                        Integer.toString(ship.getFuelCapacity()) + " tons");
		currFuelLbl.setVerticalAlignment(SwingConstants.TOP);
		panel.add(currFuelLbl, "cell 0 5");
		
		maxFuel = ship.getMaxFuel();
		
		// Array of possible tons of fuel that can be bought
		Integer[] fuelQuantity = new Integer[maxFuel];
		for (int i = 0; i < maxFuel; i++){
			fuelQuantity[i] = i + 1;
		}
		
		SpinnerListModel spinnerModel = new SpinnerListModel(fuelQuantity);
		
		btnBuy = new JButton(" Buy ");
		panel.add(btnBuy, "cell 1 5");
		btnBuy.addActionListener(new BuyListener());
		
		spinner = new JSpinner(spinnerModel);
		JComponent editor = spinner.getEditor();
		
		// Change the size of the spinner's text field 
		ftf = ((JSpinner.DefaultEditor)editor).getTextField();
		ftf.setColumns(4);
		
		panel.add(spinner, "cell 2 5 2 1");
		
		lblCreditsAvailable = new JLabel("Credits Available: " + p.getMoney());
		panel.add(lblCreditsAvailable, "cell 0 6");
		
		JLabel lblCostCrton = new JLabel("Fuel Cost: 100 cr./ton of fuel");
		panel.add(lblCostCrton, "cell 2 6");

	}
	
	/**
	 * Updates the buttons, which can be enabled or disabled 
	 * depending on whether or not the player has enough credits
	 * to purchase a ship or fuel. Labels are also updated after
	 * a purchase.
	 */
	private void update(){
		// If the tank of player's ship is full or if the player
		// has no money disable "Buy" button
		if (ship.getMaxFuel() == 0 || player.getMoney() == 0)
			btnBuy.setEnabled(false);
		else
			btnBuy.setEnabled(true);
		lblCreditsAvailable.setText("Credits Available: " + 
			                        Integer.toString(player.getMoney()));
		currFuelLbl.setText(Integer.toString(ship.getFuelAmount()) + " tons / " + 
                Integer.toString(ship.getFuelCapacity()) + " tons");
	}
	
	/**
	 * Listener used to update amount of credits available and fuel
	 * available for sell when the user buys fuel.
	 */
	private class BuyListener implements ActionListener{
		
		/**
		 * When the "Buy" button is clicked the ship's current
		 * amount of fuel and the player's credits  are updated.
		 */
		public void actionPerformed(ActionEvent event){
			int fuelSell = Integer.parseInt(ftf.getText());
			int totalFuelPrice = fuelSell * PRICE;
			System.out.println(player.getMoney() + "vs " + totalFuelPrice);
			if (ship.getMaxFuel() >= fuelSell && totalFuelPrice <= player.getMoney()){
				player.setMoney(-1 * totalFuelPrice);
				ship.setFuelAmount(ship.getFuelAmount() + fuelSell);	
			}
			update();
		}
	}
}
