package cn.davidma.neat.capability;

/**
 * This interface indicates that the object can be subjected to relative transform.
 * Examples are moving object to the right by 5 pixels, or scale object by 2.
 * 
 * @author David Ma
 */
public interface IRelative {
	
	/**
	 * Moves the object on the x axis.
	 * 
	 * @param xOffset Distance.
	 */
	public void moveX(int xOffset);
	
	/**
	 * Moves the object on the y axis.
	 * 
	 * @param yOffset Distance.
	 */
	public void moveY(int yOffset);
	
	/**
	 * Rotates the object.
	 * 
	 * @param angle The angle of rotation (in radians).
	 */
	public void rotate(double angle);
	
	/**
	 * Rotates the object.
	 * 
	 * @param angle The angle of rotation (in radians).
	 * @param x The x axis of the center of rotation.
	 * @param y The y axis of the center of rotation.
	 */
	public void rotate(double angle, int x, int y);
	
	
	/**
	 * Enlarges the object.
	 * 
	 * @param amount The amount to scale.
	 */
	public void enlarge(double amount);
	
	/**
	 * Enlarges the object.
	 * 
	 * @param scale The amount to scale.
	 * @param x The x axis of the center of the resize.
	 * @param y The y axis of the center of the resize.
	 */
	public void enlarge(double amount, int x, int y);
	
	/**
	 * Hides the object.
	 */
	public void hide();
	
	/**
	 * Shows the object.
	 */
	public void show();
}
