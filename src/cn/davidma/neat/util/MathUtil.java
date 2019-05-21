package cn.davidma.neat.util;

import cn.davidma.neat.object.GameObject;
import javafx.scene.effect.ColorAdjust;

/**
 * This class does math. Wadda u expect?
 * 
 * @author David Ma
 */
public class MathUtil {
	
	/**
	 * Maps a value into an oscillating pattern between the min and max.
	 * 
	 * @param time The value to be mapped.
	 * @param min The minimum bound for the oscillation.
	 * @param max The maximum bound for the oscillation.
	 * @return The mapped value.
	 */
	public static double oscillate(double time, double min, double max) {
		return min + (Math.sin(time) + 1) / 2 * (max - min);
	}
	
	/**
	 * Maps a value to another interval.
	 * 
	 * @param value The value to be mapped.
	 * @param initialStart The start of the initial interval.
	 * @param initialEnd The end of the initial interval.
	 * @param targetStart The start of the target interval.
	 * @param targetEnd The end of the target interval.
	 * @return The value after map.
	 */
	public static double map(double value, double initialStart, double initialEnd, double targetStart, double targetEnd) {
		return targetStart + (targetEnd - targetStart) * (value - initialStart) / (initialEnd - initialStart);
	}
	
	/**
	 * Calculates the hue needed to change a white color to a target color.
	 * 
	 * @param targetHue The hue of the target color [0, 360).
	 * @return The resulting hue.
	 */
	public static double convHue(double targetHue) {
		return map((targetHue + 180) % 360, 0, 360, -1, 1);
	}
	
	/**
	 * Calculates the saturation needed to change a white color to a target color.
	 * 
	 * @param targetSaturation The saturation of the target color [0, 100].
	 * @return The resulting saturation.
	 */
	public static double convSaturation(double targetSaturation) {
		return targetSaturation / 100;
	}
	
	/**
	 * Calculates the brightness needed to change a white color to a target color.
	 * 
	 * @param targetBrightness The brightness of the target color [0, 100].
	 * @return The resulting brightness.
	 */
	public static double convBrightness(double targetBrightness) {
		return map(targetBrightness, 0, 100, -1, 0);
	}
	
	/**
	 * Calculates the final color to change a white color to.
	 * 
	 * @param targetHue  The hue of the target color [0, 360).
	 * @param targetSaturation The saturation of the target color [0, 100].
	 * @param targetBrightness The brightness of the target color [0, 100].
	 * @param contrast The contrast of the color.
	 * @return The ColorAdjust object of the target color.
	 */
	public static ColorAdjust convColorAdjust(double targetHue, double targetSaturation, double targetBrightness,
			double contrast) {
		ColorAdjust result = new ColorAdjust();
		result.setHue(convHue(targetHue));
		result.setSaturation(convSaturation(targetSaturation));
		result.setBrightness(convBrightness(targetBrightness));
		result.setContrast(contrast);
		return result;
	}
	
	/**
	 * Checks whether the given value is in the specified interval (inclusive).
	 * 
	 * @param value The value.
	 * @param start The start of the interval (inclusive).
	 * @param end The end of the interval (inclusive).
	 * @return Whether the value is in range.
	 */
	public static boolean inRange(double value, double start, double end) {
		return value >= start && value <= end;
	}
	
	/**
	 * Determines whether the two interval overlaps (inclusive) with each other.
	 * 
	 * @param firstStart The start of the first interval (inclusive).
	 * @param firstEnd The end of the first interval (inclusive).
	 * @param secondStart The start of the second interval (inclusive).
	 * @param secondEnd The end of the second interval (inclusive).
	 * @return
	 */
	public static boolean rangeOverlap(double firstStart, double firstEnd, double secondStart, double secondEnd) {
		return !(firstStart > secondEnd || firstEnd < secondStart);
	}
	
	/**
	 * Determines whether the given point is touching the GameObject.
	 * 
	 * @param x The x position of the point.
	 * @param y The y position of the point.
	 * @param gameObject The GameObject.
	 * @return
	 */
	public static boolean pointTouchingGameObject(double x, double y, GameObject gameObject) {
		double half = gameObject.getFitWidth() / 2;
		if (!inRange(x, gameObject.getX() - half, gameObject.getX() + half)) return false;
		
		half = gameObject.getFitHeight() / 2;
		return inRange(y, gameObject.getY() - half, gameObject.getY() + half);
	}
	
	/**
	 * Checks whether two GameObjects are overlapping each other.
	 * 
	 * <p>
	 * Note that this only use the image on screen as the bounding box.
	 * </p>
	 * 
	 * @param first The first GameObject.
	 * @param second The second GameObject.
	 * @return Whether the two GameObjects are overlapping each other.
	 */
	public static boolean gameObjectOverlap(GameObject first, GameObject second) {
		int firstX = first.getX();
		int secondX = second.getX();
		double firstHalf = first.getFitWidth() / 2;
		double secondHalf = second.getFitWidth() / 2;
		if (!rangeOverlap(firstX - firstHalf, firstX + firstHalf, secondX - secondHalf, secondX + secondHalf)) {
			return false;
		}
		
		int firstY = first.getY();
		int secondY = second.getY();
		firstHalf = first.getFitHeight() / 2;
		secondHalf = second.getFitHeight() / 2;
		return rangeOverlap(firstY - firstHalf, firstY + firstHalf, secondY - secondHalf, secondX + secondHalf);
	}
}
