package com.josh.game;

import java.util.Observable;

public class PlayerCharacter extends Observable implements Renderable {
	
	private int[] position;
	private int currentFloorIndex;
	private String representation = "@";
	
	private World currentWorld;

	public PlayerCharacter(World world) {
		currentWorld = world;
		setPosition(getStartPosition());
	}
	
	public int getCurrentFloorIndex() {
		return currentFloorIndex;
	}

	public void setCurrentFloorIndex(int index) {
		this.currentFloorIndex = index;
		setChanged();
		notifyObservers();
	}

	private int[] getStartPosition() {
		return currentWorld.getCurrentFloor(currentFloorIndex).getRandomWalkablePos();
	}

	public void setPosition(int[] position) {
		this.position = position;
		setChanged();
		notifyObservers();
	}

	public int[] getPosition() {
		return position;
	}

	public Floor getCurrentFloor() {
		return currentWorld.getCurrentFloor(currentFloorIndex);
	}

	@Override
	public String getStringRepresentation() {
		return representation;
	}
}
