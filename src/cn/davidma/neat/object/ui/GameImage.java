package cn.davidma.neat.object.ui;

import cn.davidma.neat.object.GameObject;

/**
 * An image for display purposes only.
 * 
 * @author David Ma
 */
public class GameImage extends GameObject {
	
	public GameImage(String path) {
		super();
		this.setImage(path);
	}
	
	public GameImage(String path, int x, int y, double scaleX, double scaleY) {
		this(path);
		this.setX(x).setY(y).setScaleX(scaleX).setScaleY(scaleY);
	}
	
	@Override
	public void start() {
		
	}

	@Override
	public void update() {
		
	}
}
