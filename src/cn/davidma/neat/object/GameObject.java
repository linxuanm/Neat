package cn.davidma.neat.object;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends SceneObject<ImageView> {
	
	private String path;
	private Image image;
	
	public GameObject() {
		super(new ImageView());
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
		
		return this;
	}
	
	@Override
	public GameObject setY(int y) {
		super.setY(y);
		this.renderCache.setY(this.getY() - this.renderCache.getFitHeight() / 2);
		
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
}
