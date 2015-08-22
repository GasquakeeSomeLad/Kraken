package com.breakfastsoftware.kraken.util;

import java.awt.Graphics2D;

public class Camera {
	private int x, y;
	private int dx, dy;
  
  	public Camera(int x, int y) {
  		this.x = x;
  		this.y = y;
  	}
  
  	public void update() {
  		move(dx, dy);
  	}
  	
  	public void move(int dx, int dy) {
  		x += dx;
  		y += dy;
  	}
  
  	public int getX() {
  		return x;
  	}
  
  	public void setX(int x) {
  		this.x = x;
  	}
  
  	public int getY() {
  		return y;
  	}
  
  	public void setY(int y) {
  		this.y = y;
  	}
}