package cn.davidma.neat.util;

import cn.davidma.neat.geometry.BoundingBox;
import cn.davidma.neat.geometry.BoundingBox.Point;
import cn.davidma.neat.geometry.CollisionType;
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
	 * Determines whether the given point is touching the BoundingBox.
	 * 
	 * @param x The x position of the point.
	 * @param y The y position of the point.
	 * @param boundingBox The BoundingBox.
	 * @return
	 */
	public static boolean pointInBoundingBox(double x, double y, BoundingBox boundingBox) {
		if (!inRange(x, boundingBox.startX, boundingBox.endX)) return false;
		return inRange(y, boundingBox.startY, boundingBox.endY);
	}
	
	/**
	 * Checks whether two BoundingBox are overlapping each other.
	 * 
	 * @param first The first BoundingBox.
	 * @param second The second BoundingBox.
	 * @return Whether the two BoundingBox are overlapping each other.
	 */
	public static boolean boundingBoxOverlap(BoundingBox first, BoundingBox second) {
		if (!rangeOverlap(first.startX, first.endX, second.startX, second.endX))  return false;
		return rangeOverlap(first.startY, first.endY, second.startY, second.endY);
	}
	
	/**
	 * Calculates the {@link cn.davidma.neat.geometry.CollisionType} of the given BoundingBoxes.
	 * 
	 * <p>
	 * The returned CollisionType is the orientation of the target BoundingBox to the original BoundingBox.
	 * </p>
	 * 
	 * @param origin The original BoundingBox.
	 * @param target The target BoundingBox.
	 * @return The CollisionType of the two BoundingBoxes.
	 */
	public static CollisionType getBoundingBoxCollision(BoundingBox origin, BoundingBox target) {
		if (!boundingBoxOverlap(origin, target)) return CollisionType.NONE;
		Point originMid = origin.getMidPoint();
		Point targetMid = target.getMidPoint();
		double[] angles = origin.getVertsAngles();
		double targetAngle = getAngle(originMid.x, originMid.y, targetMid.x, targetMid.y);
		
		// Special treatment for the last side, thus only 3 sides handled here.
		for (int i = 0; i < 3; i++) {
			if (inRange(targetAngle, angles[i], angles[i + 1])) {
				return CollisionType.values()[i + 1]; // Plus one because the first value is None, and therefore skipped.
			}
		}
		
		// All can be left is right.
		// Since the right side's vertices can be close two [2 * PI, 0] (this interval crosses 0 radian, and spans
		// the entire [0, 2 * PI]), it cannot be handled normally.
		return CollisionType.RIGHT;
	}
	
	/**
	 * Calculates the angle (in radians) from the first point to the second point.
	 * 
	 * <p>
	 * The angle is measured counter-clockwise from the +x axis.
	 * </p>
	 * 
	 * @param fromX The x position of the original point.
	 * @param fromY The y position of the original point.
	 * @param toX The x position of the destination.
	 * @param toY The y position of the destination.
	 * @return The angle (in radians) from the first point to the second point.
	 */
	public static double getAngle(double fromX, double fromY, double toX, double toY) {
		double deltaX = toX - fromX;
		double deltaY = fromY - toY; // Reversed since the y axis is reversed.
		double result =  Math.atan2(deltaY, deltaX);
		
		// Add 2 * PI if in 3 or 4 quadrant.
		if (toY > fromY) {
			result += 2 * Math.PI;
		}
		
		return result;
	}
}
