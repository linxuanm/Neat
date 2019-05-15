package cn.davidma.neat.object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends SceneObject {
	
	private Image image;
	private ImageView renderCache;
	
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
	public void render(javafx.scene.Group screen) {
		if (this.isVisible() && this.image != null) {
			if (this.renderChanged || this.renderCache == null) {
				double targetWidth = image.getWidth() * this.getScaleX();
				double targetHeight = image.getHeight() * this.getScaleY();
				
				ImageView render = new ImageView(this.image);
				render.setFitWidth(targetWidth);
				render.setFitHeight(targetHeight);
				render.setX(this.getX() - targetWidth / 2);
				render.setY(this.getY() - targetHeight / 2);
				render.setRotate(this.getRotation());
				render.setOpacity(this.getOpacity());
				
				this.renderCache = render;
				this.renderChanged = false;
			}
			screen.getChildren().add(this.renderCache);
		}
	}
}
