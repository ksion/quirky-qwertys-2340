package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Inventory;
import model.Player;
import model.TradableItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * view of the trade window; can be trader/planet or trader/trader(future todo)
 * @author QuirkyQwertys
 *
 */
public class TradeWindow extends JPanel {
	private JTable tableLeft;
	private JTable tableRight;
	private JLabel lblCredits;
	private static final int BUY = 0;
	private static final int SELL = 1;
	
	private Player player;
	private Inventory otherInventory;

	/**
	 * Create the panel.
	 */
	public TradeWindow() {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel tablePanel = new JPanel();
		add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new MigLayout("", "[grow][][grow]", "[][][grow][][][grow]"));
		
		JLabel lblMessage = new JLabel("Use the arrow buttons to buy/trade items from inventory.");
		tablePanel.add(lblMessage, "cell 0 0 3 1");
		
		JLabel lblTraderInventory = new JLabel("Your Inventory");
		lblTraderInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblTraderInventory, "cell 0 1");
		
		JLabel lblOtherInventory = new JLabel("Vendor Inventory");
		lblOtherInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblOtherInventory, "cell 2 1");
		
		JButton btnTradeBuy = new JButton("<--");
		btnTradeBuy.setEnabled(false);
		
		JButton btnTradeSell = new JButton("-->");
		btnTradeSell.setEnabled(false);
		
		tableLeft = new JTable();
		tableLeft.getSelectionModel().addListSelectionListener(new TableListener(tableLeft, btnTradeSell));
		tablePanel.add(new JScrollPane(tableLeft), "cell 0 2 1 4,grow");
		
		tableRight = new JTable();
		tableRight.getSelectionModel().addListSelectionListener(new TableListener(tableRight,btnTradeBuy));
		
		tablePanel.add(new JScrollPane(tableRight), "cell 2 2 1 4,grow");
		
		
		
		btnTradeSell.addActionListener(new ArrowListener(SELL));
		tablePanel.add(btnTradeSell, "cell 1 3");
		
		
		btnTradeBuy.addActionListener(new ArrowListener(BUY));
		tablePanel.add(btnTradeBuy, "cell 1 4");
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel lblCreditsAvailable = new JLabel("Credits Available: ");
		buttonPanel.add(lblCreditsAvailable);
		
		lblCredits = new JLabel();
		buttonPanel.add(lblCredits);
		buttonPanel.add(Box.createHorizontalGlue());
	
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		buttonPanel.add(btnSaveAndQuit);
		
		JButton btnNext = new JButton("Next");
		btnNext.setToolTipText("Done Trading");
		buttonPanel.add(btnNext);
	}
	
/**
 * gets the right side inventory
 * @return the right side inventory
 */
	public Inventory getOtherInventory() {
		return otherInventory;
	}

	/**
	 * sets the inventory for the right side of the window
	 * @param otherInventory right side inventory; could be planet or other trader
	 */
	public void setOtherInventory(Inventory otherInventory) {
		this.otherInventory = otherInventory;
		tableRight.setModel(new InventoryTableModel(otherInventory));
	}
	
	/**
	 * gets the player's credits
	 * @return player's money
	 */
	public int getCredits(){
		return player.getMoney();
	}
	
	/**
	 * sets the player
	 * @param p the player
	 */
	public void setPlayer(Player p){
		player = p;
		lblCredits.setText(Integer.toString(getCredits()));
		tableLeft.setModel(new InventoryTableModel(player.getCargo()));
		
	}
	
	/**
	 * model used by the tables in the gui
	 * @author QuirkyQwertys
	 *
	 */
	private class InventoryTableModel extends ListTableModel<TradableItem>{
		private Inventory inventory;
		/**
		 * model used by the table
		 * @param inventory passing in the inventory to attach to the table
		 */
		public InventoryTableModel(Inventory inventory){
			super(new String[] {"Name","Quantity", "Cost"},inventory.getGoods());
			this.inventory = inventory;
		}

		/**
		 * gets the value in the list at that cell in the table
		 * @return the value in the cell
		 */
		public Object getValueAt(int row, int col) {
			TradableItem item = get(row);
			switch(col){
				case 0:return item.getName();
				case 1: return item.getQty();
				case 2: return item.getPrice();
				default: return null;
			}
		
		}
		/**
		 * purchase action
		 * @param buy a tradable item
		 * @return true if successful
		 */
		public boolean purchase( TradableItem buy ) {
			boolean added = inventory.addTradeGood(buy);
			if( added ) {
				notifyChange();
			}
			return added;
		}
		/**
		 * sell action 
		 * @param item a tradeable item
		 * @return true if successful
		 */
		public boolean sold( TradableItem item ) {
			TradableItem modelItem = inventory.findItem(item);
			if (modelItem.getQty()>0){
				modelItem.setQty(modelItem.getQty() - 1);
				// todo should use a more precise method to update the table so we don't loose the selection 
				notifyChange();
				return true;
			}
			return false;
		}
	}
	
	/**
	 * class to make sure an item in the table is selected
	 * @author Quirky Qwertys
	 *
	 */
	private class TableListener implements ListSelectionListener{
		JTable table;
		JButton button;
		TableListener(JTable table, JButton button) {
			    this.table = table;
			    this.button = button;
		}
		/**
		 * checks whether an item from the table is selected before enabling the buttons
		 */
		public void valueChanged(ListSelectionEvent e) {
				button.setEnabled(! (table.getSelectionModel().isSelectionEmpty()) );
			
		}
					
		
	}
	/**
	 * listener for the buy/sell buttons
	 * @author Quirky Qwertys
	 *
	 */
	private class ArrowListener implements ActionListener{
		private int transaction;
		/**
		 * constructor for the listener which takes in one of the buy/sell buttons
		 * @param direction buy/sell
		 */
		public ArrowListener(int transaction){
			this.transaction = transaction;
		
	    }
		/**
		 * action performed based on buy/sell; reloads the table when items move out
		 */
		public void actionPerformed(ActionEvent e){
			if (transaction == BUY){//buying an item
				
				
				TradableItem toBuy = getOtherInventory().getGoods().get(tableRight.getSelectedRow());
				int cost = toBuy.getPrice();
				int qty = toBuy.getQty();
				
				InventoryTableModel model = (InventoryTableModel)tableLeft.getModel();
				if (qty>0 && cost < getCredits() && model.purchase(toBuy)) {
					player.setMoney(-cost);
					lblCredits.setText(Integer.toString(getCredits()));
					
					InventoryTableModel soldModel = (InventoryTableModel)tableRight.getModel();
					soldModel.sold( toBuy );
				}
			}
			else{//player selling an item
				
				TradableItem toSell = player.getCargo().getGoods().get(tableLeft.getSelectedRow());
				int cost = toSell.getPrice();
				
				InventoryTableModel model = (InventoryTableModel)tableLeft.getModel();
				if ( model.sold(toSell) == true){
					player.setMoney(cost);
					lblCredits.setText(Integer.toString(getCredits()));
					InventoryTableModel buyModel = (InventoryTableModel)tableRight.getModel();
					buyModel.purchase( toSell );
			}
			}
		}
    }
	
	
	/*public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new TradeWindow());
		frame.setBounds(100, 100, 450, 300);
		frame.setPreferredSize(new Dimension(650, 250));
		//frame.setIconImage(spaceIcon);
		frame.setVisible(true);
		frame.pack();
		
	}*/
}