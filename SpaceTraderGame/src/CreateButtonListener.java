import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ButtonListener that connects with PlayerWindow button "Create"
 * @author Keanna //not finished
 */
public class CreateButtonListener implements ActionListener {
	
	String playerName = null;
	int[] skills = new int[4];
	PlayerWindow bigPicture = null;
	
	public CreateButtonListener(String name, PlayerWindow obj) {
		playerName = name;
		bigPicture = obj;
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
		if (sum > 15 || sum < 15) {
			bigPicture.setSkillFieldZero();
			bigPicture.getCreateButton().setEnabled(FieldListener.setEnable);
		} else {
			Player newPlayer = new Player(playerName, skills);
			System.out.println(newPlayer.toString());
		}
	}
}
