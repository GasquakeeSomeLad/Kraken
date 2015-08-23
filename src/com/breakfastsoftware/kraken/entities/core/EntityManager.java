package com.breakfastsoftware.kraken.entities.core;

import java.util.ArrayList;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.entities.Ship;

public class EntityManager {
	private ArrayList<Cloud> clouds;
	private ArrayList<Ship> ships;
	
	public EntityManager() {
		clouds = new ArrayList<Cloud>();
		ships = new ArrayList<Ship>();
	}
	
	public void update() {
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).update();
		}
	}
	
	public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
	}
	
	public void addCloud(Cloud cloud) {
		clouds.add(cloud);
	}
	
	public void removeCloud(Cloud cloud) {
		clouds.remove(cloud);
	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public void removeShip(Ship ship) {
		ships.remove(ship);
	}
}