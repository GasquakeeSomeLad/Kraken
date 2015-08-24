package com.breakfastsoftware.kraken.entities.core;

import java.util.ArrayList;

import com.breakfastsoftware.kraken.entities.Blimp;
import com.breakfastsoftware.kraken.entities.Bullet;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.entities.DeathEmote;
import com.breakfastsoftware.kraken.entities.Fish;
import com.breakfastsoftware.kraken.entities.Ship;
import com.breakfastsoftware.kraken.entities.Submarine;

public class EntityManager {

	private ArrayList<Cloud> clouds;
	private ArrayList<Ship> ships;
	private ArrayList<Blimp> blimps;
	private ArrayList<Bullet> bullets;
	private ArrayList<Submarine> submarines;
	private ArrayList<DeathEmote> emotes;
	private ArrayList<Entity> dead;
	private Fish fish;

	public EntityManager() {
		clouds = new ArrayList<Cloud>();
		ships = new ArrayList<Ship>();
		bullets = new ArrayList<Bullet>();
		submarines = new ArrayList<Submarine>();
		blimps = new ArrayList<Blimp>();
		emotes = new ArrayList<DeathEmote>();
		dead = new ArrayList<Entity>();
	}

	public void update() {
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).update();
		}
		for (int i = 0; i < ships.size(); i++) {
			ships.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i ++) {
			bullets.get(i).update();
		}
		for (int i = 0; i < blimps.size(); i ++) {
			blimps.get(i).update();
		}
		for (int i = 0; i < submarines.size(); i ++) {
			submarines.get(i).update();
		}
		if (fish != null) {
			fish.update();
		}
		for (int i = 0; i < dead.size(); i++) {
			dead.get(i).update();
		}
		for (int i = 0; i < emotes.size(); i ++) {
			emotes.get(i).update();
		}
	}

	public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
		for (int i = 0; i < dead.size(); i++) {
			dead.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < ships.size(); i++) {
			ships.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < blimps.size(); i ++) {
			blimps.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < submarines.size(); i++) {
			submarines.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		if (fish != null) {
			fish.render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < dead.size(); i++) {
			dead.get(i).update();
		}
		for (int i = 0; i < emotes.size(); i ++) {
			emotes.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
	}

	public void fancyRender(int cameraX, int cameraY, int screenWidth, int[] pixels, int[] alphaPixels) {
		for (int i = 0; i < dead.size(); i++) {
			dead.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < ships.size(); i++) {
			ships.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < submarines.size(); i++) {
			submarines.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < blimps.size(); i++) {
			blimps.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		if (fish != null) {
			fish.render(cameraX, cameraY, screenWidth, pixels);
		}
		for (int i = 0; i < emotes.size(); i ++) {
			emotes.get(i).render(cameraX, cameraY, screenWidth, alphaPixels);
		}
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).render(cameraX, cameraY, screenWidth, alphaPixels);
		}
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public void addBlimp(Blimp blimp) {
		blimps.add(blimp);
	}

	public void removeBlimp(Blimp blimp) {
		blimps.remove(blimp);
		dead.add(blimp);
	}

	public void addSubmarine(Submarine submarine) {
		submarines.add(submarine);
	}

	public void removeSubmarine(Submarine submarine) {
		submarines.remove(submarine);
		dead.add(submarine);
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
		dead.add(ship);
	}

	public int getEnemyCount() {
		return ships.size()+submarines.size()+blimps.size();
	}

	public void removeEmote(DeathEmote deathEmote) {
		emotes.remove(deathEmote);
	}

	public void addEmote(DeathEmote deathEmote) {
		emotes.add(deathEmote);
	}

	public void removeDead(Entity entity) {
		dead.remove(entity);
	}
}