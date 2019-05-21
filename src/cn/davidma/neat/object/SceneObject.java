package cn.davidma.neat.object;

import cn.davidma.neat.application.InputManager;
import cn.davidma.neat.application.InputManager.MouseEvent;
import cn.davidma.neat.capability.IRelative;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.layout.LayoutObject;
import cn.davidma.neat.util.StrUtil;
import javafx.scene.Node;

/**
 * The basic object that is meant to be added in the game.
 * It should be able to be displayed on the screen.
 * 
 * @param R The type of {@link javafx.scene.Node} the SceneObject is supposed to produce.
 * 
 * @author David Ma
 */
public abstract class SceneObject<R extends Node> extends LayoutObject implements IRelative {
	
	/**
	 * The unique ID given to each LayoutObject;
	 * this will be randomly assigned if left empty.
	 */
	private String id;
	/**
	 * The {@link javafx.scene.Node} the SceneObject is supposed to produce.
	 */
	protected R renderCache;
	/**
	 * Whether this SceneObject should be removed.
	 */
	private boolean shouldRemove;
	private GameScene parentScene;
	
	/**
	 * Whether the mouse is hovering on this SceneObject.
	 */
	private boolean hover;
	
	private int x;
	private int y;
	private double rotation;
	private double scaleX = 1;
	private double scaleY = 1;
	private double opacity = 1;
	private boolean showing = true;
	
	public SceneObject(R renderCache) {
		this.renderCache = renderCache;
		this.renderCache.setOnMouseEntered(event -> this.onMouseEnter());
		this.renderCache.setOnMouseExited(event -> this.onMouseExit());
		this.renderCache.setOnMouseClicked(event -> this.onClick(new MouseEvent(event)));
	}
	
	/**
	 * Called when the GameObject is first added to the scene.
	 */
	public abstract void start();
	/**
	 * Called every tick.
	 */
	public abstract void update();
	
	/**
	 * Constructs the {@link #renderCache} from the properties.
	 */
	public void constructRender() {
		this.setX(this.getX());
		this.setY(this.getY());
		this.setScaleX(this.getScaleX());
		this.setScaleY(this.getScaleY());
		this.setRotation(this.getRotation());
		this.setOpacity(this.getOpacity());
	}
	
	/**
	 * Gets the initial Node to be added to {@link javafx.scene.Scene}.
	 * 
	 * @return The Node to be added to {@link javafx.scene.Scene}.
	 */
	public R getRenderNode() {
		return this.renderCache;
	}
	
	/**
	 * Brings the SceneObject to the front of the scene.
	 */
	public SceneObject<R> bringToFront() {
		this.renderCache.toFront();
		
		return this;
	}
	
	/**
	 * Brings the SceneObject to the back of the scene.
	 */
	public SceneObject<R> bringToBack() {
		this.renderCache.toBack();
		
		return this;
	}
	
	/**
	 * Called when the SceneObject is clicked.
	 */
	public void onClick(InputManager.MouseEvent mouseEvent) {
		
	}
	
	/**
	 * Called when the mouse move to this object.
	 */
	public void onMouseEnter() {
		this.hover = true;
	}
	
	/**
	 * Called when the mouse leave this object.
	 */
	public void onMouseExit() {
		this.hover = false;
	}
	
	public boolean isMouseHover() {
		return this.hover;
	}
	
	public String getId() {
		return this.id;
	}
	
	public SceneObject<R> setId(String id) {
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
	
	public SceneObject<R> setX(int x) {
		this.x = x;
		
		return this;
	}
	
	@Override
	public void moveX(int xOffset) {
		this.setX(this.x + xOffset);
	}
	
	public int getY() {
		return this.y;
	}
	
	public SceneObject<R> setY(int y) {
		this.y = y;
		
		return this;
	}

	@Override
	public void moveY(int yOffset) {
		this.setY(this.y + yOffset);
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public SceneObject<R> setRotation(double rotation) {
		this.rotation = rotation;
		this.renderCache.setRotate(this.rotation);
		
		return this;
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
	
	public SceneObject<R> setScaleX(double scaleX) {
		this.scaleX = scaleX;
		
		return this;
	}
	
	public double getScaleY() {
		return this.scaleY;
	}
	
	public SceneObject<R> setScaleY(double scaleY) {
		this.scaleY = scaleY;
		
		return this;
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
		return this.showing ? this.opacity : 0;
	}
	
	public SceneObject<R> setOpacity(double opacity) {
		this.opacity = opacity;
		this.renderCache.setOpacity(this.getOpacity());
		
		return this;
	}
	
	public boolean isVisible() {
		return this.showing;
	}
	
	/**
	 * Mark that this SceneObject should be removed.
	 */
	public void setShouldRemove() {
		this.shouldRemove = true;
	}
	
	public boolean shouldRemove() {
		return this.shouldRemove;
	}

	@Override
	public void hide() {
		this.showing = false;
	}

	@Override
	public void show() {
		this.showing = true;
	}
}
