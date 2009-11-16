package com.josh.game;

import java.util.Random; 

public class Dice {

	private static final Random gen = new Random();
	
	public Dice() {}
	
	public static final int roll(int max) {
		return gen.nextInt(max);
	}
	
	public static final int rollNonZero(int max) {
		return roll(max) + 1;
	}
	
}
