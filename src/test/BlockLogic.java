package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BlockLogic {
	
	public static List<BlockPos> genMines(int width, int height, int mineNum) {
		List<BlockPos> allPos = new ArrayList<BlockPos>();
		List<BlockPos> mines = new ArrayList<BlockPos>();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				allPos.add(new BlockPos(j, i));
			}
		}
		
		Random rand = new Random();
		for (int i = 0; i < mineNum; i++) {
			int index = rand.nextInt(allPos.size());
			mines.add(allPos.get(index));
			allPos.remove(index);
		}
		
		return mines;
	}
	
	public static List<BlockPos> massClick(int width, int height, int[][] field, BlockState[][] state, BlockPos pos) {
		List<BlockPos> clicked = new ArrayList<BlockPos>();
		boolean[][] visited = new boolean[width][height];
		
		LinkedList<BlockPos> cache = new LinkedList<BlockPos>();
		cache.add(pos);
		
		while(cache.size() > 0) {
			BlockPos curr = cache.pop();
			if (curr.x < 0 || curr.x >= width || curr.y < 0 || curr.y >= height) continue;
			if (visited[curr.x][curr.y] || state[curr.x][curr.y] == BlockState.EMPTY) continue;
			visited[curr.x][curr.y] = true;
			
			clicked.add(curr);
			
			if (field[curr.x][curr.y] == 0) {
				for (int[] i: Minesweeper.DIR) {
					cache.add(new BlockPos(curr.x + i[0], curr.y + i[1]));
				}
			}
		}
		
		return clicked;
	}
	
	public static class BlockPos {
		public int x;
		public int y;
		
		public BlockPos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
