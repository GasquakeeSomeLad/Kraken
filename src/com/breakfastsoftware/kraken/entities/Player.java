package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;
import com.breakfastsoftware.kraken.util.Camera;

public class Player extends Entity {
	protected int maxX, maxY;
	private Camera camera;

	public Player(int x, int y, int maxX, int maxY, Camera camera) {
		super(x, y, Sprite.PLAYERHEAD);
		this.maxX = maxX;
		this.maxY = maxY;
		this.camera = camera;
		w = Sprite.PLAYERHEAD.WIDTH;
		h = Sprite.PLAYERHEAD.HEIGHT;
	}

	public void update() {
		if (!Calculations.collision(x-camera.getX(), y-camera.getY(), w, h, Kraken.getMouse().getX()/2, Kraken.getMouse().getY()/2, 1, 1)) {
			int diffX = Kraken.getMouse().getX()/2 - x + camera.getX();
			int diffY = Kraken.getMouse().getY()/2 - y + camera.getY();
			float angle = (float) Math.atan2(diffY, diffX);
			if (Calculations.collision(x-camera.getX()-w/2, y-camera.getY()-h/2,
					w*2, h*2, Kraken.getMouse().getX()/2, Kraken.getMouse().getY()/2, 1, 1)) {
				x += 3 * Math.cos(angle);
				y += 3 * Math.sin(angle);
			}
			else {
				x += 5 * Math.cos(angle);
				y += 5 * Math.sin(angle);
			}
		}
		if (Kraken.getMouse().getX()/2 > x+w/2-camera.getX()) {
			direction = RIGHT;
		} else {
			direction = LEFT;
		}
		move(dx, dy);
		if (x < 0) {
			if (dx < 0) {
				x = 0;
			}
		}
		if (x > maxX) {
			if (dx > 0) {
				x = 0;
			}
		}
		if (y < 0) {
			if (dy < 0) {
				dy = 0;
			}
		}
		if (y > maxY) {
			if (dy > 0) {
				dy = 0;
			}
		}
	}
}