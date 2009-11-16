package com.josh.game;

import java.awt.Color;

public interface Environmental extends Renderable {
	public Color getColor();
	public boolean isWalkable(); 
	public String getName();
	public void setName(String name); 
}
