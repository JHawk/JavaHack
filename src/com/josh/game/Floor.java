package com.josh.game;

public class Floor {

	private int sizeRow = 30;
	private int sizeCol = 30;
	private int maxRoomWidth = 4;
	private int maxRoomHeight = 4;
	private double percentTunnelToRoom = 60.0; 
	private double maxPercentWalkable = 30.0;
	private Environmental baseEnvrion = new RockEnvironmental();
	private Environmental board[][] = new Environmental[sizeRow][sizeCol];
	
	public Floor(Environmental baseE) {
		baseEnvrion = baseE;
		makeAllBaseEnvrion();
		int[] point = getPointOnBoard();
		double per = 0.0;
		while ((maxPercentWalkable / 100) > per) {
				point = addTunnelOrRoom(point);
				per = getPercentWalkable();
		}
	}
	
	public int getSizeRow() {
		return sizeRow;
	}

	public int getSizeCol() {
		return sizeCol;
	}

	public Environmental[][] getBoard() {
		return board;
	}

	public void setBoard(Environmental[][] board) {
		this.board = board;
	}

	private int boardArea = board.length * board[0].length;

	private int[] addTunnelOrRoom(int[] point) {
		if (percentTunnelToRoom > Dice.rollNonZero(100))  {
			point = addTunnel(point);
		} else {
			point = addRoom(point);
		}
		return point;
	}

	private void drawRoom(int w, int h, int[] point, Environmental e) {
		for (int row = point[0] - h; row < point[0] + h; row++) {
			for(int col = point[1] - w; col < point[1] + w; col++)
			{
				if (!board[row][col].isWalkable()) {
					board[row][col] = e;	
				}
			}		
		}
	}
	
	// adds a room unless the room goes out of bounds - then it tries to tunnel
	private int[] addRoom(int[] point) {
		int w = Dice.rollNonZero(maxRoomWidth) + 1;
		int h = Dice.rollNonZero(maxRoomHeight) + 1;
		if (((point[0] - h) <= 0) ||
				((point[0] + h) >= board.length) ||
				((point[1] + w) >= board[0].length) || 
				((point[1] - w) <= 0)) {
				point = addTunnel(point);
		} else {
		drawRoom(w, h, point, new WallEnvironmental());
		drawRoom(w - 1, h - 1, point, new GroundEnvironmental());
		}
		return point;
	}

	private double getPercentWalkable() {
		int walkable = 0; 
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col].isWalkable()) {
					walkable++;
				}
			}
		}
		return (walkable / (double)boardArea);
	}

	private void makeAllBaseEnvrion() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = baseEnvrion;
			}
		}
	}

	private int[] getPointOnBoard() {
		int row = Dice.roll(board.length - 2) + 1;
		int col = Dice.roll(board[0].length - 2) + 1;
		return new int[] { row, col };
	}

	private int[] drawTunnel(int l, int[] start, int[] dir, Environmental g, Environmental wall) {
		for (int length = l; length >= 0; length--) {
			int row = start[0] + (dir[0] * length);
			int col = start[1] + (dir[1] * length);
			board[row][col] = g;	
			int [][] dirs = Direction.queenDirs;
			for (int d = 0; d < dirs.length; d++) {
				int surRow = row + dirs[d][0];
				int surCol = col + dirs[d][1];
				Environmental surrounding = board[surRow][surCol];
				if (!surrounding.isWalkable()) {
					board[surRow][surCol] = wall;
				}
			}
		}
		return new int[] { start[0] + (dir[0] * l), start[1] + (dir[1] * l) };
	}
	
	// returns the end point 
	private int[] addTunnel(int[] start) {
		int[] dir = Direction.getRandomRookDir();
		int dist = getDistToWall(start, dir);
		if (dist <= 1) {
			dir = Direction.getOppDir(dir);
			dist = getDistToWall(start, dir);
		}	
		int l = Dice.rollNonZero(dist);
		Environmental ground = new GroundEnvironmental();
		Environmental wall = new WallEnvironmental();
		return drawTunnel(l, start, dir, ground, wall); 
	}

	private int getDistToWall(int[] point, int[] dir) {
		if (dir[0] > 0) {
			return board.length - point[0] - 2;
		} else if (dir[0] < 0) {
			return point[0] - 1;
		}
		if (dir[1] > 0) {
			return board[0].length - point[1] - 2;
		} else if (dir[1] < 0) {
			return point[1] - 1;
		}
		return 0;
	}

	public int[] getRandomWalkablePos() {
		int[] position = new int[2];
		Environmental e = null;
		while (e == null || !e.isWalkable()) {
			position = getPointOnBoard();
			e = board[position[0]][position[1]];
		}
		return position;
	}
}