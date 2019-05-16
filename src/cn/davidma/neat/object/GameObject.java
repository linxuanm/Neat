package cn.davidma.neat.object;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends SceneObject {
	
	private Image image;
	private ImageView renderCache;
	
	public GameObject() {
		this.renderCache = new ImageView();
		this.renderCache.setOnMouseEntered(event -> this.onMouseEnter());
		this.renderCache.setOnMouseExited(event -> this.onMouseExit());
		this.renderChanged = true;
	}
	
	/**
	 * Sets the image of the GameObject.
	 * 
	 * @param path The relative path (from the location of the children class) of the image.
	 */
	public void setImage(String path) {
		this.image = new Image(this.getClass().getResource(path).toExternalForm());
		this.renderChanged = true;
	}
	
	@Override
	public void render() {
		if (this.isVisible() && this.image != null) {
			if (this.renderChanged) {
				double targetWidth = image.getWidth() * this.getScaleX();
				double targetHeight = image.getHeight() * this.getScaleY();
				
				this.renderCache.setImage(this.image);
				this.renderCache.setFitWidth(targetWidth);
				this.renderCache.setFitHeight(targetHeight);
				this.renderCache.setX(this.getX() - targetWidth / 2);
				this.renderCache.setY(this.getY() - targetHeight / 2);
				this.renderCache.setRotate(this.getRotation());
				this.renderCache.setOpacity(this.getOpacity());
				this.renderChanged = false;
			}
		}
	}
	
	@Override
	public ImageView getRenderNode() {
		return this.renderCache;
	}
	
	/**
	 * Sets the color adjust of the ImageView of this GameObject.
	 * 
	 * @param colorAdjust The color adjust to be set.
	 */
	public void setColorEffect(ColorAdjust colorAdjust) {
		this.renderCache.setEffect(colorAdjust);
		this.renderChanged = true;
	}
}
