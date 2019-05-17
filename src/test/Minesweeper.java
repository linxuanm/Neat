package test;

import java.util.List;
import java.util.Random;

import cn.davidma.neat.application.InputHandler;
import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.layout.Group;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.object.SceneObject;
import cn.davidma.neat.object.ui.GameText;
import test.BlockLogic.BlockPos;

public class Minesweeper extends NeatGame {
	
	public static final int WINDOW_SIZE = 800;
	public static final int BAR_SIZE = 90;
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
	public static Group all;
	public static int flagLeft;
	public static GameText textFlagLeft;
	private static GameScene mainScene;
	private static int grassLeft;
	private static int[][] field;
	private static BlockState[][] state;
	private static List<BlockPos> mines;
	private static GrassBlock[] grassBlocks;
	private static double shakeFactor;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	protected void setup() {
		this.setTitle("Minesweeper");
		this.setBackgroundColor(0, 0, 0);
		this.setSize(CELL_SIZE * FIELD_WIDTH, CELL_SIZE * FIELD_HEIGHT + BAR_SIZE);
		InputHandler.setKeyDownHandler(keycode -> {
			if (keycode.equals("R")) {
				this.gameStart();
			}
		});
	}

	@Override
	protected void gameStart() {
		mainScene = new GameScene();
		setScene(mainScene);
		rand = new Random();
		all = new Group();
		
		// Generate background.
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				GameObject background = new BackgroundBlock((j + i) % 2 == 0);
				background.setX(CELL_SIZE / 2 + j * CELL_SIZE);
				background.setY(CELL_SIZE / 2 + i * CELL_SIZE + BAR_SIZE);
				all.addChild(background);
				mainScene.addChild(background);
			}
		}
		
		// Generate mines.
		flagLeft = MINE_COUNT;
		field = new int[FIELD_WIDTH][FIELD_HEIGHT];
		state = new BlockState[FIELD_WIDTH][FIELD_HEIGHT];
		mines = BlockLogic.genMines(FIELD_WIDTH, FIELD_HEIGHT, MINE_COUNT);
		grassLeft = FIELD_WIDTH * FIELD_HEIGHT - mines.size();
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
					text.setY(CELL_SIZE / 2 + i * CELL_SIZE - (15 * CELL_SIZE / 70) + BAR_SIZE);
					mainScene.addChild(text);
					all.addChild(text);
				} else if (flag == -1) {
					GameObject mine = new Mine();
					mine.setX(CELL_SIZE / 2 + j * CELL_SIZE);
					mine.setY(CELL_SIZE / 2 + i * CELL_SIZE + BAR_SIZE);
					mainScene.addChild(mine);
					all.addChild(mine);
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
				grass.setY(CELL_SIZE / 2 + i * CELL_SIZE + BAR_SIZE);
				grass.setId(String.format("grass_%d_%d", j, i));
				grassBlocks[i * FIELD_WIDTH + j] = grass;
				mainScene.addChild(grass);
				all.addChild(grass);
			}
		}
		
		// Info bar.
		GameObject bar = new InfoBar();
		mainScene.addChild(bar);
		
		// Flag left.
		textFlagLeft = new GameText(String.valueOf(flagLeft));
		textFlagLeft.setColor(255, 255, 255);
		textFlagLeft.setSize(BAR_SIZE * 0.5);
		textFlagLeft.setFont("Courier");
		textFlagLeft.setX((int) (bar.getScaleX() / 2));
		textFlagLeft.setY(BAR_SIZE / 2 - 10 * BAR_SIZE / 90);
		mainScene.addChild(textFlagLeft);
	}
	
	@Override
	protected void update() {
		if (shakeFactor > 0) {
			double rad = Math.toRadians(rand.nextInt(360));
			int newX = (int) (Math.cos(rad) * shakeFactor);
			int newY = (int) (Math.sin(rad) * shakeFactor);
			all.setX(newX);
			all.setY(newY);
			
			shakeFactor *= 0.95;
			if (shakeFactor <= 1e-3) {
				shakeFactor = 0;
				all.setX(0);
				all.setY(0);
			}
		}
		
		super.update();
	}

	public static void processClick(int x, int y) {
		
		// Death.
		if (field[x][y] == -1) {
			System.out.println("Ya lose!");
			mines.forEach(pos -> {
				GrassBlock grass = grassBlocks[pos.y * FIELD_WIDTH + pos.x];
				SceneObject explosion = new Explosion();
				explosion.setX(grass.getX());
				explosion.setY(grass.getY());
				mainScene.addChild(explosion);
				all.addChild(explosion);
				grass.setClick();
				grassBlocks[pos.y * FIELD_WIDTH + pos.x] = null;
			});
			shakeFactor = 20;
			mainScene.addChild(new Background());
			return;
		}
		
		// Normal click.
		if (field[x][y] > 0) {
			killGrass(x, y);
		} else {
			
			// Mass click.
			BlockLogic.massClick(FIELD_WIDTH, FIELD_HEIGHT, field, state, new BlockPos(x, y)).forEach(pos -> {
				killGrass(pos.x, pos.y);
			});
		}
	}
	
	public static void killGrass(int x, int y) {
		GrassBlock grass = grassBlocks[y * FIELD_WIDTH + x];
		grass.setClick();
		grassBlocks[y * FIELD_WIDTH + x] = null;
		state[x][y] = BlockState.EMPTY;
		if (--grassLeft <= 0) {
			mainScene.addChild(new Background());
			System.out.println("Ya win!");
		}
	}
	
	public static void updateFlagText() {
		textFlagLeft.setText(String.valueOf(flagLeft));
	}
}
