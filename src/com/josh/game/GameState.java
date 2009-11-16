package com.josh.game;

public class GameState {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		(new GameState()).start();
	}

	public void start() throws InterruptedException {
		World world = new World();
		PlayerCharacter pc = new PlayerCharacter(world);
		PlayerCharacterView vpc = new PlayerCharacterView(pc);
		vpc.displayView();
	    
	    /**
		for (int i=0; i < 100; i++) { 
			int[] dir = Direction.getRandomQueenDir();
			int[] pos = pc.getPosition();
			Floor f = pc.getCurrentFloor();
			int[] newPos = {pos[0] + dir[0], pos[1] + dir[1]};
			Environmental e = f.getBoard()[newPos[0]][newPos[1]];
			if (e.isWalkable()) {
				pc.setPosition(newPos);
			} else {
				System.out.println("ouch... I hit a wall!");
			}
			Thread.sleep(1000);
		}*/
	}

}
