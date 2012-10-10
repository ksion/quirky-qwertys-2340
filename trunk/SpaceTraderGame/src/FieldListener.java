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
		if (sum > 9)
			sum = sum - 1;
		if (sum > 16)
			sum = 0;
		System.out.println(sum);/*
		pass(arg0, arg0.getLength());*/
	}
	

	/*private void pass(DocumentEvent arg0, int length) {
		int ace = sum;
	}*/

	@Override
	public void changedUpdate(DocumentEvent arg0) {}
	
	@Override
	public void removeUpdate(DocumentEvent arg0) {}
		
}
