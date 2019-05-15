package test;

import cn.davidma.neat.application.InputHandler;
import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.object.GameObject;

public class ExampleObject extends GameObject {
	
	private int deltaX = 1;
	private int deltaY = 1;
	private double speed = 5;
	
	@Override
	public void start() {
		this.setImage("resources/images/Ball.png");
		this.setScaleX(0.25);
		this.setScaleY(0.25);
	}

	@Override
	public void update() {
		/*
		this.moveX((int) (this.deltaX * this.speed));
		this.moveY((int) (this.deltaY * this.speed));
		if (this.getX() >= NeatGame.getInstance().getWidth() || this.getX() <= 0) {
			this.deltaX *= -1;
		}
		if (this.getY() >= NeatGame.getInstance().getHeight() || this.getY() <= 0) {
			this.deltaY *= -1;
		}*/
		if (InputHandler.isKeyDown("A")) this.moveX(-1);
		if (InputHandler.isKeyDown("D")) this.moveX(1);
		if (InputHandler.isKeyDown("S")) this.moveY(1);
		if (InputHandler.isKeyDown("W")) this.moveY(-1);
	}
}
