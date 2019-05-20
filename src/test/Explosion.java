package test;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;
import cn.davidma.neat.util.PathUtil;

public class Explosion extends GameObject {
	
	private static final double MAX_OPACITY = 0.8;
	
	private double opacity;
	
	@Override
	public void start() {
		this.setImage(PathUtil.fromRelativePath(Explosion.class, "resources/images/square.png"));
		this.setScaleX(Minesweeper.CELL_SIZE);
		this.setScaleY(Minesweeper.CELL_SIZE);
		this.setColorEffect(MathUtil.convColorAdjust(0, 80, 50, 1));
		this.opacity = 0;
	}

	@Override
	public void update() {
		if (this.opacity <= MAX_OPACITY) {
			this.setOpacity(this.opacity += 0.02);
		}
	}
}
