package com.josh.game;

import java.awt.Color;

public class WallEnvironmental implements Environmental {

	private String name = "#";
	private boolean walkable = false; 
	private Color color = Color.darkGray;

	@Override
	public String getStringRepresentation() {
		return getName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isWalkable() {
		return walkable;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	} 

	@Override
	public Color getColor() {
		return color;
	} 
}
