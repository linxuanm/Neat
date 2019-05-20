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
		super(new Text());
		this.text = "";
		this.font = "Courier";
		this.size = 10;
		this.color = Color.BLACK;
	}
	
	public GameText(String text) {
		this();
		this.text = text;
	}
	
	public GameText setColor(String color) {
		this.color = Color.web(color);
		this.renderCache.setFill(this.color);
		
		return this;
	}
	
	public GameText setColor(int r, int g, int b) {
		this.color = Color.rgb(r, g, b);
		this.renderCache.setFill(this.color);
		
		return this;
	}
	
	public double getSize() {
		return this.size;
	}
	
	public GameText setSize(double size) {
		this.size = size;
		this.renderCache.setFont(new Font(this.font, this.size));
		
		return this;
	}
	
	public String getFont() {
		return this.font;
	}
	
	public GameText setFont(String font) {
		this.font = font;
		this.renderCache.setFont(new Font(this.font, this.size));
		
		return this;
	}
	
	public String getText() {
		return this.text;
	}
	
	public GameText setText(String text) {
		this.text = text;
		this.renderCache.setText(this.text);
		this.setX(this.getX());
		this.setY(this.getY());
		
		return this;
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
	public GameText setX(int x) {
		super.setX(x);
		this.renderCache.setX(this.getX() - this.getScaleX() / 2);
		
		return this;
	}
	
	@Override
	public GameText setY(int y) {
		super.setY(y);
		this.renderCache.setY(this.getY() + this.getScaleY() / 2);
		
		return this;
	}
}
