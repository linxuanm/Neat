package cn.davidma.neat.object;

import cn.davidma.neat.application.InputHandler;
import cn.davidma.neat.capability.IRelative;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.layout.LayoutObject;
import cn.davidma.neat.util.StrUtil;
import javafx.scene.Node;

/**
 * The basic object that is meant to be added in the game.
 * It should be able to be displayed on the screen.
 * 
 * @author David Ma
 */
public abstract class SceneObject extends LayoutObject implements IRelative {
	
	/**
	 * The unique ID given to each LayoutObject;
	 * this will be randomly assigned if left empty.
	 */
	private String id;
	private GameScene parentScene;
	
	/**
	 * Whether this object's render should be recalculated next frame.
	 */
	protected boolean renderChanged;
	
	private int x;
	private int y;
	private double rotation;
	private double scaleX = 1;
	private double scaleY = 1;
	private double opacity = 1;
	private boolean showing = true;
	
	/**
	 * Called when the GameObject is first added to the scene.
	 */
	public abstract void start();
	/**
	 * Called every tick.
	 */
	public abstract void update();
	/**
	 * Renders the SceneObject.
	 */
	public abstract void render();
	/**
	 * Gets the initial Node to be added to {@link javafx.scene.Scene}.
	 * 
	 * @return The Node to be added to {@link javafx.scene.Scene}.
	 */
	public abstract Node getRenderNode();
	/**
	 * Brings the SceneObject to the front of the scene.
	 */
	public abstract void bringToFront();
	/**
	 * Brings the SceneObject to the back of the scene.
	 */
	public abstract void bringToBack();
	
	/**
	 * Called when the SceneObject is clicked.
	 */
	public void onClick(InputHandler.MouseEvent mouseEvent) {
		
	}
	
	/**
	 * Called when the mouse move to this object.
	 */
	public void onMouseEnter() {
		
	}
	
	/**
	 * Called when the mouse leave this object.
	 */
	public void onMouseExit() {
		
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns this for chaining purpose.
	 */
	public SceneObject setId(String id) {
		if (StrUtil.isEmpty(this.id)) {
			this.id = id;
		} else {
			throw new UnsupportedOperationException("The ID of a SceneObject can only be set once.");
		}
		
		return this;
	}
	
	/**
	 * Called when a SceneObject is removed from the scene.
	 */
	public void clearId() {
		this.id = "";
	}
	
	public GameScene getParentScene() {
		return this.parentScene;
	}
	
	/**
	 * Called when this SceneObject is added to a scene.
	 */
	public void addToScene(GameScene parentScene) {
		this.parentScene = parentScene;
	}
	
	/**
	 * Removes the SceneObject.
	 */
	public void removeFromScene() {
		if (this.parentScene != null) {
			this.parentScene.removeObject(this.id);
			this.clearId();
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		if (x != this.x) {
			this.x = x;
			this.renderChanged = true;
		}
	}
	
	@Override
	public void moveX(int xOffset) {
		this.setX(this.x + xOffset);
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		if (y != this.y) {
			this.y = y;
			this.renderChanged = true;
		}
	}

	@Override
	public void moveY(int yOffset) {
		this.setY(this.y + yOffset);
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public void setRotation(double rotation) {
		if (rotation != this.rotation) {
			this.rotation = rotation;
			this.renderChanged = true;
		}
	}

	@Override
	public void rotate(double angle) {
		this.setRotation(this.rotation + angle);
	}

	@Override
	public void rotate(double angle, int x, int y) {
		this.rotate(angle);
		
		
	}
	
	public double getScaleX() {
		return this.scaleX;
	}
	
	public void setScaleX(double scaleX) {
		if (scaleX != this.scaleX) {
			this.scaleX = scaleX;
			this.renderChanged = true;
		}
	}
	
	public double getScaleY() {
		return this.scaleY;
	}
	
	public void setScaleY(double scaleY) {
		if (scaleY != this.scaleY) {
			this.scaleY = scaleY;
			this.renderChanged = true;
		}
	}

	@Override
	public void enlarge(double amount) {
		this.setScaleX(this.scaleX * amount);
		this.setScaleY(this.scaleY * amount);
	}

	@Override
	public void enlarge(double amount, int x, int y) {
		this.enlarge(amount);
		this.setX((int) (this.x - (x - this.x) * amount));
		this.setY((int) (this.y - (y - this.y) * amount));
	}
	
	public double getOpacity() {
		return this.opacity;
	}
	
	public void setOpacity(double opacity) {
		if (opacity != this.opacity) {
			this.opacity = opacity;
			this.renderChanged = true;
		}
	}
	
	public boolean isVisible() {
		return this.showing;
	}

	@Override
	public void hide() {
		if (this.showing) {
			this.showing = false;
			this.renderChanged = true;
		}
	}

	@Override
	public void show() {
		if (!this.showing) {
			this.showing = true;
			this.renderChanged = true;
		}
	}
}
