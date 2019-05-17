package test;

import cn.davidma.neat.application.InputHandler.MouseEvent;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;
import javafx.scene.effect.ColorAdjust;

public class GrassBlock extends GameObject {
	
	private static final ColorAdjust DARK = MathUtil.convColorAdjust(85, 45, 34, 1);
	private static final ColorAdjust LIGHT = MathUtil.convColorAdjust(85, 45, 36, 1);
	private static final ColorAdjust HOVER = MathUtil.convColorAdjust(85, 45, 38, 1);
	private static final int FALL_LENGTH = 40;
	
	private int x;
	private int y;
	private boolean dark;
	
	// Used for fall off aimation.
	private int falling;
	private double horizontal;
	private double vertical;
	
	public GrassBlock(int x, int y, boolean dark) {
		super();
		this.x = x;
		this.y = y;
		this.dark = dark;
		this.falling = -1;
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
	
	@Override
	public void onClick(MouseEvent mouseEvent) {
		Minesweeper.processClick(this.x, this.y);
	}
	
	@Override
	public void onMouseEnter() {
		if (this.falling < 0) this.setColorEffect(HOVER);
	}
	
	@Override
	public void onMouseExit() {
		if (this.falling < 0) this.setColorEffect(this.dark ? DARK : LIGHT);
	}
	
	public void setClick() {
		this.bringToFront();
		this.falling = FALL_LENGTH;
		int force = Minesweeper.rand.nextInt(6);
		this.horizontal = force - 3;
		this.vertical = -force - 3;
	}
}
