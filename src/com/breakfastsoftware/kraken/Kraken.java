package com.breakfastsoftware.kraken;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Kraken extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static int width = 800, height = 600;
	private static String title = "Kraken";
	private static boolean running = false;
	private Thread thread;

    private Kraken() {}
	
	public void init() {
		requestFocus();
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
		
	}
	
	public void render() {
        BufferStrategy bs = getBufferStrategy();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		//state.render(g);
		
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

    public static int getGameWidth() {
        return width;
    }

    public static int getGameHeight() {
        return height;
    }
}