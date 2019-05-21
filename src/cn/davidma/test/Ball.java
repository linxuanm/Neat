package cn.davidma.test;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.PathUtil;

public class Ball extends GameObject {
	
	private int deltaX = 1;
	private int deltaY = 1;
	
	@Override
	public void start() {
		this.setImage(PathUtil.fromRelativePath(Ball.class, "images/ball.png"));
		this.setX(100).setY(100).setScaleX(50).setScaleY(50);
	}

	@Override
	public void update() {
		this.moveX(this.deltaX * 10);
		this.moveY(this.deltaY * 10);
		MainGame game = (MainGame) NeatGame.getInstance();
		boolean hitPlatform = this.getX() > game.platform.getX() - Platform.WIDTH / 2 && this.getX() < game.platform.getX() + Platform.WIDTH / 2 && this.getY() + 25 > game.platform.getY() - Platform.HEIGHT / 2 && this.getY() + 25 < game.platform.getY() + Platform.HEIGHT / 2;
		if (this.getX() < 0 || this.getX() > game.getWidth()) {
			this.deltaX *= -1;
		}
		if (this.getY() < 0 || this.getY() > game.getHeight() || hitPlatform) {
			this.deltaY *= -1;
		}
	}
}
