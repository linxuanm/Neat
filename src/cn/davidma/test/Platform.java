package cn.davidma.test;

import cn.davidma.neat.application.InputManager;
import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;
import cn.davidma.neat.util.PathUtil;

public class Platform extends GameObject {
	
	public static final int WIDTH = 150;
	public static final int HEIGHT = 20;
	public static final int SPEED = 10;
	
	@Override
	public void start() {
		this.setImage(PathUtil.fromRelativePath(MainGame.class, "images/square.png"));
		this.setX(NeatGame.getInstance().getWidth() / 2);
		this.setY(NeatGame.getInstance().getHeight() - 50);
		this.setScaleX(WIDTH);
		this.setScaleY(HEIGHT);
		this.setColorEffect(MathUtil.convColorAdjust(0, 0, 0, 0));
	}

	@Override
	public void update() {
		if (InputManager.isKeyDown("LEFT")) {
			this.moveX(-SPEED);
		}
		if (InputManager.isKeyDown("RIGHT")) {
			this.moveX(SPEED);
		}
	}
}
