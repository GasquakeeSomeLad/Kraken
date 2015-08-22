package com.breakfastsoftware.kraken.entities.core;

import java.util.ArrayList;
import com.breakfastsoftware.kraken.entities.Cloud;

public class EntityManager {

	public ArrayList<Cloud> clouds;
	
	public EntityManager() {
		clouds = new ArrayList<Cloud>();
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
	
}