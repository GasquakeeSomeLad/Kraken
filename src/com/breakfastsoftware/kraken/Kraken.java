package com.breakfastsoftware.kraken;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.breakfastsoftware.kraken.input.Keyboard;
import com.breakfastsoftware.kraken.input.Mouse;
import com.breakfastsoftware.kraken.states.LogoState;
import com.breakfastsoftware.kraken.states.core.StateManager;

public class Kraken extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static int width = 800, height = 600;
	private static String title = "Kraken";
	private static boolean running = false;
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
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		long Timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				updates++;
				update();
				frames++;
				render();
				delta--;
			}
			if (System.currentTimeMillis() - Timer >= 1000) {
				Timer += 1000;
				System.out.println(updates + ", " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void update() {
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
}