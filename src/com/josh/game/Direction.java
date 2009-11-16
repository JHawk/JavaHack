package com.josh.game;

public class Direction {

	public static final int[][] rookDirs = {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1}}; 

	public static final int[][] queenDirs = {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
		{1,1},
		{1,-1},
		{-1,1},
		{-1,-1}}; 

	public Direction() {}
	
	public static int[] getRandomRookDir() {
		return rookDirs[Dice.rollNonZero(rookDirs.length - 1)];
	}
	
	public static int[] getRandomQueenDir() {
		return queenDirs[Dice.rollNonZero(queenDirs.length - 1)];
	}	
	
	// not in use
	// utils
	public static int[][] suffleTwoDimArray(int[][] dirs) {
		int l = dirs.length;
		for (int swaps = 0; swaps < l; swaps++) {
			int spot1 = Dice.roll(l);
			int spot2 = Dice.roll(l);
			int[] temp = dirs[spot1];
			dirs[spot1] = dirs[spot2];
			dirs[spot2] = temp;
		}
		return dirs;
	}
	
	// not in use
	public static int[][] getRandRookdirs() {
		return suffleTwoDimArray(rookDirs);
	}
	
	// not in use
	public static int[][] getRandQueendirs() {
		return suffleTwoDimArray(queenDirs);
	}

	public static int[] getOppDir(int[] dir) {
		for (int i = 0; i < dir.length; i++) {
			dir[i] = dir[i] * -1;
		}
		return dir;
	}
}
