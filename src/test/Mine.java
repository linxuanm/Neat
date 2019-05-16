package test;

import cn.davidma.neat.object.GameObject;

public class Mine extends GameObject {

	@Override
	public void start() {
		this.setImage("resources/images/mine.png");
		this.setScaleX(Minesweeper.CELL_SIZE / 140D);
		this.setScaleY(Minesweeper.CELL_SIZE / 140D);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
