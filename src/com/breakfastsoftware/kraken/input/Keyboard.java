package com.breakfastsoftware.kraken.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private int keyUp;
	private int keyDown;
	
	public boolean keyUp(int key) {
		if (keyUp != key) {
			return false;
		}
		keyUp = 0;
		return true;
	}
	
	public boolean keyDown(int key) {
		if (keyDown != key) {
			return false;
		}
		keyDown = 0;
		return true;
	}
	
	public void keyPressed(KeyEvent e) {
		keyDown = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {
		keyUp = e.getKeyCode();
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public int getKeyUp() {
		return keyUp;
	}

	public void setKeyUp(int keyUp) {
		this.keyUp = keyUp;
	}

	public int getKeyDown() {
		return keyDown;
	}

	public void setKeyDown(int keyDown) {
		this.keyDown = keyDown;
	}

}