package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import controller.GameController;

import model.Player;
import model.Ship;
import model.Shipyard;
import model.TradableItem;
import net.miginfocom.swing.MigLayout;

/**
 * Represents the shipyard where the user may trade his/her
 * old ship for a new a new one. Fuel can also be bought in
 * the shipyard.
 * 
 * @author Quirky Qwertys
 * @version 1.0 11.05.12
 */
public class ShipyardWindow extends JPanel {

	private GameController controller;
	
	private Ship ship;
	private Player player;
	
	private JTable table;
	private JButton btnBuy;
	private JFormattedTextField ftf;
	private JSpinner spinner;
	private JLabel currFuelLbl;
	private JLabel lblCreditsAvailable;
	private JButton btnTrade;
	private Ship[] ships;
	private JLabel shipLbl;
	private JLabel lblYourShip;
	private ShipTableModel shipTableModel;
	
	/** Maximum amount of fuel a player can buy. */
	private int maxFuel;
	
	/** Price of fuel per ton. */
	private final int PRICE = 100;

	/**
	 * Create the panel.
	 */
	public ShipyardWindow(GameController controller) {
		this.controller = controller;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][grow][][][grow][]"));
		
		JLabel lblWelcomeToThe = new JLabel("You may trade your ship for a new one or buy fuel here.");
		panel.add(lblWelcomeToThe, "cell 0 0 3 1");
		
		lblYourShip = new JLabel();
		panel.add(lblYourShip, "cell 0 1");
		
		//JLabel lblOtherShips = new JLabel("Ships for Sell	              Cost");
		//panel.add(lblOtherShips, "cell 2 1");
		
		shipLbl = new JLabel("");
		panel.add(shipLbl, "cell 0 2");
		
		btnTrade = new JButton("Trade");
		panel.add(btnTrade, "cell 1 2");
		btnTrade.addActionListener(new TradeListener());
		
		
		shipTableModel= new ShipTableModel();
		table = new JTable(shipTableModel);
		panel.add(new JScrollPane(table), "cell 2 2,grow");
		
		JLabel lblCurrentFuelAmount = new JLabel("Current Fuel Amount");
		panel.add(lblCurrentFuelAmount, "cell 0 4 2 1");
		
		JLabel lblFuelForSell = new JLabel("Qty. Fuel to Buy");
		panel.add(lblFuelForSell, "cell 2 4");
		
		currFuelLbl = new JLabel();
		currFuelLbl.setVerticalAlignment(SwingConstants.TOP);
		panel.add(currFuelLbl, "cell 0 5");
		
		
		spinner = new JSpinner();
		JComponent editor = spinner.getEditor();
		
		// Change the size of the spinner's text field 
		ftf = ((JSpinner.DefaultEditor)editor).getTextField();
		ftf.setColumns(4);
		
		panel.add(spinner, "cell 2 5 2 1");
		
		btnBuy = new JButton(" Buy ");
		panel.add(btnBuy, "cell 1 5");
		btnBuy.addActionListener(new BuyListener());
		
		lblCreditsAvailable = new JLabel();
		panel.add(lblCreditsAvailable, "cell 0 6");
		
		JLabel lblCostCrton = new JLabel("Fuel Cost: 100 cr./ton of fuel");
		panel.add(lblCostCrton, "cell 2 6");
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ShipyardWindow.this.controller.showPlanet();
				
			}
			
		});
		add(btnDone, BorderLayout.SOUTH);
		
		
		//update();
	}
	
	/**
	 * Updates the buttons, which can be enabled or disabled 
	 * depending on whether or not the player has enough credits
	 * to purchase a ship or fuel. Labels are also updated after
	 * a purchase.
	 */
	private void update(){
		
		// If the player has no money disable all buttons
		if (player.getMoney() == 0){
			btnTrade.setEnabled(false);
		}
		
		else{
			btnBuy.setEnabled(true);
			btnTrade.setEnabled(true);
		}
		
		// If the tank of player's ship is full disable "Buy" button
		if (ship.getMaxFuel() == 0 || player.getMoney() == 0)
			btnBuy.setEnabled(false);
		
		else
			btnBuy.setEnabled(true);
		lblCreditsAvailable.setText("Credits Available: " + 
			                        Integer.toString(player.getMoney()));
		
		currFuelLbl.setText(Integer.toString(player.getShip().getFuelAmount()) + " tons / " + 
                Integer.toString(player.getShip().getFuelCapacity()) + " tons");
	}
	
	/**
	 * Listener used to update amount of credits available and 
	 * fuel available for sell when the user buys fuel.
	 */
	private class BuyListener implements ActionListener{
		
		/**
		 * When the "Buy" button is clicked the ship's current
		 * amount of fuel and the player's credits  are updated
		 * if the user has enough credits.
		 */
		public void actionPerformed(ActionEvent event){
			int fuelSell = Integer.parseInt(ftf.getText());
			int totalFuelPrice = fuelSell * PRICE;
			if (player.getShip().getMaxFuel() >= fuelSell && totalFuelPrice <= player.getMoney()){
				player.setMoney(-1 * totalFuelPrice);
				ship.setFuelAmount(player.getShip().getFuelAmount() + fuelSell);	
			}
			update();
		}
	}
	
	/**
	 * Listener used to update the player's status after 
	 * he/she has traded the ship.
	 */
	private class TradeListener implements ActionListener{
		/**
		 * If user has enough credits, then the user may 
		 * trade the current ship for a new one and pay the
		 * difference.
		 */
		public void actionPerformed(ActionEvent event){
			int currShipCost = player.getShip().getCost();
			Ship selectedShip = shipTableModel.get(table.getSelectedRow());
			
			if (selectedShip.getCost() <= currShipCost)
				player.setShip(selectedShip);
			
			int totalAssets = currShipCost + player.getMoney();
			if (totalAssets >= selectedShip.getCost()){
				player.setShip(selectedShip);
				if (selectedShip.getCost() - currShipCost <= 0)
					;
				else 
					player.setMoney((selectedShip.getCost() - currShipCost) * -1);
			}
			shipLbl.setText("Cost of your ship: " + player.getShip().getCost());
			lblYourShip.setText("Your Ship: " + player.getShip().getName());	
			update();
		}
	}
	
	private class ShipTableModel extends ListTableModel<Ship>{
		
		public ShipTableModel(){
			super(new String[]{"Ship Type", "Cost"},new ArrayList<Ship>());
					
			
		}

		@Override
		public Object getValueAt(int row, int col) {
			Ship ship = get(row);
			switch(col){
				case 0:return ship.getName();
				case 1: return ship.getCost();
				default: return null;
			}
		}
		
	}
	
	public void setPlayer( Player p ) {
		player = p;
		ship = p.getShip();
		
		lblCreditsAvailable.setText("Credits Available: " + p.getMoney());
		lblYourShip.setText("Your Ship: " + ship.getName());
		shipLbl.setText("Cost of your ship: " + ship.getCost());
		currFuelLbl.setText(Integer.toString(ship.getFuelAmount()) + " tons / " + 
                Integer.toString(ship.getFuelCapacity()) + " tons");
		
		
		maxFuel = player.getShip().getMaxFuel();
		
		// Array of possible tons of fuel that can be bought
		Integer[] fuelQuantity = new Integer[maxFuel];
		for (int i = 0; i < maxFuel; i++){
			fuelQuantity[i] = i + 1;
		}
		
		SpinnerListModel spinnerModel;
		
		// In case, player's just bought/traded for a new ship
		// if window is closed and reopened--that case is handled
		// here
		if (maxFuel == 0){
			spinnerModel = new SpinnerListModel(new Integer[] {0});
		}
		
		else
			spinnerModel = new SpinnerListModel(fuelQuantity);
		
		spinner.setModel(spinnerModel);
		
		Shipyard yard = new Shipyard(ship);
		ships = yard.getShips();
		
		shipTableModel.clear();
		shipTableModel.addAll(Arrays.asList(ships));
		update();
		
		
		
		
		
	}
	
		
}