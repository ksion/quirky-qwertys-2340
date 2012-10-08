import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * ButtonListener that connects with PlayerWindow button "Create"
 * @author Keanna //not finished
 */
public class PWButtonListener implements ActionListener {
	
	static String playerName = null;
	static int[] skills = new int[4];
	PlayerWindow bigPicture;
	
	public PWButtonListener(String name, PlayerWindow object) {
		playerName = name;
		bigPicture = object;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		skills[0] = new Integer(PlayerWindow.getTextFieldPilot());
		skills[1] = new Integer(PlayerWindow.getTextFieldFighter());
		skills[2] = new Integer(PlayerWindow.getTextFieldEngineer());
		skills[3] = new Integer(PlayerWindow.getTextFieldTrader());
		int sum = 0;
		for (int i = 0; i < 4; i++)
			sum += skills[i];
		if (sum > 15)
			bigPicture.setSkillFieldZero();
		else {
			//create new player
		}
			
	}

}
