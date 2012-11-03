package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Style {
	public static final Font SPACEAGE_NORMAL = new Font("Space Age", 1, 12);
	public static final Color SPACEAGE_COLOR = new Color(0, 255, 0);

	public static JLabel createNormalLabel() {
		JLabel label = new JLabel();
		label.setFont(Style.SPACEAGE_NORMAL);
		label.setForeground(Style.SPACEAGE_COLOR);
		return label;
	}
}
