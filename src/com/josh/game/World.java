package com.josh.game;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	private List<Floor> floors = new ArrayList<Floor>(); 
	
	public World() {
		addFloor(new Floor(new RockEnvironmental()));
	}
	
	public Floor getCurrentFloor(int floorIndex) {
		return floors.get(floorIndex);
	}
	
	public void addFloor(Floor f) {
		floors.add(f);
	}

}
