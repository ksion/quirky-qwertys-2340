package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class TradeWindow extends JPanel {
	private JTable tableLeft;
	private JTable tableRight;
	private JTextField textField;
	private final int LEFT = 0;
	private final int RIGHT = 1;

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
		
		tableLeft = new JTable();
		tablePanel.add(new JScrollPane(tableLeft), "cell 0 2 1 4,grow");
		
		tableRight = new JTable();
		tablePanel.add(new JScrollPane(tableRight), "cell 2 2 1 4,grow");
		
		
		JButton btnTradeRight = new JButton("-->");
		tablePanel.add(btnTradeRight, "cell 1 3");
		
		JButton btnTradeLeft = new JButton("<--");
		tablePanel.add(btnTradeLeft, "cell 1 4");
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel lblCreditsAvailable = new JLabel("Credits Available");
		buttonPanel.add(lblCreditsAvailable);
		
		textField = new JTextField();
		buttonPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		buttonPanel.add(btnSaveAndQuit);
		
		JButton btnNext = new JButton("Next");
		btnNext.setToolTipText("Done Trading");
		buttonPanel.add(btnNext);
	}
	
	private class ArrowListener implements ActionListener{
		private int direction;
		
		public ArrowListener(int direction){
			this.direction = direction;
		
	    }
		public void actionPerformed(ActionEvent e){
			if (direction == LEFT){
				//TODO: get right table selection; check that funds are available; move it to left table; decrement funds
				// TradeGood row = tableRight.getSelectedRow();
				
			}
			else{
				//TODO: get left table selection; move it to right table; increment funds
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