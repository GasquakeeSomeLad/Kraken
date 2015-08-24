package com.breakfastsoftware.kraken.entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.states.MenuState;
import com.breakfastsoftware.kraken.util.Calculations;
import com.breakfastsoftware.kraken.util.Camera;

public class Player extends Entity {
	
	private Camera camera;
	private boolean jumping = false, outOfWater = false;
	private int jumpTime = 16, jumpCounter = 30;
	private int jabTimer = 0, jabTime = 9;
	private int playerHP = 100;
	ArrayList<PlayerSegment> segments = new ArrayList<PlayerSegment>();

	public Player(int x, int y, Camera camera) {
		super(x, y, Sprite.PLAYERHEAD);
		this.camera = camera;
		w = Sprite.PLAYERHEAD.WIDTH;
		h = Sprite.PLAYERHEAD.HEIGHT;
		segments.add(new PlayerSegment(this, 1, Sprite.PLAYERSEGMENT1));
		for (int i = 0; i < 15; i++) {
			if (i%2 == 0) {
				segments.add(new PlayerSegment(segments.get(i), Sprite.PLAYERSEGMENT2));
			}
			else {
				segments.add(new PlayerSegment(segments.get(i), Sprite.PLAYERSEGMENT1));
			}
		}
		segments.add(new PlayerSegment(segments.get(segments.size()-1), 1, Sprite.PLAYERLEGS));
		segments.add(new PlayerSegment(segments.get(segments.size()-1), 1, Sprite.PLAYERFEET));
		segments.add(new PlayerSegment(segments.get(segments.size()-1), 1, Sprite.PLAYERTAIL));
	}

	public void update() {
		moveLogic();
		if (y < 300 && !jumping) {
			if (--jumpCounter < 0 && (Kraken.getKeyboard().keyDown(KeyEvent.VK_Z)
					|| Kraken.getKeyboard().keyDown(KeyEvent.VK_SPACE))) {
				jumping = true;
				Sound.JUMP.play();
			}
		}
		if (jumping) {
			move(400, -jumpTime--);
			if (y < 220) {
				outOfWater = true;
			} else if (y > 230 && outOfWater) {
				jumpTime = 16;
				jumpCounter = 30;
				jumping = outOfWater = false;
			}
		}
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).update();
		}
		if (playerHP <= 0) {
			Kraken.getStateManager().setState(new MenuState());
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
			if (--jabTime >= 0 || (--jabTimer < 0 && Kraken.getMouse().left())) {
				if (jabTimer < 0) {
					Sound.JAB.play();
				}
				jabTimer = 70;
				if (jabTime < 0) {
					jabTime = 9;
				}
				if (direction == LEFT) {
					move(-10, 0);
				}
				if (direction == RIGHT) {
					move(10, 0);
				}
			}
		}
	}

	public void move(int dx, int dy) {
		if (jumping) {
			if (dx == 400) {
				super.move(0, dy);
			}
			return;
		}
		if (dy == 1 || dy == -1) {
			dy = 0;
		}
		if (Calculations.collision(x-camera.getX(), 0, w, 5, Kraken.getMouse().getX()/2, 1, 1, 1)) {
			dx = 0;
		} else if (dx < 0 && direction == RIGHT) {
			direction = LEFT;
			super.move(-w + 3, 0);
		} else if (dx > 0 && direction == LEFT) {
			direction = RIGHT;
			super.move(w - 3, 0);
		}
		super.move(dx, dy);
		if (y < 230) {
			y = 230;
		}
	}

	public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
		for (int i = segments.size()-1; i >=0; i--) {
			segments.get(i).render(cameraX, cameraY, screenWidth, pixels);
		}
		super.render(cameraX, cameraY, screenWidth, pixels);
	}

	public int getCameraX() {
		return segments.get(segments.size()/2).getX();
	}

	public int getCameraY() {
		return segments.get(segments.size()/2).getY();
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public void setPlayerHP(int playerHP) {
		this.playerHP = playerHP;
	}
	
}