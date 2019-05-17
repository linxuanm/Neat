package test;

import java.util.List;
import java.util.Random;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.object.ui.GameText;
import test.BlockLogic.BlockPos;

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
	
	public static Random rand;
	private static GameScene mainScene;
	private static int[][] field;
	private static BlockState[][] state;
	private static List<BlockPos> mines;
	private static GrassBlock[] grassBlocks;
	
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
		mainScene = new GameScene();
		setScene(mainScene);
		rand = new Random();
		
		// Generate background.
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				GameObject background = new BackgroundBlock((j + i) % 2 == 0);
				background.setX(CELL_SIZE / 2 + j * CELL_SIZE);
				background.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
				mainScene.addChild(background);
			}
		}
		
		// Generate mines.
		field = new int[FIELD_WIDTH][FIELD_HEIGHT];
		state = new BlockState[FIELD_WIDTH][FIELD_HEIGHT];
		mines = BlockLogic.genMines(FIELD_WIDTH, FIELD_HEIGHT, MINE_COUNT);
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
					mainScene.addChild(text);
				} else if (flag == -1) {
					GameObject mine = new Mine();
					mine.setX(CELL_SIZE / 2 + j * CELL_SIZE);
					mine.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
					mainScene.addChild(mine);
				}
			}
		}
		
		// Generate grass.
		grassBlocks = new GrassBlock[FIELD_WIDTH * FIELD_HEIGHT];
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				state[j][i] = BlockState.GRASS;
				GrassBlock grass = new GrassBlock(j, i, (j + i) % 2 == 0);
				grass.setX(CELL_SIZE / 2 + j * CELL_SIZE);
				grass.setY(CELL_SIZE / 2 + i * CELL_SIZE + 70);
				grass.setId(String.format("grass_%d_%d", j, i));
				grassBlocks[i * FIELD_WIDTH + j] = grass;
				mainScene.addChild(grass);
			}
		}
	}

	public static void processClick(int x, int y) {
		BlockLogic.massClick(FIELD_WIDTH, FIELD_HEIGHT, field, state, new BlockPos(x, y)).forEach(pos -> {
			GrassBlock grass = grassBlocks[pos.y * FIELD_WIDTH + pos.x];
			grass.setClick();
			grassBlocks[pos.y * FIELD_WIDTH + pos.x] = null;
			state[pos.x][pos.y] = BlockState.EMPTY;
		});
	}
}
