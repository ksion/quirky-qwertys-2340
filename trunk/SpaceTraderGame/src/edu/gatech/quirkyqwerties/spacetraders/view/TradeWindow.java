// $codepro.audit.disable spaceAfterCasts, numericLiterals, appendString
/**
 * TradeWindow.java
 * @version 1.0
 * copyright 2012
 */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;
import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;
import edu.gatech.quirkyqwerties.spacetraders.model.Inventory;
import edu.gatech.quirkyqwerties.spacetraders.model.Player;
import edu.gatech.quirkyqwerties.spacetraders.model.TradableItem;

/**
 * view of the trade window; can be trader/planet or trader/trader(future todo).
 * @version 1.0
 * @author QuirkyQwertys
 */
public class TradeWindow extends JPanel {

	/** The table left. */
	private final JTable tableLeft;

	/** The table right. */
	private final JTable tableRight;

	/** The lbl credits. */
	private final JLabel lblCredits;

	/** The Constant BUY. */
	private static final int BUY = 0;

	/** The Constant SELL. */
	private static final int SELL = 1;

	/** The t. */
	private final TradeWindow tradeWin = this;

	/** The table border. */
	private final Border tableBorder;
	
	/** The controller. */
	private final GameController controller;
	
	/** The player. */
	private Player player;

	/** The other inventory. */
	private Inventory otherInventory;

	/**
	 * Create the panel.
	 *
	 * @param controller the controller
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TradeWindow(GameController controller) {  // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
		// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
		this.controller = controller;

		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout(0, 0));

		setOpaque(false);
		
		final JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0x66, 0x66, 0x66, 125));
		tablePanel.setBorder(new LineBorder(new 
				Color(0x5d, 0xdf, 0xfb, 255), 2, true));
		//tablePanel.setOpaque(false);
		add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new MigLayout("", "[grow][][grow]", 
				"[][][grow][][][grow][]"));
		
		final JLabel lblMessage = new JLabel("Use the arrow buttons to " +
				"buy/trade items from inventory.");
		lblMessage.setForeground(Style.SPACEAGE_COLOR);
		lblMessage.setFont(Style.SPACEAGE_NORMAL);
		tablePanel.add(lblMessage, "cell 0 0 3 1");
		
		final JLabel lblTraderInventory = new JLabel("Your Inventory");
		lblTraderInventory.setForeground(Style.SPACEAGE_COLOR);
		lblTraderInventory.setFont(Style.SPACEAGE_NORMAL);
		lblTraderInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblTraderInventory, "cell 0 1");
		
		final JLabel lblOtherInventory = new JLabel("Vendor Inventory");
		lblOtherInventory.setForeground(Style.SPACEAGE_COLOR);
		lblOtherInventory.setFont(Style.SPACEAGE_NORMAL);
		lblOtherInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblOtherInventory, "cell 2 1");
		
		final JButton btnTradeBuy = new JButton("<==");
		btnTradeBuy.setEnabled(false);
		
		final JButton btnTradeSell = new JButton("==>");
		btnTradeSell.setEnabled(false);
		
		tableLeft = new JTable();
		tableLeft.getSelectionModel().addListSelectionListener(new 
				TableListener(tableLeft, btnTradeSell));
		final JScrollPane scrollPaneLeft = new JScrollPane(tableLeft);
		scrollPaneLeft.setOpaque(false);
		scrollPaneLeft.setBorder(null);
		scrollPaneLeft.getViewport().setOpaque(false);
		scrollPaneLeft.getViewport().setBorder(null);
		tablePanel.add(scrollPaneLeft, "cell 0 2 1 4,grow");
		
		tableRight = new JTable();
		
		tableBorder = new LineBorder(new Color(0, 255, 0, 255), 1);
		
		styleTable(tableRight);
		styleTable(tableLeft);
		tableRight.getSelectionModel().addListSelectionListener(new TableListener
					(tableRight, btnTradeBuy));
		final JScrollPane scrollPane = new JScrollPane(tableRight);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getViewport().setBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		tablePanel.add(scrollPane, "cell 2 2 1 4,grow");

		btnTradeSell.addActionListener(new ArrowListener(SELL));
		tablePanel.add(btnTradeSell, "cell 1 3");

		btnTradeBuy.addActionListener(new ArrowListener(BUY));
		tablePanel.add(btnTradeBuy, "cell 1 4");
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		final JLabel lblCreditsAvailable = new JLabel("Credits Available: ");
		lblCreditsAvailable.setForeground(Style.SPACEAGE_COLOR);
		lblCreditsAvailable.setFont(Style.SPACEAGE_NORMAL);
		//buttonPanel.add(lblCreditsAvailable);
		
		lblCredits = new JLabel();
		lblCredits.setForeground(Style.SPACEAGE_COLOR);
		
		lblCredits.setFont(Style.SPACEAGE_NORMAL);
		//buttonPanel.add(lblCredits);
		tablePanel.add(lblCredits, "cell 0 6");
		//tablePanel.add(lblCredits, "cell 1 6");
		buttonPanel.add(Box.createHorizontalGlue());
	
		
	}

	/**
	 * styles the table to make it transparent.
	 *
	 * @param table the table
	 */
	public void styleTable(JTable table){
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(
				Object.class)).setForeground(new Color(0, 255, 0));
		((DefaultTableCellRenderer)table.getDefaultRenderer(
				Object.class)).setOpaque(false);
		table.setShowGrid(false);
		table.setBorder(tableBorder);
		table.setSelectionForeground(Color.YELLOW);
		
	}
	
/**
 * gets the right side inventory.
 *
 * @return the right side inventory
 */
	public Inventory getOtherInventory() {
		return otherInventory;
	}

	/**
	 * sets the inventory for the right side of the window.
	 *
	 * @param otherInventory right side inventory; could be planet or other trader
	 */
	public void setOtherInventory(Inventory otherInventory) {
		this.otherInventory = otherInventory;
		tableRight.setModel(new InventoryTableModel(otherInventory));
	}
	
	/**
	 * gets the player's credits.
	 *
	 * @return player's money
	 */
	public int getCredits(){
		return player.getMoney();
	}
	
	/**
	 * sets the player.
	 *
	 * @param p the player
	 */
	public void setPlayer(Player p){
		player = p;
		lblCredits.setText("Credits Available: " + Integer.toString(getCredits()));
		tableLeft.setModel(new InventoryTableModel(player.getCargo()));
		repaint();
		
	}
	
	/**
	 * model used by the tables in the gui.
	 *
	 * @author QuirkyQwertys
	 */
	private static class InventoryTableModel extends ListTableModel<TradableItem>{
		
		/** The inventory. */
		private final Inventory inventory;

		/**
		 * model used by the table.
		 *
		 * @param inventory passing in the inventory to attach to the table
		 */
		private InventoryTableModel(Inventory inventory){
			super(new String[] {"Name", "Quantity", "Cost"}, inventory.getGoods());
			this.inventory = inventory;
		}

		/**
		 * gets the value in the list at that cell in the table.
		 *
		 * @param row the row
		 * @param col the col
		 * @return the value in the cell
		 */
		public Object getValueAt(int row, int col) {
			final TradableItem item = get(row);
			switch(col){
				case 0:return item.getName();
				case 1: return item.getQty();
				case 2: return item.getPrice();
				default: return null;
			}
		
		}

		/**
		 * purchase action.
		 *
		 * @param buy a tradable item
		 * @param qty the qty
		 * @return true if successful
		 */
		public boolean canPurchase( TradableItem buy, int qty ) {
			final boolean added = inventory.purchase(buy, qty);
			if( added ) {
				notifyChange();
			}
			return added;
		}
		
		/**
		 * sell action.
		 *
		 * @param item a tradeable item
		 * @param qty the qty
		 * @return true if successful
		 */
		public boolean canSell(TradableItem item, int qty) {
			notifyChange();
			return true;
		}
		
		/**
		 * String Representation.
		 * @return String
		 */
		public String toString() {
			final StringBuffer res = new StringBuffer("[ ");
			final List<TradableItem> ace = this.inventory.getGoods();
			for (TradableItem x : ace) {
				res.append(x + " ");
			}
			res.append("]");
			return res.toString();
		}
	}
	
	/**
	 * class to make sure an item in the table is selected.
	 *
	 * @author Quirky Qwertys
	 */
	private static class TableListener implements ListSelectionListener{
		
		/** The table. */
		private final JTable table;

		/** The button. */
		private final JButton button;

		/**
		 * Instantiates a new table listener.
		 *
		 * @param table the table
		 * @param button the button
		 */
		private TableListener(JTable table, JButton button) {
			    this.table = table;
			    this.button = button;
		}

		/**
		 * checks whether an item from the table is selected before enabling the buttons.
		 *
		 * @param e the e
		 */
		public void valueChanged(ListSelectionEvent e) {
				button.setEnabled(! (table.getSelectionModel().isSelectionEmpty()) );
			
		}
		
		/**
		 * String Representation
		 * @return String
		 */
		public String toString() {
			return null;
		}
		
	}

	/**
	 * listener for the buy/sell buttons.
	 *
	 * @author Quirky Qwertys
	 */
	private class ArrowListener implements ActionListener{
		
		/** The transaction. */
		private final int transaction;

		/**
		 * constructor for the listener which takes in one of the buy/sell 
		 * buttons.
		 *
		 * @param transaction the transaction
		 */
		private ArrowListener(int transaction){
			this.transaction = transaction;
		
	    }

		/**
		 * action performed based on buy/sell; reloads the table when items 
		 * move out.
		 *
		 * @param e the e
		 */
		public void actionPerformed(ActionEvent e){
			final Integer initialSelect = 0;
			
			if (transaction == BUY) {
				//buying an item
				final TradableItem toBuy = getOtherInventory().getGoods().get(
						tableRight.getSelectedRow());
				int cost = toBuy.getPrice();
				final int qty = toBuy.getQty();
			
				final Object[] nums = new Object[qty + 1];
				for (int i = 0; i < qty + 1; i++) {
					nums[i] = new Integer(i);
				}
				
				//need to find a way to set the new price you bought it
				//at in the left table...the price stays the same for 
				//some reason... --> Keanna
				
				final int qtyWant = (Integer) JOptionPane.showInputDialog(
						tradeWin, "How many to buy ?", 
						"Quantity Buy", JOptionPane.PLAIN_MESSAGE, 
						null, nums, initialSelect);
				
				final InventoryTableModel model = 
						(InventoryTableModel)tableLeft.getModel();
				if (qty > 0 && cost < getCredits() && model.canPurchase(toBuy, qtyWant)) {
					player.addMoney(-cost * qtyWant);
					lblCredits.setText("Credits Available: " + 
							Integer.toString(getCredits()));
					
					final InventoryTableModel soldModel = 
							(InventoryTableModel)tableRight.getModel();
					soldModel.canSell(toBuy, qtyWant);
					repaint();
				}
			} else {
				//player selling an item
				final TradableItem toSell = 
						player.getCargo().getGoods().get(tableLeft.getSelectedRow());
				int cost = toSell.getPrice();
				//Sell at the price they are selling --> Keanna
				if (otherInventory.findItem(toSell) != null) {
					final TradableItem thing = otherInventory.findItem(toSell);
					if (cost < thing.getPrice()) {
						cost = thing.getPrice();
					}
				}
				
				final int qty = toSell.getQty();
			
				final Object[] nums = new Object[qty + 1];
				for (int i = 0; i < qty + 1; i++) {
					nums[i] = new Integer(i);
				}
				
				final Integer qtyWant = (Integer)JOptionPane.showInputDialog(tradeWin, 
						"How many to sell ?", 
						"Quantity Sell", JOptionPane.PLAIN_MESSAGE, null, 
						nums, initialSelect);
				if( qtyWant != null ) {
					final InventoryTableModel model = 
							(InventoryTableModel)tableLeft.getModel();
					if (model.canSell(toSell, qtyWant)){
						player.addMoney(cost * qtyWant);
						lblCredits.setText("Credits Available: " + 
								Integer.toString(getCredits()));	 // $codepro.audit.disable whiteSpaceUsage
						final InventoryTableModel buyModel = 
								(InventoryTableModel)tableRight.getModel();
						buyModel.canPurchase(toSell, qtyWant);
						repaint();
					}
				}
			}
		}
		
		/**
		 * String Representation
		 * @return String
		 */
		public String toString() {
			return null;
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