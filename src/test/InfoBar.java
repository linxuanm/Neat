package test;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;

public class InfoBar extends GameObject {
	
	@Override
	public void start() {
		this.setImage("resources/images/square.png");
		this.setScaleX(Minesweeper.CELL_SIZE * Minesweeper.FIELD_WIDTH);
		this.setScaleY(Minesweeper.BAR_SIZE);
		this.setX((int) (this.getScaleX() / 2));
		this.setY(Minesweeper.BAR_SIZE / 2);
		this.setColorEffect(MathUtil.convColorAdjust(85, 80, 30, 0));
	}

	@Override
	public void update() {
		
	}
	
}
