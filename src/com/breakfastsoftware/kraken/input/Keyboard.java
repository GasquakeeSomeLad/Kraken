package com.breakfastsoftware.kraken.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	
	public void keyPressed(KeyEvent e) {
		if (!keyDown(e.getKeyCode()))
			keys.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < keys.size(); i++)
			if (keys.get(i).intValue() == e.getKeyCode()) {
				keys.remove(i);
				return;
			}

	}

	public void keyTyped(KeyEvent e) {
	}

	public boolean keyDown(int key) {
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i).intValue() == key) {
				return true;
			}
		}
		return false;
	}

	public boolean keyUp(int key) {
		return !keyDown(key);
	}

}