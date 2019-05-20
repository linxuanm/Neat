package test;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.PathUtil;

public class Flag extends GameObject {
	
	// Used for fall off aimation.
	private int falling;
	private double horizontal;
	private double vertical;
	
	@Override
	public void start() {
		this.setImage(PathUtil.fromRelativePath(Flag.class, "resources/images/flag.png"));
		this.setScaleX(Minesweeper.CELL_SIZE * 0.8D);
		this.setScaleY(Minesweeper.CELL_SIZE * 0.8D);
		this.falling = -1;
	}

	@Override
	public void update() {
		if (this.falling >= 0) {
			this.moveX((int) this.horizontal);
			this.moveY((int) Math.min(this.vertical += 0.5, 10));
			this.rotate(this.horizontal);
			this.enlarge(0.94);
			if (--this.falling == 0) {
				this.removeFromScene();
			}
		}
	}
	
	public void setClick() {
		this.bringToFront();
		this.falling = GrassBlock.FALL_LENGTH;
		int force = Minesweeper.rand.nextInt(6);
		this.horizontal = force - 3;
		this.vertical = -force - 3;
	}
}
