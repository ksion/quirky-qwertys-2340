package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * class to hold common styles used across app
 * @author Quirky Qwertys
 *
 */
public class Style implements java.io.Serializable{
	public static final Font SPACEAGE_NORMAL = new Font("Space Age", 1, 14);
	public static final Color SPACEAGE_COLOR = new Color(0, 255, 0);
	public static final Font ARIAL_NORMAL = new Font("Arial", 1, 14);
	public static final Color TRON_COLOR = new Color(0x5d,0xdf,0xfb,255);
	public static final Font ARIAL_LARGE = new Font("Arial", 1, 16);
	private static final long serialVersionUID = 1L;

	/**
	 * adds spaceage styling to a label
	 * @return the label
	 */
	public static JLabel createNormalLabel() {
		JLabel label = new JLabel();
		label.setFont(Style.SPACEAGE_NORMAL);
		label.setForeground(Style.SPACEAGE_COLOR);
		return label;
	}
	/**
	 * adds TRON COLOR styling to a label
	 * @return the label
	 */
	public static JLabel createTronLabel() {
		JLabel label = new JLabel();
		label.setFont(Style.ARIAL_LARGE);
		label.setForeground(Style.TRON_COLOR);
		return label;
	}
	/**
	 * adds the green color to the label
	 * @return the label
	 */
	public static JLabel createSkillLabel() {
		JLabel label = new JLabel();
		label.setForeground(Style.SPACEAGE_COLOR);
		return label;
	}
	/**
	 * adds Red COLOR styling to a label
	 * @return the label
	 */
	public static JLabel createRedLabel() {
		JLabel label = new JLabel();
		label.setFont(Style.ARIAL_LARGE);
		label.setForeground(Color.RED);
		return label;
	}
}
