package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
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
	private JTable table;
	private Ship ship;

	/**
	 * Create the panel.
	 */
	public ShipyardWindow(Player p) {
		
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
		
		JLabel currFuelLbl = new JLabel(Integer.toString(ship.getFuelAmount()) + " tons / " + 
				                        Integer.toString(ship.getFuelCapacity()) + " tons");
		panel.add(currFuelLbl, "cell 0 5");
		
		// Maximum amount of fuel a player can buy
		int maxFuel = ship.getFuelCapacity() - ship.getFuelAmount();
		
		// Array of possible tons of fuel that can be bought
		Integer[] fuelQuantity = new Integer[maxFuel];
		for (int i = 0; i < maxFuel; i++){
			fuelQuantity[i] = i + 1;
		}
		
		SpinnerListModel spinnerModel = new SpinnerListModel(fuelQuantity);
		
		JButton btnBuy = new JButton("Buy");
		panel.add(btnBuy, "cell 1 5");
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setPreferredSize(new Dimension(30,30));
		panel.add(spinner, "cell 2 5 2 1");
		
		JLabel lblCreditsAvailable = new JLabel("Credits Available: " + p.getMoney());
		panel.add(lblCreditsAvailable, "cell 0 6");
		
		JLabel lblCostCrton = new JLabel("Fuel Cost: 100 cr./ton of fuel");
		panel.add(lblCostCrton, "cell 2 6");

	}
	
}
