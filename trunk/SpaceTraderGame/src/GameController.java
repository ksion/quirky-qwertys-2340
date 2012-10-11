import java.io.IOException;

/**
 * Controls the game flow
 * @author Quirky Qwertys
 * @version 1.0 
 */
public class GameController {
	
	/**
	 * Main method to start the game
	 * @param args
	 */
	public static void main(String[] args){
		//launch the player creation window;
		PlayerWindow playerWin;
		try {
			playerWin = new PlayerWindow();
			playerWin.launch();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	

		
		
		
	

}
