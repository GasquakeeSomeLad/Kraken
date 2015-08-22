package com.breakfastsoftware.kraken.entities;

import java.awt.event.KeyEvent;
import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;

public class Player extends Entity {
	
	private boolean left, right, up, down;
	private int maxX, maxY;
	
	public Player(int x, int y, int maxX, int maxY) {
		super(x, y, Sprite.PLAYERHEAD);
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void update() {
		if (Kraken.getKeyboard().keyDown(KeyEvent.VK_A)) {
            left = true;
        }
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_D)) {
            right = true;
        }
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_W)) {
            up = true;
        }
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_S)) {
            down = true;
        }
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_A)) {
        	left = false;
        }
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_D)) {
            right = false;
        }
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_W)) {
            up = false;
        }
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_S)) {
            down = false;
        }
        if (left) {
			if (!right) {
				direction = LEFT;
			}
        	dx = -4;
        }
        if (right) {
			if (!left) {
				direction = RIGHT;
			}
        	dx = 4;
        }
        if (up) {
        	dy = -4;
        }
        if (down) {
        	dy = 4;
        }
        if (!left && !right) {
        	dx = 0;
        }
        if (!up && !down) {
        	dy = 0;
        }
        if (x <= 0) {
        	if (dx < 0) {
				x = 0;
				left = false;
			}
		}
        if (y <= 0) {
        	if (dy < 0) {
				y = 0;
				up = false;
			}
		}
        if (x >= maxX) {
        	if (dx > 0) {
				x = maxX;
				right = false;
			}
		}
        if (y >= maxY) {
        	if (dy > 0) {
				y = maxY;
				down = false;
			}
		}
        move(dx, dy);
	}
	
	public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
		super.render(cameraX, cameraY, screenWidth, pixels);
	}
	
}