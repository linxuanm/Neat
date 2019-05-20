package cn.davidma.neat.object.ui;

import cn.davidma.neat.object.SceneObject;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameText extends SceneObject<Text> {
	
	private String text;
	private double size;
	private String font;
	private Color color;
	
	public GameText() {
		this.text = "";
		this.font = "Courier";
		this.size = 10;
		this.color = Color.BLACK;
		this.renderCache = new Text();
		this.renderCache.setOnMouseEntered(event -> this.onMouseEnter());
		this.renderCache.setOnMouseExited(event -> this.onMouseExit());
	}
	
	public GameText(String text) {
		this();
		this.text = text;
	}
	
	public void setColor(String color) {
		this.color = Color.web(color);
		this.renderCache.setFill(this.color);
	}
	
	public void setColor(int r, int g, int b) {
		this.color = Color.rgb(r, g, b);
		this.renderCache.setFill(this.color);
	}
	
	public double getSize() {
		return this.size;
	}
	
	public void setSize(double size) {
		this.size = size;
		this.renderCache.setFont(new Font(this.font, this.size));
	}
	
	public String getFont() {
		return this.font;
	}
	
	public void setFont(String font) {
		this.font = font;
		this.renderCache.setFont(new Font(this.font, this.size));
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
		this.renderCache.setText(this.text);
		this.setX(this.getX());
		this.setY(this.getY());
	}
	
	@Override
	public void start() {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void constructRender() {
		super.constructRender();
		this.renderCache.setFill(this.color);
		this.setFont(this.getFont());
		this.setText(this.getText());
		this.setSize(this.getSize());
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
	public void setX(int x) {
		super.setX(x);
		this.renderCache.setX(this.getX() - this.getScaleX() / 2);
	}
	
	@Override
	public void setY(int y) {
		super.setY(y);
		this.renderCache.setY(this.getY() + this.getScaleY() / 2);
	}
}
