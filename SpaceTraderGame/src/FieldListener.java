import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FieldListener implements DocumentListener {

	JTextField text = null;
	PlayerWindow bigPicture;
	int sum = 0;
	static boolean setEnable = false;
	int value = 0;
	
	public FieldListener(JTextField text, PlayerWindow obj) {
		this.text = text;
		bigPicture = obj;
		System.out.println("FieldListener Created!");
	}
	
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		sum += (new Integer(text.getText())).intValue();
		//The sum variable is incrementing on the old
		//sum, so like if i put 10, then back space to 
		//replace 0 with a five, sum is equal to 25.
		System.out.println(sum);
	}
	

	/*private void pass(DocumentEvent arg0, int length) {
		int ace = sum;
	}*/

	@Override
	public void changedUpdate(DocumentEvent arg0) {}
	
	@Override
	public void removeUpdate(DocumentEvent arg0) {}
		
}
