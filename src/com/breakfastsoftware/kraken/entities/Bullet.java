package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Bullet extends Entity {

	private EntityManager em;
	private Player player;
	private float angle;
	private int timer;
	private int initX, initY;

	public Bullet(int x, int y, Player player, EntityManager em, boolean canon) {
		super(x, y, (canon) ? Sprite.BULLET : Sprite.MISSILE);
		this.em = em;
		this.player = player;
		initX = x;
		initY = y;
		int diffX = player.getX() + player.getWidth() / 2 - x;
		int diffY = player.getY() + player.getHeight() / 2 - y;
		angle = (float) Math.atan2(diffY, diffX);
		Sound.CANON.play();
	}

	public void update() {
		timer++;
		x = (int) (initX + timer * 4 * Math.cos(angle));
		y = (int) (initY + timer * 4 * Math.sin(angle));
		if (timer >= 1800) {
			em.removeBullet(this);
		}
		if (Calculations.collision(player.getX(), player.getY(), player.getWidth(), player.getHeight(), x, y, w, h)) {
			player.setPlayerHP(player.getPlayerHP()-2);
			em.removeBullet(this);
			return;
		}
		for (int i = 0; i < player.segments.size(); i++) {
			PlayerSegment segment = player.segments.get(i);
			if (Calculations.collision(segment.getX(), segment.getY(), segment.getWidth(), segment.getHeight(), x, y, w, h)) {
				player.setPlayerHP(player.getPlayerHP()-1);
				em.removeBullet(this);
				return;
			}
		}
	}

}