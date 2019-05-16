package test;

import java.util.List;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.object.ui.GameText;
import test.Generation.BlockPos;

public class Minesweeper extends NeatGame {
	
	public static final int WINDOW_SIZE = 800;
	public static final int FIELD_WIDTH = 10;
	public static final int FIELD_HEIGHT = 10;
	public static final int CELL_SIZE = WINDOW_SIZE / Math.max(FIELD_WIDTH, FIELD_HEIGHT);
	public static final int MINE_COUNT = 10;
	public static final int[][] DIR = new int[][] {
		{-1, -1},
		{-1, 0},
		{-1, 1},
		{0, -1},
		{0, 0},
		{0, 1},
		{1, -1},
		{1, 0},
		{1, 1}
	};
	
	public static int[][] field;
	public static List<BlockPos> mines;
	
	public GameScene mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	protected void setup() {
		this.setTitle("Minesweeper");
		this.setBackgroundColor("#000000");
		this.setSize(CELL_SIZE * FIELD_WIDTH, CELL_SIZE * FIELD_HEIGHT + 70);
	}

	@Override
	protected void gameStart() {
		this.mainScene = new GameScene();
		this.setScene(this.mainScene);
		
		// Generate background.
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				GameObject background = new BackgroundBlock((j + i) % 2 == 0);
				background.setX(CELL_SIZE / 2 + j * CELL_SIZE);
				background.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
				this.mainScene.addChild(background);
			}
		}
		
		// Generate mines.
		field = new int[FIELD_WIDTH][FIELD_HEIGHT];
		mines = Generation.genMines(FIELD_WIDTH, FIELD_HEIGHT, MINE_COUNT);
		mines.forEach(mine -> {
			field[mine.x][mine.y] = -1;
			for (int[] i: DIR) {
				int newX = mine.x + i[0];
				int newY = mine.y + i[1];
				if (newX >= 0 && newX < FIELD_WIDTH && newY >= 0 && newY < FIELD_HEIGHT) {
					if (field[newX][newY] >= 0) {
						field[newX][newY]++;
					}
				}
			}
		});
		
		// Generate numbers.
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				int flag = field[j][i];
				if (flag > 0) {
					GameText text = TextBuilder.getText(flag);
					text.setX(CELL_SIZE / 2 + j * CELL_SIZE);
					text.setY(CELL_SIZE / 2 + i * CELL_SIZE - (15 * CELL_SIZE / 70) + 70);
					this.mainScene.addChild(text);
				} else if (flag == -1) {
					GameObject mine = new Mine();
					mine.setX(CELL_SIZE / 2 + j * CELL_SIZE);
					mine.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
					this.mainScene.addChild(mine);
				}
			}
		}
		
		// Generate grass.
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				GameObject grass = new GrassBlock((j + i) % 2 == 0);
				grass.setX(CELL_SIZE / 2 + j * CELL_SIZE);
				grass.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
				grass.setId(String.format("grass_%d_%d", j, i));
				this.mainScene.addChild(grass);
			}
		}
	}
}
