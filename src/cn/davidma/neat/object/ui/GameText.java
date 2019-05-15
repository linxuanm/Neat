package cn.davidma.neat.object.ui;

import cn.davidma.neat.object.SceneObject;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameText extends SceneObject {
	
	private String text;
	private double size;
	private String font;
	private Text renderCache;
	
	public GameText() {
		this.text = "";
		this.font = "Courier";
		this.size = 10;
	}
	
	public GameText(String text) {
		this();
		this.text = text;
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
	public void render(javafx.scene.Group screen) {
		if (this.isVisible()) {
			if (this.renderChanged || this.renderCache == null) {
				Text render = new Text(this.text);
				render.setFont(new Font(this.font, this.size));
				render.setX(this.getX());
				render.setY(this.getY());
				render.setRotate(this.getRotation());
				render.setOpacity(this.getOpacity());
				
				this.renderCache = render;
				this.renderChanged = false;
			}
			screen.getChildren().add(this.renderCache);
		}
	}
}
