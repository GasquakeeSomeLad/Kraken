package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Submarine extends Entity {

	private Player player;
	private EntityManager em;
	private boolean inWater, pursuePlayerX;
	private String retreat = "NO";
	private int bulletTime = 60;
	
	public Submarine(Player player, EntityManager em) {
		super(600, 600, Sprite.FRIGATE1);
		this.player = player;
		this.em = em;
	}

	public void update() {
		if (Calculations.getDistanceX(x, player.getX()) < 120 && Calculations.getDistanceY(y, player.getY()) < 100) {
			pursuePlayerX = false;
			bulletTime--;
        	if (bulletTime <= 0) {
        		em.addBullet(new Bullet(x + 30, y + 30, player, em));
        		bulletTime = 60;
        	}
		} else {
			pursuePlayerX = true;
			retreat = "NO";
		}
		if (!pursuePlayerX && retreat == "NO") {
			if (Calculations.getDistanceX(x, player.getX()) < 90 && Calculations.getDistanceX(y, player.getY()) < 50) {
				if (player.getDx() > 0) {
					retreat = "RIGHT";
				} else if (player.getDx() < 0) {
					retreat = "LEFT";
				}
			}
		}
		if (y > 230) {
			inWater = true;
		} else {
			inWater = false;
			if (player.getY() > 230) {
				int diffY = player.getY() - y;
				float angle = (float) Math.atan2(diffY, 0);
				if (pursuePlayerX) {
					y += 2 * Math.sin(angle);
				}
			}
		}
		if (inWater) {
			int diffX = player.getX() - x;
			int diffY = player.getY() - y;
			float angle = (float)Math.atan2(diffY, diffX);
			if (pursuePlayerX) {
				x += 2 * Math.cos(angle);
				y += 2 * Math.sin(angle);
			}
		}
		if (retreat == "LEFT") {
			move(-2, dy);
			direction = LEFT;
		} else if (retreat == "RIGHT") {
			move(2, dy);
			direction = RIGHT;
		} else if (retreat == "NO") {
			if (player.getX() > x) {
				direction = RIGHT;
			} else {
				direction = LEFT;
			}
		}
		move(dx, dy);
	}

}