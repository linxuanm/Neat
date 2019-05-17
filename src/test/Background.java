package test;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;

public class Background extends GameObject {
	
	private static final double MAX_OPACITY = 0.6;
	
	private double opacity;
	private int delay;
	
	@Override
	public void start() {
		this.setImage("resources/images/square.png");
		this.setX(Minesweeper.FIELD_WIDTH * Minesweeper.CELL_SIZE / 2);
		this.setY(Minesweeper.FIELD_HEIGHT * Minesweeper.CELL_SIZE / 2 + Minesweeper.BAR_SIZE);
		this.setScaleX(Minesweeper.FIELD_WIDTH * Minesweeper.CELL_SIZE);
		this.setScaleY(Minesweeper.FIELD_HEIGHT * Minesweeper.CELL_SIZE);
		this.setColorEffect(MathUtil.convColorAdjust(0, 0, 0, 0));
		this.setOpacity(0);
	}

	@Override
	public void update() {
		if (this.delay++ >= 100) {
			if (this.opacity <= MAX_OPACITY) {
				this.setOpacity(this.opacity += 0.02);
			}
		}
	}
}
