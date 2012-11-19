// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods, numericLiterals, com.instantiations.assist.eclipse.analysis.unusedReturnValue, methodJavadoc
/** the window that first comes up when you start the game */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.border.LineBorder;

import edu.gatech.quirkyqwerties.spacetraders.controller.GameController;

/** the start window contains the new and load game buttons 
 *	@author Hayden
 *	@version 1.awesome! 
 */
public class StartWindow extends JPanel{
	
	/***/
	private static final long serialVersionUID = 1L;
	
	/** the new game button */
	private final JButton btnNewGame = new JButton("New Game");
	
	/** the load game button */
	private final JButton btnLoadGame = new JButton("Load Game");
	
	/** the background image for the window */
	private final Image background; // $codepro.audit.disable hidingInheritedFields
	
	/** the panel that holds the buttons ? */
	private final JPanel menuPanel;
	
	/** the game controller of the game */
	private final GameController gc;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @param GameController gc
	 */

	public StartWindow(GameController gc) throws IOException {
		setLayout(new BorderLayout());
		background = ImageIO.read(getClass().getResource(
				"/edu/gatech/quirkyqwerties/spacetraders/view/spacestation1024.jpg"));
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setBackground( new Color( 0x66, 0x66, 0x66, 175 ) );
		menuPanel.setOpaque(false);
		menuPanel.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(0x5d, 0xdf, 0xfb, 255), 2, true), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		btnNewGame.setBounds(42, 33, 108, 23);
		btnNewGame.addActionListener(new NewGameListener());
		
		final JLabel welcomeTxt1 = Style.createTronLabel();
		welcomeTxt1.setText("WELCOME TO");
		final JLabel welcomeTxt2 = Style.createTronLabel();
		welcomeTxt2.setText("SPACE TRADERS");
		menuPanel.add(welcomeTxt1);
		menuPanel.add(welcomeTxt2);
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(btnNewGame);
		btnLoadGame.setBounds(42, 67, 108, 23);
		btnLoadGame.addActionListener(new LoadListener());
		
		menuPanel.add(btnLoadGame);
		
		menuPanel.add(Box.createVerticalStrut(130));
		add(menuPanel, BorderLayout.WEST);
		this.gc = gc;
	}
	
	/**
	 * draws the background image on the GUI
	 * @param g - the graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	/**
	 * The listener for the load game button.
	 * @author Hayden
	 *
	 */
	private class LoadListener implements ActionListener{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
		
		/**
		 * the method executed when the load button is clicked
		 * @param ActionEvent e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				gc.loadGame();
			}
			catch(Exception a){
				a.printStackTrace();
				JOptionPane.showMessageDialog(getTopLevelAncestor(), 
						"We couldn't find a previous game!", 
	                      "Woops!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	/**
	 * The listener for the new game button
	 * @author Hayden
	 *
	 */
	private class NewGameListener implements ActionListener{ // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
		
		/**
		 * The method executed when the new game button is clicked
		 * @param ActionEvent e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			gc.newPlayer();
		}
		
	}
}
