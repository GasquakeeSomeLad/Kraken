package com.breakfastsoftware.kraken.entities;

import java.awt.event.KeyEvent;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;
import com.breakfastsoftware.kraken.util.Camera;

public class Player extends Entity {

	private Camera camera;
	private boolean jumping = false, outOfWater = false;
	private int jumpTime = 16, jumpCounter = 30;

	public Player(int x, int y, Camera camera) {
		super(x, y, Sprite.PLAYERHEAD);
		this.camera = camera;
		w = Sprite.PLAYERHEAD.WIDTH;
		h = Sprite.PLAYERHEAD.HEIGHT;
	}

	public void update() {
		if (Kraken.getMouse().getX()/2 > x+w/2-camera.getX()) {
			direction = RIGHT;
		}
		else {
			direction = LEFT;
		}
		moveLogic();

		if (y < 300 && !jumping) {
			if (--jumpCounter < 0 && (Kraken.getKeyboard().keyDown(KeyEvent.VK_Z)
					|| Kraken.getKeyboard().keyDown(KeyEvent.VK_SPACE)))
				jumping = true;
		}
		if (jumping) {
			move(400, -jumpTime--);
			if (y < 220)
				outOfWater = true;
			else if (y > 230 && outOfWater) {
				jumpTime = 16;
				jumpCounter = 30;
				jumping = outOfWater = false;
			}
		}
	}

	public void moveLogic() {
		if (!Calculations.collision(x-camera.getX(), y-camera.getY(), w, h, Kraken.getMouse().getX()/2, Kraken.getMouse().getY()/2, 1, 1)) {
			int diffX = Kraken.getMouse().getX()/2 - x + camera.getX();
			int diffY = Kraken.getMouse().getY()/2 - y + camera.getY();
			float angle = (float) Math.atan2(diffY, diffX);
			if (Calculations.collision(x-camera.getX()-w/2, y-camera.getY()-h/2, w*2, h*2, Kraken.getMouse().getX()/2, Kraken.getMouse().getY()/2, 1, 1)) {
				move((int)(3 * Math.cos(angle)), (int)(3 * Math.sin(angle)));
			} else {
				move((int)(5 * Math.cos(angle)), (int)(5 * Math.sin(angle)));
			}
		}
	}

	public void move(int dx, int dy) {
		if (jumping) {
			if (dx == 400)
				super.move(0, dy);
			return;
		}
		super.move(dx, dy);
		if (y < 230)
			y = 230;
	}

}