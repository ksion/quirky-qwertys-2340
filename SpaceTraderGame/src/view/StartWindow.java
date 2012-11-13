package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.border.LineBorder;

import model.Player;
import model.SaveLoad;
import model.Ship;
import controller.GameController;

public class StartWindow extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private final JButton btnNewGame = new JButton("New Game");
	private final JButton btnLoadGame = new JButton("Load Game");
	private Image background;
	private JPanel menuPanel;
	GameController loadGame, oldGame;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */

	public StartWindow(GameController gc) throws IOException {
		setLayout(new BorderLayout());
		background = ImageIO.read(getClass().getResource("/view/spacestation1024.jpg"));
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		

		menuPanel.setBackground( new Color( 0x66,0x66,0x66,175 ) );
		menuPanel.setOpaque(false);
		menuPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(0x5d,0xdf,0xfb,255), 2,true), BorderFactory.createEmptyBorder(10,10,10,10)));
		btnNewGame.setBounds(42, 33, 108, 23);
		btnNewGame.setForeground(Style.TRON_COLOR);
		btnNewGame.addActionListener(new NewGameListener());
		
		JLabel welcomeTxt1 = Style.createTronLabel();
		welcomeTxt1.setText("WELCOME TO");
		JLabel welcomeTxt2 = Style.createTronLabel();
		welcomeTxt2.setText("SPACE TRADERS");
		menuPanel.add(welcomeTxt1);
		menuPanel.add(welcomeTxt2);
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(btnNewGame);
		btnLoadGame.setBounds(42, 67, 108, 23);
		btnLoadGame.setForeground(Style.TRON_COLOR);
		//btnLoadGame.addActionListener(new LoadListener());
		
		menuPanel.add(btnLoadGame);
		
		menuPanel.add(Box.createVerticalStrut(130));
		add(menuPanel,BorderLayout.WEST);
		oldGame = gc;
	}
	
	/**
	 * draws the background image on the GUI
	 * @param g - the graphics object
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	
	private class LoadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				GameController gc = new GameController();
				ArrayList<Object> game = (ArrayList<Object>) SaveLoad.load();
				gc.newPlayer = (Player) game.get(0);
				gc.newPlayer.setShip((Ship) game.get(1));
				

				
			}
			catch(Exception a){
				a.printStackTrace();
				JOptionPane.showMessageDialog(null, "We couldn't find a previous game!", 
	                      "Woops!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	private class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			oldGame.newPlayer();
		}
		
	}
}
