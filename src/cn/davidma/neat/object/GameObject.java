package cn.davidma.neat.object;

import cn.davidma.neat.geometry.BoundingBox;
import cn.davidma.neat.geometry.CollisionType;
import cn.davidma.neat.util.MathUtil;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends SceneObject<ImageView> {
	
	private String path;
	private Image image;
	/**
	 * The bounding box of this GameObject. Used for collision detection.
	 */
	private BoundingBox boundingBox;
	
	public GameObject() {
		super(new ImageView());
		this.boundingBox = new BoundingBox(0, 0, 0, 0);
	}
	
	/**
	 * Sets the image of the GameObject.
	 * 
	 * @param path The relative path (from the location of the children class) of the image.
	 */
	public GameObject setImage(String path) {
		this.path = path;
		this.image = new Image(this.path);
		this.renderCache.setImage(this.image);
		this.updateBoundingBox();
		
		return this;
	}
	
	/**
	 * Sets the color adjust of the ImageView of this GameObject.
	 * 
	 * @param colorAdjust The color adjust to be set.
	 */
	public GameObject setColorEffect(ColorAdjust colorAdjust) {
		this.renderCache.setEffect(colorAdjust);
		
		return this;
	}
	
	@Override
	public GameObject setX(int x) {
		super.setX(x);
		this.renderCache.setX(this.getX() - this.renderCache.getFitWidth() / 2);
		this.updateBoundingBox();
		
		return this;
	}
	
	@Override
	public GameObject setY(int y) {
		super.setY(y);
		this.renderCache.setY(this.getY() - this.renderCache.getFitHeight() / 2);
		this.updateBoundingBox();
		
		return this;
	}
	
	@Override
	public GameObject setScaleX(double scaleX) {
		super.setScaleX(scaleX);
		this.renderCache.setFitWidth(this.getScaleX());
		this.setX(this.getX());
		
		return this;
	}
	
	@Override
	public GameObject setScaleY(double scaleY) {
		super.setScaleY(scaleY);
		this.renderCache.setFitHeight(this.getScaleY());
		this.setY(this.getY());
		
		return this;
	}
	
	@Override
	public void constructRender() {
		super.constructRender();
	}
	
	@Override
	public boolean isUpdatable() {
		return true;
	}
	
	/**
	 * Updates the bounding box of this GameObject.
	 */
	private void updateBoundingBox() {
		this.boundingBox = new BoundingBox(this);
	}
	
	/**
	 * Gets the width of the display on screen of this GameObject.
	 * 
	 * @return The width of the display on screen of this GameObject.
	 */
	public double getFitWidth() {
		return this.renderCache.getFitWidth();
	}
	
	/**
	 * Gets the height of the display on screen of this GameObject.
	 * 
	 * @return The height of the display on screen of this GameObject.
	 */
	public double getFitHeight() {
		return this.renderCache.getFitHeight();
	}
	
	/**
	 * Gets the bounding box of this GameObject.
	 * 
	 * @return The bounding box.
	 */
	public BoundingBox getBoundingBox() {
		return this.boundingBox;
	}
	
	/**
	 * Whether this GameObject is colliding with the given GameObject.
	 * 
	 * @param other The other GameObject.
	 * @return Whether they are colliding.
	 */
	public boolean collidingWith(GameObject other) {
		return MathUtil.boundingBoxOverlap(this.getBoundingBox(), other.getBoundingBox());
	}
	
	public CollisionType getCollisionTypeWith(GameObject other) {
		return MathUtil.getBoundingBoxCollision(this.getBoundingBox(), other.getBoundingBox());
	}
	
	/**
	 * Whether this GameObject is touching the given point.
	 * 
	 * @param x The x position of the point.
	 * @param y The y position of the point.
	 * @return Whether they are touching.
	 */
	public boolean collidingWithPoint(int x, int y) {
		return MathUtil.pointInBoundingBox(x, y, this.getBoundingBox());
	} 
}
