package com.breakfastsoftware.kraken.util;

public class Calculations {

	public static boolean collision(int x, int y, int w, int h, int x2, int y2, int w2, int h2) {
		if (x + w <= x2) {
			return false;
		}
		if (y + h <= y2) {
			return false;
		}
		if (x2 + w2 <= x) {
			return false;
		}
		if (y2 + h2 <= y) {
			return false;
		}
		return true;
	}
  
  	public static double getDistanceX(int x1, int x2) {
	  	double distance = Math.sqrt((x1 - x2) * (x1 - x2));
    	return distance;
  	}
  
  	public static double getDistanceY(int y1, int y2) {
  		double distance = Math.sqrt((y1 - y2) * (y1 - y2));
    	return distance;
  	}
	
}