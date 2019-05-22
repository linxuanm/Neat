package cn.davidma.neat.geometry;

/**
 * This enum holds the types of possible collision between {@link cn.davidma.neat.geometry.BoundingBox}.
 * 
 * @author David Ma
 */
public enum CollisionType {
	
	/**
	 * No collision happened.
	 */
	NONE,
	/**
	 * The other BoundingBox is colliding the top of this BoundingBox.
	 */
	TOP,
	/**
	 * The other BoundingBox is colliding the left of this BoundingBox.
	 */
	LEFT,
	/**
	 * The other BoundingBox is colliding the bottom of this BoundingBox.
	 */
	BOTTOM,
	/**
	 * The other BoundingBox is colliding the right of this BoundingBox.
	 */
	RIGHT;
}
