package cn.davidma.neat.object.ui;

import cn.davidma.neat.object.SceneObject;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameText extends SceneObject {
	
	private String text;
	private double size;
	private String font;
	private Color color;
	private Text renderCache;
	
	public GameText() {
		this.text = "";
		this.font = "Courier";
		this.size = 10;
		this.color = Color.BLACK;
		this.renderCache = new Text();
		this.renderChanged = true;
		this.renderCache.setOnMouseEntered(event -> this.onMouseEnter());
		this.renderCache.setOnMouseExited(event -> this.onMouseExit());
	}
	
	public GameText(String text) {
		this();
		this.text = text;
	}
	
	public void setColor(String color) {
		this.color = Color.web(color);
		this.renderChanged = true;
	}
	
	public void setColor(int r, int g, int b) {
		this.color = Color.rgb(r, g, b);
		this.renderChanged = true;
	}
	
	public double getSize() {
		return this.size;
	}
	
	public void setSize(double size) {
		if (size != this.size) {
			this.size = size;
			this.renderChanged = true;
		}
	}
	
	public String getFont() {
		return this.font;
	}
	
	public void setFont(String font) {
		if (!font.equals(this.font)) {
			this.font = font;
			this.renderChanged = true;
		}
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		if (!text.equals(this.text)) {
			this.text = text;
			this.renderChanged = true;
		}
	}
	
	@Override
	public void start() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		if (this.isVisible()) {
			if (this.renderChanged) {
				this.renderCache.setText(this.text);
				this.renderCache.setFont(new Font(this.font, this.size));
				this.renderCache.setFill(this.color);
				this.renderCache.setX(this.getX() - this.getScaleX() / 2);
				
				// Opposite because text's pivot is at the bottom.
				this.renderCache.setY(this.getY() + this.getScaleY() / 2);
				
				this.renderCache.setRotate(this.getRotation());
				this.renderCache.setOpacity(this.getOpacity());
				
				this.renderChanged = false;
			}
		}
	}
	
	/**
	 * Returns the width of the text.
	 */
	@Override
	public double getScaleX() {
		return this.renderCache.getLayoutBounds().getWidth();
	}
	
	/**
	 * Returns the height of the text.
	 */
	@Override
	public double getScaleY() {
		return this.renderCache.getLayoutBounds().getHeight();
	}

	@Override
	public Text getRenderNode() {
		return this.renderCache;
	}
	
	@Override
	public void bringToFront() {
		this.renderCache.toFront();
	}
	
	@Override
	public void bringToBack() {
		this.renderCache.toBack();
	}
}
