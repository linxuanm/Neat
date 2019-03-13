package cn.davidma.neat.capability;

/**
 * This interface indicates that the object can be subjected to relative transform.
 * Examples are moving object to the right by 5 pixels, or scale object by 2.
 * 
 * @author David Ma
 */
public interface IMovable {

	/**
	 * Move the object on the x axis.
	 * 
	 * @param xOffset Distance.
	 */
	public void moveX(int xOffset);
	
	/**
	 * Move the object on the y axis.
	 * 
	 * @param yOffset Distance.
	 */
	public void moveY(int yOffset);
	
	/**
	 * Rotate the object.
	 * 
	 * @param angle The angle of rotation (in radians).
	 */
	public void rotate(double angle);
	
	/**
	 * Rotate the object.
	 * 
	 * @param angle The angle of rotation (in radians).
	 * @param x The x axis of the center of rotation.
	 * @param y The y axis of the center of rotation.
	 */
	public void rotate(double angle, int x, int y);
	
	
	/**
	 * Scale the object.
	 * 
	 * @param scale The amount to scale.
	 */
	public void scale(double scale);
	
	/**
	 * Scale the object.
	 * 
	 * @param scale The amount to scale.
	 * @param x The x axis of the center of the resize.
	 * @param y The y axis of the center of the resize.
	 */
	public void scale(double scale, int x, int y);
}
