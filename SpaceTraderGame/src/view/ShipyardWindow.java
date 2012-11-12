package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

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
	private Border tableBorder;
	
	/** Maximum amount of fuel a player can buy. */
	private int maxFuel;
	
	/** Price of fuel per ton. */
	private final int PRICE = 100;

	/**
	 * Create the panel.
	 */
	public ShipyardWindow(GameController controller) {
		this.controller = controller;
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		
		JLabel lblWelcomeToThe =Style.createNormalLabel();
		lblWelcomeToThe.setText("You may trade your ship for a new one or buy fuel here.");
		
		lblYourShip = Style.createNormalLabel();
		
		//JLabel lblOtherShips = new JLabel("Ships for Sell	              Cost");
		//panel.add(lblOtherShips, "cell 2 1");
		
		shipLbl = Style.createNormalLabel();
		
		btnTrade = new JButton("Trade");
		btnTrade.addActionListener(new TradeListener());
		
		tableBorder = new LineBorder(new Color(0,255,0,255),1);
		shipTableModel= new ShipTableModel();
		table = new JTable(shipTableModel);
		table.setOpaque(false);
		styleTable(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		
		JLabel lblCurrentFuelAmount = Style.createNormalLabel(); 
		lblCurrentFuelAmount.setText("Current Fuel Amount");
		
		JLabel lblFuelForSell = Style.createNormalLabel(); 
		lblFuelForSell.setText("Qty. Fuel to Buy");
		
		currFuelLbl = Style.createNormalLabel();
		currFuelLbl.setVerticalAlignment(SwingConstants.TOP);
		
		
		spinner = new JSpinner();
		JComponent editor = spinner.getEditor();
		
		// Change the size of the spinner's text field 
		ftf = ((JSpinner.DefaultEditor)editor).getTextField();
		ftf.setColumns(4);
		
		
		btnBuy = new JButton(" Buy ");
		btnBuy.addActionListener(new BuyListener());
		
		lblCreditsAvailable = Style.createNormalLabel();
		JLabel lblCostCrton = Style.createNormalLabel(); 
		lblCostCrton.setText("Fuel Cost: 100 cr./ton");
	

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x66,0x66,0x66,125));
		panel.setBorder(new LineBorder(new Color(0x5d,0xdf,0xfb,255), 2,true));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][][grow][][][][][]"));
		
		panel.add(lblWelcomeToThe, "cell 0 0 3 1");
		panel.add(lblYourShip, "cell 0 1");
		panel.add(shipLbl, "cell 0 2");
		panel.add(scrollPane, "cell 0 3 3 1,grow");
		panel.add(btnTrade, "cell 2 4, align right");
		
		panel.add(lblCurrentFuelAmount, "cell 0 6 2 1");
		panel.add(lblFuelForSell, "cell 2 6");
		panel.add(currFuelLbl, "cell 0 7");
		panel.add(spinner, "cell 2 7 2 1");
		panel.add(btnBuy, "cell 1 7");
		panel.add(lblCreditsAvailable, "cell 0 8");
		panel.add(lblCostCrton, "cell 2 8");
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
	
	/**
	 * styles the table to make it transparent
	 * @param table
	 */
	public void styleTable(JTable table){
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setForeground(new Color(0,255,0));
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		table.setShowGrid(false);
		table.setBorder(tableBorder);
		table.setSelectionForeground(Color.YELLOW);
		table.setSelectionBackground(Color.yellow);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
	
		
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
		
		JComponent editor = spinner.getEditor();
		
		// Change the size of the spinner's text field 
		ftf = ((JSpinner.DefaultEditor)editor).getTextField();
		ftf.setColumns(4);
		
		Shipyard yard = new Shipyard(ship);
		ships = yard.getShips();
		
		shipTableModel.clear();
		shipTableModel.addAll(Arrays.asList(ships));
		update();	
	}
}