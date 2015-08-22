package com.breakfastsoftware.kraken.res;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private Images image;
	
	public SpriteSheet(Images image) {
		this.image = image;
	}
	
	public int[] getPixels(int x, int y, int w, int h) {
		int[] pixels = new int[w*h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < j; j++) {
				pixels[i+j*w] = image.getPixels()[(i+x)+(j+y)*w];
			}
		}
		return pixels;
	}
}