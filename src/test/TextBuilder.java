package test;

import cn.davidma.neat.object.ui.GameText;

public enum TextBuilder {
	
	BLUE(25, 118, 210),
	GREEN(56, 142, 60),
	RED(211, 47, 47),
	PURPLE(123, 31, 162);
	
	private int r;
	private int g;
	private int b;
	
	private TextBuilder(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public GameText build(int num) {
		GameText gameText = new GameText(String.valueOf(num));
		gameText.setFont("Arier");
		gameText.setSize(Minesweeper.CELL_SIZE * 0.9);
		gameText.setColor(this.r, this.g, this.b);
		return gameText;
	}
	
	public static GameText getText(int num) {
		return values()[(num - 1) % values().length].build(num);
	}
}
