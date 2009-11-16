package com.josh.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Tile extends JComponent {

	private int x1;
	private int x2;
	private int y1; 
	private int y2;
	private Color color;
	
	public Tile(int x,int y,int w,int h, Color c) {
		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		color = c;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2, y2);
	}
}

public class PlayerCharacterView implements Observer {

	private PlayerCharacter pchar;
	private int tileWidth = 15;
	private int tileHeight = 15;
	private JFrame frame;
	
	public PlayerCharacterView(PlayerCharacter pc) {
		pchar = pc;
		pchar.addObserver(this);
		frame = new JFrame("JoshHack Java");
	}
	
	public void renderFloor() {
		Environmental[][] board = pchar.getCurrentFloor().getBoard();
		
		int[] position = pchar.getPosition();
		
		Floor floor = pchar.getCurrentFloor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(floor.getSizeCol() * tileWidth, floor.getSizeRow() * tileHeight); 
	    for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				Tile tile = new Tile(col * tileWidth,row * tileHeight,tileWidth,tileHeight,board[row][col].getColor());
				frame.getContentPane().add(tile);
			}
		}
	}

	public void displayView() {
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		renderFloor();
		
	}	
}
