package com.breakfastsoftware.kraken.entities;

import java.awt.event.KeyEvent;
import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;
import com.breakfastsoftware.kraken.util.Camera;

public class Player extends Entity {

	private Camera camera;
	private int maxX, maxY;

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
				x += 2 * Math.cos(angle);
				y += 2 * Math.sin(angle);
			}
			else {
				x += 4 * Math.cos(angle);
				y += 4 * Math.sin(angle);
			}
		}
		if (Kraken.getMouse().getX()/2 > x+w/2-camera.getX()) {
			direction = RIGHT;
		} else {
			direction = LEFT;
		}
		move(dx, dy);
	}
}