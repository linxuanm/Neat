package test;

import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.MathUtil;
import cn.davidma.neat.util.PathUtil;
import javafx.scene.effect.ColorAdjust;

public class BackgroundBlock extends GameObject {
	
	private static final ColorAdjust DARK = MathUtil.convColorAdjust(40, 40, 37, 1);
	private static final ColorAdjust LIGHT = MathUtil.convColorAdjust(40, 30, 40, 1);
	
	private boolean dark;
	
	public BackgroundBlock(boolean dark) {
		super();
		this.dark = dark;
	}

	@Override
	public void start() {
		this.setScaleX(Minesweeper.CELL_SIZE);
		this.setScaleY(Minesweeper.CELL_SIZE);
		this.setImage(PathUtil.fromRelativePath(BackgroundBlock.class, "resources/images/square.png"));
		this.setColorEffect(this.dark ? DARK : LIGHT);
	}

	@Override
	public void update() {
		
	}
}
