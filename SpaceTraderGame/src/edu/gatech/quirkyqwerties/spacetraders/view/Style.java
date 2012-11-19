/**
 * Style.java
 * @author QuirkyQwerties
 */
package edu.gatech.quirkyqwerties.spacetraders.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * class to hold common styles used across app
 * @author Quirky Qwertys
 * @version	1.0
 */
public class Style {
	
	/** The Constant SPACEAGE_NORMAL. */
	public static final Font SPACEAGE_NORMAL = new Font("Space Age", 1, 14);

	/** The Constant SPACEAGE_COLOR. */
	public static final Color SPACEAGE_COLOR = new Color(0, 255, 0);

	/** The Constant ARIAL_NORMAL. */
	public static final Font ARIAL_NORMAL = new Font("Arial", 1, 14);

	/** The Constant TRON_COLOR. */
	public static final Color TRON_COLOR = new Color(0x5d, 0xdf, 0xfb, 255);

	/** The Constant ARIAL_LARGE. */
	public static final Font ARIAL_LARGE = new Font("Arial", 1, 16);

	/**
	 * adds spaceage styling to a label
	 * @return the label */
	public static JLabel createNormalLabel() {
		final JLabel label = new JLabel();
		label.setFont(Style.SPACEAGE_NORMAL);
		label.setForeground(Style.SPACEAGE_COLOR);
		return label;
	}

	/**
	 * adds TRON COLOR styling to a label
	 * @return the label */
	public static JLabel createTronLabel() {
		final JLabel label = new JLabel();
		label.setFont(Style.ARIAL_LARGE);
		label.setForeground(Style.TRON_COLOR);
		return label;
	}

	/**
	 * adds the green color to the label
	 *
	 * @return the label */
	public static JLabel createSkillLabel() {
		final JLabel label = new JLabel();
		label.setForeground(Style.SPACEAGE_COLOR);
		return label;
	}

	/**
	 * adds Red COLOR styling to a label
	 *
	 * @return the label */
	public static JLabel createRedLabel() {
		final JLabel label = new JLabel();
		label.setFont(Style.ARIAL_LARGE);
		label.setForeground(Color.RED);
		return label;
	}
	
	/** 
	 * Simple toString method
	 *
	 * @return String
	 */
	public String toString() {
		return null;
	}
}
