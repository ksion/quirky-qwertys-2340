package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import model.SaveLoad;
import controller.GameController;

public class StartWindow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final JButton btnNewGame = new JButton("New Game");
	private final JButton btnLoadGame = new JButton("Load Game");
	GameController loadGame, oldGame;

	/**
	 * Create the panel.
	 */

	public StartWindow(GameController gc) {
		setLayout(null);
		btnNewGame.setBounds(42, 33, 108, 23);
		btnNewGame.addActionListener(new NewGameListener());
		
		add(btnNewGame);
		btnLoadGame.setBounds(42, 67, 108, 23);
		btnLoadGame.addActionListener(new LoadListener());
		
		add(btnLoadGame);
		oldGame = gc;
	}

	
	private class LoadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				loadGame = (GameController) SaveLoad.load();
				GameWindow gw = new GameWindow(loadGame);
				gw.launch();

				
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
