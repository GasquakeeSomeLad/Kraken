package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.Sprite;

public class Bullet extends Entity {

	private EntityManager em;
	private float angle;
	private int timer;
	private int initX, initY;
	
	public Bullet(int x, int y, Player player, EntityManager em) {
		super(x, y, Sprite.BULLET);
		this.em = em;
		int diffX = player.getX() + player.getWidth() / 2 - x;
		int diffY = player.getY() + player.getHeight() / 2 - y;
		initX = x;
		initY = y;
		angle = (float) Math.atan2(diffY, diffX);
	}

	public void update() {
		timer++;
		x = (int) (initX + timer * 4 * Math.cos(angle));
		y = (int) (initY + timer * 4 * Math.sin(angle));
		if (timer >= 1800) {
			em.removeBullet(this);
		}
	}

}