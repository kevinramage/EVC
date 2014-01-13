/**
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * Temp class in order to generate random color
 */

package fr.istic.evc.utils;

import java.awt.Color;
import java.util.Random;

public class RandomColor {
	
	private static Random random = new Random();
	private static Color [] colors = new Color[] {
		Color.red,
		Color.blue,
		Color.yellow,
		Color.green,
		Color.pink,
		Color.cyan
	};
	
	public static Color getColor() {
		return colors[random.nextInt(colors.length)];
	}
}	
