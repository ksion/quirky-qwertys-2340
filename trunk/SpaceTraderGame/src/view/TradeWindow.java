package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
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

import controller.GameController;

import model.Inventory;
import model.Player;
import model.TradableItem;
import net.miginfocom.swing.MigLayout;

/**
 * view of the trade window; can be trader/planet or trader/trader(future todo)
 * @author QuirkyQwertys
 *
 */
public class TradeWindow extends JPanel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableLeft;
	private JTable tableRight;
	private JLabel lblCredits;
	private static final int BUY = 0;
	private static final int SELL = 1;
	private Image img;
	private Color c = new Color(0, 255, 0);
	private Font f = new Font("Space Age", 1, 12);
	private TradeWindow t = this;
	private Border tableBorder;
	
	private GameController controller;
	
	private Player player;
	private Inventory otherInventory;

	/**
	 * Create the panel.
	 */
	public TradeWindow(GameController controller) throws IOException {
		this.controller = controller;
		
		
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout(0, 0));
		
	
		setOpaque(false);
		
		//img = ImageIO.read(new File("src/view/Space.jpg"));
		//img = ImageIO.read(getClass().getResource("/view/starsBackground.jpeg"));
		
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0x66,0x66,0x66,125));
		tablePanel.setBorder(new LineBorder(new Color(0x5d,0xdf,0xfb,255), 2,true));
		//tablePanel.setOpaque(false);
		add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new MigLayout("", "[grow][][grow]", "[][][grow][][][grow][]"));
		
		JLabel lblMessage = new JLabel("Use the arrow buttons to buy/trade items from inventory.");
		lblMessage.setForeground(Style.SPACEAGE_COLOR);
		lblMessage.setFont(Style.SPACEAGE_NORMAL);
		tablePanel.add(lblMessage, "cell 0 0 3 1");
		
		JLabel lblTraderInventory = new JLabel("Your Inventory");
		lblTraderInventory.setForeground(Style.SPACEAGE_COLOR);
		lblTraderInventory.setFont(Style.SPACEAGE_NORMAL);
		lblTraderInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblTraderInventory, "cell 0 1");
		
		JLabel lblOtherInventory = new JLabel("Vendor Inventory");
		lblOtherInventory.setForeground(Style.SPACEAGE_COLOR);
		lblOtherInventory.setFont(Style.SPACEAGE_NORMAL);
		lblOtherInventory.setHorizontalAlignment(SwingConstants.CENTER);
		tablePanel.add(lblOtherInventory, "cell 2 1");
		
		JButton btnTradeBuy = new JButton("<==");
		btnTradeBuy.setEnabled(false);
		
		JButton btnTradeSell = new JButton("==>");
		btnTradeSell.setEnabled(false);
		
		tableLeft = new JTable();
		tableLeft.getSelectionModel().addListSelectionListener(new TableListener(tableLeft, btnTradeSell));
		JScrollPane scrollPaneLeft = new JScrollPane(tableLeft);
		scrollPaneLeft.setOpaque(false);
		scrollPaneLeft.setBorder(null);
		scrollPaneLeft.getViewport().setOpaque(false);
		scrollPaneLeft.getViewport().setBorder(null);
		tablePanel.add(scrollPaneLeft, "cell 0 2 1 4,grow");
		
		tableRight = new JTable();
		
		tableBorder = new LineBorder(new Color(0,255,0,255),1);
		/*
		tableRight.setOpaque(false);
		((DefaultTableCellRenderer)tableRight.getDefaultRenderer(Object.class)).setForeground(new Color(0,255,0));
		((DefaultTableCellRenderer)tableRight.getDefaultRenderer(Object.class)).setOpaque(false);
		tableRight.setShowGrid(false);
		tableRight.setBorder(tableBorder);
		*/
		styleTable(tableRight);
		styleTable(tableLeft);
		tableRight.getSelectionModel().addListSelectionListener(new TableListener(tableRight,btnTradeBuy));
		JScrollPane scrollPane = new JScrollPane(tableRight);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getViewport().setBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		tablePanel.add(scrollPane, "cell 2 2 1 4,grow");
		
		
		
		btnTradeSell.addActionListener(new ArrowListener(SELL));
		tablePanel.add(btnTradeSell, "cell 1 3");
		
		
		btnTradeBuy.addActionListener(new ArrowListener(BUY));
		tablePanel.add(btnTradeBuy, "cell 1 4");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel lblCreditsAvailable = new JLabel("Credits Available: ");
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
		lblCredits.setText("Credits Available: "+Integer.toString(getCredits()));
		tableLeft.setModel(new InventoryTableModel(player.getCargo()));
		repaint();
		
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
		public boolean purchase( TradableItem buy, int qty ) {
			boolean added = false;
			for (int i = 0; i < qty; i++) {
				added = inventory.addTradeGood(buy);
				if( added ) {
					notifyChange();
					added = true;
				}
			}
			return added;
		}
		
		/**
		 * sell action 
		 * @param item a tradeable item
		 * @return true if successful
		 */
		public boolean sold(TradableItem item, int qty) {
			TradableItem modelItem = inventory.findItem(item);
			if (modelItem.getQty() > 0){
				modelItem.setQty(modelItem.getQty() - qty);
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
			if (transaction == BUY) {
				//buying an item
				TradableItem toBuy = getOtherInventory().getGoods().get(tableRight.getSelectedRow());
				int cost = toBuy.getPrice();
				int qty = toBuy.getQty();
			
				Object[] nums = new Object[qty + 1];
				for (int i = 0; i < qty + 1; i++) {
					nums[i] = new Integer(i);
				}
				
				//need to find a way to set the new price you bought it at in the left
				//table...the price stays the same for some reason... --> Keanna
				
				int qtyWant = (Integer) JOptionPane.showInputDialog(t, "How many to buy ?", 
						"Quantity Buy", JOptionPane.PLAIN_MESSAGE, null, nums, new Integer(0));
				
				InventoryTableModel model = (InventoryTableModel)tableLeft.getModel();
				if (qty > 0 && cost < getCredits() && model.purchase(toBuy, qtyWant)) {
					player.setMoney(-cost * qtyWant);
					lblCredits.setText("Credits Available: "+Integer.toString(getCredits()));
					
					InventoryTableModel soldModel = (InventoryTableModel)tableRight.getModel();
					soldModel.sold(toBuy, qtyWant);
					repaint();
				}
			} else {
				//player selling an item
				TradableItem toSell = player.getCargo().getGoods().get(tableLeft.getSelectedRow());
				int cost = toSell.getPrice();
				//Sell at the price they are selling --> Keanna
				if (otherInventory.findItem(toSell) != null) {
					TradableItem thing = otherInventory.findItem(toSell);
					if (cost < thing.getPrice())
						cost = thing.getPrice();
				}
				
				int qty = toSell.getQty();
			
				Object[] nums = new Object[qty + 1];
				for (int i = 0; i < qty + 1; i++) {
					nums[i] = new Integer(i);
				}
				
				Integer qtyWant = (Integer)JOptionPane.showInputDialog(t, "How many to sell ?", 
						"Quantity Sell", JOptionPane.PLAIN_MESSAGE, null, nums, new Integer(0));
				if( qtyWant != null ) {
					InventoryTableModel model = (InventoryTableModel)tableLeft.getModel();
					if (model.sold(toSell, qtyWant) == true){
						player.setMoney(cost * qtyWant);
						lblCredits.setText("Credits Available: "+Integer.toString(getCredits()));				
						InventoryTableModel buyModel = (InventoryTableModel)tableRight.getModel();
						buyModel.purchase(toSell, qtyWant);
						repaint();
					}
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