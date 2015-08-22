package com.breakfastsoftware.kraken.util;

public class Camera {
	
	private int x, y, maxX, maxY;
	private int dx, dy;
  
  	public Camera(int x, int y, int maxX, int maxY) {
  		this.x = x;
  		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;
  	}
  
  	public void update() {
  		move(dx, dy);
  	}
  	
  	public void move(int dx, int dy) {
  		x += dx;
  		y += dy;

		if (x < 0)
			x = 0;
		else if (x >= maxX)
			x = maxX-1;

		if (y < 0)
			y = 0;
		else if (y >= maxY)
			y = maxY-1;
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
}