package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Submarine extends Entity {

	private Player player;
	private EntityManager em;
	private String retreat = "NO";
	private boolean inWater, pursuePlayerX, alive = true;
	private int bulletTime = 60;

	public Submarine(Player player, EntityManager em) {
		super((int)(Math.random() * Images.BACKGROUND.getImage().getWidth()),
				(int)(Math.random() * ((Images.BACKGROUND.getImage().getHeight()-400)))+400, Sprite.SUBMARINE);
		this.player = player;
		this.em = em;
	}

	public void update() {
		if (!alive) {
			if (++y > 1200) {
				em.removeDead(this);
			}
			return;
		}
		else if (Calculations.collision(player.getX(), player.getY(), player.getWidth(), player.getHeight(), x, y, w, h)) {
			Sound.DESTROY.play();
			alive = false;
			em.addEmote(new DeathEmote(x+w/2, y+h/2, em));
			em.removeSubmarine(this);
			move(0, h);
			sprite = Sprite.SUBMARINEDEAD;
			return;
		}
		if (Calculations.getDistanceX(x, player.getX()) < 200 && Calculations.getDistanceY(y, player.getY()) < 200) {
			pursuePlayerX = false;
			bulletTime--;
			if (bulletTime <= 0) {
				em.addBullet(new Bullet(x + 20, y + 20, player, em, false));
				bulletTime = 60;
			}
		} else {
			pursuePlayerX = true;
			retreat = "NO";
		}
		if (!pursuePlayerX && retreat == "NO") {
			if (Calculations.getDistanceX(x, player.getX()) < 80 && Calculations.getDistanceX(y, player.getY()) < 50) {
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
			dx = -4;
			direction = LEFT;
		} else if (retreat == "RIGHT") {
			dx = 4;
			direction = RIGHT;
		} else if (retreat == "NO") {
			dx = 0;
			dy = 0;
			if (player.getX() > x) {
				direction = RIGHT;
			} else {
				direction = LEFT;
			}
		}
		if (x < -100) {
			pursuePlayerX = true;
			retreat = "NO";
			dx = 0; dy = 0;
		}
		if (x > 1700) {
			pursuePlayerX = true;
			retreat = "NO";
			dx = 0; dy = 0;
		}
		move(dx, dy);
	}

}