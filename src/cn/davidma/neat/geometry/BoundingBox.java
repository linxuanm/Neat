package cn.davidma.neat.geometry;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;

/**
 * An instance of this class specifies a rectangular space in 2-dimension.
 * 
 * <p>
 * Mainly used for collision detection.
 * </p>
 * 
 * @author David Ma
 */
public class BoundingBox {
	
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	
	public BoundingBox(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	
	public BoundingBox(GameObject gameObject) {
		this.startX = this.endX = gameObject.getX();
		this.startY = this.endY = gameObject.getY();
		int halfX = (int) (gameObject.getFitWidth() / 2);
		int halfY = (int) (gameObject.getFitHeight() / 2);
		this.startX -= halfX;
		this.endX += halfX;
		this.startY -= halfY;
		this.endY += halfY;
	}
	
	/**
	 * Gets the midpoint of this BoundingBox.
	 * 
	 * @return The midpoint.
	 */
	public Point getMidPoint() {
		return new Point(this.startX + (this.getWidth()) / 2, this.startY + (this.getHeight()) / 2);
	}
	
	/**
	 * Gets the vertices of this BoundingBox as an array [top-right, top-left, bottom-left, bottom-right].
	 * 
	 * @return An array of vertices.
	 */
	public Point[] getVertices() {
		return new Point[] {
			new Point(this.endX, this.startY),
			new Point(this.startX, this.startY),
			new Point(this.startX, this.endY),
			new Point(this.endX, this.endY)
		};
	}
	
	/**
	 * Gets the angle (in radians) from the midpoint to each vertex as an array.
	 * 
	 * @return An array of angles (in radians).
	 */
	public double[] getVertsAngles() {
		double[] angles = new double[4];
		Point[] points = this.getVertices();
		Point mid = this.getMidPoint();
		for (int i = 0; i < 4; i++) {
			angles[i] = MathUtil.getAngle(mid.x, mid.y, points[i].x, points[i].y);
		}
		return angles;
	} 
	
	/**
	 * Gets the width of this BoundingBox.
	 * 
	 * @return The width of this BoundingBox.
	 */
	public int getWidth() {
		return this.endX - this.startX;
	}
	
	/**
	 * Gets the height of this BoundingBox.
	 * 
	 * @return The height of this BoundingBox.
	 */
	public int getHeight() {
		return this.endY - this.startY;
	}
	
	public static class Point {
		
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
