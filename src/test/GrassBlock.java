package test;

import cn.davidma.neat.application.InputHandler.MouseEvent;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;
import javafx.scene.effect.ColorAdjust;

public class GrassBlock extends GameObject {
	
	private static final ColorAdjust DARK = MathUtil.convColorAdjust(85, 45, 34, 1);
	private static final ColorAdjust LIGHT = MathUtil.convColorAdjust(85, 45, 36, 1);
	private static final ColorAdjust HOVER = MathUtil.convColorAdjust(85, 45, 38, 1);
	
	private boolean dark;
	
	public GrassBlock(boolean dark) {
		super();
		this.dark = dark;
	}
	
	@Override
	public void start() {
		this.setScaleX(Minesweeper.CELL_SIZE);
		this.setScaleY(Minesweeper.CELL_SIZE);
		this.setImage("resources/images/square.png");
		this.setColorEffect(this.dark ? DARK : LIGHT);
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void onClick(MouseEvent mouseEvent) {
		this.removeFromScene();
	}
	
	@Override
	public void onMouseEnter() {
		this.setColorEffect(HOVER);
	}
	
	@Override
	public void onMouseExit() {
		this.setColorEffect(this.dark ? DARK : LIGHT);
	}
}
