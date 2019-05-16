package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
	
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
	
	public static class BlockPos {
		public int x;
		public int y;
		
		public BlockPos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
