package cn.davidma.neat.object;

import cn.davidma.neat.capability.IRelative;
import cn.davidma.neat.layout.LayoutObject;

/**
 * The basic object that is meant to be added in the game.
 * It should be able to be displayed on the screen.
 * 
 * @author David Ma
 */
public abstract class GameObject extends LayoutObject implements IRelative {
	
	private int x;
	private int y;
	private double rotation;
	private double scale;
	private double opacity;
	private boolean showing;
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public void moveX(int xOffset) {
		this.x += xOffset;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void moveY(int yOffset) {
		this.y += yOffset;
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	@Override
	public void rotate(double angle) {
		this.rotation += angle;
	}

	@Override
	public void rotate(double angle, int x, int y) {
		this.rotate(angle);
		
		
	}
	
	public double getScale() {
		return this.scale;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	public void enlarge(double amount) {
		this.scale += amount;
	}

	@Override
	public void enlarge(double amount, int x, int y) {
		this.enlarge(amount);
		
		this.x -= (x - this.x) * amount;
		this.y -= (y - this.y) * amount;
	}
	
	public double getOpacity() {
		return this.opacity;
	}
	
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	
	public boolean isVisible() {
		return this.showing;
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
