package com.breakfastsoftware.kraken;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import com.breakfastsoftware.kraken.input.Keyboard;
import com.breakfastsoftware.kraken.input.Mouse;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.states.LogoState;
import com.breakfastsoftware.kraken.states.core.StateManager;

public class Kraken extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static int width = 800, height = 600;
	private static String title = "Kraken";
	private static boolean running = false, sound = true, fancy = false;
	private static StateManager stateManager;
	private Thread thread;
	private static Mouse mouse;
	private static Keyboard keyboard;

	public void init() {
		requestFocus();
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		stateManager = new StateManager(new LogoState());
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run() {
		init();
		int updates = 0, frames = 0;
		long nextTime = System.nanoTime(), counter = nextTime,
				delta = 1000000000/60, delta5 = delta*5;

		while (running) {
			long currentTime = System.nanoTime();
			if (currentTime - nextTime >= delta5)
				nextTime = currentTime;
			else if (nextTime < currentTime) {
				nextTime += delta;
				update();
				updates++;
				if (nextTime > System.nanoTime()) {
					render();
					frames++;
				}
			}
			if (System.nanoTime() - counter >= 1000000000) {
				counter+= 1000000000;
				System.out.println(updates + ", " + frames);
				frames = updates = 0;
			}
		}
	}
	
	public void update() {
		if (keyboard.keyDown(KeyEvent.VK_M)) {
			keyboard.releaseKey(KeyEvent.VK_M);
			sound = !sound;
		}
		if (Kraken.getKeyboard().keyDown(KeyEvent.VK_F)) {
			Kraken.getKeyboard().releaseKey(KeyEvent.VK_F);
			fancy = !fancy;
		}
		stateManager.update();
	}
	
	public void render() {
        BufferStrategy bs = getBufferStrategy();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		stateManager.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Sound.START.play();
		JFrame frame = new JFrame(title);
		Kraken kraken = new Kraken();
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(kraken);
        kraken.createBufferStrategy(3);
		kraken.start();
	}
	
	public static Keyboard getKeyboard() {
		return keyboard;
	}
	
	public static Mouse getMouse() {
		return mouse;
	}

    public static int getGameWidth() {
        return width;
    }

    public static int getGameHeight() {
        return height;
    }

	public static StateManager getStateManager() { return stateManager;}

	public static boolean playSound() {
		return sound;
	}

	public static boolean fancy() {
		return fancy;
	}
}