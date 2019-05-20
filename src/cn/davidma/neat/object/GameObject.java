package cn.davidma.neat.object;

import cn.davidma.neat.application.InputHandler.MouseEvent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends SceneObject<ImageView> {
	
	private String path;
	private Image image;
	
	public GameObject() {
		this.renderCache = new ImageView();
		this.renderCache.setOnMouseEntered(event -> this.onMouseEnter());
		this.renderCache.setOnMouseExited(event -> this.onMouseExit());
		this.renderCache.setOnMouseClicked(event -> this.onClick(new MouseEvent(event)));
	}
	
	/**
	 * Sets the image of the GameObject.
	 * 
	 * @param path The relative path (from the location of the children class) of the image.
	 */
	public void setImage(String path) {
		this.path = path;
		this.image = new Image(this.path);
		this.renderCache.setImage(this.image);
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
	public void setX(int x) {
		super.setX(x);
		this.renderCache.setX(this.getX() - this.renderCache.getFitWidth() / 2);
	}
	
	@Override
	public void setY(int y) {
		super.setY(y);
		this.renderCache.setY(this.getY() - this.renderCache.getFitHeight() / 2);
	}
	
	@Override
	public void setScaleX(double scaleX) {
		super.setScaleX(scaleX);
		this.renderCache.setFitWidth(this.getScaleX());
		this.setX(this.getX());
	}
	
	@Override
	public void setScaleY(double scaleY) {
		super.setScaleY(scaleY);
		this.renderCache.setFitHeight(this.getScaleY());
		this.setY(this.getY());
	}
	
	@Override
	public void constructRender() {
		super.constructRender();
	}
}
