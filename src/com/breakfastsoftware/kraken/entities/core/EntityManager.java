package com.breakfastsoftware.kraken.entities.core;

import java.util.ArrayList;

import com.breakfastsoftware.kraken.entities.Bullet;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.entities.Fish;
import com.breakfastsoftware.kraken.entities.Ship;

public class EntityManager {

	private ArrayList<Cloud> clouds;
	private ArrayList<Ship> ships;
	private ArrayList<Bullet> bullets;
	private Fish fish;

	public EntityManager() {
		clouds = new ArrayList<Cloud>();
		ships = new ArrayList<Ship>();
		bullets = new ArrayList<Bullet>();
	}

	public void update() {
		for (int i = 0; i < ships.size(); i++) {
			clouds.get(i).update();
		}
		for (int i = 0; i < ships.size(); i++) {
			ships.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i ++) {
			bullets.get(i).update();
		}
		if (fish != null) {
			fish.update();
		}
	}

	public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
		for (int i = 0; i < ships.size(); i++) {
			clouds.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < ships.size(); i++) {
			ships.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		if (fish != null)
			fish.render(cameraX, cameraY, screenWidth, pixels);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public void addCloud(Cloud cloud) {
		clouds.add(cloud);
	}

	public void removeCloud(Cloud cloud) {
		clouds.remove(cloud);
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public boolean noFish() { return fish == null;}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public void removeShip(Ship ship) {
		ships.remove(ship);
	}

}