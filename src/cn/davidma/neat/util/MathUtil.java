package cn.davidma.neat.util;

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
}
